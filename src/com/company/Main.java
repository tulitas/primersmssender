package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main extends Application {



    public static ParallelScanner in;
    public static PrintStream out;

    public TextField Phone;
    public Sms sms = new Sms();
    public CheckBox Time;
    public CheckBox Status;
    public CheckBox S8Off;
    public CheckBox Info;
    public CheckBox L1;
    public CheckBox L2;
    public CheckBox Actv;
    public CheckBox Dact;
    public TextArea textWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("SmsSender");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(450);
        primaryStage.setScene(new Scene(root, 500, 500));


        primaryStage.show();

    }

    public static void main(String[] args) {
//подключение к серверу


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
        launch(args);

    }

     static void connection(Socket socket, PrintStream out, ParallelScanner in) throws Exception {

//        String line;
//        while (!"-END-".equals(line = in.nextLine())) {
//            // processing of the input
//
//            if (line.equals("Asterisk Call Manager/1.1")) {
//                System.out.println("connected");
//
//            }
//
//
//        }
        enterPassword(socket, out, in);
        readFromDevice();

    }

    private static void enterPassword(Socket socket, PrintStream out, ParallelScanner in) throws IOException {
//        вводим пароль
        out = new PrintStream(socket.getOutputStream());
        out.print("Action: login\r\nUsername: apiuser\r\nSecret: apipass\r\n\r\n");
        String line;
        while (!"-END-".equals(line = in.nextLine())) {
            if (line.equals("Message: Authentication accepted")) {
                System.out.println("Connected to nasa date base");
            }
        }


    }





    public void addPhone(ActionEvent actionEvent) {

//вводим номер и отправляем в SMS
        String phone = Phone.getText();
        sms.setPhone(phone);
        System.out.println(sms.getPhone());
        textWindow.appendText("Добавлен номер: " + sms.getPhone() + "\n\r");

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

    public void handleOptions(ActionEvent actionEvent) {


        String host = "192.168.1.80";
        int port = 5038;
        try (Socket socket = new Socket(host, port)) {


            if (Info.isSelected()) {


                System.out.println(sms.getInfo());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}