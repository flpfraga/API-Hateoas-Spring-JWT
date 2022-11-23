package com.fraga.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fraga.APIRest.data.vo.UploadFileResponseVO;
import com.fraga.APIRest.service.FileStorageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    @Autowired
    private FileStorageService service;
    
    @PostMapping("/uploadFile")
    public UploadFileResponseVO uploadFile(@RequestParam ("file") MultipartFile file) {
        var filename = service.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/downloadFile/")
                .path(filename)
                .toUriString();
        return new UploadFileResponseVO(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }
    
    
}
