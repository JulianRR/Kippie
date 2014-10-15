package net.cs76.projects.student10352783.kippieallergenen;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by julianruger on 12-10-14.
 */
public class Queries {

    private static Queries instance;

    private ArrayList<String> productNamesList;
    private Map<String, List<String>> allergenenList = new HashMap<String, List<String>>();
    private Map<String, String> priceList = new HashMap<String, String>();

    public void setProductNames() {
        productNamesList = new ArrayList<String>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                for (ParseObject comment : commentList) {
                    ArrayList<String> allergenen = new ArrayList<String>();
                    Object productName = comment.get("ProductName");
                    Object productPrice = comment.get("PriceEach");
                    productNamesList.add((String) productName);
                    priceList.put((String) productName, (String) productPrice);
                    List<Object> list = new ArrayList<Object>();
                    list = comment.getList("Allergenen");

                    for (Object i : list) {
                        allergenen.add((String) i);
                    }
                    allergenenList.put((String) productName, allergenen);
                    Log.d("array", allergenenList.toString());

                }
            }
        });

        IgnoreCompareComparator icc =new IgnoreCompareComparator();
        Collections.sort(productNamesList, icc);

    }

    public ArrayList<String> getProductNames() {
        return this.productNamesList;
    }

    public Map<String, String> getPriceList() { return this.priceList; }

    public Map<String, List<String>> getAllergenenList() { return this.allergenenList; }

    public static synchronized Queries getInstance() {
        if(instance == null) {
            instance = new Queries();
        }

        return instance;
    }

    class IgnoreCompareComparator implements Comparator<String> {
        public int compare(String strA, String strB) {
            return strA.compareToIgnoreCase(strB);
        }
    }

}
