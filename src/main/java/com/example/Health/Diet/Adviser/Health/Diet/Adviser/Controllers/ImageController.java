//package com.example.Health.Diet.Adviser.Health.Diet.Adviser.Controllers;
//
//
//import jakarta.annotation.Resource;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.nio.file.Files;
//
//@RestController
//@RequestMapping("/api/images")
//public class ImageController {
////    @GetMapping("/{imageName:.+}")
////    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
////        Resource imageResource = new ClassPathResource(imageName);
////        byte[] imageBytes = Files.readAllBytes(imageResource.getFile().toPath());
////
////        return ResponseEntity.ok()
////                .contentType(MediaType.IMAGE_JPEG) // Adjust the MediaType according to your image type
////                .body(imageBytes);
////    }
//}
