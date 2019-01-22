package com.company;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelScanner extends Thread {
    private final Scanner scanner;
    private final BlockingQueue<String> receivingQueue = new LinkedBlockingQueue<>();

    public ParallelScanner(Scanner scanner) {
        this.scanner = scanner;
        setDaemon(true);
    }

    public String nextLine() {
        try {
            return receivingQueue.take();
        } catch (InterruptedException e) {
            return null;
        }

    }

    public boolean hasNext() {
        return !receivingQueue.isEmpty();
    }

    @Override
    public void run() {
        while (!interrupted()) {
            String line = scanner.nextLine();
            System.out.println(line);
            receivingQueue.add(line);
        }


    }

    public Scanner getScanner() {
        return scanner;
    }

    public BlockingQueue<String> getReceivingQueue() {
        return receivingQueue;
    }
}
