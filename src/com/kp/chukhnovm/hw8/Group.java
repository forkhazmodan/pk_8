package com.kp.chukhnovm.hw8;

import com.kp.chukhnovm.hw8.Comparators.Students.NameComparator;
import com.kp.chukhnovm.hw8.Enums.Gender;
import com.kp.chukhnovm.hw8.Enums.SortOrder;
import com.kp.chukhnovm.hw8.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw8.Exceptions.GroupFulFilledException;
import com.kp.chukhnovm.hw8.Interfaces.CsvCompatible;
import com.kp.chukhnovm.hw8.Interfaces.IsMilita;
import com.kp.chukhnovm.hw8.Interfaces.Voenkom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Group implements Voenkom, CsvCompatible {

    private final int maxLength = 10;

    private Student[] students = new Student[maxLength];

    /*
    |--------------------------------------------------------------------------
    | CONSTRUCTORS
    |--------------------------------------------------------------------------
    */

    public Group() {
    }

    public Group(Student[] students) throws GroupFulFilledException {
        this.setStudents(students);
    }

    /*
    |--------------------------------------------------------------------------
    | GETTERS & SETTERS
    |--------------------------------------------------------------------------
    */

    public void setStudents(Student[] students) throws GroupFulFilledException {
        if (students.length > maxLength) {

            String message = String.format(
                    "You try create group with %s students. Group cannot carry more than %s students.",
                    students.length,
                    maxLength
            );

            throw new GroupFulFilledException(message);
        }

        this.students = students;
    }

    public Student[] getStudents() {

        int resultLength = 0;
        Student[] result;

        // Filter from nulls before sort
        for (Student student : this.students) {
            if (student != null) resultLength++;
        }

        result = new Student[resultLength];

        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) result[i] = this.students[i];
        }

        Arrays.sort(result);

        return result;
    }

    /*
    |--------------------------------------------------------------------------
    | METHODS
    |--------------------------------------------------------------------------
    */

    public void setStudent(Student student) throws GroupFulFilledException, GroupDuplicateStudentException {

        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] == null) {
                this.students[i] = student;
                return;
            } else if (this.students[i].equals(student)) {
                // Check if user already exists in group.
                throw new GroupDuplicateStudentException("Such student already exists");
            }
        }

        // If student was not added it means group already fulfilled
        throw new GroupFulFilledException("The group is full.");
    }

    public Student[] searchStudent(String lastName) {

        ArrayList<Student> buffer = new ArrayList<>();

        for (Student student : this.students) {
            if (student == null) continue;
            if (student.name.matches(".*\\b" + lastName + "\\b")) {
                buffer.add(student);
            }
        }

        Student[] searchResult = new Student[buffer.size()];

        return buffer.toArray(searchResult);
    }

    public void removeStudent(Student student) {
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i].equals(student)) {
                this.students[i] = null;
            }
        }
    }

    public Student[] sortByLastName() {
        return this.sortByLastName(SortOrder.ASC);
    }

    public Student[] sortByLastName(SortOrder order) {

        Student[] students = this.students;
        Comparator comparator = new NameComparator();

        if (order.equals(SortOrder.DESC)) {
            Arrays.sort(students, comparator.reversed());
        } else {
            Arrays.sort(students, comparator);
        }

        return students;
    }

    @Override
    public Student[] getMilitia() {

        IsMilita isMilitia = (Human h) -> {
            return h != null &&
                    h.gender.equals(Gender.MALE) &&
                    h.getAgeInYears() >= 18;
        };

        int i = 0;
        for (Student student : this.getStudents()) {
            if (isMilitia.check(student)) {
                ++i;
            }
        }

        Student[] militia = new Student[i];

        int j = 0;
        for (Student student : this.getStudents()) {
            if (isMilitia.check(student)) {
                militia[j++] = student;
            }
        }

        return militia;
    }

    public String toCSVString() {
        StringBuilder strB = new StringBuilder();
        for (CsvCompatible student : this.getStudents()) {
            strB.append(student.toCSVString());
            strB.append("\n");
        }

        return strB.toString();
    }

    @Override
    public String toString() {
        // Sort students by name
        //TODO: implement selection sorting

        StringBuilder strB = new StringBuilder();
        for (Student student : this.getStudents()) {
            strB.append("\n");
            strB.append(student);
        }

        String studentsString = strB.toString();

        return "Group{" +
                "students=[" + studentsString + "]" +
                '}';
    }

}
