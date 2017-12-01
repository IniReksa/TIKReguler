package com.inireksa.tikreguler.Tampildata;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inireksa.tikreguler.Firebase.Info;
import com.inireksa.tikreguler.Firebase.InfoActivity;
import com.inireksa.tikreguler.R;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;
import java.util.List;

public class TampilDataActivity extends AppCompatActivity {

    TextView emptyText;

    List<Info> infos;
    TampilDataAdapter adapter;

    DatabaseReference databaseInfo;

    RecyclerView tRecyclerView;

    private InterstitialAd mInterstial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_saja);

        databaseInfo = FirebaseDatabase.getInstance().getReference("Info");

        emptyText = (TextView) findViewById(R.id.txt_no_data);

        infos = new ArrayList<>();

        tRecyclerView = (RecyclerView) findViewById(R.id.recTampilSaja);
        tRecyclerView.setHasFixedSize(true);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);

        //buat datatrakhir muncul di atas
        lin.setReverseLayout(true);
        lin.setStackFromEnd(true);

        tRecyclerView.setLayoutManager(lin);

        adapter = new TampilDataAdapter(infos);
        tRecyclerView.setAdapter(adapter);
        checkEmpty();
        tampilInfo();


    }

    private void checkEmpty(){
        if (infos.size() == 0){
            tRecyclerView.setVisibility(View.INVISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            tRecyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.INVISIBLE);
        }
    }

    private void tampilInfo(){
        databaseInfo.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                infos.add(dataSnapshot.getValue(Info.class));
                adapter.notifyDataSetChanged();
                checkEmpty();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Info model = dataSnapshot.getValue(Info.class);

                int index = getItemIndex(model);
                infos.set(index, model);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Info model = dataSnapshot.getValue(Info.class);

                int index = getItemIndex(model);
                infos.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(Info info){
        int index = -1;

        for(int i = 0; i < infos.size(); i++){
            if(infos.get(i).infoId.equals(info.infoId)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        mInterstial = new InterstitialAd(this);
        mInterstial.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        final AdRequest request = new AdRequest.Builder().build();
        mInterstial.loadAd(request);
        mInterstial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                showDialogInfo();
                mInterstial.loadAd(request);
            }
        });

        if(id == R.id.menuAdmin){
            if(mInterstial.isLoaded()){
                mInterstial.show();
            }
            else {
                showDialogInfo();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogInfo(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_info, null);

        dialogBuilder.setView(dialogView);

        final EditText edtTextDialog = (EditText) dialogView.findViewById(R.id.editTextDialog);
        final Button btnDialog = (Button) dialogView.findViewById(R.id.btnDialog);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etDialog = edtTextDialog.getText().toString();

                if (etDialog.contains("inilogininfo")){
                    Intent intent = new Intent(TampilDataActivity.this, InfoActivity.class);
                    startActivity(intent);
                    edtTextDialog.setText("");
                    alertDialog.cancel();
                }else if(etDialog.matches("")) {
                    Toast.makeText(TampilDataActivity.this, "Isi password" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TampilDataActivity.this, "password salah" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
