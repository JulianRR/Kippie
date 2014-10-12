package net.cs76.projects.student10352783.kippieallergenen;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by julianruger on 12-10-14.
 */
public class Queries {

    private static Queries instance;

    private ArrayList<String> productNamesList;

    public void setProductNames() {
        productNamesList = new ArrayList<String>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                for (ParseObject comment : commentList) {
                    Object productName = comment.get("ProductName");
                    productNamesList.add((String) productName);
                }
            }
        });

        IgnoreCompareComparator icc =new IgnoreCompareComparator();
        Collections.sort(productNamesList, icc);

    }

    public ArrayList<String> getProductNames() {
        return this.productNamesList;
    }

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
