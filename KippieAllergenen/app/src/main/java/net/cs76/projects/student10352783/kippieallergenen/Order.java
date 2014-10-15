package net.cs76.projects.student10352783.kippieallergenen;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by julianruger on 14-10-14.
 */
public class Order {

    private String[] keuze = {"Bami", "Nasi", "Mihoen", "Chinese Bami"};
    private String[] aantal =  {"0", "1", "2"};
    private String[] pannen = {"Snackpan Groot (90 hapjes) €29,59", "Snackapan Klein (60 hapjes) €19,95",
                               "Maaltijdpan Groot (2400 gr) €29,95", "Maaltijdpan (1600 gr) Klein €19,95"};

    Map<String, String> snackpanGroot = new HashMap<String, String>();
    Map<String, String> snackpanKlein = new HashMap<String, String>();
    Map<String, String> maaltijdpanGroot = new HashMap<String, String>();
    Map<String, String> maaltijdpanKlein = new HashMap<String, String>();

    private Spinner spinnerChoosePan, spinnerKeuze, spinnerAantal;
    private TableLayout table, table2;
    private Button send;
    private String pan;

    private String name, phone, date = "";



    public void setView(View rootView, final Activity activity, final LayoutInflater inflater) {
        spinnerChoosePan = (Spinner) rootView.findViewById(R.id.choosePan);
        spinnerKeuze = (Spinner) rootView.findViewById(R.id.keuzeuit);
        spinnerAantal = (Spinner) rootView.findViewById(R.id.aantal);
        table = (TableLayout) rootView.findViewById(R.id.table1);
        table2 = (TableLayout) rootView.findViewById(R.id.table3);
        send = (Button) rootView.findViewById(R.id.send);

        /* array adapters for the spinners.*/
        ArrayAdapter<String> adapter_pan =
                new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, pannen);

        adapter_pan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoosePan.setAdapter(adapter_pan);

        ArrayAdapter<String> adapter_keuze =
                new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, keuze);

        adapter_keuze.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKeuze.setAdapter(adapter_keuze);

        ArrayAdapter<String> adapter_aantal =
                new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, aantal);

        adapter_aantal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAantal.setAdapter(adapter_aantal);

        /* Choose pan */
        spinnerChoosePan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerChoosePan.setSelection(i);
                table.setVisibility(View.VISIBLE);
                pan = (String) spinnerChoosePan.getSelectedItem();
                if (pan.equals(pannen[0]) || pan.equals(pannen[1])) {
                    table.getChildAt(0).setVisibility(View.INVISIBLE);
                } else {
                    table.getChildAt(0).setVisibility(View.VISIBLE);
                }
                Toast.makeText(activity, "test", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* Keuze */
        spinnerKeuze.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerKeuze.setSelection(i);
                if (pan.equals(pannen[2])) {
                    maaltijdpanGroot.put("keuze", spinnerKeuze.getSelectedItem().toString());
                } else {
                    maaltijdpanKlein.put("keuze", spinnerKeuze.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* Aantal */
        spinnerAantal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerAantal.setSelection(i);
                if (pan.equals(pannen[0])) {
                    snackpanGroot.put("aantal", spinnerAantal.getSelectedItem().toString());
                    addRow(pan, snackpanGroot, table2, activity);
                } else if (pan.equals(pannen[1])) {
                    snackpanKlein.put("aantal", spinnerAantal.getSelectedItem().toString());
                    addRow(pan, snackpanKlein, table2, activity);
                } else if (pan.equals(pannen[2])) {
                    maaltijdpanGroot.put("aantal", spinnerAantal.getSelectedItem().toString());
                    addRow(pan, maaltijdpanGroot, table2, activity);
                } else {
                    maaltijdpanKlein.put("aantal", spinnerAantal.getSelectedItem().toString());
                    addRow(pan, maaltijdpanKlein, table2, activity);
                }
                Log.d("SG", snackpanGroot.toString());
                Log.d("SK", snackpanKlein.toString());
                Log.d("MG", maaltijdpanGroot.toString());
                Log.d("MK", maaltijdpanKlein.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* Send order */
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOrder(activity);
            }
        });


    }

    /* Adds a row to the view to see what the users orders */
    public void addRow(String p, Map<String, String> map, TableLayout table, Activity activity) {
        TableRow row = new TableRow(activity);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        TextView pan = new TextView(activity);
        TextView keuze = new TextView(activity);
        TextView aantal = new TextView(activity);
        pan.setText(p);
        if (p.equals(pannen[2]) || p.equals(pannen[3])) {
            keuze.setText(map.get("keuze").toString());
        } else {
            keuze.setText("");
        }
        aantal.setText("x" + map.get("aantal").toString());

        row.addView(pan);
        row.addView(keuze);
        row.addView(aantal);
        table.addView(row);

    }

    /* starts email intent to send the order */
    public void sendOrder(Activity activity) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"julianruger94@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "test subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, pan + " x" + snackpanGroot.get("aantal").toString());
        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(activity, "Fail", Toast.LENGTH_LONG);
        }
    }
}
