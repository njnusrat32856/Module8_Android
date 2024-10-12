package com.nusrat.childcareapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nusrat.childcareapp.R;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    private RadioGroup rgAgeGroup;
    private Button btnSubmit;


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

        rgAgeGroup = findViewById(R.id.rg_age_group);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                int selectedId = rgAgeGroup.getCheckedRadioButtonId();
                Intent intent = new Intent(Home.this, AdviceActivity.class);

                if (selectedId == R.id.rbage01) {
                    intent.putExtra("ageGroup", "0-1");
                } else if (selectedId == R.id.rbage13) {
                    intent.putExtra("ageGroup", "1-3");
                } else if (selectedId == R.id.rbage47) {
                    intent.putExtra("ageGroup", "4-7");
                } else if (selectedId == R.id.rbage810) {
                    intent.putExtra("ageGroup", "8-10");
                }
//                switch (selectedId) {
//                    case R.id.rbage01:
//                        intent.putExtra("ageGroup", "0-1");
//                        break;
//                    case R.id.rbage13:
//                        intent.putExtra("ageGroup", "1-3");
//                        break;
//                    case R.id.rbage47:
//                        intent.putExtra("ageGroup", "4-7");
//                        break;
//                    case R.id.rbage810:
//                        intent.putExtra("ageGroup", "8-10");
//                        break;
//                }
                startActivity(intent);
            }
        });
    }
}