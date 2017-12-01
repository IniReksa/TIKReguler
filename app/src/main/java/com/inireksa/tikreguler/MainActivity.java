package com.inireksa.tikreguler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.inireksa.tikreguler.AdapterMain.GridAdapter;
import com.inireksa.tikreguler.Tampildata.TampilDataActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtprofile;
    TextView txtnpm;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtprofile = (TextView) findViewById(R.id.textProfile);
        txtnpm = (TextView) findViewById(R.id.textnpm);

        Intent intent = getIntent();
        String profile = intent.getStringExtra("Name");
        String npm = intent.getStringExtra("npm");

        txtprofile.setText(profile);
        txtnpm.setText(npm);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /**
         * RecyclerView: Implementing single item click and long press (Part-II)
         * */
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                if(position == 0){
                    Intent intent = new Intent(MainActivity.this, DosenActivity.class);
                    startActivity(intent);
                }
                if (position == 1){
                        Intent intent = new Intent(MainActivity.this, JadwalActivity.class);
                        startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(MainActivity.this, mhsActivity.class);
                    startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(MainActivity.this, TampilDataActivity.class);
                    startActivity(intent);
                }
                else {
                }
            }


            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        mAdapter = new GridAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // Load an ad into the AdMob banner view.
        AdView mAdView = (AdView) findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Yakin keluar dari Aplikasi?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}