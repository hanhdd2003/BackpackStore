package com.swd392.backpackstore.service;

import com.swd392.backpackstore.model.Backpack;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IBackpackService {
    List<Backpack> findAllBackpacks();

    Optional<Backpack> findBackpackById(int id);

    Backpack addBackpack(Backpack backpack);

    Optional<Backpack> updateBackpack(int id, Backpack backpack);

    void deleteBackpack(int id);
}
