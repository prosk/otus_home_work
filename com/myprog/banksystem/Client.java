package com.myprog.banksystem;

import java.time.LocalDate;
import java.time.Period;

public class Client {
    private final static int MIN_CLIENT_AGE = 18;
    private final static int MAX_CLIENT_AGE = 90;

    // уникальный идентификатор клиента
    private final int id;
    private String fullName;
    private LocalDate birthDate;

    public Client(int id, String fullName, LocalDate birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;

        int age = getAge();
        if (age < MIN_CLIENT_AGE || age > MAX_CLIENT_AGE) {
            throw new IllegalArgumentException("Возраст клиента должен быть в диапазоне от " +
                    MIN_CLIENT_AGE + " до " + MAX_CLIENT_AGE);
        }
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;

        Client otherClient = (Client) other;
        return (this.id == otherClient.id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
