package com.example.test_dawd.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_dawd.R;
import com.example.test_dawd.entity.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<Employee> employees;

    public EmployeeAdapter(Activity activity, List<Employee> employees) {
        this.activity = activity;
        this.employees = employees;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_employee, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeHolder employeeHolder = (EmployeeHolder) holder;
        Employee employee = employees.get(position);
        employeeHolder.tvName.setText(employee.getName());
        employeeHolder.tvDes.setText(employee.getDesignation());
        employeeHolder.tvSalary.setText(employee.getSalary() + "");
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSalary, tvDes;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            tvDes = itemView.findViewById(R.id.tvDes);
            tvName = itemView.findViewById(R.id.tvName);
            tvSalary = itemView.findViewById(R.id.tvSalary);
        }
    }
}
