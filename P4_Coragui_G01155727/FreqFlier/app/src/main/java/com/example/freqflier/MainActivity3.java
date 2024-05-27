package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView flight_id = findViewById(R.id.textView8);
        TextView flight_miles = findViewById(R.id.textView11);
        TextView destinations = findViewById(R.id.textView12);
        Button back = findViewById(R.id.button8);

        RequestQueue queue = Volley.newRequestQueue(this);
        int pid = getIntent().getIntExtra("pid",0);

        String url = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String flightids = "";
                String flightmiles = "";
                String dests = "";
                String [] row = result.split("#");
                for(int i = 0; i < row.length;i++) {
                    String[] col = row[i].split(",");
                    flightids += col[0] + "\n";
                    flightmiles += col[1] + "\n";
                    dests += col[2] + "\n";
                }

                flight_id.setText(flightids);
                flight_miles.setText(flightmiles);
                destinations.setText(dests);
            }
        },null);
        queue.add(request);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }

}