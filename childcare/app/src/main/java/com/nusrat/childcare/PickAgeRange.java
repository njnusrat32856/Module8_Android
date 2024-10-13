package com.nusrat.childcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PickAgeRange extends AppCompatActivity {

    private Button submission;
    private TextView age;
    private int minage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pick_age_range);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the button and text view components
        submission = findViewById(R.id.btnSubmit);
        age = findViewById(R.id.inputAge);

        // Set up the submit button click listener
        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int num = Integer.parseInt(age.getText().toString());

                    // Check the age range and call the appropriate method
                    if (num >= 0 && num < 6) {
                        minusThanSeven();
                    } else if (num >= 6 && num <= 10) {
                        moreThanSeven();
                    } else {
                        Toast.makeText(PickAgeRange.this, "Age must be between 0 and 10", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(PickAgeRange.this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to increase age when "increase" button is clicked
    public void increaseAge(View view) {
        minage += 1;
        display(minage);
    }

    // Method to decrease age when "decrease" button is clicked
    public void decreaseAge(View view) {
        if (minage > 0) {
            minage -= 1;
        }
        display(minage);
    }

    // Method to display the current age value in the text view
    private void display(int number) {
        TextView displayInteger = findViewById(R.id.inputAge);
        displayInteger.setText(String.valueOf(number));
    }

    // Navigate to home screen for children aged less than 7 years
    private void minusThanSeven() {
        Intent openHome1 = new Intent(this, Home.class);
        startActivity(openHome1);
        finish();
    }

    // Navigate to home screen for children aged 7 years and above
    private void moreThanSeven() {
        Intent openHome2 = new Intent(this, Home.class);
        startActivity(openHome2);
        finish();
    }
}