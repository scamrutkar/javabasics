package org.javacase.multithreading.synchronizers;

class Sequence implements Runnable{

    int mod;
    static int maxnum = 10;
    static int number = 0;
    static Object lock = new Object();

    public Sequence(int mod){
        this.mod = mod;
    }

    @Override
    public void run() {
        while(number < maxnum -1){
            synchronized (lock){
                if(number % 3 != mod) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread"+mod+" printing "+number);
                number++;
                lock.notifyAll();
            }
        }
    }
}

public class SequencePrinter {

    public static void main(String[] args){
        new Thread(new Sequence(0)).start();
        new Thread(new Sequence(1)).start();
        new Thread(new Sequence(2)).start();
    }
}
