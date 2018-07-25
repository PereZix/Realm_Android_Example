package com.a2p.examplesimplerealm.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a2p.examplesimplerealm.Interface.OnItemClickListener;
import com.a2p.examplesimplerealm.Object.Dog;
import com.a2p.examplesimplerealm.R;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by alvaro.perez on 25/07/2018.
 */

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    private RealmResults<Dog> dogs;
    public OnItemClickListener mlistener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txvRaza;

        public ViewHolder(View itemView) {
            super(itemView);
            txvRaza = itemView.findViewById(R.id.txvRaza);
        }

        public void bind(final Dog item, final OnItemClickListener mlistener){
            txvRaza.setText(item.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onItemClick(item);
                }
            });
        }
    }

    public AdapterMain(RealmResults<Dog> values, OnItemClickListener mlistener){
        this.dogs = values;
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dogs.get(position), mlistener);
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }
}
