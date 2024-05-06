package com.sqlite_database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class crud extends Activity implements View.OnClickListener {
    private EditText etName, etAge, etHeight, etWeight;
    private Button btnSave, btnDelete, btnUpdate, btnQuery, btnShowTableData;
    private CheckBox cbMarried; // Add CheckBox reference
    private UserDBHelper dbHelper;
    private LinearLayout llUserInfoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud);

        // Initialize UI elements
        etName = findViewById(R.id.et_crud_name);
        etAge = findViewById(R.id.et_crud_age);
        etHeight = findViewById(R.id.et_crud_height);
        etWeight = findViewById(R.id.et_crud_weight);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        btnQuery = findViewById(R.id.btn_query);
        cbMarried = findViewById(R.id.cb_crud_marriage); // Initialize CheckBox
        llUserInfoContainer = findViewById(R.id.ll_user_info_container);
        // Inside `onCreate` method, after setting click listeners
        btnShowTableData = findViewById(R.id.btn_show_table_data);


        // Set click listeners
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnShowTableData.setOnClickListener(this);

        // Initialize DBHelper
        dbHelper = new UserDBHelper(this);
    }

    private void addUserToUI(User user) {
        // Inflate the user info layout
        View userInfoLayout = getLayoutInflater().inflate(R.layout.user_info_layout, null);

        // Find views inside the inflated layout
        TextView tvUserName = userInfoLayout.findViewById(R.id.tv_user_name);
        TextView tvUserAge = userInfoLayout.findViewById(R.id.tv_user_age);
        TextView tvUserHeight = userInfoLayout.findViewById(R.id.tv_user_height);
        TextView tvUserWeight = userInfoLayout.findViewById(R.id.tv_user_weight);
        TextView tvUserMarried = userInfoLayout.findViewById(R.id.tv_user_married);
        TextView tvUserUpdateTime = userInfoLayout.findViewById(R.id.tv_user_update_time);

        // Set user information to the views
        tvUserName.setText("Name: " + user.getName());
        tvUserAge.setText("Age: " + user.getAge());
        tvUserHeight.setText("Height: " + user.getHeight());
        tvUserWeight.setText("Weight: " + user.getWeight());
        tvUserMarried.setText("Married: " + (user.isMarried() ? "Yes" : "No"));
        tvUserUpdateTime.setText("Update Time: " + user.getUpdateTime());

        // Add the inflated layout to the user info container
        llUserInfoContainer.addView(userInfoLayout);
    }

    private void clearUserInfoUI() {
        llUserInfoContainer.removeAllViews(); // Clear all user info blocks
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            saveUser();
        } else if (view.getId() == R.id.btn_delete) {
            promptAndDeleteUser();
        } else if (view.getId() == R.id.btn_update) {
            updateUser();
        } else if (view.getId() == R.id.btn_query) {
            queryUsers();
        } else if (view.getId() == R.id.btn_show_table_data) {
        showAllUserData();
    }
    }

    private void showAllUserData() {
        // Clear existing user info UI
        clearUserInfoUI();

        // Query all users from the database
        List<User> userList = dbHelper.queryAll();

        // Add each user to the UI
        for (User user : userList) {
            addUserToUI(user);
        }
    }





    private void saveUser() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();

        if (name.isEmpty() || ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);
        long height = Long.parseLong(heightStr);
        float weight = Float.parseFloat(weightStr);
        boolean married = cbMarried.isChecked();; // Initialized as false, you need to implement this
        String updateTime = getCurrentTime(); // Initialized with a placeholder, you need to implement this

        User user = new User(name, age, height, weight, married, updateTime);
        long result = dbHelper.saveUser(user);

        if (result != -1) {
            Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to save user", Toast.LENGTH_SHORT).show();
        }
    }



    private void promptAndDeleteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter User ID");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userIdStr = input.getText().toString().trim();
                if (!userIdStr.isEmpty()) {
                    int userId = Integer.parseInt(userIdStr);
                    deleteUser(userId);
                } else {
                    Toast.makeText(crud.this, "Please enter user ID to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteUser(int userId) {
        boolean result = dbHelper.deleteUser(userId);

        if (result) {
            Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter User ID");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userIdStr = input.getText().toString().trim();
                if (!userIdStr.isEmpty()) {
                    int userId = Integer.parseInt(userIdStr);
                    updateUserById(userId);
                } else {
                    Toast.makeText(crud.this, "Please enter user ID to update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void updateUserById(int userId) {
        // Assuming you have the updated user details
        // For example, you can get them from text fields
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();

        if (name.isEmpty() || ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);
        long height = Long.parseLong(heightStr);
        float weight = Float.parseFloat(weightStr);
        boolean married = cbMarried.isChecked(); // Get the checked status of CheckBox
        String updateTime = getCurrentTime(); // Get current time

        User user = new User(name, age, height, weight, married, updateTime);
        boolean result = dbHelper.updateUserById(userId, user);

        if (result) {
            Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    private void queryUsers() {
        List<User> userList = dbHelper.queryAll();

        if (userList.isEmpty()) {
            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (User user : userList) {
                stringBuilder.append("ID: ").append(user.getId()).append("\n");
                stringBuilder.append("Name: ").append(user.getName()).append("\n");
                stringBuilder.append("Age: ").append(user.getAge()).append("\n");
                stringBuilder.append("Height: ").append(user.getHeight()).append("\n");
                stringBuilder.append("Weight: ").append(user.getWeight()).append("\n");
                stringBuilder.append("Married: ").append(user.isMarried() ? "Yes" : "No").append("\n");
                stringBuilder.append("Update Time: ").append(user.getUpdateTime()).append("\n\n");
            }
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        }
    }



    private void clearFields() {
        etName.setText("");
        etAge.setText("");
        etHeight.setText("");
        etWeight.setText("");
    }
}
