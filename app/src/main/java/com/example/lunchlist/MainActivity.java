package com.example.lunchlist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.AdapterView;
import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
/*
private Restaurant r = new Restaurant();
*/
private List<Restaurant> listRestaurant = new ArrayList<Restaurant>();
private ArrayAdapter<Restaurant> adapter =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave);
        ListView list =(ListView)findViewById(R.id.restaurants);

        adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1,listRestaurant);
        list.setAdapter(adapter);

    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();
            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);
            RadioGroup types = (RadioGroup)findViewById(R.id.types);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            switch (types.getCheckedRadioButtonId()) {
                case R.id.take_out:
                    r.setType("Take out");
                    break;
                case R.id.sit_down:
                    r.setType("Sit down");
                    break;
                case R.id.delivery:
                    r.setType("Delivery");
                    break;
                default:
                    break;
            }
            listRestaurant.add(r);
//            helper.insert(restaurant.getName(), restaurant.getAddress(),
//                    restaurant.getType());
//            curRestaurant.requery();
        }
    };
}