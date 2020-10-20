package org.javacase.multithreading.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class PartyClass implements Runnable{

    CyclicBarrier barrier;

    public PartyClass(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("Party - "+Thread.currentThread().getName()+" has arrived");
        try {
            Thread.sleep(2000);
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args){

        CyclicBarrier barrier = new CyclicBarrier(3,()->{
           System.out.println("All parties arrived, Hence starting party");
        });

        new Thread(new PartyClass(barrier)).start();
        new Thread(new PartyClass(barrier)).start();
        new Thread(new PartyClass(barrier)).start();

        barrier.reset();

        new Thread(new PartyClass(barrier)).start();
        new Thread(new PartyClass(barrier)).start();
        new Thread(new PartyClass(barrier)).start();
    }
}
