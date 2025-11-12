package lab6;

public class Practice6_1 {

    public static void main(String[] args) {
        System.out.println("--- Тест 1: Валідні значення ---");
        try {
            System.out.println("21 у двійковій: " + intToBinaryString(21));
            System.out.println("0 у двійковій: " + intToBinaryString(0));
            System.out.println("255 у двійковій: " + intToBinaryString(255));
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        }

        System.out.println("\n--- Тест 2: Невалідні значення (від'ємне число) ---");
        try {
            // Це має викликати виключення
            System.out.println("-5 у двійковій: " + intToBinaryString(-5));
        } catch (IllegalArgumentException e) {
            System.err.println("Перехоплено очікувану помилку: " + e.getMessage());
        }
    }


    public static String intToBinaryString(int i) {
        // Перевірка аргументів
        if (i < 0) {
            throw new IllegalArgumentException("Аргумент має бути невід'ємним числом для цього алгоритму.");
        }

        if (i == 0) {
            return "0";
        }

        StringBuilder binaryBuilder = new StringBuilder();
        int current = i;

        while (current > 0) {
            int remainder = current % 2;
            binaryBuilder.append(remainder);
            current = current / 2;
        }

        // Результат записується у зворотному порядку, тому потрібно перевернути рядок
        return binaryBuilder.reverse().toString();
    }
}