package main.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.DateTimeException;
import java.time.LocalDate;

public class DateBirth {

    public static final String DATE_CHANGED = "date";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private int day;
    private int month;
    private int year;

    public DateBirth() {
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }


    public DateBirth(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public void addChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(DATE_CHANGED, listener);
    }


    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        support.firePropertyChange(DATE_CHANGED, null, this);
    }

    public int getDay() {

        return day;
    }

    public int getMonth() {

        return month;
    }

    public int getYear() {

        return year;
    }

    public int getDayOfWeek() {
        LocalDate date = LocalDate.of(year, month, day);
        return date.getDayOfWeek().getValue() - 1;
    }

    public boolean isValid() {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return !date.isAfter(LocalDate.now());
        } catch (DateTimeException e) {
            return false;
        }
    }
}