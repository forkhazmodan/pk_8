package com.kp.chukhnovm.hw8.Directors;

import com.kp.chukhnovm.hw8.Builders.StudentBuilder;
import com.kp.chukhnovm.hw8.Student;

import java.util.Scanner;

public class ScannerDirector {

    private Scanner sc = new Scanner(System.in);
    private StudentBuilder builder = new StudentBuilder(sc);

    public Scanner getSc() {
        return sc;
    }

    public Student createStudent() {

        Student student = new Student();
        student.generateDocumentId();

        builder.setStudent(student);
        builder.setName();
        builder.setBirthDay();
        builder.setGender();

        return builder.getResult();
    }
}
