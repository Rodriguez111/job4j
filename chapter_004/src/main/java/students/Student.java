package students;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparator<Student> {
    private String name;
    private int scope;

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o2.scope, o1.scope);
    }

    /**
     *
     * @param students unsorted nullable ru.job4j.list of students.
     * @param bound - threshold above which students are filtered.
     * @return - filtered students with scope more then bound.
     */
    public List<Student> levelOf(List<Student> students, int bound) {
    return students.stream().flatMap(Stream::ofNullable).sorted(this).takeWhile(student -> student.scope > bound).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='"
                + name + '\''
                + ", scope=" + scope
                + '}';
    }
}
