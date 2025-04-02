package com.ivoyant.filehandling.image.controller;

import com.ivoyant.filehandling.image.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class StorageController {

    @Autowired
    StorageService service;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage= service.uploadImage(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }



    //mapping get request
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
         byte[] imageData=service.downloadImage(fileName);
         return ResponseEntity.status(HttpStatus.OK)
                 .contentType(MediaType.valueOf("image/png"))
                 .body(imageData);
    }
}
