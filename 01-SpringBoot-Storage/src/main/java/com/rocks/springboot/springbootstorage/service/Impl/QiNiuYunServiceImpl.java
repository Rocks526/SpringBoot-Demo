package com.rocks.springboot.springbootstorage.service.Impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.rocks.springboot.springbootstorage.exception.FastDfsException;
import com.rocks.springboot.springbootstorage.exception.QiNiuYunException;
import com.rocks.springboot.springbootstorage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @Author Rocks526
 * @ClassName QiNiuYunServiceImpl
 * @Description 七牛云存储服务
 * @Date 2020/5/27 18:25
 **/
@Slf4j
@Service("qiNiuYunServiceImpl")
public class QiNiuYunServiceImpl implements StorageService, InitializingBean {


    @Value("${storage.qiniuyun.ak}")
    private String AccessKey;

    @Value("${storage.qiniuyun.sk}")
    private String SecretKey;

    @Value("${storage.qiniuyun.bucket}")
    private String Bucket;

    @Value("${storage.qiniuyun.domain}")
    private String domain;

    private Auth auth;

    private String uploadToken;

    private UploadManager uploadManager;

    private BucketManager bucketManager;


    @Override
    public String upload(byte[] data, String extName) {
        //文件名 不指定的情况下默认以文件内容的hash为文件名
        String fileName = null;
        try {
            //上传文件
            Response response = uploadManager.put(data, fileName, uploadToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String filePath = domain + "/" + putRet.key;
            log.info("七牛云文件上传成功,filePath:{}",filePath);
            return filePath;
        } catch (Exception e) {
            throw new QiNiuYunException("七牛云文件上传失败！",e);
        }
    }

    @Override
    public int delete(String filePath) {
        try{
            bucketManager.delete(Bucket,filePath);
            log.info("七牛云文件删除成功,filePath:{}",filePath);
        }catch (QiniuException e){
            log.error("七牛云文件删除失败,Reason:{}",e.response.toString());
            throw new FastDfsException("七牛云文件删除失败！",e);
        }
        return 0;
    }

    @Override
    public byte[] downLoad(String filePath) {
        return new byte[0];
    }


    //初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        //创建配置对象 指定Region为华北地区
        Configuration configuration = new Configuration(Region.region1());
        //获取上传管理器
        uploadManager = new UploadManager(configuration);
        //获取凭证
        auth = Auth.create(AccessKey, SecretKey);
        //获取Token
        uploadToken = auth.uploadToken(Bucket);
        //获取空间管理器
        bucketManager = new BucketManager(auth,configuration);
        log.info("Init QiNiuYunStorage Success!");
    }

}
