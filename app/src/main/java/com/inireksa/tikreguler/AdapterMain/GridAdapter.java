package com.inireksa.tikreguler.AdapterMain;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inireksa.tikreguler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 8/4/2017.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<EndangeredItem> mItems;

    public GridAdapter() {
        super();
        mItems = new ArrayList<EndangeredItem>();
        EndangeredItem nama = new EndangeredItem();
        nama.setName("Dosen");
        nama.setThumbnail(R.drawable.ic_dosen);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Jadwal ");
        nama.setThumbnail(R.drawable.ic_matkul);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Mahasiswa");
        nama.setThumbnail(R.drawable.ic_mhs);
        mItems.add(nama);

        nama = new EndangeredItem();
        nama.setName("Info");
        nama.setThumbnail(R.drawable.ic_info);
        mItems.add(nama);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_gridmain, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        EndangeredItem nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());

        /**ini semua list d klik sama aksi
         * viewHolder.cvmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Cardview diklik", Snackbar.LENGTH_LONG).show();
                }
        });**/
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {


        public ImageView imgThumbnail;
        public TextView tvspecies;
        public CardView cvmain;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView)itemView.findViewById(R.id.status);
            cvmain = (CardView)itemView.findViewById(R.id.cardviewMain);
        }
    }
}