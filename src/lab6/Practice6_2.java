package lab6;

public class Practice6_2 {

    public static void main(String[] args) {
        System.out.println("--- Тест 1: Приклад із завдання ---");
        String text1 = "The user with the nickname koala757677 this month wrote 3 times more comments than the user with the nickname croco181dile920 4 months ago";
        try {
            int count = countNumbers(text1);
            System.out.println("Речення: " + text1);
            System.out.println("Кількість чисел: " + count);
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }

        System.out.println("\n--- Тест 2: Рядок тільки з чисел ---");
        String text2 = "10 20 30";
        try {
            System.out.println("Кількість чисел у '10 20 30': " + countNumbers(text2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Тест 3: Невалідні значення (null) ---");
        try {
            countNumbers(null);
        } catch (NullPointerException e) {
            System.err.println("Перехоплено очікувану помилку: " + e.getMessage());
        }
    }


    public static int countNumbers(String sentence) {
        // Перевірка на null
        if (sentence == null) {
            throw new NullPointerException("Вхідний рядок не може бути null.");
        }

        if (sentence.trim().isEmpty()) {
            return 0;
        }

        // Розбиваємо речення на слова по пробілах
        String[] words = sentence.split("\\s+");
        int count = 0;

        for (String word : words) {
            if (isNumeric(word)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Допоміжний метод для перевірки, чи складається рядок виключно з цифр.
     */
    private static boolean isNumeric(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        // Використовуємо toCharArray згідно з завданням
        char[] chars = word.toCharArray();

        for (char c : chars) {
            // Якщо хоча б один символ не є цифрою, слово не є чистим числом
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}