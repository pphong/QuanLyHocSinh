package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tdc.ltdd2.quanlyhocsinh.database.ClassDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Class;

public class ClassAddEdit extends AppCompatActivity {

    ClassDatabaseHandler classDatabaseHandler;
    TextView tvClassEditTitle, tvClassEditId;
    EditText txClassName,txClassClass,txClassGender,txClassBirth;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add_edit);
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
                    //delete class
                    int id = Integer.parseInt(tvClassEditId.getText().toString());
                    deleteClass(id);
                } else {
                    txClassName.setText("");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txClassName.getText().toString();

                if (actionEdit) {
                    //call update class
                    int id = Integer.parseInt(tvClassEditId.getText().toString());
                    Class classes = new Class(id,name);
                    updateClass(classes);
                } else {
                    Class classes = new Class(0,name);
                    addClass(classes);
                }
            }
        });
    }

    private void switchElement() {
        if (actionEdit){
            tvClassEditTitle.setText("Edit class");
            btnClear.setText("Delete");
        } else {
            tvClassEditTitle.setText("Add new class");
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
        classDatabaseHandler = new ClassDatabaseHandler(this);
        tvClassEditTitle = findViewById(R.id.tvClassEditTitle);
        tvClassEditId = findViewById(R.id.tvClassEditId);
        txClassName = findViewById(R.id.txClassName);
        btnClear = findViewById(R.id.btnAddClass);
        btnSave = findViewById(R.id.btnClassAddSave);
        btnClear = findViewById(R.id.btnClassAddClear);
    }

    private void setValues(){
        tvClassEditId.setText(this.intent.getStringExtra("classId"));
        txClassName.setText(this.intent.getStringExtra("className"));
    }

    private void addClass(Class classes) {
        classDatabaseHandler.addClass(classes);
        this.reorderToFront();
    }

    private void deleteClass(int id) {
        classDatabaseHandler.deleteClass(id);
        this.reorderToFront();
    }

    private void updateClass(Class classes) {
        classDatabaseHandler.updateClass(classes);
        this.reorderToFront();
    }

    private void reorderToFront(){
        Intent intent = getIntent().setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setClass(ClassAddEdit.this, ClassList.class);
        startActivity(intent);
    }
}