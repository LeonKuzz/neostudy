package org.example.services;

import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class OtpuskService {

    private ArrayList<Calendar> list = new ArrayList<>();
    public OtpuskService() {
        addHolidays();
    }
    public double getMoney(double wages, int days) {
        double perDay = wages / 12 / 29.3;
        return Math.ceil(perDay * days * 100) / 100;
    }
    public double getMoney(double wages, LocalDate firstDay, LocalDate lastDay) {
        int days = 0;
        for (LocalDate date = firstDay; date.isBefore(lastDay) || date.isEqual(lastDay); date = date.plusDays(1)) {
            int montDate = date.getMonth().getValue();
            int dayOfMontDate = date.getDayOfMonth();
            Calendar calendar = new GregorianCalendar(2000, montDate, dayOfMontDate);
            if (!list.contains(calendar)) {
                days++;
            }
        }
        double perDay = wages / 12 / 29.3;
        return Math.ceil(perDay * days * 100) / 100;
    }
    private void addHolidays () {
        this.list.add(new GregorianCalendar(2000, 1, 1));
        this.list.add(new GregorianCalendar(2000, 1, 2));
        this.list.add(new GregorianCalendar(2000, 1, 3));
        this.list.add(new GregorianCalendar(2000, 1, 4));
        this.list.add(new GregorianCalendar(2000, 1, 5));
        this.list.add(new GregorianCalendar(2000, 1, 6));
        this.list.add(new GregorianCalendar(2000, 1, 7));
        this.list.add(new GregorianCalendar(2000, 1, 8));
        this.list.add(new GregorianCalendar(2000, 2, 23));
        this.list.add(new GregorianCalendar(2000, 3, 8));
        this.list.add(new GregorianCalendar(2000, 5, 1));
        this.list.add(new GregorianCalendar(2000, 5, 9));
        this.list.add(new GregorianCalendar(2000, 6, 12));
        this.list.add(new GregorianCalendar(2000, 11, 4));
        /*1, 2, 3, 4, 5, 6, 8 января – новогодние каникулы;
        7 января – Рождество Христово;
        23 февраля – День защитника Отечества;
        8 марта – Международный женский день;
        1 мая – Праздник Весны и Труда;
        9 мая – День Победы;
        12 июня – День России;
        4 ноября – День народного единства.
    */
    }
    public ArrayList<Calendar> getList() {
        return list;
    }
}
