/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dto.CourseDTO;
import dto.StudentRequestDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentRepository {

    private List<Student> list = new ArrayList<>();

    /**
     * Get the list of all students.
     * @return List of students
     */
    public List<Student> getList() {
        return list;
    }

    /**
     * Add a new student or add a new course to an existing student by ID.
     * @param request Student information and course details
     */
    public void addStudent(StudentRequestDTO request) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(request.getId())) {
                s.getCourses().add(new Course(
                        request.getCourse().getSemester(),
                        request.getCourse().getCourse()
                ));
                return;
            }
        }

        Student newStudent = new Student(request.getId(), request.getName());
        newStudent.getCourses().add(new Course(
                request.getCourse().getSemester(),
                request.getCourse().getCourse()
        ));
        list.add(newStudent);
    }

    /**
     * Get the total number of unique students.
     * @return count of students
     */
    public int studentSize() {
        return list.size();
    }

    /**
     * Search for students by name (partial match) and sort the results.
     * @param name Name to search for
     * @return Filtered and sorted list of students
     */
    public List<Student> searchStudent(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : list) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    /**
     * Check if the repository is empty.
     * @return true if no students exist
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Check if a student exists by their ID.
     * @param id Student ID
     * @return true if found
     */
    public boolean isExistedStudent(String id) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update an existing student by adding a new course record.
     * @param requestDTO Update details containing ID and new course info
     */
    public void updateStudent(StudentRequestDTO requestDTO) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(requestDTO.getId())) {
                s.getCourses().add(new Course(requestDTO.getCourse().getSemester(),
                        requestDTO.getCourse().getCourse()));
                break;
            }
        }
    }

    /**
     * Delete a student by their ID.
     * @param requestDTO Request containing student ID to delete
     */
    public void deleteStudent(StudentRequestDTO requestDTO) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(requestDTO.getId())) {
                list.remove(i);
                break;
            }
        }
    }

    /**
     * Generate a report map containing student name, course, and total counts.
     * @return Map with reporting data
     */
    public Map<String, Integer> reportList() {
        Map<String, Integer> map = new HashMap<>();
        for (Student s : list) {
            for (Course c : s.getCourses()) {
                String key = s.getName() + " | " + c.getCourseName();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        return map;
    }
}


