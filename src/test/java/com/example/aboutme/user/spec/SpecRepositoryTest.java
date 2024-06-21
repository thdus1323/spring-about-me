package com.example.aboutme.user.spec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SpecRepositoryTest {
@Autowired
private SpecRepository specRepository;

    @Test
    void findByExpertId() {
       List<Spec> specs = specRepository.findByExpertId(21);
       specs.forEach(spec -> System.out.println("spec = " + spec));
    }
}