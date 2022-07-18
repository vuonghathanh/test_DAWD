package com.example.test_dawd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.test_dawd.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    long insertEmployee(Employee employee);

    @Update
    int updateEmployee(Employee employee);

    @Delete
    int deleteEmployee(Employee employee);

    @Query("SELECT * FROM employee where id = :id")
    Employee findEmployee(int id);

    @Query("SELECT * FROM employee")
    List<Employee> getAll();
}
