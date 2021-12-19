package HW9;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Course> courses = new ArrayList<Course>();
        Student s1 = new MyStudent("s1", courses);
        courses.add(new MyCourse());
        Student s2 = new MyStudent("s2", courses);
        Course myCourse = new MyCourse();
        courses.add(myCourse);
        Student s3 = new MyStudent("s3", courses);
        courses.add(new MyCourse());
        Student s4 = new MyStudent("s4", courses);
        courses.add(new MyCourse());
        List<Student> students = new ArrayList<Student>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        System.out.println(HW9.getCourseListFromStudentsList(students));
        System.out.println(HW9.getTreeMostCuriousStudents(students));
        System.out.println(HW9.getStudentsListeningCourse(students, myCourse));
    }
}
