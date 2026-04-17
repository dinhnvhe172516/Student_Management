/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.util.List;
import service.StudentService;
import view.StudentView;

/**
 *
 * @author Admin
 */
public class StudentController {
    private StudentService service;
    private StudentView view;
    
    public StudentController() {
        service = new StudentService();
        view = new StudentView();
    }
    
    //Retrieve the total number of student
    public int getStudentSize(){
       return service.getStudentSize();
    }
    
    //Handling adding new student enrollment
    public void addStudent(StudentRequestDTO request) throws Exception{
        service.addStudent(request);
    }
    
    public void searchStudent(String input) throws Exception{
        List<StudentResponseDTO> result = service.searchStudent(input);
        view.setStudentList(result);
        view.displayStudentList();
    }
    
    public void updateStudent(StudentRequestDTO requestDTO) throws Exception{
        service.updateStudent(requestDTO);
    }
    
    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception{
        service.deleteStudent(requestDTO);
    }
    
    public void reportList() throws Exception{
        List<StudentResponseDTO> result = service.reportList();
        view.setReportList(result);
        view.displayReportList();
    }
}
