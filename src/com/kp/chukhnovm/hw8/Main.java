package com.kp.chukhnovm.hw8;

import com.kp.chukhnovm.hw8.Containers.StackBlackList;
import com.kp.chukhnovm.hw8.Containers.StackContainer;
import com.kp.chukhnovm.hw8.Enums.Gender;
import com.kp.chukhnovm.hw8.Exceptions.ClassInBlackListException;
import com.kp.chukhnovm.hw8.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw8.Services.GroupService;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        task1();
        task2And3();
    }

    public static void task1() {
        Student[] students = {
                new Student("Valentyn Test6", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "1"),
                new Student("Vladislav Test7", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "2"),
                new Student("Andrew Test1", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "3"),
                new Student("John Test2", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "4"),
                new Student("Peter Test3", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "5"),
                new Student("Lesya Test4", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "6"),
                new Student("Petro Test8", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE, "7"),
                new Student("Tesla Test9", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "8"),
                new Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE, "9"),
        };


        try {
            Group group = new Group(students);
            String filename = GroupService.serializeToFile(group);
            Group group2 = GroupService.unserializeFromFile(filename);

            System.out.println(group.equals(group2));

        } catch (GroupFulFilledException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void task2And3() {
        StackContainer sc = new StackContainer();
        StackBlackList bk = new StackBlackList(
            Long.class
        );
        sc.setBlackList(bk);

        try {

            sc.add("dsfgsdf");
            sc.add(2);
            sc.add(3.00);

            System.out.println(Arrays.toString(sc.getList())); // [3.0, 2, dsfgsdf]
            System.out.println(sc.retrieve()); // 3.0
            System.out.println(Arrays.toString(sc.getList())); // [2, dsfgsdf]
            System.out.println(sc.get()); // 2
            System.out.println(Arrays.toString(sc.getList())); // [2, dsfgsdf]
            System.out.println(sc.retrieve()); // 2
            System.out.println(sc.retrieve()); // dsfgsdf
            System.out.println(Arrays.toString(sc.getList())); // []
            System.out.println(sc.retrieve()); // null
            System.out.println(Arrays.toString(sc.getList())); // []


            sc.add(3L); // class java.lang.Long in black list.

        } catch (ClassInBlackListException classInBlackList) {
            classInBlackList.printStackTrace();
        }
    }
}
