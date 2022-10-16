package fr.alexandre.user_registry.components.date;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateBean implements ActualDate {
    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
