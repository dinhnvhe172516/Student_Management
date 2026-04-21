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
    
    public int getStudentSize(){
        return repository.studentSize();
    }
    
    public void addStudent(StudentRequestDTO request) throws Exception {
        repository.addStudent(request);
    }
    
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
    
    public void updateStudent(StudentRequestDTO requestDTO) throws Exception{
        if(!repository.isExistedStudent(requestDTO.getId())){
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.updateStudent(requestDTO);
    }
    
    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception{
        if(!repository.isExistedStudent(requestDTO.getId())){
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.deleteStudent(requestDTO);
    }
    
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
