
    import java.util.Scanner;
import java.util.Random;

    public class GuessTheNumber {
        private static final Scanner scanner = new Scanner(System.in);
        private static final Random random = new Random();

        private static int maxNumber = 100;
        private static int maxAttempts = 0;
        private static int bestScore = Integer.MAX_VALUE;

        public static void startGame() {
            boolean playAgain = true;

            while (playAgain) {
                configureGame();
                playGame();
                playAgain = askToPlayAgain();
            }

            System.out.println("Спасибо за игру! До свидания!");
            scanner.close();
        }

        public static void configureGame() {
            System.out.println("\n--- Настройка игры ---");

            // Уровни сложности
            System.out.println("Выберите уровень сложности:");
            System.out.println("1 - Легкий (1-50)");
            System.out.println("2 - Средний (1-100)");
            System.out.println("3 - Сложный (1-500)");

            int difficulty = readInt("Ваш выбор (1-3): ", 1, 3);

            switch (difficulty) {
                case 1: maxNumber = 50; break;
                case 2: maxNumber = 100; break;
                case 3: maxNumber = 500; break;
            }

            // Ограничение попыток
            System.out.println("\nОграничить количество попыток?");
            System.out.println("1 - Без ограничений");
            System.out.println("2 - С ограничением");
            int limitChoice = readInt("Ваш выбор (1-2): ", 1, 2);

            if (limitChoice == 2) {
                maxAttempts = readInt("Введите максимальное количество попыток: ", 1, 100);
            } else {
                maxAttempts = 0;
            }
        }

        public static void playGame() {
            int secretNumber = random.nextInt(maxNumber) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\n--- Игра началась! ---");
            System.out.println("Я загадал число от 1 до " + maxNumber);
            if (maxAttempts > 0) {
                System.out.println("У вас " + maxAttempts + " попыток!");
            }

            while (!guessed && (maxAttempts == 0 || attempts < maxAttempts)) {
                attempts++;
                System.out.print("\nПопытка №" + attempts + ". Введите число: ");

                int guess = readInt("", 1, maxNumber);

                if (guess == secretNumber) {
                    guessed = true;
                    System.out.println(" Вы угадали! Загаданное число: " + secretNumber);
                    System.out.println("Количество попыток: " + attempts);

                    // Обновление рекорда
                    if (attempts < bestScore) {
                        bestScore = attempts;
                        System.out.println(" Новый рекорд! Лучший результат: " + bestScore + " попыток");
                    } else {
                        System.out.println("Ваш лучший результат: " + bestScore + " попыток");
                    }
                } else if (guess < secretNumber) {
                    System.out.println("⬆ Слишком маленькое");
                } else {
                    System.out.println("⬇ Слишком большое");
                }

                // Проверка исчерпания попыток
                if (!guessed && maxAttempts > 0 && attempts >= maxAttempts) {
                    System.out.println("\n Попытки закончились! Загаданное число было: " + secretNumber);
                    break;
                }
            }
        }

        public static int readInt(String prompt, int min, int max) {
            while (true) {
                try {
                    System.out.print(prompt);
                    String input = scanner.nextLine().trim();

                    if (input.isEmpty()) {
                        System.out.println("Ошибка: ввод не может быть пустым!");
                        continue;
                    }

                    int value = Integer.parseInt(input);

                    if (value < min || value > max) {
                        System.out.println("Ошибка: число должно быть от " + min + " до " + max + "!");
                        continue;
                    }

                    return value;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число!");
                }
            }
        }

        public static boolean askToPlayAgain() {
            while (true) {
                System.out.print("\nХотите сыграть ещё раз? (y/n): ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("y") || input.equals("yes") || input.equals("д") || input.equals("да")) {
                    return true;
                } else if (input.equals("n") || input.equals("no") || input.equals("н") || input.equals("нет")) {
                    return false;
                } else {
                    System.out.println("Пожалуйста, введите 'y' (да) или 'n' (нет)");
                }
            }
        }
    }

