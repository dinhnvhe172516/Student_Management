/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dto.StudentResponseDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public class StudentView {
    private List<StudentResponseDTO> studentList;
    private List<StudentResponseDTO> reportList;

    public void setStudentList(List<StudentResponseDTO> studentList) {
        this.studentList = studentList;
    }

    public void setReportList(List<StudentResponseDTO> reportList) {
        this.reportList = reportList;
    }
    
    public void displayStudentList(){
        for(StudentResponseDTO dto : studentList){
            System.out.println(dto);
        }
    }
    
    public void displayReportList(){
        for(StudentResponseDTO dto : reportList){
            System.out.println(dto);
        }
    }
}
