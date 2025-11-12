package lab4;

/*
 * ЗАВДАННЯ 62:
 * Сформувати новий масив:
 * 1. Знайти мінімальний елемент.
 * 2. Якщо min != 0 -> поділити всі елементи на нього.
 * 3. Якщо min == 0 -> замінити всі елементи на -10.
 */

import java.util.Arrays;

public class Practice4_3 {

    public static void main(String[] args) {
        System.out.println("--- Тестування Practice4_3 (Обробка через мінімум) ---");

        // 1. Нормальний випадок (мінімум = 2)
        int[] array1 = {10, 4, 2, 8};
        testProcessArray(array1);

        // 2. Випадок з нулем (мінімум = 0) -> Всі мають стати -10
        int[] arrayWithZero = {5, 10, 0, 3};
        testProcessArray(arrayWithZero);

        // 3. Заборонений випадок (порожній масив)
        int[] emptyArray = {};
        testProcessArray(emptyArray);

        // 4. Заборонений випадок (null)
        testProcessArray(null);
    }

    private static void testProcessArray(int[] array) {
        try {
            System.out.println("Вхідний масив: " + Arrays.toString(array));
            double[] result = processArrayByMin(array);
            System.out.println("Новий масив: " + Arrays.toString(result));
        } catch (Exception e) {
            System.out.println("Помилка: " + e.toString());
        }
        System.out.println();
    }

    /**
     * Якщо мін. елемент != 0: ділить всі елементи на мінімальний.
     * Якщо мін. елемент == 0: замінює всі елементи на -10.
     * Повертає масив double, щоб зберегти точність ділення.
     */
    public static double[] processArrayByMin(int[] array) {
        // Перевірка на null
        if (array == null) {
            throw new NullPointerException("Масив не ініціалізовано (null)");
        }
        // Перевірка на порожній масив (неможливо знайти мінімум)
        if (array.length == 0) {
            throw new IllegalArgumentException("Масив порожній, неможливо знайти мінімум");
        }

        // 1. Шукаємо мінімальний елемент
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        // 2. Формуємо новий масив
        double[] resultArray = new double[array.length];

        if (min == 0) {
            // Якщо мінімум 0, заповнюємо масив числами -10
            Arrays.fill(resultArray, -10);
        } else {
            // Якщо мінімум не 0, ділимо кожен елемент на мінімум
            for (int i = 0; i < array.length; i++) {
                resultArray[i] = (double) array[i] / min;
            }
        }

        return resultArray;
    }
}