package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdc.ltdd2.quanlyhocsinh.database.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private Intent splash;
    Button btnViewStudents, btnViewTeachers, btnViewClasses, btnViewSubjects, btnViewAnalysis, btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        splash = new Intent(this, SplashActivity.class);
//        startActivity(splash);
        setControl();
        setEvent();
    }

    private void setControl() {
        /**
         * Buttons:
         *  btnStudents
         *  btnTeachers
         *  btnClasses
         *  btnSubjects
         *  btnAnalysis
         *  btnExit
         * */
        btnViewStudents = (Button) findViewById(R.id.btnStudents);
        btnViewTeachers = (Button) findViewById(R.id.btnTeachers);
        btnViewClasses = (Button) findViewById(R.id.btnClasses);
        btnViewSubjects = (Button) findViewById(R.id.btnSubjects);
        btnViewAnalysis = (Button) findViewById(R.id.btnAnalysis);
        btnExit = (Button) findViewById(R.id.btnExit);

    }

    private void setEvent() {
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StudentList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnViewTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TeacherList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnViewClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ClassList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnViewSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubjectList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnViewAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Analysis.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}