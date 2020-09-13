package com.kp.chukhnovm.hw3_1;

import com.kp.chukhnovm.hw3_1.Enums.Gender;

import java.util.*;

public class Student extends Human {

    private String documentId;

    public Student() {
        this.generateDocumentId();
    }

    public Student(String name, Date birthDay, Gender gender) {
        super(name, birthDay, gender);
        this.generateDocumentId();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    private void generateDocumentId() {
        this.setDocumentId(UUID.randomUUID().toString());
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
