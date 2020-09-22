package com.kp.chukhnovm.hw8.Services;

import com.kp.chukhnovm.hw8.Enums.Gender;
import com.kp.chukhnovm.hw8.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw8.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw8.Group;
import com.kp.chukhnovm.hw8.Interfaces.CsvCompatible;
import com.kp.chukhnovm.hw8.Student;

import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GroupService {

    public static Group exportGroup(String filePath) {

        String line;
        String lineArray[];
        String separator = ",";
        Group group = new Group();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {

                // Prepare line
                lineArray = line.replace("\"", "").split(separator);

                // Create Student from parsed cells
                Student student = new Student();

                student.setName(lineArray[0]);
                student.setBirthDay(lineArray[1]);
                student.setGender(lineArray[2]);

                // As optional parameter documentId should be checked
                if (lineArray.length == 4) {
                    student.setDocumentId(lineArray[3]);
                } else {
                    student.generateDocumentId();
                }

                // Add students to group
                group.setStudent(student);
            }

        } catch (IOException | ParseException | GroupDuplicateStudentException | GroupFulFilledException e) {
            e.printStackTrace();
        }

        return group;
    }

    public static void importGroup(CsvCompatible canCsv, String targetFilePath) {

        File f = new File(targetFilePath);

        try (PrintWriter a = new PrintWriter(new FileOutputStream(
                new File(targetFilePath),
                f.exists()
        ))) {

            a.println(canCsv.toCSVString());

        } catch (FileNotFoundException e) {
            System.err.println("ERROR FILE WRITE");
        }
    }

    public static void generateCSV(String targetFilePath, int groups) {

        try {
            Student[] students = {
                    new Student("Valentyn Test6", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new Student("Vladislav Test7", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new Student("Andrew Test1", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new Student("John Test2", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new Student("Peter Test3", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new Student("Lesya Test4", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new Student("Petro Test8", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.FEMALE),
                    new Student("Tesla Test9", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
                    new Student("Rocket Test10", new GregorianCalendar(1990, Calendar.JUNE, 5).getTime(), Gender.MALE),
            };

            Group group = new Group(students);

            for (int i = 1; i < groups; i++) {
                GroupService.importGroup(group, targetFilePath);
                System.out.println(String.format("Iteration №%d Students added: %d", i, i * students.length));
            }

        } catch (GroupFulFilledException e) {
            e.printStackTrace();
        }

    }
}
