package uk.co.littlestickyleaves.hello.lambdas;

import com.google.common.collect.Maps;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>Go through replacing the for-loop code with streams and lambdas<br>
 * <ul>
 * <li>the first has been done</li>
 * </ul>
 */
public class ForLoopStudentCalculations implements StudentCalculations{

    /**
     * Returns a list of the first names of all students
     *
     * @param students
     * @return
     */
    @Override
    public List<String> firstNames(List<Student> students) {
//        List<String> firstNames = new ArrayList<>();
//        for (Student student : students) {
//            firstNames.add(student.getFirstName());
//        }
//        return firstNames;

        return students.stream()
                .map(Student::getFirstName) // could also write .map(student -> student.getFirstName())
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of all students who were born in June
     *
     * @param students
     * @return
     */
    @Override
    public List<Student> juneBirthdays(List<Student> students) {
        List<Student> juneBirthdays = new ArrayList<>();
        for (Student student : students) {
            if (student.getBirthday().getMonth().equals(Month.JUNE)) {
                juneBirthdays.add(student);
            }
        }
        return juneBirthdays;
    }

    /**
     * returns a List of the total number of letters in a student's first and last names
     *
     * @param students
     * @return
     */
    @Override
    public List<Integer> charactersInFullName(List<Student> students) {
        List<Integer> charactersInFullName = new ArrayList<>();
        for (Student student : students) {
            charactersInFullName.add(student.getFirstName().length() + student.getLastName().length());
        }
        return charactersInFullName;
    }

    /**
     * Makes a set of all the birthdays
     *
     * @param students
     * @return
     */
    @Override
    public Set<LocalDate> birthdays(List<Student> students) {
        Set<LocalDate> birthdays = new HashSet<>();
        for (Student student : students) {
            birthdays.add(student.getBirthday());
        }
        return birthdays;
    }

    /**
     * Returns true if any of the students was born in 1954
     *
     * @param students
     * @return
     */
    @Override
    public boolean wereAnyOfThemBornIn1954(List<Student> students) {
        for (Student student : students) {
            if (student.getBirthday().getYear() == 1954) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if all of the students were born before 1995
     *
     * @param students
     * @return
     */
    @Override
    public boolean wereAllOfThemBornBefore1990(List<Student> students) {
        for (Student student : students) {
            if (student.getBirthday().getYear() > 1990) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of all the ages of students
     *
     * @param students
     * @return
     */
    @Override
    public List<Integer> ages(List<Student> students) {
        List<Integer> ages = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Student student : students) {
            ages.add(Period.between(student.getBirthday(), today).getYears());
        }
        return ages;
    }

    /**
     * Returns the highest mark of any student
     *
     * @param students
     * @return
     */
    @Override
    public double highestMark(List<Student> students) {
        double highestSoFar = 0;
        for (Student student : students) {
            if (student.getFinalMark() > highestSoFar) {
                highestSoFar = student.getFinalMark();
            }
        }
        return highestSoFar;

    }

    /**
     * Returns the highest mark of any student who graduated in 2012
     *
     * @param students
     * @return
     */
    @Override
    public double highestMarkOf2012(List<Student> students) {
        double highestSoFar = 0;

        for (Student student : students) {
            if (student.getGraduationYear() == 2012 && student.getFinalMark() > highestSoFar) {
                highestSoFar = student.getFinalMark();
            }
        }

        return highestSoFar;
    }

    /**
     * Returns the average mark of all students who graduated in 2012
     *
     * @param students
     * @return
     */
    @Override
    public double averageMarkOf2012(List<Student> students) {
        double totalSoFar = 0;
        int numberOfStudents = 0;
        for (Student student : students) {
            if (student.getGraduationYear() == 2012) {
                totalSoFar += student.getFinalMark();
                numberOfStudents++;
            }
        }

        return totalSoFar / numberOfStudents;
    }

    /**
     * Returns a map of Month to a List of the Students who were born in that month
     *
     * @param students
     * @return
     */
    @Override
    public Map<Month, List<Student>> studentsByBirthMonth(List<Student> students) {
        Map<Month, List<Student>> studentsByBirthMonth = new HashMap<>();
        for (Student student : students) {
            Month birthMonth = student.getBirthday().getMonth();
            List<Student> monthStudents = studentsByBirthMonth.get(birthMonth);
            if (monthStudents == null) {
                monthStudents = new ArrayList<>();
                studentsByBirthMonth.put(birthMonth, monthStudents);
            }
            monthStudents.add(student);
        }
        return studentsByBirthMonth;
    }

    /**
     * Returns a map of Course to the number of students who did that course
     *
     * @param students
     * @return
     */
    @Override
    public Map<Student.Course, Long> numberOfStudentsInCourse(List<Student> students) {
        Map<Student.Course, Long> numberOfStudentsInCourse = new HashMap<>();
        for (Student student : students) {
            for (Student.Course course : student.getCourse()) {
                Long courseStudents = numberOfStudentsInCourse.get(course);
                if (courseStudents == null) {
                    numberOfStudentsInCourse.put(course, 1L);
                } else {
                    numberOfStudentsInCourse.put(course, courseStudents + 1);
                }
            }
        }
        return numberOfStudentsInCourse;

    }

    /**
     * Returns a map of letter to the frequency of that letter in all the student names
     *
     * @param students
     * @return
     */
    @Override
    public Map<String, Long> frequencyOfLettersInAllNames(List<Student> students) {
        Map<String, Long> frequencyOfLettersInAllNames = Maps.newHashMap();
        for (Student student : students) {
            String fullName = student.getFirstName() + student.getLastName();
            for (int counter = 0; counter < fullName.length(); counter++) {
                String letter = fullName.substring(counter, counter + 1).toUpperCase();
                Long frequencyOfThisLetter = frequencyOfLettersInAllNames.get(letter);
                if (frequencyOfThisLetter == null) {
                    frequencyOfLettersInAllNames.put(letter, 1l);
                } else {
                    frequencyOfLettersInAllNames.put(letter, frequencyOfThisLetter + 1);
                }
            }
        }
        return frequencyOfLettersInAllNames;

    }

}
