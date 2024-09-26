package FifthTask;
import java.util.Queue;
import java.util.LinkedList;


/**
 * Задача 5.
 * Вариант 4. Баранова Анастасия Андреевна
 * Реализуйте интерфейс "Task" для обработки данных, поступающих в очередь. Процесс обработки имитируется выводом сообщения в консоль.
 * Метод start() начинает чтение и обработку данных из очереди, а stop останавливает процесс. Для выполнения задания рекомендуется
 * использовать классы "java.util.Queue" "java.util.LinkedList".
 *
 */

public class Task5 implements Task{
    private Queue<String> queue;
    private boolean isRunning;

    public Task5(Queue<String> queue){
        this.queue = queue;
        isRunning = false;
    }
    @Override
    public void start(){
        isRunning = true;
        System.out.println("Started");
        while (isRunning && !queue.isEmpty()){
            process(queue.poll());

        }
    }
    @Override
    public void stop(){
        System.out.println("Stopped");
        isRunning = false;
    }

    private void process(String data){
        try {
            Thread.sleep(2000); // emulate process time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isRunning){
            System.out.println(data);
        }
    }

    public static void main(String[] args) { //  test the class functionality
        Queue<String> queue = new LinkedList<>();
        queue.add("First...");
        queue.add("Second...");
        queue.add("Third...");
        queue.add("Fourth...");
        queue.add("Fifth...");
        queue.add("Sixth...");
        Task5 task = new Task5(queue);
        Thread taskThread = new Thread(task::start);
        taskThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.stop();
    }
}




