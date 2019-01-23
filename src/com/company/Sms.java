package com.company;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.TextFlow;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sms {


    private Date date = new Date();
    private SimpleDateFormat formatForDate = new SimpleDateFormat("ddMMyy,HHmm");
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yy,HH:mm:ss");

    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty Status = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 status\" "+formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty Time = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 time " + formatForDate.format(date)+ ",02,0900,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty Actv = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 actv\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty Dact = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 dact\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty S8Off = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 s8off\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty Info = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 info\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty L1 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,1,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty L2 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,2,\" "+ formatDate.format(date)+"\r\n\r\n");

    public SimpleDateFormat getFormatForDate() {
        return formatForDate;
    }

    public void setFormatForDate(SimpleDateFormat formatForDate) {
        this.formatForDate = formatForDate;
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set("+"+phone);

        Status = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 status\" "+formatDate.format(date)+"\r\n\r\n");
        Time = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 time " + formatForDate.format(date)+ ",02,0900,\" "+ formatDate.format(date)+"\r\n\r\n");
        Actv = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 actv\" "+ formatDate.format(date)+"\r\n\r\n");
        Dact = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 dact\" "+ formatDate.format(date)+"\r\n\r\n");
        S8Off = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 s8off\" "+ formatDate.format(date)+"\r\n\r\n");
        Info = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 info\" "+ formatDate.format(date)+"\r\n\r\n");
        L1 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,1,\" "+ formatDate.format(date)+"\r\n\r\n");
        L2 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,2,\" "+ formatDate.format(date)+"\r\n\r\n");


    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return Status.get();
    }

    public SimpleStringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

    public String getTime() {
        return Time.get();
    }

    public SimpleStringProperty timeProperty() {
        return Time;
    }

    public void setTime(String time) {
        this.Time.set(time);
    }

    public String getActv() {
        return Actv.get();
    }

    public SimpleStringProperty actvProperty() {
        return Actv;
    }

    public void setActv(String actv) {
        this.Actv.set(actv);
    }

    public String getDact() {
        return Dact.get();
    }

    public SimpleStringProperty dactProperty() {
        return Dact;
    }

    public void setDact(String dact) {
        this.Dact.set(dact);
    }

    public String getS8Off() {
        return S8Off.get();
    }

    public SimpleStringProperty s8OffProperty() {
        return S8Off;
    }

    public void setS8Off(String s8Off) {
        this.S8Off.set(s8Off);
    }

    public String getInfo() {
        return Info.get();
    }

    public SimpleStringProperty infoProperty() {
        return Info;
    }

    public void setInfo(String info) {
        this.Info.set(info);
    }

    public String getL1() {
        return L1.get();
    }

    public SimpleStringProperty l1Property() {
        return L1;
    }

    public void setL1(String l1) {
        this.L1.set(l1);
    }

    public String getL2() {
        return L2.get();
    }

    public SimpleStringProperty l2Property() {
        return L2;
    }

    public void setL2(String l2) {
        this.L2.set(l2);
    }



    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(SimpleDateFormat formatDate) {
        this.formatDate = formatDate;
    }
}
