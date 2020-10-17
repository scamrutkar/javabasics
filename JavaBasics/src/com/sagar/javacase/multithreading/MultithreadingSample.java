package com.sagar.javacase.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class MultithreadingSample {

    private int value;

    public MultithreadingSample(){
        this.value = 0;
    }

    private synchronized int getUniqueInt(){
        int currentValue = this.value;
        this.value += 1;
        return currentValue;
    }

    public static void main(String[] args){

        MultithreadingSample ms = new MultithreadingSample();
        withMultiThreading(ms);


    }

    private static void withMultiThreading(MultithreadingSample ms) {
        long startTime = System.currentTimeMillis();
        ExecutorService service = Executors.newScheduledThreadPool(10);
        for(int i=0; i<100; i++){
            CounterService counter = new CounterService(ms);
            service.submit(counter);
        }
        long endTime= System.currentTimeMillis();
        System.out.println("Total time required with multithreading: "+ (endTime - startTime));
        service.shutdown();
    }

    static class CounterService implements Runnable{

        private MultithreadingSample ms;
        public CounterService(MultithreadingSample ms){
            this.ms = ms;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(200);
                int value = ms.getUniqueInt();
                System.out.println("The current unique value for thread "+Thread.currentThread().getName()+" is "+value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void withoutMultithreading() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        for(int i=0; i<100; i++){
            Thread.sleep(100);
        }

        long endTime= System.currentTimeMillis();
        System.out.println("Total time required without multithreading: "+ (endTime - startTime));
    }
}
