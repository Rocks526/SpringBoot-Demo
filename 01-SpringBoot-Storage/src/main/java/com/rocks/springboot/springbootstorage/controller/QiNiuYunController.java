package com.rocks.springboot.springbootstorage.controller;

import com.rocks.springboot.springbootstorage.service.StorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Author Rocks526
 * @ClassName QiNiuYunController
 * @Description QiNiuYun测试Controller
 * @Date 2020/5/27 18:11
 **/
@RestController
@RequestMapping("/qiniuyun")
public class QiNiuYunController {


    @Resource(name = "qiNiuYunServiceImpl")
    private StorageService storageService;


    @RequestMapping(value = "/upload",method = RequestMethod.PUT)
    public String uploadFile(@RequestBody MultipartFile uploadFile) throws IOException {
        String filePath = storageService.upload(uploadFile.getBytes(),null);
        return filePath;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String uploadFile(@RequestParam(value = "filePath",required = true) String filePath)  {
        int delete = storageService.delete(filePath);
        if (delete == 0){
            return "success!";
        }
        return "failed!";
    }

}
