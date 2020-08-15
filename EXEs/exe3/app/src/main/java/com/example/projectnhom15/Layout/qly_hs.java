package com.example.projectnhom15.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectnhom15.R;

public class qly_hs extends AppCompatActivity {
    Button btnAddhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qly_hs);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnAddhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qly_hs.this,add_student.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnAddhs = findViewById(R.id.btn_add_std);
    }
}