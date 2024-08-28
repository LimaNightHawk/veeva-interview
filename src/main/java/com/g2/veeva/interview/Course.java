package com.g2.veeva.interview;

import java.util.Collection;

public class Course {


    private String courseId;

    private Collection<String> prerequisites;

    public Course(String courseId, Collection<String> prerequisites) {

        this.courseId = courseId;
        this.prerequisites = prerequisites;
    }

    public String getCourseId() {

        return courseId;
    }

    public void setCourseId(String courseId) {

        this.courseId = courseId;
    }

    public Collection<String> getPrerequisites() {

        return prerequisites;
    }

    public void setPrerequisites(Collection<String> prerequisites) {

        this.prerequisites = prerequisites;
    }
}
