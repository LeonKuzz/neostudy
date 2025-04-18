package org.example.services;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import static org.assertj.core.api.Assertions.assertThat;

public class OtpuskServiceTest {
    OtpuskService service = new OtpuskService();
    @Test
    void addHolidaysTest() {
        assertThat(service.getList().contains(new GregorianCalendar(2000, 1, 1))).isTrue();
        assertThat(service.getList().isEmpty()).isFalse();
        assertThat(service.getList().contains(new GregorianCalendar(2000, 2, 1))).isFalse();
    }

    @Test
    void whenTheNumberOfDaysIsSpecified () {
        double income = 300000.0;
        int days = 7;
        double actual = service.getMoney(income, days);
        double expected = Math.ceil(income / 12 / 29.3 * days * 100) / 100;
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void WhenAVacationIsNotAHoliday () {
        double income = 300000.0;
        LocalDate d1 = LocalDate.of(2005, 2, 1);
        LocalDate d2 = LocalDate.of(2005, 2, 7);
        double actual = service.getMoney(income, d1, d2);
        double expected = Math.ceil(income / 12 / 29.3 * 7 * 100) / 100;
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void WhenAVacationIsAHoliday () {
        double income = 300000.0;
        LocalDate d1 = LocalDate.of(2005, 2, 21);
        LocalDate d2 = LocalDate.of(2005, 2, 27);
        double actual = service.getMoney(income, d1, d2);
        double expected = Math.ceil(income / 12 / 29.3 * 6 * 100) / 100;
        assertThat(actual).isEqualTo(expected);
    }
}
