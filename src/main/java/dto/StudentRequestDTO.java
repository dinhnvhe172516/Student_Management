package dto;

public class StudentRequestDTO {
    private String id;
    private String name;
    private CourseDTO course;

    public StudentRequestDTO() {
        this.course = new CourseDTO();
    }

    public StudentRequestDTO(String id, String name, CourseDTO course) {
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
}
