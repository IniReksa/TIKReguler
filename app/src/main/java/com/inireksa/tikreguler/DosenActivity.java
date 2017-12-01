package com.inireksa.tikreguler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inireksa.tikreguler.AdapterDosen.DsnAdapter;

public class DosenActivity extends AppCompatActivity {

    RecyclerView dRecyclerView;
    RecyclerView.LayoutManager dLayoutManager;
    RecyclerView.Adapter dAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        dRecyclerView = (RecyclerView) findViewById(R.id.recDsn);
        dRecyclerView.setHasFixedSize(true);

        dLayoutManager = new LinearLayoutManager(this);
        dRecyclerView.setLayoutManager(dLayoutManager);

        dAdapter = new DsnAdapter();
        dRecyclerView.setAdapter(dAdapter);
    }
}
