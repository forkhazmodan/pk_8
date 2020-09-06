package com.kp.chukhnovm.hw1_1;

import com.kp.chukhnovm.hw1_1.Enums.Gender;

import java.util.Date;
import java.util.UUID;

public class Student extends Human{

    private String documentId;

    public Student(){
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Student(String name, Date birthDay, Gender gender) {
        super(name, birthDay, gender);
        this.documentId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Student{" +
                "documentId='" + documentId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                '}';
    }
}
