package com.inireksa.tikreguler.Tampildata;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.tikreguler.Firebase.Info;
import com.inireksa.tikreguler.Firebase.InfoAdapter;
import com.inireksa.tikreguler.R;

import java.util.List;

/**
 * Created by Muhamad Angga Reksa on 9/23/2017.
 */

public class TampilDataAdapter extends RecyclerView.Adapter<TampilDataAdapter.ViewHolder> {

    private List<Info> task;

    public TampilDataAdapter(List<Info> task){
        this.task = task;
    }

    @Override
    public TampilDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inf = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info, parent, false);
        ViewHolder viewHolderIf = new ViewHolder(inf);
        return viewHolderIf;
    }

    @Override
    public void onBindViewHolder(TampilDataAdapter.ViewHolder holder, int position) {
        Info info = task.get(position);
        holder.jdlInfo.setText(info.getInfojudul());
        holder.isiInfo.setText(info.getInfoIsi());
    }

    @Override
    public int getItemCount() {
        return this.task.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView jdlInfo, isiInfo;

        public ViewHolder(View itemView){
            super(itemView);
            jdlInfo = (TextView) itemView.findViewById(R.id.textViewJudul);
            isiInfo = (TextView) itemView.findViewById(R.id.textViewIsi);
        }
    }
}
