package com.example.aboutme.voucher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("SELECT MIN(v.price) " +
            "FROM Voucher v " +
            "WHERE v.expert.id = :expertId")
    Double findLowestPriceByExpertId(@Param("expertId") Integer expertId);

    List<Voucher> findByExpertId(Integer expertId);
}
