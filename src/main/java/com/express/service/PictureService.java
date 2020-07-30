package com.express.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PictureService {
    Map<String,Object> uploadPicture(MultipartFile picFile, String path);
}
