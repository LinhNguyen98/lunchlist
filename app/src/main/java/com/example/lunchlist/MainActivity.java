package com.example.lunchlist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MainActivity extends Activity {
private Restaurant r = new Restaurant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave); //

    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant restaurant = new Restaurant();
            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);
            RadioGroup types = (RadioGroup)findViewById(R.id.types);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            switch (types.getCheckedRadioButtonId()) {
                case R.id.take_out:
                    restaurant.setType("Take out");
                    break;
                case R.id.sit_down:
                    restaurant.setType("Sit down");
                    break;
                case R.id.delivery:
                    restaurant.setType("Delivery");
                    break;
                default:
                    break;
            }
//            helper.insert(restaurant.getName(), restaurant.getAddress(),
//                    restaurant.getType());
//            curRestaurant.requery();
        }
    };
}