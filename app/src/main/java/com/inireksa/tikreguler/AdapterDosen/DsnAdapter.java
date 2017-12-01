package com.inireksa.tikreguler.AdapterDosen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.tikreguler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhamad Angga Reksa on 9/24/2017.
 */

public class DsnAdapter extends RecyclerView.Adapter<DsnAdapter.ViewHolder>{

    List<DsnItem> dsnItems;

    public DsnAdapter(){
        super();
        dsnItems = new ArrayList<DsnItem>();
        DsnItem dsn = new DsnItem();
        dsn.setNmDsn("Anton Sukamto, S.Kom.,M.Ti");
        dsn.setMkDsn1("o Praktek Proyek TI");
        dsn.setMkDsn2("o Rekayasa Perangkat Lunak");
        dsnItems.add(dsn);

        dsn = new DsnItem();
        dsn.setNmDsn("Drs. Richard Kusuma, MBA");
        dsn.setMkDsn1("o Etika Profesi");
        dsnItems.add(dsn);

        dsn = new DsnItem();
        dsn.setNmDsn("Aryanto Nur, S.E, M.M");
        dsn.setMkDsn1("o Dasar Akuntansi");
        dsn.setMkDsn2("");
        dsnItems.add(dsn);

        dsn = new DsnItem();
        dsn.setNmDsn("Doni Wihartika, S.Si, M.M");
        dsn.setMkDsn1("o Stategi Bisnis & Kewirausahaan");
        dsn.setMkDsn2("");
        dsnItems.add(dsn);

        dsn = new DsnItem();
        dsn.setNmDsn("Adriana Sari Aryani, S.Kom, M.Cs");
        dsn.setMkDsn1("o Pemrograman Web II");
        dsn.setMkDsn2("");
        dsnItems.add(dsn);
    }

    @Override
    public DsnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View d = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dosen, parent, false);
        ViewHolder viewHolderD = new ViewHolder(d);
        return viewHolderD;
    }

    @Override
    public void onBindViewHolder(DsnAdapter.ViewHolder holder, int position) {
        DsnItem dsnItem = dsnItems.get(position);
        holder.NmDosen.setText(dsnItem.getNmDsn());
        holder.Matkul1.setText(dsnItem.getMkDsn1());
        holder.Matkul2.setText(dsnItem.getMkDsn2());
    }

    @Override
    public int getItemCount() {
        return dsnItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView NmDosen, Matkul1, Matkul2;

        public ViewHolder(View itemView){
            super(itemView);
            NmDosen = (TextView) itemView.findViewById(R.id.NmDsn);
            Matkul1 = (TextView) itemView.findViewById(R.id.MkDsn1);
            Matkul2 = (TextView) itemView.findViewById(R.id.MkDsn2);
        }
    }
}
