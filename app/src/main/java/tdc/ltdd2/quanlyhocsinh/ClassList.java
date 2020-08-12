//package tdc.ltdd2.quanlyhocsinh;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import tdc.ltdd2.quanlyhocsinh.adapter.classAdapter;
//import tdc.ltdd2.quanlyhocsinh.database.ClassDatabaseHandler;
//import tdc.ltdd2.quanlyhocsinh.model.Class;
//
//public class ClassList extends AppCompatActivity {
//
//    ClassDatabaseHandler classDatabaseHandler;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_class_list);
//        setControl();
//        setEvent();
//    }
//
//    private void setEvent() {
//        Class c1 = new Class(0,"1A");
//        Class c2 = new Class(0,"1B");
//        Class c3 = new Class(0,"2A");
//        this.addClass(c1);
//        this.addClass(c2);
//        this.addClass(c3);
//    }
//
//    private void setControl() {
//        classDatabaseHandler = new ClassDatabaseHandler(this);
//        List<Class> image_details = getListData();
//        final ListView listView = (ListView) findViewById(R.id.lsClass);
//        listView.setAdapter(new classAdapter(this, image_details));
//
//        // When the user clicks on the ListItem
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Object o = listView.getItemAtPosition(i);
//                Class classs = (Class) o;
//                // nhet vo intent pass qua detail class
//            }
//        });
//    }
//    private  List<Class> getListData() {
//        List<Class> list = new ArrayList<Class>();
//        list.addAll(classDatabaseHandler.getAllClasses());
//        return list;
//    }
//
//    private void addClass(Class classs) {
//        classDatabaseHandler.addClass(classs);
//    }
//
//    private void deleteClass(int id) {
//        classDatabaseHandler.deleteClass(id);
//    }
//
//    private void updateClass(Class classs) {
//        classDatabaseHandler.updateClass(classs);
//    }
//}