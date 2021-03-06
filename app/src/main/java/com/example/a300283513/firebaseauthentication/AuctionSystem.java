package com.example.a300283513.firebaseauthentication;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AuctionSystem extends AppCompatActivity {


    Button btncreate,btnauction,btnwon,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_system);


        btncreate=(Button)findViewById(R.id.create);
        btnauction=(Button)findViewById(R.id.myauction);
        btnwon=(Button)findViewById(R.id.won);
        btnback=(Button)findViewById(R.id.owned);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuctionSystem.this, CreateAuction.class);
                String user =  getIntent().getExtras().getString("user1");
                intent.putExtra("user1",user);
                startActivity(intent);

            }
        });

        btnauction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =  getIntent().getExtras().getString("user1");
                Intent intent = new Intent(AuctionSystem.this, MyAuction.class);
                intent.putExtra("user1",user);
                startActivity(intent);

            }
        });

        btnwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =  getIntent().getExtras().getString("user1");
                Intent intent = new Intent(AuctionSystem.this, AuctionWon.class);
                intent.putExtra("user1",user);
                startActivity(intent);

            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =  getIntent().getExtras().getString("user1");
                Intent intent = new Intent(AuctionSystem.this, OwnedAuction.class);
                intent.putExtra("user1",user);
                startActivity(intent);

            }
        });


    }


}
