package com.shelling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shelling.entity.PayEntity;

public interface PayRepository extends JpaRepository<PayEntity,Long>{}
