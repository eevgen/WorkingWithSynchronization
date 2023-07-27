package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    private static int counter = 0;

    private static final int INCREMENT_ALL_NUMBERS_FIRST_THREAD = 100;
    private static final int INCREMENT_ALL_NUMBERS_SECOND_THREAD  = 300;

    public static void main(String[] args){

        Thread thread1 = creatingNewThreads(INCREMENT_ALL_NUMBERS_FIRST_THREAD);
        Thread thread2 = creatingNewThreads(INCREMENT_ALL_NUMBERS_FIRST_THREAD);
        Thread thread3 = creatingNewThreads(INCREMENT_ALL_NUMBERS_SECOND_THREAD);
        Thread thread4 = creatingNewThreads(INCREMENT_ALL_NUMBERS_SECOND_THREAD);

        startThreads(thread1, thread2, thread3, thread4);

        joinThreads(thread1, thread2, thread3, thread4);

        System.out.println(counter);

    }

    private static Thread creatingNewThreads(int amountOfIncrementing) {
        return new Thread(() -> IntStream.range(0, amountOfIncrementing).forEach(i -> incrementNumber()));
    }

    private static void startThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static void joinThreads(Thread... threads){
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized static void incrementNumber() {
        counter++;
    }
}