package com.company;


import javafx.scene.control.TextArea;

import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelScanner extends Thread implements AutoCloseable {
    private final Scanner scanner;
    private final BlockingQueue<String> receivingQueue = new LinkedBlockingQueue<>();
    String line;
    public TextArea textWindow;

    @Override
    public void close() {
        interrupt();
        scanner.close();
    }

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
            line = scanner.nextLine();
            System.out.println(line);

            receivingQueue.add(line);
            if (line.equals("Message: Authentication accepted")) {
                System.out.println("Connected");

            }

        }


    }

    public Scanner getScanner() {
        return scanner;
    }

    public BlockingQueue<String> getReceivingQueue() {
        return receivingQueue;
    }


}
