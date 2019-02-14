package com.company;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class MainController {

    private static ParallelScanner in;
    private static PrintStream out;

    public TextField Phone;
    private Sms sms = new Sms();
    public CheckBox Time;
    public CheckBox Status;
    public CheckBox S8Off;
    public CheckBox Info;
    public CheckBox L1;
    public CheckBox L2;
    public CheckBox Actv;
    public CheckBox Dact;
    public TextArea textWindow;
    private Object ParsePosition;
    private Object ActionEvent;
    public String phone;
    public String line;

    public MainController() throws IOException {
    }

    public void initialize() {

        final Properties properties = new Properties();


        try (InputStream is = Main.class.getResourceAsStream("config.properties")) {
            properties.load(is);

            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            Socket socket = new Socket(host, Integer.parseInt(port));


            in = new ParallelScanner(new Scanner(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            in.start();
            connection(socket, out, in);
//метод обновляет входящие потоки
            Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    readFromDevice();
                }
            }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();


        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    private void connection(Socket socket, PrintStream out, ParallelScanner in) throws Exception {


        enterPassword(socket, out, in);
        readFromDevice();

    }

    private static void enterPassword(Socket socket, PrintStream out, ParallelScanner in) throws IOException {
//        вводим пароль, вводные данные идут из файла config.properties.login
        final Properties properties = new Properties();
        try
                (InputStream is = Main.class.getResourceAsStream("config.properties.login")) {
            properties.load(is);
            String login = properties.getProperty("cp.action");
            String username = properties.getProperty("cp.username");
            String secret = properties.getProperty("cp.secret");
            out.print("Action: " + login + "\r\nUsername: " + username + "\r\nSecret: " + secret + "\r\n\r\n");
        }


    }


    public void addPhone(ActionEvent actionEvent) {

//вводим номер и отправляем в SMS
        phone = Phone.getText();
        sms.setPhone(phone);

        System.out.println(sms.getPhone());
        textWindow.appendText("Добавлен номер: " + sms.getPhone() + "\n\r");

    }


    @FXML
    private void readFromDevice() {
// информационные сообщения
        while (in.hasNext()) {
            line = in.nextLine();


            if (line.equals("Message: Authentication accepted")) {
                textWindow.appendText("Успешное подключение!!!" + "\n");
                return;
            }
            if (line.equals("Message: Authentication failed")) {
                textWindow.appendText("Ошибка подключения. \nПроверьте логин и пароль в файле config.properties.login\n");
                return;
            }
            if (line.equals("Message: Permission denied")) {
                textWindow.appendText("Ошибка подключения. \nПроверьте функцию action в файле config.properties.login\n");
            }
            if (line.equals("Status: 1")) {
                textWindow.appendText("Отправлено" + "\n");
            }
            if (line.equals("Status: 0")) {
                textWindow.appendText("Ошибка отправления, возможно, указан неверный номер\n");
            }
            if (line.equals("Sender: " + sms.getPhone())) {
                textWindow.appendText("Устройство с номером " + sms.getPhone() + " получило настройки\n\r");

            }
            if (line.startsWith("Content:")) {

                textWindow.appendText(line.replace("Content:", "Ответ:") + "\r\n");
            }

        }


    }

    public static ParallelScanner getIn() {
        return in;
    }


    public static PrintStream getOut() {
        return out;
    }


    public void handleOptions(ActionEvent actionEvent) {
// выбор сообщений
        String message = "Выбрано: \n";

        if (Time.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
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

    public void Help(javafx.event.ActionEvent actionEvent) throws IOException {
//Всплывающее окно, с инструкцией из файла
        String text = new String(Files.readAllBytes(Paths.get("src/com/company/help.txt")));
        System.out.println(text);

        Stage window = new Stage();
        window.setMinWidth(350);
        window.setMinHeight(400);

        Label label = new Label();
        label.setFont(Font.font(20));
        VBox layout = new VBox(20);

        layout.getChildren().addAll(label);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        label.setText(text);
        window.show();
//        try {
//            FileInputStream fstream = new FileInputStream("src/com/company/help.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//            String strLine;
//            while ((strLine = br.readLine()) != null) {
//                System.out.println(strLine);
//
//            }



    }

}


