package net.cs76.projects.student10352783.kippieallergenen;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by julianruger on 12-10-14.
 */
public class Allergenen {

    private ListView listView;
    EditText search;
    ArrayAdapter<String> adapter;

    public void setView(View rootView, Activity activity) {
        String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};
        Arrays.sort(products);

        listView = (ListView) rootView.findViewById(R.id.list_view);
        search = (EditText) rootView.findViewById(R.id.inputSearch);

        adapter = new ArrayAdapter<String>(activity, R.layout.list_item, R.id.product_name, products);
        listView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = listView.getAdapter().getItem(i);
                String value = obj.toString();
                Log.d("item", value);
            }
        });

        return;
    }
}
