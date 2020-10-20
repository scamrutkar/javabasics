package org.javacase.multithreading.synchronizers;

class SampleQueue {

    int number;
    boolean isGetting = false;

    public int get() {
        synchronized (this) {
            try {
                while (!isGetting) {
                    wait();
                }
                isGetting = false;
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return number;
        }
    }

    public void put(int number) {
        synchronized (this) {
            try {
                while (isGetting)
                    wait();
                this.number = number;
                isGetting = true;
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumerProblem {

    public static void main(String[] args) {

        SampleQueue queue = new SampleQueue();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                queue.put(i);
                System.out.println("Putting Element : " + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                int j = queue.get();
                System.out.println("Getting Element : " + j);
            }
        }).start();
    }
}
