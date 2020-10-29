package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.hs.emirim.sumtan.R;

public class Frag_check_shelter extends Fragment {

//    RecyclerView recyclerView;
//    PersonAdapter adapter;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_check_shelter,container,false);
//
//        recyclerView = view.findViewById(R.id.rv);
//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(recyclerView.getContext());
//        recyclerView.setLayoutManager(layoutManager1);
//        adapter = new PersonAdapter();
//        adapter.addItem(new Person("김민수","010-1000-1000"));
//        adapter.addItem(new Person("김하늘","010-2000-2000"));
//        adapter.addItem(new Person("박현","010-3000-3000"));
//        recyclerView.setAdapter(adapter);
//        adapter.setOnItemClicklistener(new OnPersonItemClickListener() {
//            @Override
//            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
//                Person item = adapter.getItem(position);
////                Toast.makeText(getContext(),"아이템 선택 " + item.getName(), Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getContext(), Frag_shelter_check_detail.class));
//            }
//        });

        return view;
    }
}