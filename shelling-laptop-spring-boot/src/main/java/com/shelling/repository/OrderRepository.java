package com.shelling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shelling.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{}
