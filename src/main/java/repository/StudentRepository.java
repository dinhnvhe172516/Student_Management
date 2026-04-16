package repository;

import dto.CourseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;
import model.Student;

public class StudentRepository {
    private List<Student> list = new ArrayList<>();

    public StudentRepository() {
        // Initialize 9 mock students
        Student s1 = new Student("S1", "Nguyen Van A");
        s1.getCourses().add(new Course("Spring", "Java"));
        list.add(s1);

        Student s2 = new Student("S2", "Tran Thi B");
        s2.getCourses().add(new Course("Summer", ".Net"));
        list.add(s2);

        Student s3 = new Student("S3", "Le Van C");
        s3.getCourses().add(new Course("Fall", "C/C++"));
        list.add(s3);

        Student s4 = new Student("S4", "Pham Thi D");
        s4.getCourses().add(new Course("Spring", "Java"));
        list.add(s4);

        Student s5 = new Student("S5", "Hoang Van E");
        s5.getCourses().add(new Course("Summer", ".Net"));
        list.add(s5);

        Student s6 = new Student("S6", "Vu Thi F");
        s6.getCourses().add(new Course("Fall", "C/C++"));
        list.add(s6);

        Student s7 = new Student("S7", "Ngo Van G");
        s7.getCourses().add(new Course("Spring", "Java"));
        list.add(s7);

        Student s8 = new Student("S8", "Do Thi H");
        s8.getCourses().add(new Course("Summer", ".Net"));
        list.add(s8);

        Student s9 = new Student("S9", "Bui Van I");
        s9.getCourses().add(new Course("Fall", "C/C++"));
        list.add(s9);
    }

    public boolean isDuplicate(StudentRequestDTO requestDTO) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(requestDTO.getId())) {
                for (Course c : s.getCourses()) {
                    if (c.getSemester().equalsIgnoreCase(requestDTO.getCourse().getSemester())
                            && c.getCourseName().equalsIgnoreCase(requestDTO.getCourse().getCourseName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addStudent(StudentRequestDTO requestDTO) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(requestDTO.getId())) {
                s.getCourses().add(new Course(requestDTO.getCourse().getSemester(), requestDTO.getCourse().getCourseName()));
                return;
            }
        }
        Student newStudent = new Student(requestDTO.getId(), requestDTO.getName());
        newStudent.getCourses().add(new Course(requestDTO.getCourse().getSemester(), requestDTO.getCourse().getCourseName()));
        list.add(newStudent);
    }

    public boolean isExistStudent(String id) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public void updateStudent(StudentRequestDTO updateDto) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(updateDto.getId())) {
                s.setName(updateDto.getName());
                if (!s.getCourses().isEmpty()) {
                    s.getCourses().get(0).setSemester(updateDto.getCourse().getSemester());
                    s.getCourses().get(0).setCourseName(updateDto.getCourse().getCourseName());
                } else {
                    s.getCourses().add(new Course(updateDto.getCourse().getSemester(), updateDto.getCourse().getCourseName()));
                }
                break;
            }
        }
    }

    public void deleteStudent(StudentRequestDTO deleteDto) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(deleteDto.getId())) {
                list.remove(i);
                break;
            }
        }
    }

    public List<StudentResponseDTO> searchStudent(String name) {
        List<StudentResponseDTO> result = new ArrayList<>();
        for (Student s : list) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                for (Course c : s.getCourses()) {
                    result.add(new StudentResponseDTO(s.getId(), s.getName(), new CourseDTO(c.getSemester(), c.getCourseName())));
                }
            }
        }
        result.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    public List<StudentResponseDTO> report() {
        Map<String, Integer> map = new HashMap<>(); // key: "name|courseName"
        for (Student s : list) {
            for (Course c : s.getCourses()) {
                String key = s.getName() + "|" + c.getCourseName();
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        List<StudentResponseDTO> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] parts = entry.getKey().split("\\|");
            StudentResponseDTO dto = new StudentResponseDTO();
            dto.setName(parts[0]);
            CourseDTO cDto = new CourseDTO();
            cDto.setCourseName(parts[1]);
            dto.setCourse(cDto);
            dto.setTotalCourse(entry.getValue());
            result.add(dto);
        }
        return result;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        int count = 0;
        for (Student s : list) {
            count += s.getCourses().size();
        }
        return count;
    }
}
