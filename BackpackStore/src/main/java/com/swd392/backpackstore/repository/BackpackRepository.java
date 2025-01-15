package com.swd392.backpackstore.repository;

import com.swd392.backpackstore.model.Backpack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackpackRepository extends JpaRepository<Backpack, Integer> {
}
