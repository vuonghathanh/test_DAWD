package com.example.test_dawd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_dawd.database.AppDatabase;
import com.example.test_dawd.entity.Employee;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edId;
    Button btDelete;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        edId = findViewById(R.id.edDeleteId);
        btDelete = findViewById(R.id.btDl);
        appDatabase = AppDatabase.getAppDatabase(this);
    }

    private void onDelete() {
        int id = Integer.parseInt(edId.getText().toString());
        Employee employee = appDatabase.employeeDao().findEmployee(id);
        if (employee == null) {
            Toast.makeText(this, "Employee not found", Toast.LENGTH_SHORT).show();
        } else {
            int empId = appDatabase.employeeDao().deleteEmployee(employee);
            if (empId > 0) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btDl:
                onDelete();
                break;
            case R.id.btGoHome:
                goHome();
                break;
            default:
                break;
        }
    }
}