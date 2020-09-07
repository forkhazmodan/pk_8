package com.kp.chukhnovm.hw3_1;

import com.kp.chukhnovm.hw3_1.Enums.Gender;

import java.util.Date;

public class Human implements Comparable<Human> {

    protected String name;
    protected Date birthDay;
    protected Gender gender;

    public Human() {
    }

    public Human(String name, Date birthDay, Gender gender) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return this.birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Get age in days
     * @return Int
     */
    public long getAgeInDays() {

        Date d1 = this.getBirthday();
        Date d2 = new Date();

        return d1 == null || d2 == null
                ? 0
                : (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + this.name + '\'' +
                ", dayBirth=" + this.birthDay +
                '}';
    }

    @Override
    public int compareTo(Human o) {
        if(o == null) return -1;
        return name.compareTo(o.name);
    }
}
