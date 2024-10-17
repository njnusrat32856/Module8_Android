package com.nusrat.childcare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nusrat.childcare.db.DatabaseHelper;
import com.nusrat.childcare.model.User;

public class LoginPage extends AppCompatActivity {

    private TextView notMember;
    private EditText email;
    private EditText password;
    private Button login;
    private TextView log;

    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();


        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);
        notMember = findViewById(R.id.notMember);
        log = findViewById(R.id.log);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();

                // Validate the input fields
                if (mail.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    email.setError("Enter a valid Email");
                } else {
                    // Query the database to check if the email and password match
                    cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE Email = ? AND Password = ?", new String[]{mail, pass});
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        User user = new User();
                        user.setFullname(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_2)));
                        user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_4)));
                        user.setType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_3)));

                        // Clear input fields after successful login
                        email.setText(null);
                        password.setText(null);
                        loginMember();
                    } else {
                        Toast.makeText(LoginPage.this, "Password or email is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // "Not a member?" TextView
        notMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNotMember();
            }
        });

        // log TextView (Additional functionality)
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLog();
            }
        });
    }


    private void userNotMember() {
        Intent openRegister = new Intent(this, Register.class);
        startActivity(openRegister);
        this.finish();
    }


    private void loginMember() {
        Intent openNextActivity = new Intent(this, PickAgeRange.class);
        startActivity(openNextActivity);
        this.finish();
    }


    private void userLog() {
        Intent openLog = new Intent(this, PickAgeRange.class);
        startActivity(openLog);
        this.finish();
    }

}