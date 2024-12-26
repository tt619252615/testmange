package capt_1;

// import capt_1.InvalidInputException;

import java.util.regex.Pattern;

//格式监测
public class InputValidator {
    public static void validateId(String id) throws InvalidInputException {
        String idRegex = "^AI\\d{4}$";
        if (!Pattern.matches(idRegex, id)) {
            throw new InvalidInputException("学号格式不正确，应以 'AI' 开头，后面跟4位数字，总长度为6位。");
        }
    }

    public static void validateAge(int age) throws InvalidInputException {
        if (age < 0 || age > 100) {
            throw new InvalidInputException("年龄应介于0 - 100之间。");
        }
    }

    public static void validateSex(String sex) throws InvalidInputException {
        if (!"男".equals(sex) && !"女".equals(sex)) {
            throw new InvalidInputException("性别只能输入 '男' 或者 '女'。");
        }
    }

    public static void validateBirthday(String birthday) throws InvalidInputException {
        String birthdayRegex = "^\\d{4}\\.\\d{2}\\.\\d{2}$";
        if (!Pattern.matches(birthdayRegex, birthday)) {
            throw new InvalidInputException("生日格式不正确，应为 'XXXX.XX.XX' 形式。");
        }
    }
}