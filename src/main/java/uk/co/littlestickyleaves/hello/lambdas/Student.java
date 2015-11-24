package uk.co.littlestickyleaves.hello.lambdas;

import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private int graduationYear;
    private List<Course> courses;
    private double finalMark;

    public enum Course { ARCHAEOLOGY, BACTERIOLOGY, COMPUTING }

    public Student(String firstName, String lastName, LocalDate birthday, int graduationYear, double finalMark, Course... courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.graduationYear = graduationYear;
        this.finalMark = finalMark;
        this.courses = Lists.newArrayList(courses);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public double getFinalMark() {
        return finalMark;
    }
}

