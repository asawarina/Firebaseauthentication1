package com.example.a300283513.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.auction.asawari.AuctionObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnedAuction extends AppCompatActivity {


    private DatabaseReference mDatabase ;
    private ListView dataList;
    private ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owned_auctions);
        final ArrayList<AuctionObject> auctions = new ArrayList<AuctionObject>();
        dataList = (ListView) findViewById(R.id.ownuser_list);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        final HashMap<String, AuctionObject> datamap = new HashMap<>();
        final String current_user = getIntent().getExtras().getString("user1");

        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot temp1:dataSnapshot.getChildren())
                {
                    AuctionObject zabuza = temp1.getValue(AuctionObject.class);



                    String startDateString = zabuza.getStartDate().toString()+" "+zabuza.getStartTime().toString();

                    Calendar c = Calendar.getInstance();
                    try {

                        String pattern = "yyyy-MM-dd HH:mm";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        // Date date1 = simpleDateFormat.parse("2018-11-25 13:19");
                        Date date1 = simpleDateFormat.parse(startDateString);

                        System.out.println("date1 "+ date1.toString());
                        System.out.println("current date "+ c.getTime().toString());


                        if(date1.before(c.getTime())&&zabuza.getLastbid().equalsIgnoreCase(current_user)) {
                            System.out.println("continue");

                        }
                        else{
                            if(zabuza.getCreatedBy().equalsIgnoreCase(current_user)){
                                System.out.println("finished");
                                datamap.put(temp1.getKey(),zabuza);
                                auctions.add(zabuza);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (auctions.size()!=0) {
                    for (AuctionObject temp: auctions)
                    {     //  temp.getStartDate()<currenttime
                        items.add("Auction Name :- "+temp.getName()+"\n"+"Description :- "+temp.getDescription()+"\n"
                        +"Won By :- "+temp.getLastbid()+"\n"+"Sold For :- "+temp.getMinPrice());
                    }
                }
                dataList.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* dataList.setClickable(true);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key="";

                *//*Intent intent = new Intent(MyAuction.this, BidPage.class);
                intent.putExtra("auction",auctions.get(position));
                intent.putExtra("name",auctions.get(position).getName());
                intent.putExtra("desc",auctions.get(position).getDescription());
                intent.putExtra("createdby",auctions.get(position).getCreatedBy());
                intent.putExtra("startdate",auctions.get(position).getStartDate());
                intent.putExtra("starttime",auctions.get(position).getStartTime());
                intent.putExtra("bid",auctions.get(position).getMinPrice());
                intent.putExtra("user1",current_user);
                for(String itr: datamap.keySet())
                {
                    AuctionObject tempAuction=datamap.get(itr);
                    if( auctions.get(position).getName().equalsIgnoreCase(tempAuction.getName())
                            && auctions.get(position).getDescription().equalsIgnoreCase(tempAuction.getDescription())
                            &&auctions.get(position).getCreatedBy().equalsIgnoreCase(tempAuction.getCreatedBy()))
                    {
                        key=itr;
                    }
                    intent.putExtra("key",key);
                    Toast.makeText(MyAuction.this,key,Toast.LENGTH_LONG).show();
                }


                startActivity(intent);
                finish();

                Toast.makeText(MyAuction.this,auctions.get(position).getStartDate(),Toast.LENGTH_LONG).show();*//*
            }
        });*/
    }
}
