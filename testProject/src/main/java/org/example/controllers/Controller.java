package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.services.OtpuskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/calculacte")
@RequiredArgsConstructor
public class Controller {
    private final OtpuskService service;

    @GetMapping("{str}")
    public ResponseEntity<?> calculate(@PathVariable String str) {
        String[] arr = str.split(" ");
        try {

            if (arr.length == 2) {
                double wages = Double.parseDouble(arr[0]);
                int days = Integer.parseInt(arr[1]);
                return new ResponseEntity<>(service.getMoney(wages, days), HttpStatus.OK);
            } else {
                double wages = Double.parseDouble(arr[0]);
                LocalDate firstDay = LocalDate.parse(arr[1]);
                LocalDate lastDay = LocalDate.parse(arr[2]);
                return new ResponseEntity<>(service.getMoney(wages, firstDay, lastDay), HttpStatus.OK);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Неверный формат ввода. допустим один из вариантов: \n" +
                    "1200000 7 (сумма количество_дней)\n" +
                    "1200000 2025-03-02 2025-03-08 (сумма дата_начала дата_окончания)", HttpStatus.BAD_REQUEST);
        }
    }
}
