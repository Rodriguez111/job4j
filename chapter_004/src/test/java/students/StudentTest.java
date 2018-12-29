package students;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
public class StudentTest {

    @Test
    public void whenBoundIs7Then98() {
        Student student1 = new Student("St6", 6);
        Student student2 = new Student("St2", 5);
        Student student3 = new Student("St5", 9);
        Student student4 = new Student("St4", 2);
        Student student5 = new Student("St3", 8);
        Student student6 = new Student("St1", 7);

        List<Student> studentList = new ArrayList<>();
        studentList.add(null);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);

        List<Student> actual =  student1.levelOf(studentList, 7);
        List<Student> expected = new ArrayList<>();
        expected.add(student3);
        expected.add(student5);
        assertThat(actual, is(expected));

    }

}
