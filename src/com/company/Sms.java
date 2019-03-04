package com.company;

import javafx.beans.property.SimpleStringProperty;

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
    private SimpleStringProperty S4Off = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 s4off\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty Info = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 info\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty L1 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,1,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty L2 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,2,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty RFmodeA = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 rfmode a,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty RFmodeB = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 rfmode b,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty razvchas = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 offline s,1,6,sms,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty razvsutki = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 offline s,1,240,sms,\" "+ formatDate.format(date)+"\r\n\r\n");
    private SimpleStringProperty razv2sutki = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 offline s,1,480,sms,\" "+ formatDate.format(date)+"\r\n\r\n");

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
        S4Off = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 s4off\" "+ formatDate.format(date)+"\r\n\r\n");
        Info = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 info\" "+ formatDate.format(date)+"\r\n\r\n");
        L1 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,1,\" "+ formatDate.format(date)+"\r\n\r\n");
        L2 = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 out L,2,\" "+ formatDate.format(date)+"\r\n\r\n");
        RFmodeA = new  SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 rfmode a,\" "+ formatDate.format(date)+"\r\n\r\n");
        RFmodeB = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 rfmode b,\" "+ formatDate.format(date)+"\r\n\r\n");
        razvchas = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 offline s,1,6,sms,\" "+ formatDate.format(date)+"\r\n\r\n");
        razvsutki = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 offline s,1,240,sms,\" "+ formatDate.format(date)+"\r\n\r\n");
        razv2sutki = new SimpleStringProperty("Action: smscommand\r\ncommand: gsm send sms 2 " + getPhone() + " \"12345 offline s,1,480,sms,\" "+ formatDate.format(date)+"\r\n\r\n");

    }

    public String getRazv2sutki() {
        return razv2sutki.get();
    }

    public SimpleStringProperty razv2sutkiProperty() {
        return razv2sutki;
    }

    public void setRazv2sutki(String razv2sutki) {
        this.razv2sutki.set(razv2sutki);
    }

    public String getRazvsutki() {
        return razvsutki.get();
    }

    public SimpleStringProperty razvsutkiProperty() {
        return razvsutki;
    }

    public void setRazvsutki(String razvsutki) {
        this.razvsutki.set(razvsutki);
    }

    public String getRazvchas() {
        return razvchas.get();
    }

    public SimpleStringProperty razvchasProperty() {
        return razvchas;
    }

    public void setRazvchas(String razvchas) {
        this.razvchas.set(razvchas);
    }

    public String getS4Off() {
        return S4Off.get();
    }

    public SimpleStringProperty s4OffProperty() {
        return S4Off;
    }

    public void setS4Off(String s4Off) {
        this.S4Off.set(s4Off);
    }

    public String getRFmodeA() {
        return RFmodeA.get();
    }

    public SimpleStringProperty RFmodeAProperty() {
        return RFmodeA;
    }

    public void setRFmodeA(String RFmodeA) {
        this.RFmodeA.set(RFmodeA);
    }

    public String getRFmodeB() {
        return RFmodeB.get();
    }

    public SimpleStringProperty RFmodeBProperty() {
        return RFmodeB;
    }

    public void setRFmodeB(String RFmodeB) {
        this.RFmodeB.set(RFmodeB);
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
