package dto;

public class StudentResponseDTO {
    private String id;
    private String name;
    private CourseDTO course;
    private int totalCourse;

    public StudentResponseDTO() {
        this.course = new CourseDTO();
    }

    public StudentResponseDTO(String id, String name, CourseDTO course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public CourseDTO getCourse() { return course; }
    public void setCourse(CourseDTO course) { this.course = course; }
    
    public int getTotalCourse() { return totalCourse; }
    public void setTotalCourse(int totalCourse) { this.totalCourse = totalCourse; }

    @Override
    public String toString() {
        if (totalCourse > 0) {
            return name + " | " + course.getCourseName() + " | " + totalCourse;
        }
        return name + " | " + course.getSemester() + " | " + course.getCourseName();
    }
}
