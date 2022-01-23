package HW9;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HW9 {

    /**
     1. Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.
     2. Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных (любознательность
     определяется количеством курсов).
     3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов, которые посещают этот курс.
     */


    public static List<Course> getCourseListFromStudentsList (List<Student> students){
        return students.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Student> getTreeMostCuriousStudents(List<Student> students){
        return students.stream()
                .sorted((s1, s2) -> s2.getAllCourses().size() - s1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> getStudentsListeningCourse(List<Student> students, Course course){
        return students.stream()
                .filter(Student -> Student.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }
}
