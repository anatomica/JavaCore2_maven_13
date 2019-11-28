package Lesson5.Homework;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    private static final int CARS_COUNT = 4;
    private static Thread[] threads = new Thread[CARS_COUNT];
    private static final ExecutorService exeSe = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        // Car.setCyclicBarrier(new CyclicBarrier(CARS_COUNT));
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            threads[i] = new Thread(cars[i]);
            threads[i].start();
        }
        Thread.sleep(CARS_COUNT * 1000);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        Car.cdl.countDown();
        for (int i = 0; i < cars.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}