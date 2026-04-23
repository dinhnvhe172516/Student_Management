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
    
    /**
     * Retrieve the total number of students.
     * @return student count
     */
    public int getStudentSize(){
       return service.getStudentSize();
    }
    
    /**
     * Handling adding new student enrollment.
     * @param request student/course data
     * @throws Exception if something goes wrong
     */
    public void addStudent(StudentRequestDTO request) throws Exception{
        service.addStudent(request);
    }

    /**
     * Check if student exists by ID.
     * @param id Student ID
     * @return true if exists
     */
    public boolean isExistedStudent(String id) {
        return service.isExistedStudent(id);
    }
    
    /**
     * Search for students by name and display them in the view.
     * @param input name or part of name
     * @throws Exception if search fails or database empty
     */
    public void searchStudent(String input) throws Exception{
        List<StudentResponseDTO> result = service.searchStudent(input);
        view.setStudentList(result);
        view.displayStudentList();
    }
    
    /**
     * Update a student's information.
     * @param requestDTO update data
     * @throws Exception if student ID not found
     */
    public void updateStudent(StudentRequestDTO requestDTO) throws Exception{
        service.updateStudent(requestDTO);
    }
    
    /**
     * Delete a student by ID.
     * @param requestDTO student ID to delete
     * @throws Exception if student ID not found
     */
    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception{
        service.deleteStudent(requestDTO);
    }
    
    /**
     * Display the student report list.
     * @throws Exception if database is empty
     */
    public void reportList() throws Exception{
        List<StudentResponseDTO> result = service.reportList();
        view.setReportList(result);
        view.displayReportList();
    }
}
