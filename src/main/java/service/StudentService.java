package service;

import constants.Message;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.util.List;
import repository.StudentRepository;

public class StudentService {
    private StudentRepository repository;

    public StudentService() {
        this.repository = new StudentRepository();
    }

    // Get the current total number of student courses
    public int getStudentSize() {
        return repository.size();
    }

    // Check for duplicates before adding a new student to the system
    public void addStudent(StudentRequestDTO requestDTO) throws Exception {
        if (repository.isDuplicate(requestDTO)) {
            throw new Exception(Message.DUPLICATE);
        }
        repository.addStudent(requestDTO);
    }

    // Verify existence of a student before performing an update
    public void updateStudent(StudentRequestDTO requestDTO) throws Exception {
        if (!repository.isExistStudent(requestDTO.getId())) {
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.updateStudent(requestDTO);
    }

    // Ensure a student exists before allowing deletion
    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception {
        if (!repository.isExistStudent(requestDTO.getId())) {
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.deleteStudent(requestDTO);
    }

    // Return a list of students matching the search, throwing exceptions if not found
    public List<StudentResponseDTO> searchStudent(String input) throws Exception {
        if (repository.isEmpty()) {
            throw new Exception(Message.DATABASE_EMPTY);
        }
        List<StudentResponseDTO> result = repository.searchStudent(input);
        if (result == null || result.isEmpty()) {
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        return result;
    }

    // Fetch the counted report, checking if the database is initially empty
    public List<StudentResponseDTO> report() throws Exception {
        if (repository.isEmpty()) {
            throw new Exception(Message.DATABASE_EMPTY);
        }
        return repository.report();
    }
}
