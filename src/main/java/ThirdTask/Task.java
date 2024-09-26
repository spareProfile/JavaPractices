package ThirdTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Задача 3
 * Вариант 4. Баранова Анастасия Андреевна
 * 1. Необходимо реализовать java-класс сотрудник (код "Employee").
 * Приватные поля класса: ФИО ("fullName" тип "String"), Возраст ("age"
 * тип "Integer"), Отдел ("department" тип "String"), 3/П ("salary" тип
 * "Double"). Класс должен содержать геттеры и сеттеры для доступа к полям.
 * 2. Необходимо реализовать предзаполненный список (тип
 * "ArrayList<Employee>") с объектами класса "Employee", по которым будем выполняться задание. Необходимо создать не менее 5 элементов списка.
 * 3. Выполнить задание в соответствии с вашим вариантом. При выполнении задания необходимо использовать возможности Stream API!
 * Вариант: Преобразовать спсиок сотрудников в список строк вида Имя - отдел
 */

public class Task {
    public static void execute3Task(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Jane Air", 30, "HR", 15000));
        list.add(new Employee("John Smith", 35, "Finance", 20000));
        list.add(new Employee("John WithoutSecondName", 40, "Marketing", 25000));
        list.add(new Employee("John Johnson", 45, "Sales", 30000));
        list.add(new Employee("John Willyams", 25, "IT", 35000));
        list.add(new Employee("Alice Frank", 22, "IT", 40000));

        List<String> list2 = list.stream().map(e -> e.getFullName() + " - " + e.getDepartment()).toList();
        //System.out.println(list2);
        list2.stream().forEach(System.out::println);
    }


    public static void main(String[] args) {
        execute3Task();
    }
}
