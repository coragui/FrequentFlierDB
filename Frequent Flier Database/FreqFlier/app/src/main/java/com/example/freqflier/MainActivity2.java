package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView name = findViewById(R.id.textView2);
        TextView points = findViewById(R.id.textView5);

        Button flightdetail = findViewById(R.id.button3);
        Button flights = findViewById(R.id.button2);
        Button redemption = findViewById(R.id.button4);
        Button travelp = findViewById(R.id.button5);
        ImageView imageView = findViewById(R.id.imageView);

        RequestQueue queue = Volley.newRequestQueue(this);
        int pid = getIntent().getIntExtra("pid",0);

        String url = "http://10.0.2.2:8080/frequentflier/Info.jsp?pid="+pid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String [] cleanup = result.split("#");
                String [] output = cleanup[0].split(",");

                name.setText(output[0]);
                points.setText(output[1]);
            }
        },null);
        queue.add(request);

        String photo = "http://10.0.2.2:8080/frequentflier/images/"+pid+".jpeg";
        ImageRequest photoreq = new ImageRequest(photo, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        },0,0,null,null);
        queue.add(photoreq);
        //flight details
        flightdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
        //flight details
        flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
        //Redemption Info
        redemption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
        //Transfer points
        travelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity6.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
}