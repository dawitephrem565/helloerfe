package com.filenber.helloerfe;

public class Add_operator_model extends Operator_Id {
    String name;
    String Phone;
    String id;

    public Add_operator_model(){}

    public Add_operator_model(String name, String phone, String id) {
        this.name = name;
        Phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
