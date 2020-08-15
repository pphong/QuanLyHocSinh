package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.customSpinner;
import tdc.ltdd2.quanlyhocsinh.database.ClassDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.database.TeacherDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Class;
import tdc.ltdd2.quanlyhocsinh.model.SpinnerItem;
import tdc.ltdd2.quanlyhocsinh.model.Teacher;

public class TeacherAddEdit extends AppCompatActivity {

    TeacherDatabaseHandler teacherDatabaseHandler;
    TextView tvTeacherEditTitle, tvTeacherEditId;
    EditText txTeacherName,txTeacherClass,txTeacherGender,txTeacherBirth;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;
    Spinner customSpinner;
    List<Class> classes;

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

        ArrayList<SpinnerItem> customList = new ArrayList<>();
        classes = getListClassData();
        for (Class classO : classes) {
            customList.add(new SpinnerItem(classO.getClassName(), R.drawable.ic_baseline_home_work_24));
        }

        customSpinner customAdapter = new customSpinner(this, customList);

        if (customSpinner != null) {
            customSpinner.setAdapter(customAdapter);
            customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Object objClass = customSpinner.getItemAtPosition(i);
                    SpinnerItem spinnerO = (SpinnerItem) objClass;
                    txTeacherClass.setText(spinnerO.getClassName());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

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
        customSpinner = findViewById(R.id.spTeacherClass);
    }

    private void setValues(){
        tvTeacherEditId.setText(this.intent.getStringExtra("teacherId"));
        txTeacherName.setText(this.intent.getStringExtra("teacherName"));
        txTeacherClass.setText(this.intent.getStringExtra("teacherClass"));
        txTeacherGender.setText(this.intent.getStringExtra("teacherGender"));
        txTeacherBirth.setText(this.intent.getStringExtra("teacherBirth"));
    }

    private List<Class> getListClassData() {
        ClassDatabaseHandler classDatabaseHandler = new ClassDatabaseHandler(this);
        List<Class> list = new ArrayList<Class>();
        list.addAll(classDatabaseHandler.getAllClasses());
        return list;
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
//        Intent intent = getIntent().setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.setClass(TeacherAddEdit.this, TeacherList.class);
        Intent intent = new Intent(this,TeacherList.class);
        startActivity(intent);
    }
}