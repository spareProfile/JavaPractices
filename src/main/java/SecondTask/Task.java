package SecondTask;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задача 2
 * Вариант 4. Баранова Анастасия Андреевна
 * Заполните список (тип "ArrayList<Double>") случайным числами и отсортируйте его.
 * Метод сортировки выбирается в соответствии с вашим вариантом.
 * Сортировку необходимо реализовать вручную (не использовать встроенные возможности Java или сторонних библиотек).
 * Вариант: Сортировка выбором (Selection Sort).
 */

public class Task {

    private static List<Double> SelectionSort(List<Double> list, int size){
        for (int i = 0; i < size-1; i++){
            int min_index = i;
            for (int j = i + 1; j < size; j++){
                if (list.get(j) < list.get(min_index)){
                    min_index = j;
                }
            }
            double temp1 = list.get(i);
            list.set(i, list.get(min_index));
            list.set(min_index, temp1);
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();
        scanner.close();
        List<Double> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextDouble());
        }

        List<Double> initial_list = new ArrayList<>(list);
        list = SelectionSort(list, size);

        System.out.println("Initial array: " + initial_list);
        System.out.println("Sorted: " + list);


    }
}