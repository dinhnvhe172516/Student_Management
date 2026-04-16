package controller;

import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.util.List;
import service.StudentService;
import view.StudentView;

public class StudentController {
    private StudentService service;
    private StudentView view;

    public StudentController() {
        service = new StudentService();
        view = new StudentView();
    }
    
    public int getStudentSize() {
        return service.getStudentSize();
    }

    public void addStudent(StudentRequestDTO requestDTO) throws Exception {
        service.addStudent(requestDTO);
    }

    public void updateStudent(StudentRequestDTO requestDTO) throws Exception {
        service.updateStudent(requestDTO);
    }

    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception {
        service.deleteStudent(requestDTO);
    }

    public void searchStudent(String input) throws Exception {
        List<StudentResponseDTO> result = service.searchStudent(input);
        view.setStudentList(result);
        view.displayStudentList();
    }

    public void report() throws Exception {
        List<StudentResponseDTO> result = service.report();
        view.setReportList(result);
        view.displayReport();
    }
}
