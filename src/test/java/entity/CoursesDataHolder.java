package entity;

import model.entity.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by click on 12/1/2016.
 */
public class CoursesDataHolder {
    private List<Course> courses;

    public CoursesDataHolder() {
        courses = new ArrayList<>();
    }

    public void add(Course course){
        courses.add(course);
    }

    public void delete(Course course) {
        courses.remove(course);
    }

    public void update(Course course){
        courses.set(courses.indexOf(course), course);
    }

    public Course get(Integer id){
        return courses.get(courses.indexOf(id));
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
