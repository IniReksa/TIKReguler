package com.inireksa.tikreguler.AdapterMhs;

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
 * Created by Muhamad Angga Reksa on 9/24/2017.
 */

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.ViewHolder> {

    List<MhsItem> mhsItems;

    public MhsAdapter(){
        super();
        mhsItems = new ArrayList<MhsItem>();
        MhsItem mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Aldy Putra Indra");
        mhs.setNpmMhs("15371068");
        mhs.setFtMhs(R.drawable.di);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Diki Dermawan");
        mhs.setNpmMhs("15371038");
        mhs.setFtMhs(R.drawable.dik);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Angga Ganda Winata");
        mhs.setNpmMhs("15371051");
        mhs.setFtMhs(R.drawable.bir);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Mupti Maulana Bahri");
        mhs.setNpmMhs("15371065");
        mhs.setFtMhs(R.drawable.bah);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Muhamad Andita Bagas");
        mhs.setNpmMhs("15371067");
        mhs.setFtMhs(R.drawable.gas);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Mas Muhammad Derian");
        mhs.setNpmMhs("15371017");
        mhs.setFtMhs(R.drawable.der);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Muhamad Angga Reksa");
        mhs.setNpmMhs("15371016");
        mhs.setFtMhs(R.drawable.ane);
        mhsItems.add(mhs);

        mhs = new MhsItem();
        mhs.setFtMhs(R.drawable.ic_mhs);
        mhs.setNmMhs("Nurjaman");
        mhs.setNpmMhs("15371029");
        mhs.setFtMhs(R.drawable.nrman);
        mhsItems.add(mhs);
    }

    @Override
    public MhsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View m = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mhs, parent, false);
        ViewHolder viewHolder = new ViewHolder(m);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MhsAdapter.ViewHolder holder, int position) {
        MhsItem mhsItem = mhsItems.get(position);
        holder.ftMhs.setImageResource(mhsItem.getFtMhs());
        holder.NamaMhs.setText(mhsItem.getNmMhs());
        holder.NpmMhs.setText(mhsItem.getNpmMhs());

    }

    @Override
    public int getItemCount() {
        return mhsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView NamaMhs, NpmMhs;
        public ImageView ftMhs;

        public ViewHolder(View ItemView){
            super(ItemView);
            ftMhs = (ImageView) itemView.findViewById(R.id.icon_mhs);
            NamaMhs = (TextView) itemView.findViewById(R.id.NmMhs);
            NpmMhs = (TextView) itemView.findViewById(R.id.NpmMhs);
        }
    }
}
