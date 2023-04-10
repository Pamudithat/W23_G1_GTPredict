package com.example.w23_g1_gtpredict;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GTDataDao {
   // @Query("SELECT * FROM user")
   // List<User> getAll();

   // @Query("SELECT * FROM user WHERE uid IN (:userIds)")
   // List<User> loadAllByIds(int[] userIds);

  //  @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
          //  "last_name LIKE :last LIMIT 1")
    //User findByName(String first, String last);
@Query("Select * from GTData")
    List<GTData>getallgtdatas();
    @Insert
    void insertrecord(GTData gtdatas);
    @Query("Select * from GTData order by 'Temp' DESC")
    List<GTData>getallgtdatasDESC();
   // @Delete
   // void delete(User user);
}
