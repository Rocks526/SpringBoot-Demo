package com.rocks.springboot.springbootstorage.service;

/**
 * @Author Rocks526
 * @ClassName StorageService
 * @Description 对象存储服务接口
 * @Date 2020/5/27 18:11
 **/
public interface StorageService {


    /**
     * 上传文件
     *
     * @param data    文件的二进制内容
     * @param extName 扩展名
     * @return 上传成功后返回生成的文件URL；失败，返回null
     */
    String upload(byte[] data, String extName);

    /**
     * 删除文件
     *
     * @param filePath 被删除的文件路径
     * @return 删除成功后返回0，失败后返回错误代码
     */
    int delete(String filePath);

    /**
     * 下载文件
     *
     * @param filePath 要下载的文件路径
     * @return 删除成功后返回0，失败后返回错误代码
     */
    byte[] downLoad(String filePath);


}
