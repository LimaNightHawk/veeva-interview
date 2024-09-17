package com.g2.veeva.interview;

import java.util.*;

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

        List<String> schedule = new ArrayList<>();

        Map<String, List<Course>> parents = new HashMap<>();
        Map<String, Integer> children = new HashMap<>();

        for (Course course : courses) {
            String parent = course.getCourseId();
            for (String child : course.getPrerequisites()) {
                children.put(parent, children.getOrDefault(parent, 0) + 1);
                parents.computeIfAbsent(child, c -> new ArrayList<>()).add(course);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (Course course : courses) {
            if (course.getPrerequisites().isEmpty()) {
                queue.offer(course.getCourseId());
            }
        }

        while(!queue.isEmpty()){

            String courseId = queue.poll();
            schedule.add(courseId);

            Course course = courseMap.get(courseId);
            List<Course> myParents = parents.get(courseId);
            if (myParents != null) {
                for (Course myParent : myParents) {
                    children.put(myParent.getCourseId(), children.get(myParent.getCourseId()) - 1);
                    if (children.get(myParent.getCourseId()) == 0){
                        queue.offer(myParent.getCourseId());
                    }
                }
            }
        }

        return schedule;
    }


    public List<String> getPreRequisitesForCourse(String courseId) {


        Set<String> schedule = new LinkedHashSet<>();

        Course course = courseMap.get(courseId);
        for (String prerequisite : course.getPrerequisites()) {
            addRequisitesToSchedule(prerequisite, schedule);
        }

        return schedule.stream().toList();
    }

    public void addRequisitesToSchedule(String courseId,  Set<String> schedule) {
        if (schedule.contains(courseId)){
            return;
        }
        Course course = courseMap.get(courseId);
        for (String prerequisite : course.getPrerequisites()) {
            addRequisitesToSchedule(prerequisite, schedule);
        }

        schedule.add(courseId);

    }
}


