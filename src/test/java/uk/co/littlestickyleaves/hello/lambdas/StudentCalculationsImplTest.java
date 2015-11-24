package uk.co.littlestickyleaves.hello.lambdas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentCalculationsImplTest {

    private static final double DELTA = 0.01;
    private StudentCalculations testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new LambdaStudentCalculations();
    }

    @Test
    public void testFirstNames() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        List<String> result = testObject.firstNames(students);

        // assert
        Assert.assertEquals(students.size(), result.size());
        Assert.assertTrue(result.contains("Lindsey"));
        Assert.assertTrue(result.contains("Cameron"));
        Assert.assertTrue(result.contains("Elaine"));
        Assert.assertTrue(result.contains("Richard"));
        Assert.assertTrue(result.contains("Roberta"));
        Assert.assertTrue(result.contains("Steve"));
        Assert.assertTrue(result.contains("Maria"));
        Assert.assertTrue(result.contains("Isabel"));
        Assert.assertTrue(result.contains("Gerhard"));
        Assert.assertTrue(result.contains("Lee"));
    }

    @Test
    public void testJuneBirthdays() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        List<Student> result = testObject.juneBirthdays(students);

        // assert
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.get(0).getBirthday().getMonth().equals(Month.JUNE));
        Assert.assertTrue(result.get(1).getBirthday().getMonth().equals(Month.JUNE));
    }

    @Test
    public void testCharactersInFullName() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        List<Integer> result = testObject.charactersInFullName(students);

        // assert
        Assert.assertEquals(10, result.size());
        Assert.assertEquals(Integer.valueOf(12), result.get(0));
        Assert.assertEquals(Integer.valueOf(16), result.get(1));
        Assert.assertEquals(Integer.valueOf(16), result.get(2));
        Assert.assertEquals(Integer.valueOf(15), result.get(3));
        Assert.assertEquals(Integer.valueOf(13), result.get(4));
        Assert.assertEquals(Integer.valueOf(12), result.get(5));
        Assert.assertEquals(Integer.valueOf(9), result.get(6));
        Assert.assertEquals(Integer.valueOf(14), result.get(7));
        Assert.assertEquals(Integer.valueOf(11), result.get(8));
        Assert.assertEquals(Integer.valueOf(9), result.get(9));

    }

    @Test
    public void testBirthdays() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        Set<LocalDate> result = testObject.birthdays(students);

        // assert
        Assert.assertEquals(10, result.size());
    }

    @Test
    public void testWereAnyOfThemBornIn1954() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        boolean result = testObject.wereAnyOfThemBornIn1954(students);

        // assert
        Assert.assertTrue(result);
    }

    @Test
    public void testWereAllOfThemBornBefore1990() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        boolean result = testObject.wereAllOfThemBornBefore1990(students);

        // assert
        Assert.assertTrue(result);
    }

    @Test
    public void testAges() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        List<Integer> result = testObject.ages(students);

        // assert
        Assert.assertEquals(10, result.size());
        Assert.assertEquals(Integer.valueOf(27), result.get(0));
        Assert.assertEquals(Integer.valueOf(31), result.get(1));
        Assert.assertEquals(Integer.valueOf(25), result.get(2));
        Assert.assertEquals(Integer.valueOf(68), result.get(3));
        Assert.assertEquals(Integer.valueOf(64), result.get(4));
        Assert.assertEquals(Integer.valueOf(48), result.get(5));
        Assert.assertEquals(Integer.valueOf(61), result.get(6));
        Assert.assertEquals(Integer.valueOf(56), result.get(7));
        Assert.assertEquals(Integer.valueOf(39), result.get(8));
        Assert.assertEquals(Integer.valueOf(31), result.get(9));
    }

    @Test
    public void testHighestMark() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        double result = testObject.highestMark(students);

        // assert
        Assert.assertEquals(88.3, result, DELTA);

    }

    @Test
    public void testHighestMarkOf2012() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        double result = testObject.highestMarkOf2012(students);

        // assert
        Assert.assertEquals(77.3, result, DELTA);
    }

    @Test
    public void testAverageMarkOf2012() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        double result = testObject.averageMarkOf2012(students);

        // assert
        Assert.assertEquals(65.7, result, DELTA);
    }

    @Test
    public void testStudentsByBirthMonth() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        Map<Month, List<Student>> result = testObject.studentsByBirthMonth(students);

        // assert
        Assert.assertEquals(7, result.size());
        Assert.assertEquals(1, result.get(Month.JANUARY).size());
        Assert.assertEquals(2, result.get(Month.FEBRUARY).size());
        Assert.assertEquals(2, result.get(Month.MARCH).size());
        Assert.assertEquals(2, result.get(Month.JUNE).size());
        Assert.assertEquals(1, result.get(Month.AUGUST).size());
        Assert.assertEquals(1, result.get(Month.NOVEMBER).size());
        Assert.assertEquals(1, result.get(Month.DECEMBER).size());
    }

    @Test
    public void testNumberOfStudentsInCourse() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        Map<Student.Course, Long> result = testObject.numberOfStudentsInCourse(students);

        // assert
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(Long.valueOf(4L), result.get(Student.Course.ARCHAEOLOGY));
        Assert.assertEquals(Long.valueOf(5L), result.get(Student.Course.BACTERIOLOGY));
        Assert.assertEquals(Long.valueOf(4L), result.get(Student.Course.COMPUTING));
    }

    @Test
    public void testFrequencyOfLettersInAllNames() throws Exception {
        // arrange
        List<Student> students = testStudents();

        // act
        Map<String, Long> result = testObject.frequencyOfLettersInAllNames(students);

        // assert
        Assert.assertEquals(22, result.size());
        Assert.assertEquals(Long.valueOf(14), result.get("A"));
        Assert.assertEquals(Long.valueOf(4), result.get("B"));
        Assert.assertEquals(Long.valueOf(3), result.get("C"));
        Assert.assertEquals(Long.valueOf(4), result.get("D"));
        Assert.assertEquals(Long.valueOf(18), result.get("E"));
        Assert.assertEquals(Long.valueOf(2), result.get("F"));
        Assert.assertEquals(Long.valueOf(5), result.get("G"));
        Assert.assertEquals(Long.valueOf(4), result.get("H"));
        Assert.assertEquals(Long.valueOf(8), result.get("I"));
        Assert.assertEquals(Long.valueOf(3), result.get("K"));
        Assert.assertEquals(Long.valueOf(8), result.get("L"));
        Assert.assertEquals(Long.valueOf(3), result.get("M"));
        Assert.assertEquals(Long.valueOf(9), result.get("N"));
        Assert.assertEquals(Long.valueOf(6), result.get("O"));
        Assert.assertEquals(Long.valueOf(15), result.get("R"));
        Assert.assertEquals(Long.valueOf(7), result.get("S"));
        Assert.assertEquals(Long.valueOf(6), result.get("T"));
        Assert.assertEquals(Long.valueOf(3), result.get("U"));
        Assert.assertEquals(Long.valueOf(1), result.get("V"));
        Assert.assertEquals(Long.valueOf(1), result.get("W"));
        Assert.assertEquals(Long.valueOf(2), result.get("Y"));
        Assert.assertEquals(Long.valueOf(1), result.get("Z"));
    }

    private List<Student> testStudents() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Lindsey", "Smith", LocalDate.of(1987, Month.DECEMBER, 14), 2012, 60.5, Student.Course.ARCHAEOLOGY, Student.Course.BACTERIOLOGY));
        students.add(new Student("Cameron", "Ellsworth", LocalDate.of(1984, Month.JANUARY, 30), 2012, 59.4, Student.Course.BACTERIOLOGY));
        students.add(new Student("Elaine", "Rubenstein", LocalDate.of(1989, Month.NOVEMBER, 30), 2012, 65.7, Student.Course.COMPUTING, Student.Course.BACTERIOLOGY));
        students.add(new Student("Richard", "Figueroa", LocalDate.of(1947, Month.AUGUST, 12), 2012, 73.1, Student.Course.ARCHAEOLOGY));
        students.add(new Student("Roberta", "Bakker", LocalDate.of(1951, Month.MARCH, 13), 2012, 77.3, Student.Course.BACTERIOLOGY));
        students.add(new Student("Steve", "Colston", LocalDate.of(1967, Month.JUNE, 18), 2012, 62.3, Student.Course.COMPUTING, Student.Course.ARCHAEOLOGY));
        students.add(new Student("Maria", "Gray", LocalDate.of(1954, Month.FEBRUARY, 12), 2012, 60.5, Student.Course.ARCHAEOLOGY));
        students.add(new Student("Isabel", "Faulkner", LocalDate.of(1959, Month.JUNE, 24), 2012, 68.3, Student.Course.BACTERIOLOGY));
        students.add(new Student("Gerhard", "Ganz", LocalDate.of(1976, Month.MARCH, 18), 2012, 64.2, Student.Course.COMPUTING));
        students.add(new Student("Lee", "Danger", LocalDate.of(1984, Month.FEBRUARY, 3), 2011, 88.3, Student.Course.COMPUTING));
        return students;
    }

}