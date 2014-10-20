package net.cs76.projects.student10352783.kippieallergenen;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Allergenen.java
 *
 * This class is used to set the view for list of products with all the corresponding information.
 *
 * Name: Julian Ruger
 * Student ID: 10352783
 * E-mail: julian.ruger@student.uva.nl
 */
public class Allergenen {

    private ListView listView;
    EditText search;
    ArrayAdapter<String> adapter;
    private PopupWindow popupWindow;
    private View popupView;

    String price;

    /* This method sets the view for the "Allergenen" fragment */
    public void setView(View rootView, Activity activity, final LayoutInflater inflater,
                        ArrayList<String> productNames, final Map<String, String> priceList,
                        final Map<String, List<String>> allergensList) {

        adapter = new ArrayAdapter<String>(activity, R.layout.list_item, R.id.product_name, productNames);

        listView = (ListView) rootView.findViewById(R.id.list_view);
        search = (EditText) rootView.findViewById(R.id.inputSearch);

        listView.setAdapter(adapter);

        /* set filter for the searchbar */
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

        /* Open popup window with information about the chosen product. */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object obj = listView.getAdapter().getItem(i);
                String name = obj.toString();

                /* Create and show the popup window. */
                popupView = inflater.inflate(R.layout.popup_product, null);
                popupWindow = new PopupWindow(popupView, -1, -1, true);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                /* Set the information about the products. */

                TextView pName = (TextView) popupView.findViewById(R.id.name);
                pName.setText(name);

                TextView pPrice = (TextView) popupView.findViewById(R.id.prijsValue);
                pPrice.setText(priceList.get(name));

                TextView pAllergens = (TextView) popupView.findViewById(R.id.allergenenInfo);
                pAllergens.setText(allergensList.get(name).toString());

                Button close = (Button) popupView.findViewById(R.id.close);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        return;
    }
}
