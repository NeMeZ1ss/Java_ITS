package lab4;

/*
 * ЗАВДАННЯ 2:
 * Знайти суму елементів масиву, значення яких більше 3.
*/

import java.util.Arrays;

public class Practice4_1 {

    public static void main(String[] args) {
        System.out.println("--- Тестування Practice4_1 (Сума елементів > 3) ---");

        // 1. Нормальний випадок
        int[] array1 = {1, 5, 2, 4, 8, 0};
        testCalculateSum(array1);

        // 2. Випадок, де немає чисел більше 3
        int[] array2 = {1, 2, 3, 0, -5};
        testCalculateSum(array2);

        // 3. Заборонений випадок (Null)
        int[] arrayNull = null;
        testCalculateSum(arrayNull);
    }


    private static void testCalculateSum(int[] array) {
        try {
            System.out.println("Вхідний масив: " + Arrays.toString(array));
            int result = sumGreaterThanThree(array);
            System.out.println("Результат (сума > 3): " + result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.toString());
        }
        System.out.println();
    }

    /**
     * Метод знаходить суму елементів масиву, значення яких більше 3.
     * Використовує цикл for-each.
     */
    public static int sumGreaterThanThree(int[] array) {
        // Перевірка на null перед початком роботи
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }

        int sum = 0;
        // Використання циклу for-each
        for (int number : array) {
            if (number > 3) {
                sum += number;
            }
        }
        return sum;
    }
}