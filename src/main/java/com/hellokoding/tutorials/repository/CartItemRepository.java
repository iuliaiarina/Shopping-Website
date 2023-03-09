package com.hellokoding.tutorials.repository;

import com.hellokoding.tutorials.model.CartItem;
import com.hellokoding.tutorials.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
