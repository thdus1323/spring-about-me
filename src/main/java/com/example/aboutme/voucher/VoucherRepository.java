package com.example.aboutme.voucher;

import com.example.aboutme.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

    @Query("SELECT v FROM Voucher v WHERE v.expert.id = :expertId")
    List<Voucher> findByUserId(@Param("expertId") Integer expertId);

    @Query("SELECT MIN(v.price) " +
            "FROM Voucher v " +
            "WHERE v.expert.id = :expertId")
    Optional<Double> findLowestPriceByExpertId(@Param("expertId") Integer expertId);

    List<Voucher> findByExpertId(Integer expertId);

    // 특정 expertId의 가장 낮은 가격 조회
    @Query("SELECT MIN(v.price) FROM Voucher v WHERE v.expert.id = :expertId")
    Double findMinimumPriceByExpertId(Integer expertId);

    @Query("""
            SELECT new com.example.aboutme.user.UserResponse$ClientMainDTO$VoucherDTO(
                        v.id,
                        v.voucherType,
                        v.expert.id
                        )
                        FROM Voucher v
            """)
    List<UserResponse.ClientMainDTO.VoucherDTO> findAllVouchers();
}
