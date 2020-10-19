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
import android.database.Cursor;
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.AdapterView;

public class MainActivity extends TabActivity {
/*
private Restaurant r = new Restaurant();
*/
private List<Restaurant> listRestaurant = new ArrayList<Restaurant>();
    RestaurantAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave);
        ListView list =(ListView)findViewById(R.id.restaurants);
        list.setOnItemClickListener(onListClick);

        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);

        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details",getResources().getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);
    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();
            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);
            RadioGroup types = (RadioGroup)findViewById(R.id.type);
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

        }
    };
    class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        public RestaurantAdapter(Context context, int textViewResoureId) {
            super(context, textViewResoureId);
        }

        public RestaurantAdapter() {
            super(MainActivity.this, android.R.layout.simple_list_item_1, listRestaurant);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            if(row==null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);
            }
            Restaurant r = listRestaurant.get(position);
            ((TextView)row.findViewById(R.id.title)).
                    setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).
                    setText(r.getAddress());
            ImageView icon = (ImageView)row.findViewById(R.id.icon);
            String type = r.getType();
            if (type.equals("Take out"))
                icon.setImageResource(R.drawable.icon1);
            else if (type.equals("Sit down"))
                icon.setImageResource(R.drawable.icon2);
            else
                icon.setImageResource(R.drawable.icon3);
            return row;

        }
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
Restaurant r = listRestaurant.get(position);
            EditText name;
            EditText address;
            RadioGroup types;

            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.type);

            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("Sit down"))
                types.check(R.id.sit_down);
            else if (r.getType().equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);

            getTabHost().setCurrentTab(1);
        }
    };





}