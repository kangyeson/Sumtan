package kr.hs.emirim.sumtan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
        implements OnPersonItemClickListener{
    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener listener;
    @NonNull
    @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.activity_frag__person_item,viewGroup,false);
        return new ViewHolder(itemView,this);
    }
    @Override public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);
        viewHolder.setItem(item);
    }
    @Override public int getItemCount() {
        return items.size();
    }
    public void setOnItemClicklistener(OnPersonItemClickListener listener){
        this.listener = listener;
    }
    @Override public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameText;
        TextView mobileText;
        public ViewHolder(View itemView, final OnPersonItemClickListener listener){
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            mobileText = itemView.findViewById(R.id.mobileText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        } public void setItem(Person item){
            nameText.setText(item.getName());
            mobileText.setText(item.getMobile());
        }
    }
    public void addItem(Person item){
        items.add(item);
    }
    public void setItems(ArrayList<Person> items){
        this.items = items;
    }
    public Person getItem(int position){
        return items.get(position);
    }
    public void setItem(int position, Person item){
        items.set(position,item);
    }
}
