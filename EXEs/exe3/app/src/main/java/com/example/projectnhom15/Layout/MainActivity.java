package com.example.projectnhom15.Layout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectnhom15.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button QLLOP,QLHS,ABOUT,LOGOUT,EXIT;
    TextView txtVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setControl();
        setEvent();
    }

    private void setEvent() {

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,Login.class);
                startActivity(intent1);
            }
        });
        ABOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,about_us.class);
                startActivity(intent2);
            }
        });
        QLHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this,qly_hs.class);
                startActivity(intent3);
            }
        });
        QLLOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity.this,qly_lop.class);
                startActivity(intent4);
            }
        });

    }

    private void setControl() {
        QLLOP = findViewById(R.id.btn_Class);
        QLHS = findViewById(R.id.btn_Student);
        ABOUT = findViewById(R.id.btn_Infor);
        LOGOUT = findViewById(R.id.btn_Logout);
        txtVer = findViewById(R.id.text);
        EXIT = findViewById(R.id.btnExit);
    }
}