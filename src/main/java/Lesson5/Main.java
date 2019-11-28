package Lesson5;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

   /*     ExecutorService pool = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("NEW THREAD!");
                return new Thread(r);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(1);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(2);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(3);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(4);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(5);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(6);
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(7);
            }
        });

        pool.shutdown();
        pool.shutdownNow();

        pool.awaitTermination(1000, TimeUnit.MILLISECONDS);*/

/*        Random random = new Random();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date() + "start work");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date() + "end work");
            }
        }, 0, 1000L, TimeUnit.MILLISECONDS);*/

/*//        Main m = new Main();
//        m.testSch();*/


/*ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(2);

        abq.add("a");
        abq.add("b");
        abq.put("c");
        abq.offer("c");*/

/*        Map<String, String> chm = new ConcurrentHashMap<>();

        Map<String, String> hm = Collections.synchronizedMap(new TreeMap<String, String>());*/

/*        Semaphore smp = new Semaphore(3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        smp.acquire();
                        System.out.println(1);

                        Thread.sleep(2000);
                        smp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(2);
                }
            });


        }
        executorService.shutdown();*/

/*        CountDownLatch cdl = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(w + " 1");
                    try {
                        Thread.sleep((int)Math.random() * 3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(w + " 2");
                    cdl.countDown();
                }
            }).start();
        }
        cdl.await();

        System.out.println("END");*/

       /* CyclicBarrier cb = new CyclicBarrier(10);

        for (int i = 1; i < 11; i++) {
            int w = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(w + "start");
                    try {
                        Thread.sleep((int)Math.random() * 3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(w + "end");
                }
            }).start();
        }*/

        ReentrantLock r1 = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                r1.lock();

                System.out.println(1);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(2);


                r1.unlock();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                r1.lock();

                System.out.println(3);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(4);


                r1.unlock();
            }
        }).start();


    }
     void testSch() {
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.HOUR_OF_DAY, 23);
         calendar.set(Calendar.MINUTE, 12);
         calendar.set(Calendar.SECOND, 0);

         Date time = calendar.getTime();
         Timer timer = new Timer();
         timer.schedule(new RemindTask(), time);
    }

}

class RemindTask extends TimerTask {
    public void run() {
        System.out.println("ПЕРЕРЫВ ОКОНЧЕН!");
    }
}