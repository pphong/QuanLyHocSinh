package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tdc.ltdd2.quanlyhocsinh.database.TeacherDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Teacher;

public class TeacherAddEdit extends AppCompatActivity {

    TeacherDatabaseHandler teacherDatabaseHandler;
    TextView tvTeacherEditTitle, tvTeacherEditId;
    EditText txTeacherName,txTeacherClass,txTeacherGender,txTeacherBirth;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_edit);
        actionEdit = false;
        checkAction();
        setControl();
        switchElement();
        setEvent();
    }

    private void setEvent() {
        if (actionEdit)
        {
            setValues();
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actionEdit) {
                    //delete teacher
                    int id = Integer.parseInt(tvTeacherEditId.getText().toString());
                    deleteTeacher(id);
                } else {
                    txTeacherName.setText("");
                    txTeacherClass.setText("");
                    txTeacherGender.setText("");
                    txTeacherBirth.setText("");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txTeacherName.getText().toString();
                String classs = txTeacherClass.getText().toString();
                String gender = txTeacherGender.getText().toString();
                String bod = txTeacherBirth.getText().toString();

                if (actionEdit) {
                    //call update teacher
                    int id = Integer.parseInt(tvTeacherEditId.getText().toString());
                    Teacher teacher = new Teacher(id,name,classs,gender,bod);
                    updateTeacher(teacher);
                } else {
                    Teacher teacher = new Teacher(0,name,classs,gender,bod);
                    addTeacher(teacher);
                }
            }
        });
    }

    private void switchElement() {
        if (actionEdit){
            tvTeacherEditTitle.setText("Edit teacher");
            btnClear.setText("Delete");
        } else {
            tvTeacherEditTitle.setText("Add new teacher");
        }
    }

    private void checkAction() {
        intent = getIntent();
        boolean edit = intent.getBooleanExtra("edit",false);
        if (edit) {
            actionEdit = true;
            Bundle bundle = intent.getExtras();
        }
    }

    private void setControl() {
        teacherDatabaseHandler = new TeacherDatabaseHandler(this);
        tvTeacherEditTitle = findViewById(R.id.tvTeacherEditTitle);
        tvTeacherEditId = findViewById(R.id.tvTeacherEditId);
        txTeacherName = findViewById(R.id.txTeacherName);
        txTeacherClass = findViewById(R.id.txTeacherClass);
        txTeacherGender = findViewById(R.id.txTeacherGender);
        txTeacherBirth = findViewById(R.id.txTeacherBirth);
        btnClear = findViewById(R.id.btnAddTeacher);
        btnSave = findViewById(R.id.btnTeacherAddSave);
        btnClear = findViewById(R.id.btnTeacherAddClear);
    }

    private void setValues(){
        tvTeacherEditId.setText(this.intent.getStringExtra("teacherId"));
        txTeacherName.setText(this.intent.getStringExtra("teacherName"));
        txTeacherClass.setText(this.intent.getStringExtra("teacherClass"));
        txTeacherGender.setText(this.intent.getStringExtra("teacherGender"));
        txTeacherBirth.setText(this.intent.getStringExtra("teacherBirth"));
    }

    private void addTeacher(Teacher teacher) {
        teacherDatabaseHandler.addTeacher(teacher);
        this.reorderToFront();
    }

    private void deleteTeacher(int id) {
        teacherDatabaseHandler.deleteTeacher(id);
        this.reorderToFront();
    }

    private void updateTeacher(Teacher teacher) {
        teacherDatabaseHandler.updateTeacher(teacher);
        this.reorderToFront();
    }

    private void reorderToFront(){
        Intent intent = getIntent().setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setClass(TeacherAddEdit.this, TeacherList.class);
        startActivity(intent);
    }
}