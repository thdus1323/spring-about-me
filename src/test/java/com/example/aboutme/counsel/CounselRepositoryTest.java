package com.example.aboutme.counsel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class CounselRepositoryTest {
    @Autowired
    CounselRepository counselRepository;

    @Test
    public void findCounselsByDateAndTime_test() {
        // Given
        LocalDateTime testDate = LocalDateTime.of(2024, 6, 20, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = testDate.format(formatter);

        // When
        List<Counsel> counselList = counselRepository.findCounselsByDateAndTime(testDate);

        // Then
        assertThat(counselList).isNotEmpty();
        assertThat(counselList.get(0).getCounselDate()).isEqualTo(testDate);

        // (Optional) Print out for debugging
        for (Counsel c : counselList) {
            System.out.println(c);
        }
    }
}
