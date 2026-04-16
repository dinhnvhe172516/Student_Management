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

    public int getStudentSize() {
        return repository.size();
    }

    public void addStudent(StudentRequestDTO requestDTO) throws Exception {
        if (repository.isDuplicate(requestDTO)) {
            throw new Exception(Message.DUPLICATE);
        }
        repository.addStudent(requestDTO);
    }

    public void updateStudent(StudentRequestDTO requestDTO) throws Exception {
        if (!repository.isExistStudent(requestDTO.getId())) {
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.updateStudent(requestDTO);
    }

    public void deleteStudent(StudentRequestDTO requestDTO) throws Exception {
        if (!repository.isExistStudent(requestDTO.getId())) {
            throw new Exception(Message.NO_STUDENT_AVAILABLE);
        }
        repository.deleteStudent(requestDTO);
    }

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

    public List<StudentResponseDTO> report() throws Exception {
        if (repository.isEmpty()) {
            throw new Exception(Message.DATABASE_EMPTY);
        }
        return repository.report();
    }
}
