package com.rocks.springboot.springbootstorage.controller;

import com.rocks.springboot.springbootstorage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Author Rocks526
 * @ClassName FastDfsController
 * @Description FastDfs测试Controller
 * @Date 2020/5/27 18:11
 **/
@RestController
@RequestMapping("/fastdfs")
public class FastDfsController {


    @Resource(name = "fastDfsServiceImpl")
    private StorageService storageService;


    @RequestMapping(value = "/upload",method = RequestMethod.PUT)
    public String uploadFile(@RequestBody MultipartFile uploadFile) throws IOException {
        // 获取文件后缀
        String fileName = uploadFile.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String filePath = storageService.upload(uploadFile.getBytes(),extName);
        return filePath;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public String uploadFile(@RequestParam(value = "filePath",required = true) String filePath)  {
        int delete = storageService.delete(filePath);
        if ( delete == 0){
            return "success!";
        }
        return "failed!";
    }

    @RequestMapping(value = "/downLoad",method = RequestMethod.GET)
    public String downLoadFile(@RequestParam(value = "filePath",required = true) String filePath) throws IOException {

        String extName = filePath.substring(filePath.lastIndexOf(".") + 1);
        byte[] bytes = storageService.downLoad(filePath);
        String path = "T:\\downLoad." + extName;
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(bytes, 0, bytes.length);
        imageOutput.close();
        System.out.println("Make Picture success,Please find image in " + path);
        return "success!";
    }


}
