package uk.co.littlestickyleaves.hello.lambdas;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * StudentCalculations implemented with lambdas
 */
public class LambdaStudentCalculations implements StudentCalculations {

    @Override
    public List<String> firstNames(List<Student> students) {
        return students.stream()
                .map(Student::getFirstName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> juneBirthdays(List<Student> students) {
        return students.stream()
                .filter(student -> student.getBirthday().getMonth().equals(Month.JUNE))
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> charactersInFullName(List<Student> students) {        return students.stream()
            .map(student -> student.getFirstName().length() + student.getLastName().length())
            .collect(Collectors.toList());
    }

    @Override
    public Set<LocalDate> birthdays(List<Student> students) {
        return students.stream()
                .map(Student::getBirthday)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean wereAnyOfThemBornIn1954(List<Student> students) {
        return students.stream()
                .filter(student -> student.getBirthday().getYear() == 1954)
                .findAny()
                .map(any -> true)
                .orElse(false);
    }

    @Override
    public boolean wereAllOfThemBornBefore1990(List<Student> students) {
        return students.stream()
                .filter(student -> student.getBirthday().getYear() > 1990)
                .findAny()
                .map(any -> false)
                .orElse(true);
    }

    @Override
    public List<Integer> ages(List<Student> students) {        LocalDate today = LocalDate.now();
        return students.stream()
                .map(student -> Period.between(student.getBirthday(), today).getYears())
                .collect(Collectors.toList());
    }

    @Override
    public double highestMark(List<Student> students) {
        return students.stream()
                .mapToDouble(Student::getFinalMark)
                .max()
                .orElse(0.0d);
    }

    @Override
    public double highestMarkOf2012(List<Student> students) {
        return students.stream()
                .filter(student -> student.getGraduationYear() == 2012)
                .mapToDouble(Student::getFinalMark)
                .max()
                .orElse(0.0d);
    }

    @Override
    public double averageMarkOf2012(List<Student> students) {
        return students.stream()
                .filter(student -> student.getGraduationYear() == 2012)
                .mapToDouble(Student::getFinalMark)
                .average()
                .orElse(0.0d);
    }

    @Override
    public Map<Month, List<Student>> studentsByBirthMonth(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(student -> student.getBirthday().getMonth()));
    }

    @Override
    public Map<Student.Course, Long> numberOfStudentsInCourse(List<Student> students) {
        return students.stream()
                .flatMap(student1 -> student1.getCourse().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @Override
    public Map<String, Long> frequencyOfLettersInAllNames(List<Student> students) {
        return students.stream()
                .map((Student student) -> student.getFirstName() + student.getLastName())
                .flatMap(name -> Arrays.stream(name.split("")))
                .collect(Collectors.groupingBy(letter -> letter.toUpperCase(), Collectors.counting()));
    }
}
