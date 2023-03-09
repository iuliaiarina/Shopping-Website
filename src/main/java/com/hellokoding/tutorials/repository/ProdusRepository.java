package com.hellokoding.tutorials.repository;

import com.hellokoding.tutorials.model.Produs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdusRepository extends JpaRepository<Produs, Long> {

}
