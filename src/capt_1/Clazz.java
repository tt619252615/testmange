package capt_1;

public class Clazz {
    private String name;
    private String grade;
    private int maxStudents;
    private Student[] students;
    private int studentIndex;
    private ClassTeacher classTeacher;

    public Clazz(String name, String grade, int maxStudents) {
        this.name = name;
        this.grade = grade;
        this.maxStudents = maxStudents;
        this.students = new Student[maxStudents];
        this.studentIndex = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public int getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(int studentIndex) {
        this.studentIndex = studentIndex;
    }

    public void addStudent(Student student) {
        if (studentIndex < maxStudents) {
            students[studentIndex++] = student;
        } else {
            System.out.println("班级人数已满，无法添加学生。");
        }
    }

    public void ClassTeacher(ClassTeacher classTeacher) {
    }

    public ClassTeacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}