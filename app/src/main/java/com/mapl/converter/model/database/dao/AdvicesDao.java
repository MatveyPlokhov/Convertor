package com.mapl.converter.model.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mapl.converter.model.database.model.Advice;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface AdvicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAdvice(Advice advice);

    @Update
    void updateAdvice(Advice advice);

    @Delete
    void deleteAdvice(Advice advice);

    @Query("DELETE FROM Advice WHERE id = :id")
    void deleteAdviceById(long id);

    @Query("SELECT * FROM Advice")
    Single<List<Advice>> getAdviceListSingle();

    @Query("SELECT * FROM Advice")
    List<Advice> getAdviceList();

    @Query("SELECT * FROM Advice WHERE id = :id")
    Advice getAdviceById(long id);

    @Query("SELECT COUNT() FROM Advice")
    int getCount();
}
