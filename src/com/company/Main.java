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
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Properties;
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


      final   Properties properties = new Properties();


        try (InputStream is = Main.class.getResourceAsStream("config.properties")){
             properties.load(is);

             String host = properties.getProperty("db.host");
             String port = properties.getProperty("db.port");
             Socket socket = new Socket(host, Integer.parseInt(port));


             in = new ParallelScanner(new Scanner(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            in.start();
            connection(socket, out, in);


        } catch (Exception e) {

            e.printStackTrace();

        }
        launch(args);
    }


    static void connection(Socket socket, PrintStream out, ParallelScanner in) throws Exception {


        enterPassword(socket, out, in);
        readFromDevice();

    }

    private static void enterPassword(Socket socket, PrintStream out, ParallelScanner in) throws IOException {
//        вводим пароль

        out.print("Action: login\r\nUsername: apiuser\r\nSecret: apipass\r\n\r\n");

        while (in.hasNext()) {
            if (in.nextLine().equals("Asterisk Call Manager/1.1\n" +
                    "Response: Success\n" +
                    "Message: Authentication accepted")) {
                System.out.println("hello");
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


    private static void readFromDevice() {

        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(line);

            TextArea textArea = new TextArea();
            textArea.appendText(line);

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

        String message = "Отправлено: \n";

        if (Time.isSelected()) {
            message += "Time\n";
//                textWindow.appendText(message);
            out.print(sms.getTime());

        }

        if (L1.isSelected()) {
            message += "L1\n";
//                textWindow.appendText(message);
            out.print(sms.getL1());

        }
        if (L2.isSelected()) {
            message += "L2\n";
//                textWindow.appendText(message);
            out.print(sms.getL2());

        }
        if (Info.isSelected()) {
            message += "Info\n";
//                textWindow.appendText(message);
            out.print(sms.getInfo());

        }
        if (Actv.isSelected()) {
            message += "Actv\n";
//                textWindow.appendText(message);
            out.print(sms.getActv());

        }
        if (Dact.isSelected()) {
            message += "Dact\n";
//                textWindow.appendText(message);
            out.print(sms.getDact());

        }
        if (S8Off.isSelected()) {
            message += "S8Off\n";
//                textWindow.appendText(message);
            out.print(sms.getS8Off());

        }
        if (Status.isSelected()) {
            message += "Status\n";

            out.print(sms.getStatus());

        }
        textWindow.appendText(message + "\n\r");



    }
}


