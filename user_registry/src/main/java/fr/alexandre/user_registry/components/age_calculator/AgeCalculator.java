package fr.alexandre.user_registry.components.age_calculator;

import java.time.LocalDate;

public interface AgeCalculator {
    /** Calculate the age of a user
     * @param birthDate the birth date of the user in LocalDate format
     * @return the age of the user
     */
    public int calculateAge(LocalDate birthDate);
}
