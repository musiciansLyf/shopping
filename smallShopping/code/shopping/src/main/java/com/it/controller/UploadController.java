package com.it.controller;

import com.it.util.Result;
import com.it.util.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 图片上传
 */
@Controller
public class UploadController {
    @PostMapping("/upload.do")
    @ResponseBody
    public ResultResponse upload(MultipartFile file) {
        String fileName = "";
        try {
            fileName = file.getOriginalFilename();
            String destFileName = "D://User/" + File.separator + fileName;
            System.out.println(destFileName);
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.resuleError("上传失败," + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return Result.resuleError("上传失败," + e.getMessage());
        }
        String url = "/image/" + fileName;
        return Result.resuleSuccess(url);
    }
}
