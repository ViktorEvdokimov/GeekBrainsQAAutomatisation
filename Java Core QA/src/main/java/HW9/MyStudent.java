package HW9;

import java.util.ArrayList;
import java.util.List;

public class MyStudent implements Student{

    private String name;
    private List<Course> courses;

    public MyStudent(String name, List<Course> courses) {
        this.name = name;
        this.courses = new ArrayList<Course>();
        this.courses.addAll(courses);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return name;
    }
}
