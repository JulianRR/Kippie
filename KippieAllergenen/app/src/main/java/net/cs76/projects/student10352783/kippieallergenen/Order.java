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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Order.java
 *
 * This class is used for to order some of the special products. The order will be send by mail.
 *
 * Name: Julian Ruger
 * Student ID: 10352783
 * E-mail: julian.ruger@student.uva.nl
 */
public class Order {

    private String[] keuze = {"Bami", "Nasi", "Mihoen", "Chinese Bami"};
    private String[] aantal =  {"0", "1", "2"};
    private String[] pannen = {"Snackpan Groot (90 hapjes) €29,95", "Snackapan Klein (60 hapjes) €19,95",
                               "Maaltijdpan Groot (2400 gr) €29,95", "Maaltijdpan Klein (1600 gr) €19,95"};

    Map<String, String> snackpanGroot = new HashMap<String, String>();
    Map<String, String> snackpanKlein = new HashMap<String, String>();
    Map<String, String> maaltijdpanGroot = new HashMap<String, String>();
    Map<String, String> maaltijdpanKlein = new HashMap<String, String>();

    private Spinner spinnerChoosePan, spinnerKeuze, spinnerAantal;
    private TableLayout table, table2;
    private EditText editName, editPhone, editDate;
    private Button send;
    private String pan;

    private String name, phone, date = "";



    public void setView(final View rootView, final Activity activity, final LayoutInflater inflater) {
        spinnerChoosePan = (Spinner) rootView.findViewById(R.id.choosePan);
        spinnerKeuze = (Spinner) rootView.findViewById(R.id.keuzeuit);
        spinnerAantal = (Spinner) rootView.findViewById(R.id.aantal);
        table = (TableLayout) rootView.findViewById(R.id.table1);
        table2 = (TableLayout) rootView.findViewById(R.id.table3);
        send = (Button) rootView.findViewById(R.id.send);
        editName = (EditText) rootView.findViewById(R.id.naam);
        editPhone = (EditText) rootView.findViewById(R.id.telefoon);
        editDate = (EditText) rootView.findViewById(R.id.datumAfhalen);

        /* For convenience, set default variables for the maps to prevent NullPointerExceptions*/
        snackpanGroot.put("aantal", "0");
        snackpanKlein.put("aantal", "0");
        maaltijdpanGroot.put("aantal", "0");
        maaltijdpanKlein.put("aantal", "0");
        maaltijdpanGroot.put("keuze", "Bami");
        maaltijdpanKlein.put("keuze", "Bami");



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

        /* Choose "pan" */
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* "Keuze" */
        spinnerKeuze.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerKeuze.setSelection(i);
                /* Add the chosen "keuze" to the right map.*/
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

        /* "Aantal" */
        spinnerAantal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerAantal.setSelection(i);
                /* Add the chosen "aantal" to the right map. */
                if (pan.equals(pannen[0])) {
                    snackpanGroot.put("aantal", spinnerAantal.getSelectedItem().toString());
                    setVisibility(pan, snackpanGroot, (TextView) rootView.findViewById(R.id.pan1));
                } else if (pan.equals(pannen[1])) {
                    snackpanKlein.put("aantal", spinnerAantal.getSelectedItem().toString());
                    setVisibility(pan, snackpanKlein, (TextView) rootView.findViewById(R.id.pan2));
                } else if (pan.equals(pannen[2])) {
                    maaltijdpanGroot.put("aantal", spinnerAantal.getSelectedItem().toString());
                    setVisibility(pan, maaltijdpanGroot, (TextView) rootView.findViewById(R.id.pan3));
                } else {
                    maaltijdpanKlein.put("aantal", spinnerAantal.getSelectedItem().toString());
                    setVisibility(pan, maaltijdpanKlein, (TextView) rootView.findViewById(R.id.pan4));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* Send order */
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editName.getText().toString();
                phone = editPhone.getText().toString();
                date = editDate.getText().toString();

                /* Only send the order if all the information is filled in. */
                if (name.equals("") || phone.equals("") || date.equals("") ||
                        (snackpanGroot.get("aantal").equals("0") &&
                         snackpanKlein.get("aantal").equals("0") &&
                         maaltijdpanGroot.get("aantal").equals("0") &&
                         maaltijdpanKlein.get("aantal").equals("0"))) {
                    Toast.makeText(activity, "U heeft niet alle velden ingevuld.", Toast.LENGTH_LONG).show();
                } else {
                    sendOrder(activity);

                }

            }
        });


    }

    /* Set the textview of the corresponding "pan", so the user can see what he/she orders. */
    public void setVisibility(String p, Map<String, String> map, TextView textView) {
        StringBuilder text = new StringBuilder("");
        text.append(p);

        /* Set the text */
        if (p.equals(pannen[2]) || p.equals(pannen[3])) {
            text.append(" " + map.get("keuze"));
        } if (!map.get("aantal").equals("0")) {
            text.append(" x" + map.get("aantal"));
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
            return;
        }

        textView.setVisibility(View.INVISIBLE);
        return;

    }


    /* starts email intent to send the order */
    public void sendOrder(Activity activity) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        StringBuilder text = new StringBuilder("");
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"julianruger94@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pan bestellen");
        Log.d("pan", snackpanGroot.get("aantal"));
        /* Check which "pan" is selected and add the information to the E-mail. */
        if (!snackpanGroot.get("aantal").equals("0")) {
            text.append(pannen[0] + " x" + snackpanGroot.get("aantal") + "\n");

        } if (!snackpanKlein.get("aantal").equals("0")) {
            text.append(pannen[1] + " x" + snackpanKlein.get("aantal") + "\n");

        } if (!maaltijdpanGroot.get("aantal").equals("0")) {
            text.append(pannen[2] + " " + maaltijdpanGroot.get("keuze") +
                    " x" + maaltijdpanGroot.get("aantal") + "\n");

        } if (!maaltijdpanKlein.get("aantal").equals("0")) {
            text.append(pannen[3] + " " + maaltijdpanKlein.get("keuze") +
                    " x" + maaltijdpanKlein.get("aantal") + "\n");
        }
        Log.d("text", text.toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, text.toString());
        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(activity, "Failed to start the activity.", Toast.LENGTH_LONG);
        }
    }
}
