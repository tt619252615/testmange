package capt_1;

import java.io.*;
import java.util.Scanner;

public class Menu {
    private Clazz clazz;
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
        // 初始化班级，不设置默认教师
        clazz = new Clazz("计算机科学与技术", "2021", 50);
    }

    public void showMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("欢迎使用班级信息管理系统!");
            System.out.println("【1】班级信息");
            System.out.println("【2】学生管理");
            System.out.println("【3】教师管理");
            System.out.println("【4】保存学生信息");
            System.out.println("【5】保存教师信息");
            System.out.println("【6】读取文件");
            System.out.println("【7】退出系统");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    showClazzInfo();
                    break;
                case 2:
                    showStudentSubMenu();
                    break;
                case 3:
                    showTeacherSubMenu();
                    break;
                case 4:
                    saveStudentsToFile();
                    break;
                case 5:
                    saveTeacherToFile();
                    break;
                case 6:
                    readStudentsFromFile();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    private void showClazzInfo() {
        System.out.println("\n班级信息：");
        System.out.println("班级名称：" + clazz.getName());
        System.out.println("年级：" + clazz.getGrade());
        System.out.println("最大人数：" + clazz.getMaxStudents());
        System.out.println("当前学生人数：" + clazz.getStudentIndex());
        
        // 显示班主任信息
        ClassTeacher teacher = clazz.getClassTeacher();
        if (teacher != null) {
            System.out.println("\n班主任信息：");
            System.out.println("姓名：" + teacher.getName());
            System.out.println("年龄：" + teacher.getAge());
            System.out.println("性别：" + teacher.getSex());
        } else {
            System.out.println("\n暂无班主任信息");
        }
        System.out.println(); // 打印空行
    }

    private void showStudentSubMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("二级菜单(学生管理菜单):");
            System.out.println("【1】学生信息");
            System.out.println("【2】添加学生");
            System.out.println("【3】删除学生");
            System.out.println("【4】修改学生");
            System.out.println("【5】根据学号查询");
            System.out.println("【6】返回上级菜单");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    showStudentInfo();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    queryStudentById();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    private void showTeacherSubMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("二级菜单(教师管理菜单):");
            System.out.println("【1】教师信息");
            System.out.println("【2】添加教师");
            System.out.println("【3】删除教师");
            System.out.println("【4】修改教师信息");
            System.out.println("【5】根据教师姓名查询");
            System.out.println("【6】返回上级菜单");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    showTeacherInfo();
                    break;
                case 2:
                    addTeacher();
                    break;
                case 3:
                    deleteTeacher();
                    break;
                case 4:
                    updateTeacher();
                    break;
                case 5:
                    queryTeacherByName();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    private void showStudentInfo() {
        Student[] students = clazz.getStudents();
        boolean hasStudents = false;
        for (Student student : students) {
            if (student != null) {
                System.out.println(student.showinfo());
                hasStudents = true;
            }
        }
        if (!hasStudents) {
            System.out.println("当前没有学生信息。");
        }
    }

    private void addStudent() {
        try {
            System.out.print("请输入学号: ");
            String id = scanner.nextLine();
            InputValidator.validateId(id);

            System.out.print("请输入姓名: ");
            String name = scanner.nextLine();

            System.out.print("请输入年龄: ");
            int age = scanner.nextInt();
            InputValidator.validateAge(age);
            scanner.nextLine(); // 消耗换行符

            System.out.print("请输入性别: ");
            String sex = scanner.nextLine();
            InputValidator.validateSex(sex);

            Student student = new Student(id, name, age, sex);
            student.setClazz(clazz);
            student.setClassTeacher(clazz.getClassTeacher());
            clazz.addStudent(student);
            System.out.println("学生添加成功。");
        } catch (InvalidInputException e) {
            System.out.println("添加学生失败: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        System.out.print("请输入要删除的学生学号: ");
        String id = scanner.nextLine();
        Student[] students = clazz.getStudents();
        for (int i = 0; i < clazz.getStudentIndex(); i++) {
            if (id.equals(students[i].getId())) {
                // 将后面的学生向前移动一位
                for (int j = i; j < clazz.getStudentIndex() - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[clazz.getStudentIndex() - 1] = null;
                clazz.setStudentIndex(clazz.getStudentIndex() - 1);
                System.out.println("学生删除成功。");
                return;
            }
        }
        System.out.println("未找到该学号的学生。");
    }

    private void updateStudent() {
        System.out.print("请输入要修改的学生学号: ");
        String id = scanner.nextLine();
        Student[] students = clazz.getStudents();
        for (int i = 0; i < clazz.getStudentIndex(); i++) {
            if (id.equals(students[i].getId())) {
                try {
                    System.out.print("请输入新的姓名: ");
                    String name = scanner.nextLine();
                    students[i].setName(name);

                    System.out.print("请输入新的年龄: ");
                    int age = scanner.nextInt();
                    InputValidator.validateAge(age);
                    scanner.nextLine(); // 消耗换行符
                    students[i].setAge(age);

                    System.out.print("请输入新的性别: ");
                    String sex = scanner.nextLine();
                    InputValidator.validateSex(sex);
                    students[i].setSex(sex);

                    System.out.println("学生信息修改成功。");
                } catch (InvalidInputException e) {
                    System.out.println("修改学生信息失败: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("未找到该学号的学生。");
    }

    private void queryStudentById() {
        try {
            System.out.print("请输入要查询的学生学号: ");
            String id = scanner.nextLine().trim();
            
            if (id.isEmpty()) {
                System.out.println("���号不能为空！");
                return;
            }
            
            Student[] students = clazz.getStudents();
            boolean found = false;
            
            for (int i = 0; i < clazz.getStudentIndex(); i++) {
                Student student = students[i];
                if (student != null && student.getId().equals(id)) {
                    System.out.println("\n找到学生信息：");
                    String info = student.showinfo();
                    if (info != null) {
                        System.out.println(info);
                    } else {
                        System.out.println("学生信息格式错误");
                    }
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("未找到学号为 '" + id + "' 的学生。");
            }
        } catch (Exception e) {
            System.out.println("查询学生信息时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 教师信息显示方法
    private void showTeacherInfo() {
        try {
            ClassTeacher teacher = clazz.getClassTeacher();
            if (teacher != null) {
                System.out.println("\n当前教师信息：");
                System.out.println("姓名: " + teacher.getName());
                System.out.println("年龄: " + teacher.getAge());
                System.out.println("性别: " + teacher.getSex());
                System.out.println();
            } else {
                System.out.println("当前未设置教师信息。\n");
            }
        } catch (Exception e) {
            System.out.println("显示教师信息时发生错误: " + e.getMessage());
        }
    }

    // 添加教师方法
    private void addTeacher() {
        try {
            // 检查是否已有教师
            if (clazz.getClassTeacher() != null) {
                System.out.println("已存在班主任，如需修改请使用修改功能。");
                return;
            }

            System.out.print("请输入教师姓名: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("教师姓名不能为空！");
                return;
            }

            System.out.print("请输入教师年龄: ");
            int age;
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("年龄必须是有效的数字！");
                return;
            }

            System.out.print("请输入教师性别: ");
            String sex = scanner.nextLine().trim();

            // 验证输入
            InputValidator.validateAge(age);
            InputValidator.validateSex(sex);

            ClassTeacher newTeacher = new ClassTeacher(name, age, sex);
            clazz.setClassTeacher(newTeacher);
            System.out.println("教师添加成功。");
            
        } catch (InvalidInputException e) {
            System.out.println("添加教师失败: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("发生未知错误: " + e.getMessage());
        }
    }

    // 删除教师方法
    private void deleteTeacher() {
        try {
            ClassTeacher teacher = clazz.getClassTeacher();
            if (teacher == null) {
                System.out.println("当前没有教师信息，无需删除。");
                return;
            }

            System.out.print("确认要删除教师 '" + teacher.getName() + "' 的信息吗？(y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("y")) {
                clazz.setClassTeacher(null);
                System.out.println("教师信息已成功删除。");
            } else {
                System.out.println("取消删除操作。");
            }
        } catch (Exception e) {
            System.out.println("删除过程发生错误: " + e.getMessage());
        }
    }

    // 修改教师信息方法
    private void updateTeacher() {
        ClassTeacher teacher = clazz.getClassTeacher();
        if (teacher != null) {
            try {
                System.out.print("请输入新的教师姓名: ");
                String name = scanner.nextLine();
                teacher.setName(name);

                System.out.print("请输入新的教师年龄: ");
                int age = scanner.nextInt();
                InputValidator.validateAge(age);
                scanner.nextLine(); // 消耗换行符
                teacher.setAge(age);

                System.out.print("请输入新的教师性别: ");
                String sex = scanner.nextLine();
                InputValidator.validateSex(sex);
                teacher.setSex(sex);

                System.out.println("教师信息修改成功。");
            } catch (InvalidInputException e) {
                System.out.println("修改教师信息失败: " + e.getMessage());
            }
        } else {
            System.out.println("未设置教师信息，请先添加教师。");
        }
    }

    // 根据教师姓名查询方法
    private void queryTeacherByName() {
        try {
            System.out.print("请输入要查询的教师姓名: ");
            String name = scanner.nextLine().trim();  // 添加trim()去除空白字符
            
            if (name.isEmpty()) {
                System.out.println("教师姓名不能为空！");
                return;
            }
            
            ClassTeacher teacher = clazz.getClassTeacher();
            if (teacher != null && teacher.getName().trim().equalsIgnoreCase(name)) {  // 使用equalsIgnoreCase进行不区分大小写的比较
                System.out.println("\n找到教师信息：");
                System.out.println("姓名: " + teacher.getName());
                System.out.println("年龄: " + teacher.getAge());
                System.out.println("性别: " + teacher.getSex());
            } else {
                System.out.println("未找到姓名为 '" + name + "' 的教师。");
            }
        } catch (Exception e) {
            System.out.println("查询过程发生错误: " + e.getMessage());
        }
    }

    private void saveStudentsToFile() {
        Student[] students = clazz.getStudents();
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                if (student!= null) {
                    writer.println(student.getId() + "," + student.getName() + "," + student.getAge() + "," + student.getSex());
                }
            }
            System.out.println("学生信息保存成功。");
        } catch (IOException e) {
            System.out.println("保存学生信息失败: " + e.getMessage());
        }
    }

    private void saveTeacherToFile() {
        ClassTeacher teacher = clazz.getClassTeacher();
        try (PrintWriter writer = new PrintWriter(new FileWriter("teacher.txt"))) {
            if (teacher!= null) {
                writer.println(teacher.getName() + "," + teacher.getAge() + "," + teacher.getSex());
                System.out.println("教师信息保存成功。");
            } else {
                System.out.println("未设置教师信息，无需保存。");
            }
        } catch (IOException e) {
            System.out.println("保存教师信息失败: " + e.getMessage());
        }
    }

    private void readStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String sex = parts[3];
                    try {
                        InputValidator.validateId(id);
                        InputValidator.validateAge(age);
                        InputValidator.validateSex(sex);
                        Student student = new Student(id, name, age, sex);
                        student.setClazz(clazz);
                        student.setClassTeacher(clazz.getClassTeacher());
                        clazz.addStudent(student);
                    } catch (InvalidInputException e) {
                        System.out.println("读取学生信息失败: " + e.getMessage() + " (数据行: " + line + ")");
                    }
                } else {
                    System.out.println("读取学生信息失败: 数据格式不正确 (数据行: " + line + ")");
                }
            }
            System.out.println("学生信息读取成功。");
        } catch (FileNotFoundException e) {
            System.out.println("未找到学生信息文件。");
        } catch (IOException e) {
            System.out.println("读取学生信息失败: " + e.getMessage());
        }
    }
}