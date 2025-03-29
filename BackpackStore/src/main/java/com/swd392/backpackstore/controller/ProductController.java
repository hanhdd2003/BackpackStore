package com.swd392.backpackstore.controller;

import com.swd392.backpackstore.model.Backpack;
import com.swd392.backpackstore.service.IBackpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/backpacks")
public class ProductController {
    @Autowired
    private IBackpackService backpackService;

    private static final String UPLOAD_DIR = "uploads/image/";

    static {
        // Tạo thư mục lưu ảnh nếu chưa tồn tại
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @GetMapping
    public ResponseEntity<List<Backpack>> getAllBackpacks() {
        List<Backpack> backpacks = backpackService.findAllBackpacks();
        return ResponseEntity.ok(backpacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Backpack> getBackpackById(@PathVariable("id") int id) {
        Optional<Backpack> backpack = backpackService.findBackpackById(id);
        return backpack.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Balo thời trang phù hợp với các bạn trẻ muốn thể hiện cá tính riêng

    @PostMapping
    public ResponseEntity<?> addBackpack(@RequestParam String type,
                                        @RequestParam int quantity,
                                        @RequestParam double price,
                                        @RequestParam String description,
                                        @RequestParam int selled,
                                        @RequestParam(required = false) MultipartFile image) {

        // Lưu ảnh vào thư mục

        if (image != null && !image.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                Path filePath = uploadPath.resolve(image.getOriginalFilename());
                image.transferTo(filePath);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
            }
        }

        // Lưu thông tin sản phẩm vào cơ sở dữ liệu (giả sử bạn đã tạo được entity và repository)
        Backpack backpack = new Backpack();
        backpack.setType(type);
        backpack.setQuantity(quantity);
        backpack.setPrice(price);
        backpack.setDescription(description);
        backpack.setSelled(selled);
        backpack.setImagePath(image.getOriginalFilename() != null ? image.getOriginalFilename() : "");
        backpackService.addBackpack(backpack);

        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable int id,
                                         @RequestParam String type,
                                         @RequestParam int quantity,
                                         @RequestParam double price,
                                         @RequestParam int selled,
                                         @RequestParam String description,
                                         @RequestParam(required = false) MultipartFile image) {
        // Lấy thông tin từ form
        Backpack backpack = backpackService.findBackpackById(id).get();
        if (backpack == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        backpack.setType(type);
        backpack.setQuantity(quantity);
        backpack.setPrice(price);
        backpack.setSelled(selled);
        backpack.setDescription(description);

        // Nếu có file hình ảnh
        if (image != null && !image.isEmpty()) {
            // Lưu ảnh vào thư mục
            String imagePath = saveImage(image);
            backpack.setImagePath(image.getOriginalFilename() != null ? image.getOriginalFilename() : "");
        }

        backpackService.updateBackpack(id, backpack);

        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
    }

    private String saveImage(MultipartFile image) {
        try {
            String imageName = image.getOriginalFilename();
            Path path = Paths.get("uploads/image/" + imageName);
            Files.write(path, image.getBytes());
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBackpack(@PathVariable("id") int id) {
        Optional<Backpack> existingBackpack = backpackService.findBackpackById(id);

        if (existingBackpack.isPresent()) {
            backpackService.deleteBackpack(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // Lấy thông tin ảnh từ server
    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Paths.get(UPLOAD_DIR + imageName);
        Resource resource = new UrlResource(imagePath.toUri());
//        System.out.println(resource);
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Nếu ảnh là JPEG, bạn có thể thay đổi nếu cần.
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
