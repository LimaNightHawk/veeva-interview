package com.g2.veeva.interview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    private final List<Course> courses;
    private final Map<String, Course> courseMap;

    public Solution(List<Course> courses) {

        this.courses = courses;
        this.courseMap = new HashMap<>();
        for (Course course : this.courses) {
            courseMap.put(course.getCourseId(), course);
        }
    }

    public Course getCourseById(String courseId) {

        return courseMap.get(courseId);
    }

    public List<String> getClassToSchedule() {

        return null;
    }

    public List<String> getPreRequisitesForCourse(String courseId) {

        return null;
    }

}


