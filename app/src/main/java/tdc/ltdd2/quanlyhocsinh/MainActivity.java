package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Intent splash;
    Button btnViewStudents, btnViewTeachers, btnViewClasses, btnViewSubjects, btnViewAnalysis, btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splash = new Intent(this, SplashActivity.class);
        startActivity(splash);
        setEvent();
        setControl();
    }

    private void setEvent() {
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

    private void setControl() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}