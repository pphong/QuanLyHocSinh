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

import tdc.ltdd2.quanlyhocsinh.adapter.classAdapter;
import tdc.ltdd2.quanlyhocsinh.adapter.studentAdapter;
import tdc.ltdd2.quanlyhocsinh.database.ClassDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Class;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class ClassList extends AppCompatActivity {

    ClassDatabaseHandler classDatabaseHandler;
    Button btnAddClass;
    ListView listView;
    List<Class> classes;
    classAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
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
                Intent intent = new Intent(ClassList.this,MainActivity.class);
                startActivity(intent);
                return true;}
            case R.id.add:{
                Intent intent = new Intent(ClassList.this,ClassAddEdit.class);
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
//        Class c1 = new Class(0,"1A");
//        this.addClass(c1);
        setCustomListView();
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassList.this,ClassAddEdit.class);
                startActivity(intent);
            }
        });
    }

    private void setCustomListView() {
        adapter = new classAdapter(this, classes);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object objClass = listView.getItemAtPosition(i);
                Class classO = (Class) objClass;
                Intent intent = getIntent();
                intent.setClass(ClassList.this, ClassAddEdit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Bundle bundle = new Bundle();
                bundle.putBoolean("edit",true);
                bundle.putString("classId",classO.getClassId()+"");
                bundle.putString("className",classO.getClassName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        classDatabaseHandler = new ClassDatabaseHandler(this);
        classes = getListData();
        listView  = (ListView) findViewById(R.id.lsClass);
        btnAddClass = (Button) findViewById(R.id.btnAddClass);
    }
    private  List<Class> getListData() {
        List<Class> list = new ArrayList<Class>();
        list.addAll(classDatabaseHandler.getAllClasses());
        return list;
    }

    private void addClass(Class classs) {
        classDatabaseHandler.addClass(classs);
    }

    private void deleteClass(int id) {
        classDatabaseHandler.deleteClass(id);
    }

    private void updateClass(Class classs) {
        classDatabaseHandler.updateClass(classs);
    }
}