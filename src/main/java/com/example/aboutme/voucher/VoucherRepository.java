package com.example.aboutme.voucher;

import com.example.aboutme.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {


    List<Voucher> findByExpertId(Integer expertId);

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
