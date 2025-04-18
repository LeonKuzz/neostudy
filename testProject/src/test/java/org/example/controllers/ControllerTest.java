package org.example.controllers;

import org.example.services.OtpuskService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ControllerTest {
    Controller controller = new Controller(new OtpuskService());

    @Test
    void whenAStringOfTwoWords () {
        String str = "300000 7";
        ResponseEntity<?> actual = controller.calculate(str);
        ResponseEntity<?> expected = new ResponseEntity<>(Math.ceil(300000 / 12 / 29.3 * 7 * 100) / 100, HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void whenAStringOfThreeWords () {
        String str = "300000 2005-02-01 2005-02-07";
        ResponseEntity<?> actual = controller.calculate(str);
        ResponseEntity<?> expected = new ResponseEntity<>(Math.ceil(300000 / 12 / 29.3 * 7 * 100) / 100, HttpStatus.OK);
        assertThat(actual).isEqualTo(expected);
    }
}
