package kr.hs.emirim.sumtan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter_check extends RecyclerView.Adapter<MainAdapter_check.CustomViewHolder> {

    private ArrayList<MainData_check> arrayList;

    public MainAdapter_check(ArrayList<MainData_check> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter_check.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_check, parent, false);
        CustomViewHolder holder=new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter_check.CustomViewHolder holder, int position) {
        holder.iv_bookmark.setImageResource(arrayList.get(position).getIv_bookmark());
        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_tele.setText(arrayList.get(position).getTv_tele());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName=holder.tv_name.getText().toString();
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null!=arrayList?arrayList.size():0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_bookmark;
        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_tele;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_bookmark=(ImageView)itemView.findViewById(R.id.book_mark);
            this.iv_profile=(ImageView)itemView.findViewById(R.id.profile_img);
            this.tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            this.tv_tele=(TextView)itemView.findViewById(R.id.tv_tele);
        }
    }
}
