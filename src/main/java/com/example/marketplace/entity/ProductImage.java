package com.example.marketplace.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductImage {

    private MultipartFile file;

}
