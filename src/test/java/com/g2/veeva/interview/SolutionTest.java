package com.g2.veeva.interview;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    private static final List<Course> COURSES = Arrays.asList(

            new Course("E104", Arrays.asList("E103")),
            new Course("HS-ENG", Collections.emptyList()),
            new Course("E102", Arrays.asList("E101")),
            new Course("CW101", Arrays.asList("E101")),
            new Course("E103", Arrays.asList("E102", "CW101")),
            new Course("E101", Arrays.asList("HS-ENG"))
    );

    private Solution solution = new Solution(COURSES);

    @Test
    void getCourseById_CW101() {

        Course course = solution.getCourseById("CW101");

        assertThat(course.getCourseId()).isEqualTo("CW101");
    }

    @Test
    void getCourseById_E102() {

        Course course = solution.getCourseById("E102");

        assertThat(course.getCourseId()).isEqualTo("E102");
    }

    @Test
    void getPreRequisitesForCourse_CW101() {

        List<String> prerequisites = solution.getPreRequisitesForCourse("CW101");

        assertThat(prerequisites).containsExactly("HS-ENG", "E101");
    }

    @Test
    void getPreRequisitesForCourse_E104() {

        List<String> prerequisites = solution.getPreRequisitesForCourse("E104");

        assertThat(prerequisites).containsExactly("HS-ENG", "E101", "E102", "CW101", "E103");
    }

    @Test
    void getClassToSchedule() {

        List<String> schedule = solution.getClassToSchedule();

        assertThat(schedule).containsExactly("HS-ENG", "E101", "E102", "CW101", "E103", "E104");
    }
}