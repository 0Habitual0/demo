package com.habitual.demo.common.controller;

import com.habitual.demo.common.entity.CommonResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public CommonResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            // 获取项目根路径
            String rootPath = System.getProperty("user.dir");
            // 指定保存路径
            String directoryPath = rootPath + "/src/main/resources/static/uploads/";
            String filePath = directoryPath + uniqueFileName;

            // 创建目录
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                Files.createDirectories(Paths.get(directoryPath));
            }

            // 保存文件
            file.transferTo(new File(filePath));

            // 返回文件访问URL
            String fileUrl = "http://localhost:10100/uploads/" + uniqueFileName;

            return CommonResponse.success(fileUrl);
        } catch (IOException e) {
            return CommonResponse.fail("文件上传失败");
        }
    }

}
