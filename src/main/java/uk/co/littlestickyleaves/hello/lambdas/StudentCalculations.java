package uk.co.littlestickyleaves.hello.lambdas;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Just defines some calculations on List<Student>
 */
public interface StudentCalculations {
    List<String> firstNames(List<Student> students);

    List<Student> juneBirthdays(List<Student> students);

    List<Integer> charactersInFullName(List<Student> students);

    Set<LocalDate> birthdays(List<Student> students);

    boolean wereAnyOfThemBornIn1954(List<Student> students);

    boolean wereAllOfThemBornBefore1990(List<Student> students);

    List<Integer> ages(List<Student> students);

    List<Student> firstThreeStudents(List<Student> students);

    List<Student> firstThreeWithBirthdaysBeforeJuly(List<Student> students);

    double highestMark(List<Student> students);

    double highestMarkOf2012(List<Student> students);

    double averageMarkOf2012(List<Student> students);

    Map<Month, List<Student>> studentsByBirthMonth(List<Student> students);

    Map<Student.Course, Long> numberOfStudentsInCourse(List<Student> students);

    Map<String, Long> frequencyOfLettersInAllNames(List<Student> students);
}
