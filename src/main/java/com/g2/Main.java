package com.g2;

import com.g2.veeva.interview.Course;
import com.g2.veeva.interview.Solution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final List<Course> COURSES = Arrays.asList(

            new Course("E104", Arrays.asList("E103")),
            new Course("HS-ENG", Collections.emptyList()),
            new Course("E102", Arrays.asList("E101")),
            new Course("CW101", Collections.emptyList()),
            new Course("E103", Arrays.asList("E102", "CW101")),
            new Course("E101", Arrays.asList("HS-ENG"))
    );

    public static void main(String[] args) {

        Solution solution = new Solution(COURSES);

        System.out.println("Class Schedule:");
        List<String> classToSchedule = solution.getClassToSchedule();
        for (int i = 0; i < classToSchedule.size(); i++) {
            String courseId = classToSchedule.get(i);
            System.out.println("" + (i + 1) + ". " + courseId + " : " + solution.getCourseById(courseId).getPrerequisites() );

        }

        System.out.println("------");

        System.out.println(" CW101 requires: " + solution.getPreRequisitesForCourse("CW101"));
        System.out.println(" E104 requires: " + solution.getPreRequisitesForCourse("E104"));


    }
}