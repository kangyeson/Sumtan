package kr.hs.emirim.sumtan.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hs.emirim.sumtan.R;

public class AddressItem extends RecyclerView.Adapter<AddressItem.ViewHolder> implements AddressItemClickListeneer{
    ArrayList<Address> items = new ArrayList<Address>();
    AddressItemClickListeneer listeneer;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.address_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Address item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemClickListeneer(AddressItemClickListeneer listeneer)
    {
        this.listeneer = listeneer;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position){
        if(listeneer != null){
            listeneer.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textAddress;

        public ViewHolder(View itemView, final AddressItemClickListeneer listeneer){
            super(itemView);

            textAddress = itemView.findViewById(R.id.textAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listeneer!=null){
                        listeneer.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
        public void setItem(Address item) {
            textAddress.setText(item.getAddress2());
        }
    }

    public void addItem(Address item){
        items.add(item);
    }

    public void setItems(ArrayList<Address> items){
        this.items = items;
    }

    public Address getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Address item){
        items.set(position, item);
    }
}
