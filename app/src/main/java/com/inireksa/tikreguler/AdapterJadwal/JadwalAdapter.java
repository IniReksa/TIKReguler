package com.inireksa.tikreguler.AdapterJadwal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.tikreguler.Firebase.Info;
import com.inireksa.tikreguler.R;

import java.util.ArrayList;
import java.util.List;

import com.inireksa.tikreguler.AdapterJadwal.JadwalItem;

/**
 * Created by IniReksa on 8/11/2017.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    List<JadwalItem> rItem;

    public JadwalAdapter(){
        super();
        rItem = new ArrayList<JadwalItem>();
        JadwalItem jadwal = new JadwalItem();
        jadwal.sethari("Senin");
        jadwal.setMk1("Praktek Proyek TI");
        jadwal.setMk2("Rekayasa Perangkat Lunak");
        rItem.add(jadwal);

        jadwal = new JadwalItem();
        jadwal.sethari("Selasa");
        jadwal.setMk1("-");
        jadwal.setMk2("-");
        rItem.add(jadwal);

        jadwal = new JadwalItem();
        jadwal.sethari("Rabu");
        jadwal.setMk1("Etika Profesi");
        jadwal.setMk2("Dasar Akuntansi");
        rItem.add(jadwal);

        jadwal = new JadwalItem();
        jadwal.sethari("Kamis");
        jadwal.setMk1("Strategi Bisnis & Praktek Kewirausahaan");
        jadwal.setMk2("Pemrograman Web II");
        rItem.add(jadwal);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View jd = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jadwal, parent, false);
        ViewHolder viewHolderJd = new ViewHolder(jd);
        return viewHolderJd;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JadwalItem matkul = rItem.get(position);
        holder.hari.setText(matkul.gethari());
        holder.mtk1.setText(matkul.getMk1());
        holder.mtk2.setText(matkul.getMk2());
    }

    @Override
    public int getItemCount() {
        return rItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mtk1,mtk2,mtk3,hari;

        public ViewHolder(View itemView) {
            super(itemView);
            hari = (TextView) itemView.findViewById(R.id.hari);
            mtk1 = (TextView) itemView.findViewById(R.id.mk1);
            mtk2 = (TextView) itemView.findViewById(R.id.mk2);
        }
    }
}
