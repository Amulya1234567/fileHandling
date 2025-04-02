package com.ivoyant.filehandling.image.service;


import com.ivoyant.filehandling.image.entity.ImageData;
import com.ivoyant.filehandling.image.repository.StorageRepository;
import com.ivoyant.filehandling.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData image=storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        if(image!=null){
            return "file uploaded successfully: "+file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images=ImageUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
