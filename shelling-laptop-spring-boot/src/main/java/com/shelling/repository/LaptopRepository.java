package com.shelling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shelling.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Long>{}
