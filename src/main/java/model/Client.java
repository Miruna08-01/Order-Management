package model;

import java.awt.*;
import java.util.Date;

public class Client extends Component {
    private int id;
    private String name;
    private String email;
    private String address;
    private String birthdate;


    public Client(){

    }
    public Client(int id, String name, String email,String address,String bithdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthdate=bithdate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
