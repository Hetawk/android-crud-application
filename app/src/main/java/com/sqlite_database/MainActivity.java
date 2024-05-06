package com.sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sqlite_database.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvResult;
    private String mDatabaseName;
    private Button btnDatabaseCreate, btnDatabaseDelete, btnOpenCrudActivity;

    private UserDBHelper dbHelper;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        btnDatabaseCreate = findViewById(R.id.btn_database_create);
        btnDatabaseDelete = findViewById(R.id.btn_database_delete);
        btnOpenCrudActivity = findViewById(R.id.btn_open_crud_activity);

        btnDatabaseCreate.setOnClickListener(this);
        btnDatabaseDelete.setOnClickListener(this);
        btnOpenCrudActivity.setOnClickListener(this);

        mDatabaseName = getDatabasePath("user.db").getPath();
        dbHelper = new UserDBHelper(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_database_create) {
            createDatabase();
        } else if (id == R.id.btn_database_delete) {
            deleteDatabase();
        }
         else if (id == R.id.btn_open_crud_activity) {
            openCrudActivity(); // Handle the button click to open the crud activity
        }
        // Add more if-else blocks for other buttons


    }

    private void createDatabase() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String desc; // Declare desc outside the try-catch block
                try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
                    desc = String.format("Database %s create %s", mDatabaseName, db.isOpen() ? "success" : "failure");
                    Log.i("MainActivity", desc);
                    Log.i("DatabaseCreation", desc); // Log the database deletion status
                } catch (SQLiteException e) {
                    e.printStackTrace();
                    desc = getString(R.string.error_creating_database);
                    Log.e("DatabaseCreation", "Error creating database: " + e.getMessage()); // Log the error message
                }
                final String finalDesc = desc; // Assign desc to a final variable for use inside the inner class
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(finalDesc);
                    }
                });
            }
        });
    }

    private void deleteDatabase() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String desc; // Declare desc outside the try-catch block
                try {
                    boolean result = MainActivity.this.deleteDatabase(mDatabaseName);
                    desc = String.format("Database %s delete %s", mDatabaseName, result ? "success" : "failure");
                    Log.i("DatabaseDeletion", desc); // Log the database deletion status
                } catch (Exception e) {
                    e.printStackTrace();
                    desc = getString(R.string.error_deleting_database);
                    Log.e("DatabaseDeletion", "Error deleting database: " + e.getMessage()); // Log the error message
                }
                final String finalDesc = desc; // Assign desc to a final variable for use inside the inner class
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(finalDesc);
                    }
                });
            }
        });
    }

    private void openCrudActivity() {
        Intent intent = new Intent(this, crud.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // Shut down the ExecutorService
    }
}


