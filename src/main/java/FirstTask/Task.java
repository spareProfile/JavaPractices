package FirstTask;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * Задача 1
 * Вариант 4. Баранова Анастасия Андреевна
 * Заполните массив (тип элементов "int") случайными числами и
 * выполните задание в соответствии со своим вариантом.
 * Вариант: Найти последний положительный элемент в массиве.
 */

public class Task {

    public static void execute1Task(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        int lastPosnNum = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (array[i] > 0) {
                lastPosnNum = array[i];
                break;
            }
        }
        if (lastPosnNum == -1) {
            System.out.println("There are no positive elements in the following array  "+ Arrays.toString(array));
        } else {
            System.out.println("Array:  "+ Arrays.toString(array));
            System.out.println("The last positive number is: " + lastPosnNum);
        }
    }

    public static void main(String[] args) {
        execute1Task();
}
}