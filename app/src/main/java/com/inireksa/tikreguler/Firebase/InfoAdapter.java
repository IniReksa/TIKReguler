package com.inireksa.tikreguler.Firebase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.tikreguler.R;

import java.util.List;

/**
 * Created by IniReksa on 8/26/2017.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private List<Info> task;

    public InfoAdapter(List<Info> task) {
        this.task = task;
    }

    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inf = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info, parent, false);
        ViewHolder viewHolderIf = new ViewHolder(inf);
        return viewHolderIf;
    }

    @Override
    public void onBindViewHolder(final InfoAdapter.ViewHolder holder, int position) {
        Info info = task.get(position);
        holder.jdlInfo.setText(info.getInfojudul());
        holder.isiInfo.setText(info.getInfoIsi());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(),0 ,0 ,"Hapus");
            }
        });
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
