package fr.alexandre.user_registry.components.age_calculator;

import fr.alexandre.user_registry.components.date.ActualDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AgeCalculatorBean implements AgeCalculator {
    Logger logger = LoggerFactory.getLogger(AgeCalculatorBean.class);

    @Autowired
    private ActualDate actualDate;

    public int calculateAge(LocalDate birthDate) {
        logger.trace("AgeCalculatorBean.calculateAge birthDate=" + birthDate);
        if(birthDate == null) {
            logger.error("AgeCalculatorBean.calculateAge: birthDate is null");
            return 0;
        }
        LocalDate now = this.actualDate.now();
        if(birthDate.isAfter(now)) {
            logger.trace("AgeCalculatorBean.calculateAge: birthDate is after now");
            return 0;
        }
        int age = now.getYear() - birthDate.getYear();
        if (now.getMonthValue() < birthDate.getMonthValue()) {
            age--;
        } else if (now.getMonthValue() == birthDate.getMonthValue() && now.getDayOfMonth() < birthDate.getDayOfMonth()) {
            age--;
        }
        logger.trace("AgeCalculatorBean.calculateAge age=" + age);
        return age;
    }
}
