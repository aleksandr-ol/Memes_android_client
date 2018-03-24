package com.immortal.memes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.immortal.memes.server.Mem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.ViewHolder> {
    ArrayList<Mem> memes_list;

    public MemesAdapter(ArrayList<Mem> memes_list){
        this.memes_list = memes_list;
        setHasStableIds(true);
    }

    @Override
    public MemesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memes_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemesAdapter.ViewHolder holder, int position) {
        holder.textView.setText(memes_list.get(position).getText());

        if (memes_list.get(position).getAttachments().size() > 0)
        Picasso.with(holder.itemView.getContext())
                .load("http://192.168.100.102:5000/" + memes_list.get(position).getAttachments().get(0).getPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.memes_list.size();
    }

    public void add(Mem mem) {
        insert(mem, this.memes_list.size());
    }

    public void insert(Mem mem, int position) {
        this.memes_list.add(position, mem);
        this.notifyItemInserted(position);
    }

    public void addAll(ArrayList<Mem> mems) {
        int startIndex = this.memes_list.size();
        this.memes_list.addAll(startIndex, mems);
        notifyItemRangeInserted(startIndex, mems.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_imageView);
        }
    }
}
