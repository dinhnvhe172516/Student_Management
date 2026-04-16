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
    
    // Retrieve the total number of student enrollments
    public int getStudentSize() {
        return service.getStudentSize();
    }

    // Handle adding a new student enrollment
    public void addStudent(StudentRequestDTO requestDTO) throws Exception {
        service.addStudent(requestDTO);
    }

    // Handle updating an existing student passing through the service view
    public void updateStudent(StudentRequestDTO requestDTO) throws Exception {
        service.updateStudent(requestDTO);
    }

    // Handle deleting a student record entirely
    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception {
        service.deleteStudent(requestDTO);
    }

    // Pass a search query to the service and instruct the view to display the results
    public void searchStudent(String input) throws Exception {
        List<StudentResponseDTO> result = service.searchStudent(input);
        view.setStudentList(result);
        view.displayStudentList();
    }

    // Produce an aggregated report and send it to the view
    public void report() throws Exception {
        List<StudentResponseDTO> result = service.report();
        view.setReportList(result);
        view.displayReport();
    }
}
