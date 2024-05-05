package com.example.internetshopcar.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 20, name = "user_id")
    private int userId;

    @Column(length = 10, name = "user_name")
    private String userName;

    @Column(length = 100, name = "user_email")
    private String userEmail;

    @Column(length = 100, name = "user_password")
    private String userPassword;

    @Column(length = 100, name = "user_c_password")
    private String userCPassword;

    @Column(length = 12, name = "user_phone")
    private String userPhone;

    @Column(length = 1500, name = "user_pic")
    private String userPic ="default.jpg";

    @Column(length = 1500, name = "user_address")
    private String userAddress;

    @Column(length = 20, name = "user_type")
    private String userType = "normal";

    public User(int userId, String userName, String userEmail,String userPassword,  String userCPassword, String userPhone, String userPic, String userAddress, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCPassword = userCPassword;
        this.userPhone = userPhone;
        this.userPic = userPic;
        this.userAddress = userAddress;
        this.userType = userType;
    }

    public User(String userName, String userEmail, String userPassword, String userCPassword,  String userPhone, String userPic, String userAddress, String userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCPassword = userCPassword;
        this.userPhone = userPhone;
        this.userPic = userPic;
        this.userAddress = userAddress;
        this.userType = userType;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserCPassword() { return userCPassword; }

    public String getUserType() { return userType; }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserCPassword(String userCPassword) {
        this.userCPassword = userCPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCPassword='" + userCPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPic='" + userPic + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }


}
