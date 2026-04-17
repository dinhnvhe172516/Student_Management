/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import dto.CourseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentRepository {

    private List<Student> list = new ArrayList<>();

    public List<Student> getList() {
        return list;
    }

    public void addStudent(StudentRequestDTO request) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(request.getId())) {
                s.getCourses().add(new Course(
                        request.getCourse().getSemester(),
                        request.getCourse().getCourse()
                ));
                return;
            }
        }

        Student newStudent = new Student(request.getId(), request.getName());
        newStudent.getCourses().add(new Course(
                request.getCourse().getSemester(),
                request.getCourse().getCourse()
        ));
        list.add(newStudent);
    }

    public int studentSize() {
        return list.size();
    }

    public List<StudentResponseDTO> searchStudent(String name) {
        List<StudentResponseDTO> result = new ArrayList<>();
        for (Student s : list) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                for (Course c : s.getCourses()) {
                    result.add(new StudentResponseDTO(s.getId(), s.getName(),
                            new CourseDTO(c.getSemester(), c.getCourseName())));
                }
            }
        }
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isExistedStudent(String id) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void updateStudent(StudentRequestDTO requestDTO) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(requestDTO.getId())) {
                s.setName(requestDTO.getName());
                if (!s.getCourses().isEmpty()) {
                    s.getCourses().get(0).setSemester(requestDTO.getCourse().getSemester());
                    s.getCourses().get(0).setCourseName(requestDTO.getCourse().getCourse());
                } else {
                    s.getCourses().add(new Course(requestDTO.getCourse().getSemester(),
                            requestDTO.getCourse().getCourse()));
                }
                break;
            }
        }
    }
    
    public void deleteStudent(StudentRequestDTO requestDTO){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equalsIgnoreCase(requestDTO.getId())){
                list.remove(i);
                break;
            }
        }
    }
    
    public List<StudentResponseDTO> reportList(){
        Map<String, Integer> map = new HashMap<>();
        for(Student s : list){
            for(Course c : s.getCourses()){
                String key = s.getName() + " | " + c.getCourseName();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        List<StudentResponseDTO> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String [] parts = entry.getKey().split("\\|");
            StudentResponseDTO dto = new StudentResponseDTO();
            dto.setName(parts[0]);
            CourseDTO cour = new CourseDTO();
            cour.setCourse(parts[1]);
            dto.setCourse(cour);
            dto.setTotalCourse(entry.getValue());
            result.add(dto);
        }
        return result;
    }
}
