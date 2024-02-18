package org.example.dao;

import org.example.model.Yarn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YarnDAO extends JpaRepository<Yarn, Long>{
}
