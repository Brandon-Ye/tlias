package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.utils.AliyunOSSProperties;
import com.itheima.entity.vo.Result;
import com.itheima.utils.AliyunOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class UploadController {

//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;


    /**
     * 上传头像
     * @param file
     * @return
     * @throws Exception
     */
    @Log
    @PostMapping("/upload")
    public Result uploadImg(MultipartFile file) throws Exception {
        // 获取拼接格式
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = AliyunOSSUtils.upload(aliyunOSSProperties.getEndpoint(),aliyunOSSProperties.getBucketName(),file.getBytes(),extName);
        return Result.success(url);
    }
}
