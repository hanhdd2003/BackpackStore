package com.swd392.backpackstore.service;

import com.swd392.backpackstore.model.Backpack;
import com.swd392.backpackstore.repository.BackpackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BackpackServiceImp implements IBackpackService {
    @Autowired
    private BackpackRepository backpackRepository;

    @Override
    public List<Backpack> findAllBackpacks() {
        return backpackRepository.findAll();
    }

    @Override
    public Optional<Backpack> findBackpackById(int id) {
        return backpackRepository.findById(id);
    }

    @Override
    public Backpack addBackpack(Backpack backpack) {
        return backpackRepository.save(backpack);
    }

    @Override
    public Optional<Backpack> updateBackpack(int id, Backpack backpack) {
        // Tìm backpack theo id và cập nhật thông tin nếu tồn tại
        Optional<Backpack> existingBackpack = backpackRepository.findById(id);

        return existingBackpack.map(oldBackpack -> {
            oldBackpack.setType(backpack.getType());
            oldBackpack.setPrice(backpack.getPrice());
            oldBackpack.setQuantity(backpack.getQuantity());
            oldBackpack.setSelled(backpack.getSelled());
            return backpackRepository.save(oldBackpack); // Lưu lại thông tin đã được cập nhật
        });
    }

    @Override
    public void deleteBackpack(int id) {
        // Xóa backpack theo id
        backpackRepository.deleteById(id);
    }

}

