package com.example.aboutme.counsel;

import com.example.aboutme._core.error.exception.Exception404;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
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

    @Test
    public void findAllCounselByExpertId_test() {

        //Given
        Integer expertId = 21;

        //When
        List<Counsel> counselList = counselRepository.findAllCounselByExpertId(expertId);

        //Eye
        System.out.println(counselList.size());
    }

    @Test
    public void findByReservationId_test(){

        //Given
        Integer reservationId = 1;

        //When
        Counsel counsel = counselRepository.findByReservationId(reservationId);

        //Eye
        System.out.println(counsel);

        //Then
        assertNotNull(counsel);
        assertEquals(reservationId, counsel.getReservation().getId());
    }
}
