package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView textview1 = findViewById(R.id.textView10);
        TextView textview2 = findViewById(R.id.textView15);
        Spinner spinner = findViewById(R.id.spinner);
        Button back = findViewById(R.id.button10);

        final RequestQueue queue = Volley.newRequestQueue(this);
        int pid = getIntent().getIntExtra("pid", 0);
        ArrayList<String> list = new ArrayList<String>();

        String url = "http://10.0.2.2:8080/frequentflier/AwardIds.jsp?pid=" + pid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] row = result.split("#");
                for (int i = 0; i < row.length; i++) {
                    String[] col = row[i].split(",");
                    if(!list.contains(col[0]))
                        list.add(col[0]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                spinner.setAdapter(adapter);
            }
        }, null);
        queue.add(request);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int awardid = 1;
                awardid = Integer.parseInt(parent.getSelectedItem().toString());

                String url2 = "http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?awardid="+awardid+"&pid="+pid;
                StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result = s.trim();
                        String flightdetails = "";
                        String trips = "";
                        String[] col = new String[4];

                        String[] row = result.split("#");
                        for (int i = 0; i < row.length; i++) {
                            col = row[i].split(",");
                            trips += col[2] + "                            " + col[3] + "\n";
                        }
                        flightdetails = "Prize:\n" + col[0] + "\n\n\n" + "Points Needed:\n" + col[1] + " Points\n";
                        textview1.setText(flightdetails);
                        textview2.setText(trips);
                    }
                }, null);
                queue.add(request2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this,MainActivity2.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
}