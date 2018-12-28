package com.app;

import java.util.ArrayList;

public class Client {
    private String _firstName;
    private String _secondName;
    private String _address;
    private String _passportID;

    private Client(){}

    public String getFirstName() {
        return _firstName;
    }

    public String getSecondName() {
        return _secondName;
    }

    public String getAddress() {
        return _address;
    }

    public String getPassportID() {
        return _passportID;
    }

    public static Builder Builder(String firstName, String secondName) {
        return new Client().new Builder(firstName, secondName);
    }

    public class Builder {

        Builder(String firstName, String secondName){
            _firstName = firstName;
            _secondName = secondName;
        }

        public Builder setAddress(String address) {
            _address = address;
            return this;
        }

        public Builder setPassportID(String passportID) {
            _passportID = passportID;
            return this;
        }

        public Client build(){
            return Client.this;
        }
    }
}
