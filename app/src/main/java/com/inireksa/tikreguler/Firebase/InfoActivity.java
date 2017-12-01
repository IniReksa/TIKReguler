package com.inireksa.tikreguler.Firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inireksa.tikreguler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 8/26/2017.
 */

public class InfoActivity extends AppCompatActivity {

    Spinner spinnerjdl;
    EditText editTextisi;
    Button btnAdd;
    TextView emptyText;

    List<Info> infos;
    InfoAdapter adapter;

    DatabaseReference databaseInfo;

    RecyclerView iRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        databaseInfo = FirebaseDatabase.getInstance().getReference("Info");

        spinnerjdl = (Spinner) findViewById(R.id.spinerJudul);
        editTextisi = (EditText) findViewById(R.id.editTextIsi);
        btnAdd = (Button) findViewById(R.id.btnInfo);
        emptyText = (TextView) findViewById(R.id.txt_no_data);

        infos = new ArrayList<>();

        iRecyclerView = (RecyclerView) findViewById(R.id.recInfo);
        iRecyclerView.setHasFixedSize(true);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);

        iRecyclerView.setLayoutManager(lin);

        adapter = new InfoAdapter(infos);
        iRecyclerView.setAdapter(adapter);
        checkEmpty();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahinfo();
            }
        });

        tampilInfo();
    }

    private void tambahinfo(){
        String judul = spinnerjdl.getSelectedItem().toString();
        String isi = editTextisi.getText().toString();

        if(!TextUtils.isEmpty(isi)){

            //menambahkan id unik sebagai primarykeu untuk info
            String id = databaseInfo.push().getKey();

            //buat objek info
            Info info = new Info(id, judul, isi);

            //mnyimpan data
            databaseInfo.child(id).setValue(info);

            editTextisi.setText("");

            Toast.makeText(this, "Info baru di tambah", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Isi info terlebih dahulu", Toast.LENGTH_LONG).show();
        }
    }

    private void checkEmpty(){
        if (infos.size() == 0){
            iRecyclerView.setVisibility(View.INVISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            iRecyclerView.setVisibility(View.VISIBLE);
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

    //tampilin pilihan recyclerview
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 0:
                hapusInfo(item.getGroupId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void hapusInfo(int position){
        databaseInfo.child(infos.get(position).infoId).removeValue();
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

        if(id == R.id.menuAdmin){
            Toast.makeText(InfoActivity.this, "Anda Adalah Admin" , Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
