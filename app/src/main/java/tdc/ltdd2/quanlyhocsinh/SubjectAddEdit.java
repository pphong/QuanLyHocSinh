package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tdc.ltdd2.quanlyhocsinh.database.SubjectDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Subject;

public class SubjectAddEdit extends AppCompatActivity {

    SubjectDatabaseHandler subjectDatabaseHandler;
    TextView tvSubjectEditTitle, tvSubjectEditId;
    EditText txSubjectName;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add_edit);
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
                    //delete subject
                    int id = Integer.parseInt(tvSubjectEditId.getText().toString());
                    deleteSubject(id);
                } else {
                    txSubjectName.setText("");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txSubjectName.getText().toString();

                if (actionEdit) {
                    //call update subject
                    int id = Integer.parseInt(tvSubjectEditId.getText().toString());
                    Subject subjects = new Subject(id,name);
                    updateSubject(subjects);
                } else {
                    Subject subjects = new Subject(0,name);
                    addSubject(subjects);
                }
            }
        });
    }

    private void switchElement() {
        if (actionEdit){
            tvSubjectEditTitle.setText("Edit subject");
            btnClear.setText("Delete");
        } else {
            tvSubjectEditTitle.setText("Add new subject");
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
        subjectDatabaseHandler = new SubjectDatabaseHandler(this);
        tvSubjectEditTitle = findViewById(R.id.tvSubjectEditTitle);
        tvSubjectEditId = findViewById(R.id.tvSubjectEditId);
        txSubjectName = findViewById(R.id.txSubjectName);
        btnClear = findViewById(R.id.btnAddSubject);
        btnSave = findViewById(R.id.btnSubjectAddSave);
        btnClear = findViewById(R.id.btnSubjectAddClear);
    }

    private void setValues(){
        tvSubjectEditId.setText(this.intent.getStringExtra("subjectId"));
        txSubjectName.setText(this.intent.getStringExtra("subjectName"));
    }

    private void addSubject(Subject subjects) {
        subjectDatabaseHandler.addSubject(subjects);
        this.reorderToFront();
    }

    private void deleteSubject(int id) {
        subjectDatabaseHandler.deleteSubject(id);
        this.reorderToFront();
    }

    private void updateSubject(Subject subjects) {
        subjectDatabaseHandler.updateSubject(subjects);
        this.reorderToFront();
    }

    private void reorderToFront(){
//        Intent intent = getIntent().setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.setClass(this, SubjectList.class);
        Intent intent = new Intent(this,SubjectList.class);
        startActivity(intent);
    }
}