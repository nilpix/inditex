package com.inditex.prices.infrastructure.jpaadapter.output.persistence;

import com.inditex.prices.infrastructure.jpaadapter.entity.PriceAdapter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceAdapter, Long> {
    @Query("SELECT data FROM PriceAdapter data " +
            " WHERE :filterDate BETWEEN data.startDate AND data.endDate" +
            " AND data.productId = :productId" +
            " AND data.brandId = :brandId")
    List<PriceAdapter> filterPrices(@Param("filterDate") LocalDateTime filterDate,
                                    @Param("productId") Long productId,
                                    @Param("brandId") Long brandId,
                                    Sort sort);
}
