package fr.alexandre.user_registry.components.date;

import java.time.LocalDate;

public interface ActualDate {
    /**
     * Get the actual date
     * @return the actual date
     */
    LocalDate now();
}
