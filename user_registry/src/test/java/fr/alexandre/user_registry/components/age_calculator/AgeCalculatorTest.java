package fr.alexandre.user_registry.components.age_calculator;

import fr.alexandre.user_registry.components.date.ActualDate;
import fr.alexandre.user_registry.components.date.DateBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AgeCalculatorTest {

    @Autowired
    private AgeCalculator ageCalculator;

    @MockBean
    private ActualDate actualDate;

    @BeforeEach
    public void setUp() {
        Mockito.when(actualDate.now()).thenReturn(LocalDate.of(2020, 8, 15));
    }

    @Test
    public void testCalculateAgeNull() {
        int age = this.ageCalculator.calculateAge(null);
        assertEquals(0, age);
    }

    @Test
    public void testCalculateAgeBeforeBirthdayMonth() {
        int age = this.ageCalculator.calculateAge(LocalDate.of(1990, 1, 1));
        assertEquals(30, age);
    }

    @Test
    public void testCalculateAgeAfterBirthdayMonth() {
        int age = this.ageCalculator.calculateAge(LocalDate.of(1990, 10, 1));
        assertEquals(29, age);
    }

    @Test
    public void testCalculateAgeBeforeBirthdayDay() {
        int age = this.ageCalculator.calculateAge(LocalDate.of(1990, 8, 1));
        assertEquals(30, age);
    }

    @Test
    public void testCalculateAgeAfterBirthdayDay() {
        int age = this.ageCalculator.calculateAge(LocalDate.of(1990, 8, 20));
        assertEquals(29, age);
    }

    @Test
    public void testCalculateAgeSameBirthdayDay() {
        int age = this.ageCalculator.calculateAge(LocalDate.of(1990, 8, 15));
        assertEquals(30, age);
    }

    @Test
    public void testCalculateAgeFuture() {
        int age = this.ageCalculator.calculateAge(LocalDate.of(2070, 1, 1));
        assertEquals(0, age);
    }


}
