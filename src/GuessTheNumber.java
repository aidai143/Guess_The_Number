import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    private int bestScore = Integer.MAX_VALUE;

    public void startGame() {
        System.out.println("=== Игра: Угадай число ===");

        while (true) {
            playRound();
            System.out.print("Хотите сыграть ещё раз? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();

            if (!again.equals("y")) {
                System.out.println("Спасибо за игру!");
                break;
            }
        }
    }

    public void playRound() {

        int maxNumber = chooseDifficulty();
        int maxAttempts = 10;

        int secret = rand.nextInt(maxNumber) + 1;
        int attempts = 0;

        System.out.println("Я загадал число от 1 до " + maxNumber + ".");
        System.out.println("У вас есть " + maxAttempts + " попыток.");

        while (true) {
            System.out.print("Введите число: ");
            int guess = readIntWithEmptyCheck();

            attempts++;

            if (guess > secret) {
                System.out.println("Слишком большое!");
            } else if (guess < secret) {
                System.out.println("Слишком маленькое!");
            } else {
                System.out.println("Вы угадали! Попыток: " + attempts);
                updateBestScore(attempts);
                break;
            }

            if (attempts >= maxAttempts) {
                System.out.println("Попытки закончились!");
                System.out.println("Правильный ответ: " + secret);
                break;
            }
        }
    }


    private void updateBestScore(int attempts) {
        if (attempts < bestScore) {
            bestScore = attempts;
            System.out.println("🎉 Новый рекорд! Лучший результат: " + bestScore);
        } else {
            System.out.println("Ваш лучший рекорд: " + bestScore);
        }
    }


    private int chooseDifficulty() {
        System.out.println("Выберите уровень сложности:");
        System.out.println("1 — Лёгкий (1–50)");
        System.out.println("2 — Средний (1–100)");
        System.out.println("3 — Сложный (1–500)");
        System.out.print("Ваш выбор: ");

        while (true) {
            int choice = readIntWithEmptyCheck();

            if (choice == 1) return 50;
            if (choice == 2) return 100;
            if (choice == 3) return 500;

            System.out.print("Введите 1, 2 или 3: ");
        }
    }


    public int readIntWithEmptyCheck() {
        while (true) {
            String input = sc.nextLine().trim();

            if (input.length() == 0) {
                System.out.print("Поле не может быть пустым! Введите число: ");
                continue;
            }

            boolean isNumber = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isNumber = false;
                    break;
                }
            }

            if (isNumber) {
                return Integer.parseInt(input);
            }

            System.out.print("Ошибка! Введите число: ");
        }
    }
}
