package org.example;

import java.util.stream.IntStream;

public class Main {

    private static int counter = 0;

    private static final int INCREMENT_ALL_NUMBERS_FIRST_THREAD = 100;
    private static final int INCREMENT_ALL_NUMBERS_SECOND_THREAD  = 300;

    public static void main(String[] args) throws InterruptedException{

        Thread thread1 = creatingNewThreads(INCREMENT_ALL_NUMBERS_FIRST_THREAD);
        Thread thread2 = creatingNewThreads(INCREMENT_ALL_NUMBERS_SECOND_THREAD);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);

    }

    private static Thread creatingNewThreads(int amountOfIncrementing) {
        return new Thread(() -> IntStream.range(0, amountOfIncrementing).forEach(i -> incrementNumber()));
    }

    private static void incrementNumber() {
        counter++;
    }
}