package com.swd392.backpackstore.controller;

import com.swd392.backpackstore.model.Backpack;
import com.swd392.backpackstore.model.dto.BackpackDTO;
import com.swd392.backpackstore.service.IBackpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/backpacks")
public class ProductController {
    @Autowired
    private IBackpackService backpackService;

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

    @PostMapping
    public ResponseEntity<Backpack> addBackpack(@RequestBody BackpackDTO backpackDTO) {
        Backpack backpack = new Backpack();
        backpack.setType(backpackDTO.getType());
        backpack.setQuantity(backpackDTO.getQuantity());
        backpack.setPrice(backpackDTO.getPrice());
        backpack.setSelled(backpackDTO.getSelled());
        backpack.setDescription(backpackDTO.getDescription());
        Backpack createdBackpack = backpackService.addBackpack(backpack);
        return ResponseEntity.ok(createdBackpack);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Backpack> updateBackpack(@PathVariable("id") int id, @RequestBody Backpack backpack) {
        Optional<Backpack> existingBackpack = backpackService.findBackpackById(id);

        if (existingBackpack.isPresent()) {
//            Backpack backpack = existingBackpack.get();
//            backpack.setType(backpackDTO.getType());
//            backpack.setQuantity(backpackDTO.getQuantity());
//            backpack.setPrice(backpackDTO.getPrice());
//            backpack.setSelled(backpackDTO.getSelled());

            Backpack updatedBackpack = backpackService.updateBackpack(id, backpack).get();
            return ResponseEntity.ok(updatedBackpack);
        }

        return ResponseEntity.notFound().build();
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
}
