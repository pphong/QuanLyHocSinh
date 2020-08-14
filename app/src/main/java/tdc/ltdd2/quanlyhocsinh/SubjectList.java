package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.subjectAdapter;
import tdc.ltdd2.quanlyhocsinh.database.SubjectDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Subject;

public class SubjectList extends AppCompatActivity {

    SubjectDatabaseHandler subjectDatabaseHandler;
    Button btnAddSubject;
    ListView listView;
    List<Subject> subjects;
    subjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        setControl();
        setEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:{
                Intent intent = new Intent(SubjectList.this,MainActivity.class);
                startActivity(intent);
                return true;}
            case R.id.add:{
                Intent intent = new Intent(SubjectList.this,ClassAddEdit.class);
                startActivity(intent);
                return true;}
            case R.id.refresh:{
                finish();
                startActivity(getIntent());
                return true;}
        }

        return super.onOptionsItemSelected(item);
    }

    private void setEvent() {
//        Subject c1 = new Subject(0,"1A");
//        this.addSubject(c1);
        setCustomListView();
        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectList.this,SubjectAddEdit.class);
                startActivity(intent);
            }
        });
    }

    private void setCustomListView() {
        adapter = new subjectAdapter(this, subjects);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object objSubject = listView.getItemAtPosition(i);
                Subject subject = (Subject) objSubject;
                Intent intent = getIntent();
                intent.setClass(SubjectList.this, SubjectAddEdit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Bundle bundle = new Bundle();
                bundle.putBoolean("edit",true);
                bundle.putString("subjectId",subject.getSubjectId()+"");
                bundle.putString("subjectName",subject.getSubjectName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        subjectDatabaseHandler = new SubjectDatabaseHandler(this);
        subjects = getListData();
        listView  = (ListView) findViewById(R.id.lsSubject);
        btnAddSubject = (Button) findViewById(R.id.btnAddSubject);
    }
    private  List<Subject> getListData() {
        List<Subject> list = new ArrayList<Subject>();
        list.addAll(subjectDatabaseHandler.getAllSubjects());
        return list;
    }

    private void addSubject(Subject subject) {
        subjectDatabaseHandler.addSubject(subject);
    }

    private void deleteSubject(int id) {
        subjectDatabaseHandler.deleteSubject(id);
    }

    private void updateSubject(Subject subjects) {
        subjectDatabaseHandler.updateSubject(subjects);
    }
}