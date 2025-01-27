import java.util.Scanner;

public class PasswordChecker {
    private Integer minLength;
    private Integer maxRepeats;

    // Установка минимальной длины пароля
    public void setMinLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("Минимальная длина пароля не может быть отрицательной.");
        }
        this.minLength = minLength;
    }

    // Установка максимально допустимого количества повторений символа подряд
    public void setMaxRepeats(int maxRepeats) {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException("Максимальное количество повторений символа подряд должно быть положительным.");
        }
        this.maxRepeats = maxRepeats;
    }

    // Проверка пароля
    public boolean verify(String password) {
        if (minLength == null || maxRepeats == null) {
            throw new IllegalStateException("Настройки проверки пароля не установлены.");
        }

        if (password.length() < minLength) {
            return false;
        }

        int repeatCount = 1;
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                repeatCount++;
                if (repeatCount > maxRepeats) {
                    return false;
                }
            } else {
                repeatCount = 1;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordChecker checker = new PasswordChecker();

        try {
            System.out.print("Введите минимальную длину пароля: ");
            int minLength = Integer.parseInt(scanner.nextLine());
            checker.setMinLength(minLength);

            System.out.print("Введите максимально допустимое количество повторений символа подряд: ");
            int maxRepeats = Integer.parseInt(scanner.nextLine());
            checker.setMaxRepeats(maxRepeats);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return;
        }

        System.out.println("Введите пароли для проверки (для выхода введите 'end'):");
        while (true) {
            String input = scanner.nextLine();
            if ("end".equalsIgnoreCase(input)) {
                break;
            }

            try {
                boolean isValid = checker.verify(input);
                System.out.println("Пароль " + (isValid ? "принят" : "не принят"));
            } catch (IllegalStateException e) {
                System.out.println("Ошибка: " + e.getMessage());
                break;
            }
        }

        scanner.close();
    }
}
