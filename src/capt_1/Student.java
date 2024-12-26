package capt_1;

public class Student {

        //统计班级学生人数
        private int studentNumber=0;
        private String id;
        private String name;
        private int age;
        private String sex;
        private Clazz clazz;
        private ClassTeacher classTeacher;
        public Student(String id, String name, int age, String sex) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(int studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Clazz getClazz() {
            return clazz;
        }

        public void setClazz(Clazz clazz) {
            this.clazz = clazz;
        }

        public ClassTeacher getClassTeacher() {
            return classTeacher;
        }

        public void setClassTeacher(ClassTeacher classTeacher) {
            this.classTeacher = classTeacher;
        }
        public String showInfo(){
            return "学生信息：系统编号"+studentNumber+"学号："+id+",姓名："+name+"性别: "+sex+",年龄："+age;
        }

    public String showinfo() {
        return "学号: " + id + ", 姓名: " + name + ", 年龄: " + age + ", 性别: " + sex;
    }
}

