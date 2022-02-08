package course;

public class CourseModel {
    private int id;
    private String courseName;
    private int coursePrice;

    public CourseModel() {
    }

    public CourseModel(String courseName, int coursePrice) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }
}
