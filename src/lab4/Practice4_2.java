package lab4;

/*
 * ЗАВДАННЯ 32:
 * Знайти суму парних елементів масиву,
 * що стоять на непарних місцях (індекси 1, 3, 5...).
 */

import java.util.Arrays;

public class Practice4_2 {

    public static void main(String[] args) {
        System.out.println("--- Тестування Practice4_2 (Сума парних на непарних місцях) ---");

        // 1. Нормальний випадок:
        int[] array1 = {5, 4, 3, 8, 1, 6};
        testCalculateSpecialSum(array1);

        // 2. Випадок, коли підходящих елементів немає
        int[] array2 = {10, 1, 20, 3};
        testCalculateSpecialSum(array2);

        // 3. Заборонений випадок (Null)
        testCalculateSpecialSum(null);
    }

    private static void testCalculateSpecialSum(int[] array) {
        try {
            System.out.println("Вхідний масив: " + Arrays.toString(array));
            int result = sumEvenElementsAtOddIndices(array);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.toString());
        }
        System.out.println();
    }

    /**
     * Метод знаходить суму парних елементів, що стоять на непарних індексах (1, 3, 5...).
     * Використовує класичний цикл for.
     */
    public static int sumEvenElementsAtOddIndices(int[] array) {
        if (array == null) {
            throw new NullPointerException("Масив не може бути null");
        }

        int sum = 0;
        // Проходимо по масиву. i += 2 дозволяє переходити одразу по непарних індексах (1, 3, 5...)
        for (int i = 1; i < array.length; i += 2) {
            // Перевіряємо, чи елемент є парним
            if (array[i] % 2 == 0) {
                sum += array[i];
            }
        }
        return sum;
    }
}