package com.lokesh.loginsignup.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import org.jetbrains.annotations.NotNull;



@Entity(tableName = "userdetail")
public class userEntity {
    @ColumnInfo(name = "First Name")
    String FirstName;
    @ColumnInfo(name = "Last Name")
    String LastName;
    @ColumnInfo(name = "Email")
    String Email;
    @ColumnInfo(name = "Phone No")
    String Phone_NO;
    @ColumnInfo(name = "Username")
    @PrimaryKey() @NotNull
    String Username;
    @ColumnInfo(name = "Password")
    String Password;

    public userEntity() {
    }

    public userEntity(@NotNull String username, String password) {
        Username = username;
        Password = password;
    }

    public userEntity(String firstName, String lastName, String email, String phone_NO, String username, String password) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Phone_NO = phone_NO;
        this.Username = username;
        this.Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_NO() {
        return Phone_NO;
    }

    public void setPhone_NO(String phone_NO) {
        Phone_NO = phone_NO;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String  getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
