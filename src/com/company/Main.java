package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    private static ParallelScanner in;
    private static PrintStream out;

    public static void main(String[] args) {

        String host = "192.168.1.80";
        int port = 5038;
        try (Socket socket = new Socket(host, port)) {



            in = new ParallelScanner(new Scanner(socket.getInputStream()));
            in.start();
            connection(socket, out, in);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void connection(Socket socket, PrintStream out, ParallelScanner in) throws Exception {

        String line;
        while (!"-END-".equals(line = in.nextLine())) {
            // processing of the input

            if (line.equals("Asterisk Call Manager/1.1")){
                System.out.println("connected");
break;
            }

    }
        enterPassword(socket, out, in);
        readFromDevice();

    }

    private static void enterPassword(Socket socket, PrintStream out, ParallelScanner in) throws IOException {
        out = new PrintStream(socket.getOutputStream());
        out.print("Action: login\r\nUsername: apiuser\r\nSecret: apipass\r\n\r\n");
        String line;
        while (!"-END-".equals(line = in.nextLine())) {
           if (line.equals("Message: Authentication accepted")){
               System.out.println("Connected to nasa date base");
           }
        }
    }


    public static void readFromDevice() {

        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(line);


        }

    }

    public static ParallelScanner getIn() {
        return in;
    }

    public static void setIn(ParallelScanner in) {
        Main.in = in;
    }

    public static PrintStream getOut() {
        return out;
    }

    public static void setOut(PrintStream out) {
        Main.out = out;
    }
}