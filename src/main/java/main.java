import java.util.Scanner;

class main {

    public static void main(String[] args) {
        System.out.println("Practice2. Баранова Анастасия Андреевна РИМ 140970");
        Scanner scanner = new Scanner(System.in);
        int exit = 100;
        while (exit != 0){
            System.out.print("Введите номер задания (1, 2, 3, 4, 5, 0 - выход): ");
            int taskNum = scanner.nextInt();

            switch (taskNum){
                case 1:
                    new FirstTask.Task().execute1Task();
                    break;
                case 2:
                    new SecondTask.Task().execute2Task();
                    break;
                case 3:
                    new ThirdTask.Task().execute3Task();
                    break;
                case 4:
                    new FourthTask.Task().execute4Task();
                    break;
                case 5:
                    new FifthTask.Task5().execute5Task();
                    break;
                case 0:
                    exit = 0;
                    break;
                default:
                    System.out.println("Такого номера нет");
                    break;

            }

        }


    }
}