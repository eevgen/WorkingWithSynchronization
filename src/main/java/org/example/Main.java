package org.example;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Main {

    private static int counter1 = 0;

    private static int counter2 = 0;

    private static final int INCREMENT_ALL_NUMBERS_FIRST_THREAD = 100;
    private static final int INCREMENT_ALL_NUMBERS_SECOND_THREAD  = 300;

    private static final String MESSAGE_TEMPLATE_FOR_COUNTERS = "Number = %d\n";

    public static void main(String[] args){

        Thread thread1 = creatingNewThreads(INCREMENT_ALL_NUMBERS_FIRST_THREAD, i -> incrementFirstNumber());
        Thread thread2 = creatingNewThreads(INCREMENT_ALL_NUMBERS_FIRST_THREAD, i -> incrementFirstNumber());
        Thread thread3 = creatingNewThreads(INCREMENT_ALL_NUMBERS_SECOND_THREAD, i -> incrementSecondNumber());
        Thread thread4 = creatingNewThreads(INCREMENT_ALL_NUMBERS_SECOND_THREAD, i -> incrementSecondNumber());

        startThreads(thread1, thread2, thread3, thread4);

        joinThreads(thread1, thread2, thread3, thread4);

        printCounters(counter1, counter2);
    }

    private static Thread creatingNewThreads(int amountOfIncrementing, IntConsumer operation) {
        return new Thread(() -> IntStream.range(0, amountOfIncrementing).forEach(operation));
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

    private static void printCounters(Integer... integers) {
        Arrays.stream(integers).forEach(integer -> System.out.printf(MESSAGE_TEMPLATE_FOR_COUNTERS, integer));
    }

    private synchronized static void incrementFirstNumber() {
        counter1++;
    }
    private synchronized static void incrementSecondNumber() {
        counter2++;
    }
}