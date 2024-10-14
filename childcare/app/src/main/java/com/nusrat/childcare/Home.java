package com.nusrat.childcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    private ImageView a, b, c, d, e, f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ImageView components for different sections
        a = findViewById(R.id.imgHealth);
        b = findViewById(R.id.imgVacc);
        c = findViewById(R.id.imgSleep);
        d = findViewById(R.id.imgFood);
        e = findViewById(R.id.imgGuide);
        f = findViewById(R.id.imgBaby);

        // Set click listeners for each ImageView to open respective activities
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityHealthCare();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityVaccine();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivitySleep();
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityFood();
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityGuide();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityBaby();
            }
        });
    }

    // Methods to open different activities
    public void openActivityHealthCare() {
        Intent intent = new Intent(this, HealthCare.class);
        startActivity(intent);
    }

    public void openActivityVaccine() {
        Intent intent = new Intent(this, Vaccine.class);
        startActivity(intent);
    }

    public void openActivitySleep() {
        Intent intent = new Intent(this, Sleep.class);
        startActivity(intent);
    }

    public void openActivityFood() {
        Intent intent = new Intent(this, Food.class);
        startActivity(intent);
    }

    public void openActivityGuide() {
        Intent intent = new Intent(this, Guide.class);
        startActivity(intent);
    }

    public void openActivityBaby() {
        Intent intent = new Intent(this, Baby.class);
        startActivity(intent);
    }
}