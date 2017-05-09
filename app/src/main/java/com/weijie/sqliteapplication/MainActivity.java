package com.weijie.sqliteapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "weijie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PersonService personService = new PersonService(this);
        ListView listView = (ListView) this.findViewById(R.id.listView);

        PersonServiceTest personServiceTest = new PersonServiceTest(this);
        try {
            personServiceTest.testSave();
        } catch (Exception e) {
            Log.d(TAG, "testSave exception: " + e.getMessage());
        }

        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> title = new HashMap<String, String>();
        // title.put("personid", "编号");
        // title.put("name", "姓名");
        // title.put("age", "年龄");
        // data.add(title);

        // 适配器有：
        // ArrayAdapter<T>
        // simpAdapter
        // SimpleCursorAdapter
        Cursor cursor = personService.getRawScrollData(0, 10);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.personitem, cursor, new String[] { "_id", "name",
                "age" },
                new int[] { R.id.personid, R.id.name, R.id.age });
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            // parent即为你点击的listView
            // view为listview的外面布局
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String personid = String.valueOf(cursor.getInt(0));
                String name = String.valueOf(cursor.getString(1));
                String age = String.valueOf(cursor.getShort(2));
                Log.i(TAG, view.getClass().getName());
                Log.i(TAG, "personid: " + personid + "   name: " + name
                        + "   age:   " + age);
                Log.i(TAG, " position==id:" + (position == id));
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
