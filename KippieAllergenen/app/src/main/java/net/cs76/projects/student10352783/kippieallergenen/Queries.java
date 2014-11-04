package net.cs76.projects.student10352783.kippieallergenen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Queries.java
 *
 * This class is used to get all the information from the database.
 *
 * Name: Julian Ruger
 * Student ID: 10352783
 * E-mail: julian.ruger@student.uva.nl
 */
public class Queries {

    private static Queries instance;

    private ArrayList<String> productNamesList = new ArrayList<String>();
    private Map<String, List<String>> allergensList = new HashMap<String, List<String>>();
    private Map<String, String> priceList = new HashMap<String, String>();

    private Bitmap bitmap;
    private ArrayList<Bitmap> posters = new ArrayList<Bitmap>();

    /* called at the beginning of the app, to set all the information */
    public void setData() {
        productNamesList.clear();
        allergensList.clear();
        priceList.clear();
        posters.clear();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
        query.orderByAscending("ProductName");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                for (ParseObject comment : commentList) {
                    ArrayList<String> allergens = new ArrayList<String>();

                    /* Get the right information ffrom the right collumn of the database table */
                    Object productName = comment.get("ProductName");
                    Object productPrice = comment.get("PriceEach");
                    List<Object> list = comment.getList("Allergenen");

                    /* Cast to string and fill the Lists and Maps.*/
                    for (Object i : list) {
                        allergens.add((String) i);
                    }

                    productNamesList.add((String) productName);
                    priceList.put((String) productName, (String) productPrice);
                    allergensList.put((String) productName, allergens);

                }
            }
        });

//        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("SpecialOffers");
//        query2.getFirstInBackground(new GetCallback<ParseObject>() {
//            @Override
//            public void done(ParseObject parseObject, ParseException e) {
//                if (parseObject != null) {
//                    ParseFile file = (ParseFile) parseObject.get("Poster");
//                    file.getDataInBackground(new GetDataCallback() {
//                        @Override
//                        public void done(byte[] bytes, ParseException e) {
//                            if (e == null) {
//                                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                                Log.d("bitmap", bitmap.toString());
//                            } else {
//
//                            }
//                        }
//                    });
//                }
//            }
//        });

        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("SpecialOffers");
        query3.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                for (ParseObject comment : commentList) {
                    ParseFile file = (ParseFile) comment.get("Poster");
                    file.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] bytes, ParseException e) {
                            if (e == null) {
                                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                posters.add(bitmap);
                                Log.d("bitmap", bitmap.toString());
                            } else {

                            }
                        }
                    });
                }
            }
        });
    }

    public ArrayList<String> getProductNames() {
        return this.productNamesList;
    }

    public Map<String, String> getPriceList() { return this.priceList; }

    public Map<String, List<String>> getAllergensList() { return this.allergensList; }

    public ArrayList<Bitmap> getBitmap() { return this.posters; }

    public static synchronized Queries getInstance() {
        if(instance == null) {
            instance = new Queries();
        }

        return instance;
    }



}
