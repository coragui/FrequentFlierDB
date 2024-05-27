package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        EditText edittext = findViewById(R.id.editTextTextPersonName3);
        Spinner spinner = findViewById(R.id.spinner);
        Button button = findViewById(R.id.button7);
        Button back = findViewById(R.id.button11);

        final RequestQueue queue = Volley.newRequestQueue(this);
        int pid = getIntent().getIntExtra("pid", 0);
        ArrayList<String> list = new ArrayList<String>();

        String url = "http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?pid=" + pid;
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                spinner.setAdapter(adapter);
            }
        }, null);
        queue.add(request);
        final int[] passid = new int[1];

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                passid[0] = Integer.parseInt(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int points = Integer.parseInt(edittext.getText().toString());
                String url2 = "http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid=" + pid + "&dpid=" + passid[0] + "&npoints=" + points;
                StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(MainActivity6.this, "" + points + " Points transferred Successfully", Toast.LENGTH_LONG).show();
                    }
                }, null);
                queue.add(request2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity6.this,MainActivity2.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });

    }
}