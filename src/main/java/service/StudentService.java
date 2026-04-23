/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import constants.Message;
import dto.CourseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Course;
import model.Student;
import repository.StudentRepository;

/**
 *
 * @author Admin
 */
public class StudentService {
    private StudentRepository repository;

    public StudentService() {
        this.repository = new StudentRepository();
    }
    
    /**
     * Get the total number of students from repository.
     * @return Number of students
     */
    public int getStudentSize(){
        return repository.studentSize();
    }
    
    /**
     * Add a student or course enrollment.
     * @param request The student/course data
     * @throws Exception if validation fails (handled in main)
     */
    public void addStudent(StudentRequestDTO request) throws Exception {
        repository.addStudent(request);
    }

    /**
     * Check if a student with the given ID exists.
     * @param id Student ID to check
     * @return true if exists
     */
    public boolean isExistedStudent(String id) {
        return repository.isExistedStudent(id);
    }
    
    /**
     * Search for students by name and return a list of response DTOs.
     * @param input Name fragment to search
     * @return List of student response data
     * @throws Exception if database is empty or no students found
     */
    public List<StudentResponseDTO> searchStudent(String input) throws Exception {
        if (repository.isEmpty()) {
            throw new Exception(Message.DATABASE_EMPTY);
        }
        List<Student> students = repository.searchStudent(input);
        if (students == null || students.isEmpty()) {
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }

        List<StudentResponseDTO> result = new ArrayList<>();
        for (Student s : students) {
            for (Course c : s.getCourses()) {
                result.add(new StudentResponseDTO(s.getId(), s.getName(),
                        new CourseDTO(c.getSemester(), c.getCourseName())));
            }
        }
        return result;
    }
    
    /**
     * Update a student by ID (adds a new course).
     * @param requestDTO The update data
     * @throws Exception if student ID is not found
     */
    public void updateStudent(StudentRequestDTO requestDTO) throws Exception{
        if(!repository.isExistedStudent(requestDTO.getId())){
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.updateStudent(requestDTO);
    }
    
    /**
     * Delete a student by ID.
     * @param requestDTO The student ID to delete
     * @throws Exception if student ID is not found
     */
    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception{
        if(!repository.isExistedStudent(requestDTO.getId())){
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.deleteStudent(requestDTO);
    }
    
    /**
     * Generate the report list for all students.
     * @return List of report data
     * @throws Exception if database is empty
     */
    public List<StudentResponseDTO> reportList() throws Exception {
        if (repository.isEmpty()) {
            throw new Exception(Message.DATABASE_EMPTY);
        }
        Map<String, Integer> reportMap = repository.reportList();
        List<StudentResponseDTO> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : reportMap.entrySet()) {
            String[] parts = entry.getKey().split("\\|");
            StudentResponseDTO dto = new StudentResponseDTO();
            dto.setName(parts[0].trim());
            CourseDTO cour = new CourseDTO();
            cour.setCourse(parts[1].trim());
            dto.setCourse(cour);
            dto.setTotalCourse(entry.getValue());
            result.add(dto);
        }
        return result;
    }
}
