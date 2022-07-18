package com.example.test_dawd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_dawd.adapter.EmployeeAdapter;
import com.example.test_dawd.database.AppDatabase;
import com.example.test_dawd.entity.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edName, edSalary, edDes;
    Button btCreate, btEdit, btDelete;
    AppDatabase appDatabase;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.getAppDatabase(this);
        edName = findViewById(R.id.edName);
        edDes = findViewById(R.id.edDes);
        edSalary = findViewById(R.id.edSalary);
        btEdit = findViewById(R.id.btUpdate);
        btCreate = findViewById(R.id.btAdd);
        btDelete = findViewById(R.id.btDelete);
        btCreate.setOnClickListener(this);
        renderRv();
    }

    private void renderRv() {
        List<Employee> list = appDatabase.employeeDao().getAll();
        EmployeeAdapter adapter = new EmployeeAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.rvEmployee);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void onCreate() {
        if(validate()) {
            Employee employee = new Employee();
            employee.setDesignation(edDes.getText().toString());
            employee.setName(edName.getText().toString());
            employee.setSalary(Long.parseLong(edSalary.getText().toString()));
            long id = appDatabase.employeeDao().insertEmployee(employee);
            if (id > 0) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                renderRv();
            }
        }
    }

    private boolean validate() {
        String mes = null;
        if (edName.getText().toString().trim().isEmpty()) {
            mes = "Name not found";
        }
        if (edDes.getText().toString().trim().isEmpty()) {
            mes = "Designation no found";
        }
        if (edSalary.getText().toString().trim().isEmpty()) {
            mes = "Salary no found";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void goToPageDelete() {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

    private void goToPageUpdate() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btAdd:
                onCreate();
                break;
            case R.id.btDelete:
                goToPageDelete();
                break;
            case R.id.btUpdate:
                goToPageUpdate();
                break;
            default:
                break;
        }
    }
}