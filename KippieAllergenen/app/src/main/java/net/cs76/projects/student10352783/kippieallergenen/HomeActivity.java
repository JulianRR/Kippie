package net.cs76.projects.student10352783.kippieallergenen;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HomeActivity.java
 *
 * This activity contains the layout of the whole app, the navigation drawer and the fragments.
 * The default navigation drawer activity from android studio is used here.
 *
 * Name: Julian Ruger
 * Student ID: 10352783
 * E-mail: julian.ruger@student.uva.nl
 */
public class HomeActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Queries queries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Parse.initialize(this, "uBVqiimzahlF9p5DZZIQqB4Xd6gZUapSG0rRbgNv", "15fS3MKOedQnwG2h98WqWsCpnk1DZAiiEs5VscES");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        /* Set up the drawer. */
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        /* retrieve all the necessary variables from the database */
        queries = Queries.getInstance();
        queries.setData();



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section0);
                break;
            case 2:
                mTitle = getString(R.string.title_section1);
                break;
            case 3:
                mTitle = getString(R.string.title_section2);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /* Variables for the fragments */
        Allergenen allergensView;
        SpecialOffers specialOffers;
        Order order;
        Queries queries;

        /* The prices of the products and the allergens from the database will be stored
         * in these HashMaps.
         */
        Map<String, String> priceList = new HashMap<String, String>();
        Map<String, List<String>> allergensList = new HashMap<String, List<String>>();

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            Log.d("section", String.valueOf(sectionNumber));
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            queries = Queries.getInstance();

            ArrayList<String> productsList = queries.getProductNames();
            priceList = queries.getPriceList();
            allergensList = queries.getAllergensList();


            View rootView = inflater.inflate(R.layout.fragment_home, container, false);

            /* Change fragment content according to the chosen item. */
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {

                /* "Allergenen" fragment*/
                case 2:
                    allergensView = new Allergenen();

                    rootView = inflater.inflate(R.layout.fragment_allergenen, container, false);
                    allergensView.setView(rootView, getActivity(), inflater, productsList, priceList, allergensList);
                    break;

                /* "Aanbiedingen" fragment*/
                case 3:
                    specialOffers = new SpecialOffers();

                    rootView = inflater.inflate(R.layout.fragment_aanbiedingen, container, false);
                    specialOffers.setView(rootView, getActivity(), inflater);
                    break;

                /* "Bestellen" fragment*/
                case 4:
                    order = new Order();

                    rootView = inflater.inflate(R.layout.fragment_bestellen, container, false);
                    order.setView(rootView, getActivity(), inflater);
                    break;
            }
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }


    }

}
