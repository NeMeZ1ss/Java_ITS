package lab5;

import java.util.Arrays; // Імпорт для зручного копіювання масиву

public class Practice5 {

    public static void main(String[] args) {
        // Вихідний масив типу short
        short[] originalArray = {5, 12, -3, 0, 8, 22, 1, -9};

        System.out.println("--- Початковий масив ---");
        printArray(originalArray);

        // 1. Тестуємо Selection Sort (Сортування вибором)
        // Робимо копію масиву, щоб не псувати оригінал для другого тесту
        short[] arrayForSelection = Arrays.copyOf(originalArray, originalArray.length);
        selectionSortDesc(arrayForSelection);

        System.out.println("\n--- Результат Selection Sort (по спаданню) ---");
        printArray(arrayForSelection);

        // 2. Тестуємо Insertion Sort (Сортування вставкою)
        short[] arrayForInsertion = Arrays.copyOf(originalArray, originalArray.length);
        insertionSortDesc(arrayForInsertion);

        System.out.println("\n--- Результат Insertion Sort (по спаданню) ---");
        printArray(arrayForInsertion);
    }

    // --- Метод 1: Сортування вибором (Selection Sort) ---
    public static void selectionSortDesc(short[] arr) {
        int n = arr.length;

        // Проходимо по всьому масиву
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i; // Припускаємо, що поточний елемент - найбільший

            // Шукаємо справжній найбільший елемент у решті масиву
            for (int j = i + 1; j < n; j++) {
                // шукаємо число, яке БІЛЬШЕ за поточний максимум
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            // Міняємо місцями поточний елемент (i) та знайдений максимальний (maxIndex)
            short temp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // --- Метод 2: Сортування вставкою (Insertion Sort) ---
    public static void insertionSortDesc(short[] arr) {
        int n = arr.length;

        // Починаємо з другого елемента (індекс 1)
        for (int i = 1; i < n; i++) {
            short key = arr[i]; // Елемент, який ми хочемо вставити на правильне місце
            int j = i - 1;

            // Пересуваємо елементи, які МЕНШІ за key, вправо
            // (бо нам треба, щоб зліва були більші числа)
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            // Вставляємо key на звільнене місце
            arr[j + 1] = key;
        }
    }

    // Допоміжний метод для красивого виводу масиву в консоль
    public static void printArray(short[] arr) {
        System.out.print("[ ");
        for (short element : arr) {
            System.out.print(element + " ");
        }
        System.out.println("]");
    }
}