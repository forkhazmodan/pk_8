package com.kp.chukhnovm.hw3_1;

import com.kp.chukhnovm.hw3_1.Directors.ScannerDirector;
import com.kp.chukhnovm.hw3_1.Enums.Gender;
import com.kp.chukhnovm.hw3_1.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw3_1.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw3_1.Services.GroupService;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        Main.hw5();
    }

    public static void hw5() {
        Group group = GroupService.exportGroup("import.csv");
        GroupService.importGroup(group, String.format("export-%d.csv", System.currentTimeMillis()));
    }

    public static void hw4() {
        ScannerDirector scDir = new ScannerDirector();

        Student[] students = {
                // Create student from scanner
                scDir.createStudent(),
                new Student("Valentyn Test6", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                new Student("Vladislav Test7", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                new Student("Andrew Test1", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                new Student("John Test2", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                new Student("Peter Test3", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                new Student("Lesya Test4", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                new Student("Petro Test8", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                new Student("Tesla Test9", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                new Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),

        };

        try{
            Group group = new Group(students);
            Student[] studentsArray = group.sortByLastName();
            Student[] militia = group.getMilitia();

            for (Student student: studentsArray) {
                System.out.println(student);
            }

            System.out.println("Militia:");

            for (Student student: militia) {
                System.out.println(student);
            }

            group.removeStudent(null);

        } catch (GroupFulFilledException e) {
            System.err.println(e.getMessage());
        }



        scDir.getSc().close();
    }

    public static void hw3() {

        Student s1 = new Student("Andrew Test1", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE);
        Student s2 = new Student("John Test2", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE);
        Student s3 = new Student("Peter Test3", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE);
        Student s4 = new Student("Lesya Test4", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE);
        Student s5 = new Student("Dmitry Test5", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE);
        Student s6 = new Student("Valentyn Test6", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE);
        Student s7 = new Student("Vladislav Test7", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE);
        Student s8 = new Student("Petro Test8", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE);
        Student s9 = new Student("Tesla Test9", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE);
        Student s10 = new Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE);
        Student s11 = new Student("Test11 Test11", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE);

        Group group = new Group();
        int index = 0;
        for (Student student : new Student[]{s1, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11}) {

            try {
                System.out.printf("\nTry add %s student", ++index);
                group.setStudent(student);
                System.out.printf(" => %s", "Success");
            } catch (GroupDuplicateStudentException | GroupFulFilledException e) {
                System.out.printf(" => Skip (%s)", e.getMessage());
            }
        }

        System.out.println("\n");
        System.out.println(group.toString());

        System.out.println("Searching by last name:");
        Student[] searchResult = group.searchStudent("Test1");

        for (Student student : searchResult) {
            System.out.println(student);
        }

        group.removeStudent(s1);
        System.out.println(group.toString());
    }
}
