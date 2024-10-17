package com.nusrat.childcare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nusrat.childcare.db.DatabaseHelper;
import com.nusrat.childcare.model.User;

public class Register extends AppCompatActivity {

    private TextView member;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private EditText password;
    private RadioGroup type;
    private Button register;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize DatabaseHelper
        openHelper = new DatabaseHelper(this);


        firstname = findViewById(R.id.inputFname);
        lastname = findViewById(R.id.inputLname);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        type = findViewById(R.id.inputUserType);

        register = findViewById(R.id.btnRegister);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = openHelper.getWritableDatabase();
                User userModel = new User();

                // user input
                userModel.setFirstname(firstname.getText().toString());
                userModel.setLastname(lastname.getText().toString());
                userModel.setFullname(userModel.getFirstname() + " " + userModel.getLastname());
                userModel.setPassword(password.getText().toString());
                userModel.setEmail(email.getText().toString());


                if (type.getCheckedRadioButtonId() == R.id.parent) {
                    userModel.setType("Parents");
                } else if (type.getCheckedRadioButtonId() == R.id.helper) {
                    userModel.setType("Helper");
                }


                if (userModel.isEmpty()) {
                    Toast.makeText(Register.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
                    firstname.setError("First name is required");
                    lastname.setError("Last name is required");
                    email.setError("Email is required");
                    password.setError("Password is required");

                } else if (!Patterns.EMAIL_ADDRESS.matcher(userModel.getEmail()).matches()) {
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    email.setError("Enter a valid email");

                } else if (userModel.getPassword().length() < 4) {
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    password.setError("Password must contain at least 4 characters");

                } else {
                    // Insert data if validation passes
                    if (insertData(userModel)) {
                        // Clear the input fields
                        firstname.setText(null);
                        lastname.setText(null);
                        password.setText(null);
                        email.setText(null);
                        Toast.makeText(Register.this, "You have been registered successfully", Toast.LENGTH_SHORT).show();
                        userMember();

                    } else {
                        Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Handle case when the user is already a member
        member = findViewById(R.id.member);
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMember();  // Redirect to login
            }
        });
    }

    // Method to insert user data into the database
    public Boolean insertData(User user) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, user.getFullname());
        contentValues.put(DatabaseHelper.COL_3, user.getType());
        contentValues.put(DatabaseHelper.COL_4, user.getEmail());
        contentValues.put(DatabaseHelper.COL_5, user.getPassword());

        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        user.setId((int) id);

        // Return true if insert is successful
        return id != -1;
    }

    // Redirect to the Login activity
    private void userMember() {
        Intent openLogin = new Intent(Register.this, LoginPage.class);
        startActivity(openLogin);
    }

}