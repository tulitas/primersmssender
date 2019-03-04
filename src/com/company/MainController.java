package com.company;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class MainController {

    private static ParallelScanner in;
    private static PrintStream out;
    private Stage window;
    public TextField Phone;
    private CheckBox RFmodeB;
    private CheckBox RFmodeA;
    private Sms sms = new Sms();
    private CheckBox Time;
    private CheckBox Status;
    private CheckBox S8Off;
    private CheckBox S4Off;
    private CheckBox Info;
    private CheckBox L1;
    private CheckBox L2;
    private CheckBox Actv;
    private CheckBox Dact;
    private CheckBox razvchas;
    private CheckBox razvsutki;
    private CheckBox razv2sutki;
    public TextArea textWindow;

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
        String phone = Phone.getText();
        sms.setPhone(phone);

        System.out.println(sms.getPhone());
        textWindow.appendText("Добавлен номер: " + sms.getPhone() + "\n\r");

    }


    @FXML
    private void readFromDevice() {
// информационные сообщения
        while (in.hasNext()) {
            String line = in.nextLine();


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

                textWindow.appendText(line.replace("Content:" + "[%2F]", "Ответ:" + "/") + "\r\n");
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


    }

    public void Help(javafx.event.ActionEvent actionEvent) throws IOException {
//Всплывающее окно, с инструкцией из файла
        String text = new String(Files.readAllBytes(Paths.get("src/com/company/help.txt")));


        window = new Stage();
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


    }

    public void listView(ActionEvent actionEvent) {


        window = new Stage();
        window.setMinWidth(200);
        window.setMinHeight(200);
        window.setTitle("GM1");
        window.initModality(Modality.APPLICATION_MODAL);
        Button button = new Button("Send");
        button.setOnAction(event -> Send());

        Status = new CheckBox("Status");
        Info = new CheckBox("Info");
        Time = new CheckBox("Time");
        L1 = new CheckBox("L1");
        L2 = new CheckBox("L2");
        RFmodeA = new CheckBox("RFmodeA");
        RFmodeB = new CheckBox("RFmodeB");
        S4Off = new CheckBox("S4OFF");
        razvchas = new CheckBox("Раз в час");
        razvsutki = new CheckBox("Раз в сутки");
        razv2sutki = new CheckBox("Раз в 2 сутки");


        VBox layout = new VBox(10);

        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(button, Status, Info, Time, L1, L2, RFmodeA,RFmodeB, S4Off, razvchas, razvsutki, razv2sutki);


        Scene scene = new Scene(layout,  300, 500);
        window.setScene(scene);
        window.show();

    }

    private void Send() {
        // выбор сообщений
        String message = "Выбрано: \n";
        if (razvchas.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getRazvchas());
        }
        if (razvsutki.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getRazvsutki());
        }
        if (razv2sutki.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getRazv2sutki());
        }

        if (Time.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getTime());
        }
        if (RFmodeA.isSelected()) {
            message += "RF mode A\n";
//            textWindow.appendText(message);
            out.print(sms.getRFmodeA());
        }
        if (RFmodeB.isSelected()) {
            message += "RF mode B\n";
//            textWindow.appendText(message);
            out.print(sms.getRFmodeB());
        }

        if (L1.isSelected()) {
            message += "L1\n";
//                textWindow.appendText(message);
            out.print(sms.getL1());
            textWindow.appendText(sms.getL1());
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

        if (Status.isSelected()) {
            message += "Status\n";

            out.print(sms.getStatus());

        }
        if (S4Off.isSelected()) {
            message += "S4Off\n";
//                textWindow.appendText(message);
            out.print(sms.getS4Off());

        }
        textWindow.appendText(message + "\n\r");

    }


    public void listView2(ActionEvent actionEvent) {

        window = new Stage();
        window.setMinWidth(200);
        window.setMinHeight(200);
        window.setTitle("GM2");
        window.initModality(Modality.APPLICATION_MODAL);
        Button button = new Button("Send");
        button.setOnAction(event -> Send2());

        Status = new CheckBox("Status");
        Info = new CheckBox("Info");
        Time = new CheckBox("Time");
        Actv = new CheckBox("ACTV");
        Dact = new CheckBox("DACT");
        RFmodeA = new CheckBox("RFmodeA");
        RFmodeB = new CheckBox("RFmodeB");
        S8Off = new CheckBox("S8OFF");
        S4Off = new CheckBox("S4OFF");
        razvchas = new CheckBox("Раз в час");
        razvsutki = new CheckBox("Раз в сутки");
        razv2sutki = new CheckBox("Раз в 2 сутки");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(button, Status, Info, Time, Actv, Dact, RFmodeA, RFmodeB, S8Off, S4Off, razvchas, razvsutki, razv2sutki);

        Scene scene = new Scene(layout, 300, 500);
        window.setScene(scene);
        window.show();

    }

    private void Send2() {

        String message = "Выбрано: \n";

        if (razvsutki.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getRazvsutki());
        }
        if (razv2sutki.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getRazv2sutki());
        }
        if (razvchas.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getRazvchas());
        }
        if (Time.isSelected()) {
            message += "Time\n";
//            textWindow.appendText(message);
            out.print(sms.getTime());
        }
        if (Info.isSelected()) {
            message += "Info\n";
//                textWindow.appendText(message);
            out.print(sms.getInfo());

        }
        if (Actv.isSelected()) {
            message += "Actv\n";
            textWindow.appendText(message);
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

        }if (S4Off.isSelected()) {
            message += "S4Off\n";
//                textWindow.appendText(message);
            out.print(sms.getS4Off());

        }
        if (Status.isSelected()) {
            message += "Status\n";

            out.print(sms.getStatus());

        }
        textWindow.appendText(message + "\n\r");

    }
}




