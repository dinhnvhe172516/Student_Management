/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import constants.Message;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.util.List;
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
    
    public List<StudentResponseDTO> searchStudent(String input) throws Exception{
        if(repository.isEmpty()){
            throw new Exception(Message.DATABASE_EMPTY);
        }
        List<StudentResponseDTO> result = repository.searchStudent(input);
        if(result == null || result.isEmpty()){
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
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
    
    public List<StudentResponseDTO> reportList() throws Exception{
        if(repository.isEmpty()){
            throw new Exception(Message.DATABASE_EMPTY);
        }
        return repository.reportList();
    }
}
