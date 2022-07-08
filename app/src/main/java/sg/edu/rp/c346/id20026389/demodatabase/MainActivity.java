package sg.edu.rp.c346.id20026389.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnGetTasks;
    TextView tvResults;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert=findViewById(R.id.btnInsert);
        btnGetTasks=findViewById(R.id.btnGetTask);
        tvResults=findViewById(R.id.tvResults);
        listView=findViewById(R.id.listView);
        ArrayList newArray=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,newArray);
        listView.setAdapter(adapter);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(MainActivity.this);
                //Insert a task
                db.insertTask("Submit RJ","25 Apr 2021");

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                ArrayList<Task> datalist=db.getTasks();
                db.close();
                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                    newArray.add(datalist.get(i).getId() + " \n" +datalist.get(i).getDescription() + " \n"  + datalist.get(i).getDate() + " \n" );
                    adapter.notifyDataSetChanged();
                }
                tvResults.setText(txt);
            }
        });
    }
}