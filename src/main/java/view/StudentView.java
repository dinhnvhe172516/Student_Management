package view;

import dto.StudentResponseDTO;
import java.util.List;

public class StudentView {
    private List<StudentResponseDTO> studentList;
    private List<StudentResponseDTO> reportList;

    public void setStudentList(List<StudentResponseDTO> studentList) {
        this.studentList = studentList;
    }

    public void setReportList(List<StudentResponseDTO> reportList) {
        this.reportList = reportList;
    }

    public void displayStudentList() {
        for (StudentResponseDTO dto : studentList) {
            System.out.println(dto);
        }
    }

    public void displayReport() {
        for (StudentResponseDTO dto : reportList) {
            System.out.println(dto);
        }
    }
}
