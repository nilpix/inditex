package com.inditex.prices.infrastructure.jpaadapter.output.persistence;

import com.inditex.prices.infrastructure.jpaadapter.entity.PriceAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceAdapter, Long> {
}
