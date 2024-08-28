package com.g2.veeva.interview;

import java.util.*;

public class Solution {

    private final List<Course> courses;
    private final Map<String, Course> courseMap;

    public Solution(List<Course> courses) {

        this.courses = courses;
        this.courseMap = new HashMap<>();
        for (Course course : courses) {
            courseMap.put(course.getCourseId(), course);
        }
    }

    public Course getCourseById(String courseId) {

        return courseMap.get(courseId);
    }

    public List<String> getPreRequisitesForCourse(String courseId) {

        Set<String> schedule = new LinkedHashSet<>();

        for (String prerequisite : courseMap.get(courseId).getPrerequisites()) {
            addPreRequisitesForCourse(prerequisite, schedule);
        }

        // addPreRequisitesForCourse(courseId, schedule);

        return schedule.stream().toList();
    }

    public void addPreRequisitesForCourse(String courseId, Set<String> schedule) {

        if (schedule.contains(courseId)) {
            return;
        }

        for (String prerequisite : courseMap.get(courseId).getPrerequisites()) {
            addPreRequisitesForCourse(prerequisite, schedule);
        }

        schedule.add(courseId);
    }

    public List<String> getClassToSchedule() {

        return getClassToSchedule_dfs();
    }

    public List<String> getClassToSchedule_dfs() {

        Set<String> schedule = new LinkedHashSet<>();

//        for (Course course : courses) {
//            if (course.getPrerequisites().isEmpty()) {
//                schedule.add(course.getCourseId());
//            }
//        }

        while (schedule.size() < courses.size()) {
            for (Course course : courses) {
                addClassToSchedule_dfs(course.getCourseId(), schedule);
            }
        }

        return schedule.stream().toList();
    }

    public void addClassToSchedule_dfs(String courseId, Set<String> schedule) {

        if (schedule.contains(courseId)) {
            return;
        }

        Course course = courseMap.get(courseId);
        for (String prerequisite : course.getPrerequisites()) {
            addClassToSchedule_dfs(prerequisite, schedule);
        }

        schedule.add(courseId);
    }

    public List<String> getClassToSchedule_Kahns() {

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> childCount = new HashMap<>();
        for (Course course : courses) {
            String courseId = course.getCourseId();
            for (String prerequisite : course.getPrerequisites()) {
                parents.computeIfAbsent(prerequisite, p -> new ArrayList<>()).add(courseId);
            }
            childCount.put(courseId, course.getPrerequisites().size());
        }

        Queue<String> queue = new LinkedList<>();
        for (Course course : courses) {
            String courseId = course.getCourseId();
            if (childCount.get(courseId) == 0) {
                queue.offer(courseId);
            }
        }

        List<String> schedule = new ArrayList<>();
        while (!queue.isEmpty()) {

            String course = queue.poll();
            schedule.add(course);

            List<String> myParents = parents.get(course);
            if (myParents != null) {
                for (String myParent : myParents) {
                    childCount.put(myParent, childCount.get(myParent) - 1);
                    if (childCount.get(myParent) == 0) {
                        queue.offer(myParent);
                    }
                }
            }
        }

        return schedule;
    }
}


