package com.example.projectnhom15.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectnhom15.R;

public class qly_lop extends AppCompatActivity {
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qly_lop);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qly_lop.this,add_class.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnAdd = findViewById(R.id.btn_add_lop);
    }
}