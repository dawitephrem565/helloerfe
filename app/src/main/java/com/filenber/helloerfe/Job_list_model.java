package com.filenber.helloerfe;

import java.util.Date;

public class Job_list_model {
    String uid;
    String Kebele;
    String Woreda;
    String Zone;
    String Unique_Name;
    String Agent_name;
    String Call_center_note;
    java.util.Date Date;
    String phone_number;
    String Assigned;

    public Job_list_model() {
    }

    public Job_list_model(String uid, String kebele, String woreda, String zone, String unique_Name, String agent_name, String call_center_note, java.util.Date date, String phone_number, String assigned) {
        this.uid = uid;
        Kebele = kebele;
        Woreda = woreda;
        Zone = zone;
        Unique_Name = unique_Name;
        Agent_name = agent_name;
        Call_center_note = call_center_note;
        Date = date;
        this.phone_number = phone_number;
        Assigned = assigned;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKebele() {
        return Kebele;
    }

    public void setKebele(String kebele) {
        Kebele = kebele;
    }

    public String getWoreda() {
        return Woreda;
    }

    public void setWoreda(String woreda) {
        Woreda = woreda;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getUnique_Name() {
        return Unique_Name;
    }

    public void setUnique_Name(String unique_Name) {
        Unique_Name = unique_Name;
    }

    public String getAgent_name() {
        return Agent_name;
    }

    public void setAgent_name(String agent_name) {
        Agent_name = agent_name;
    }

    public String getCall_center_note() {
        return Call_center_note;
    }

    public void setCall_center_note(String call_center_note) {
        Call_center_note = call_center_note;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAssigned() {
        return Assigned;
    }

    public void setAssigned(String assigned) {
        Assigned = assigned;
    }
}
