package com.kp.chukhnovm.hw1_1;

import com.kp.chukhnovm.hw1_1.Enums.Gender;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Student extends Human {

    private String documentId;

    public Student() {
    }

    public Student(String name, Date birthDay, Gender gender) {
        super(name, birthDay, gender);
        this.documentId = UUID.randomUUID().toString();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(documentId, student.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId);
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
