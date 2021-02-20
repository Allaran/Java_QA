package ru.stqa.pft.addresbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String address;
    private final String phone;
    private final String email;
    private final String secondaryAddress;
    private final String group;


    public ContactData(String firstName, String lastName, String nickName, String title, String address, String phone, String email, String secondaryAddress, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.secondaryAddress = secondaryAddress;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public String getGroup() {
        return group;
    }
}