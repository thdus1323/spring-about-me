package com.example.aboutme.comm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommNativeRepository {
    private final EntityManager em;

    //게시글목록구현
    public Comm findById(Integer commId) {
        Query query = em.createNativeQuery("select * from comm_tb where comm_id = ?", Comm.class);
        query.setParameter(1, commId);
        return (Comm) query.getSingleResult();
    }
}
