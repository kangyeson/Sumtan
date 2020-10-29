package kr.hs.emirim.sumtan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ShelterViewHolder> {
    Context context;
    ArrayList<Shelter> arrayList;

    public ShelterAdapter(ArrayList<Shelter> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShelterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_course, parent, false);
        ShelterViewHolder holder = new ShelterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShelterViewHolder holder, int position){
        holder.courseTitle.setText(arrayList.get(position).getName());
        holder.courseTele.setText(arrayList.get(position).getTele());
        holder.courseAddress.setText(arrayList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public static class ShelterViewHolder extends RecyclerView.ViewHolder{
        TextView courseTitle;
        TextView courseTele;
        TextView courseAddress;

        public ShelterViewHolder(@NonNull View itemView){
            super(itemView);
            this.courseTitle = itemView.findViewById(R.id.courseTitle);
            this.courseTele = itemView.findViewById(R.id.courseTele);
            this.courseAddress = itemView.findViewById(R.id.courseAddress);
        }
    }
}
