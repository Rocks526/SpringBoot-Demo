package com.rocks.springboot.springbootstorage.service.Impl;

import com.rocks.springboot.springbootstorage.exception.FastDfsException;
import com.rocks.springboot.springbootstorage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author Rocks526
 * @ClassName FastDfsServiceImpl
 * @Description FastDfs存储服务
 * @Date 2020/5/27 18:11
 **/
@Slf4j
@Service("fastDfsServiceImpl")
public class FastDfsServiceImpl implements StorageService, InitializingBean {


    private TrackerServer trackerServer = null;

    private TrackerClient trackerClient = null;

    private StorageServer storageServer = null;

    private StorageClient storageClient = null;

    @Value("${storage.fastdfs.tracker_server}")
    private String trackerServerIP;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String upload(byte[] data, String extName) {
        try{
            Date date = new Date();
            String createTime = simpleDateFormat.format(date);
            //元数据
            NameValuePair[] meta_list = new NameValuePair[2];
            meta_list[0] = new NameValuePair("author", "Rocks526");
            meta_list[1] = new NameValuePair("createTime", createTime);
            String[] res = storageClient.upload_file(data, extName,null);
            StringBuilder filePath = new StringBuilder();
            filePath.append(res[0]).append("/").append(res[1]);
            log.info("FastDfs文件上传成功,filePath:{}",filePath);
            return filePath.toString();
        }catch (Exception e){
            log.error("FastDfs文件上传失败,Reason:{}",e.toString());
            throw new FastDfsException("FastDfs文件上传失败！",e);
        }
    }


    @Override
    public int delete(String filePath) {
        try{
            int index = filePath.indexOf('/');
            String groupName = filePath.substring(0, index);
            String fileName = filePath.substring(index+1);
            return storageClient.delete_file(groupName,fileName);
        }catch (Exception e){
            log.error("FastDfs文件删除失败,Reason:{}",e.toString());
            throw new FastDfsException("FastDfs文件删除失败！",e);
        }
    }

    @Override
    public byte[] downLoad(String filePath) {
        try{
            int index = filePath.indexOf('/');
            String groupName = filePath.substring(0, index);
            String fileName = filePath.substring(index+1);
            byte[] bytes = storageClient.download_file(groupName, fileName);
            return bytes;
        }catch (Exception e){
            log.error("FastDfs文件下载失败,Reason:{}",e.toString());
            throw new FastDfsException("FastDfs文件下载失败！",e);
        }
    }


    //初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        File confFile = File.createTempFile("FastDfs", ".conf");
        PrintWriter confWriter = new PrintWriter(new FileWriter(confFile));
        confWriter.println("tracker_server=" + trackerServerIP);
        confWriter.close();
        ClientGlobal.init(confFile.getAbsolutePath());
        confFile.delete();
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getTrackerServer();
        storageClient = new StorageClient1(trackerServer, storageServer);
        log.info("Init FastDFS Success! Tracker_server : {}", trackerServer);
    }

}
