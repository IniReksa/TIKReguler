package com.inireksa.tikreguler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.inireksa.tikreguler.AdapterJadwal.JadwalAdapter;

public class JadwalActivity extends AppCompatActivity {


    RecyclerView jRecyclerView;
    RecyclerView.LayoutManager jLayoutManager;
    RecyclerView.Adapter jAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        jRecyclerView = (RecyclerView) findViewById(R.id.rvjadwal);
        jRecyclerView.setHasFixedSize(true);

        jLayoutManager = new LinearLayoutManager(this);
        jRecyclerView.setLayoutManager(jLayoutManager);

        jAdapter = new JadwalAdapter();
        jRecyclerView.setAdapter(jAdapter);

        // Load an ad into the AdMob banner view.
        AdView mAdView = (AdView) findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
