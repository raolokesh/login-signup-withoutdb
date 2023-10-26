package com.lokesh.loginsignup.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.room.Update;

import java.util.List;

@Dao
public interface userEntityDao {

    @Insert
    void insert(userEntity item);



    @Update
    void update(userEntity item);

    @Query("update userdetail set Password = :password where Username == :username")
    void resetPassword(String username,String password);

    @Delete
    void delete(userEntity item);

    @Query("SELECT * FROM  userdetail")
    List<userEntity> getAllItem();


    @Query("select Count(Username) from userdetail where Username == :username ")
    boolean checkUsername(String username);

    @Query("SELECT COUNT(*) FROM userdetail WHERE Username == :username AND Password == :password")
    int checkUsernamePassword(String username, String password);

}