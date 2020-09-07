package com.kp.chukhnovm.hw1_1;

import com.kp.chukhnovm.hw1_1.Exceptions.GroupDuplicateStudentException;
import com.kp.chukhnovm.hw1_1.Exceptions.GroupFulFilledException;

import java.util.ArrayList;
import java.util.Arrays;

public class Group {

    private final Student[] students = new Student[10];

    public Group() {
    }

    public Student[] getStudents() {
        return this.students;
    }

    public Student[] searchStudent(String lastName) {

        ArrayList<Student> buffer = new ArrayList<>();

        for (Student student : this.students) {
            if(student == null) continue;
            if (student.name.matches(".*\\b" + lastName + "\\b")) {
                buffer.add(student);
            }
        }

        Student[] searchResult = new Student[buffer.size()];

        return buffer.toArray(searchResult);
    }

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

    public void removeStudent(Student student) {
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i].equals(student)) {
                this.students[i] = null;
            }
        }
    }


    @Override
    public String toString() {
        // Sort students by name
        //TODO: implement selection sorting
        Student[] studentSortedArray = this.students;
        Arrays.sort(studentSortedArray);

        StringBuilder strB = new StringBuilder();
        for (Student student : studentSortedArray) {
            strB.append("\n");
            strB.append(student);
        }

        String studentsString = strB.toString(); // Arrays.toString(studentSortedArray)

        return "Group{" +
                "students=[" + studentsString + "]" +
                '}';
    }

}
