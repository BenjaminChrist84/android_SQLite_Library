package se.benjaminchrist.bibliotekv52;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amitshekhar.DebugDB;

import java.util.List;
import java.util.Random;

import static android.R.id.list;

public class MainActivity extends ListActivity{

    Bock listBock;
    List<Bock> values;

    private DatabankHelper databankH;
    String[] books = {"Life is easy","Why not", "What about me", "Forever"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databankH = new DatabankHelper(this);
        databankH.open();
        String dburl = DebugDB.getAddressLog();
        Random random = new Random(System.currentTimeMillis());
        ListView listView = (ListView)findViewById(list);

        List<Bock> values = databankH.getAllBock();
        final ArrayAdapter<Bock> adapter = new ArrayAdapter<Bock>(this, android.R.layout.simple_expandable_list_item_1, values);
        setListAdapter(adapter);

        if (values.size() < 1){
            for(int i = 0; i < books.length; i++){
                listBock = databankH.createBock(books[i]);
                adapter.add(listBock);
                adapter.notifyDataSetChanged();
            }
        }
        values = databankH.getAllBock();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, EditBook.class);
        i.putExtra("position", position);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        databankH.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        databankH.close();
    }

    public void finish(){
        super.finish();
        finishAffinity();
    }

    public void finishAffinity(){super.finishAffinity();}
}
