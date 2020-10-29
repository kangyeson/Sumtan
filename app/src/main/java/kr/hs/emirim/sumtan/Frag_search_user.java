package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class Frag_search_user extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Shelter> arrayList;
    private FirebaseDatabase database;
    private FirebaseFirestore db=null;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_search_user,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.db_list_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        db= FirebaseFirestore.getInstance();

        Query query = db.collection("Users");
        FirestoreRecyclerOptions<>
        database = FirebaseDatabase.getInstance();
        db.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                arrayList.clear();
                for(DocumentSnapshot document : task.getResult()) {
                    Shelter Shelter = new Shelter(document.getString("name"), document.getString("tele"), document.getString("address"));
                    arrayList.add(Shelter);
                }
            }
        });
        adapter = new ShelterAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);


        //검색기능
        Button searchbtn = (Button)view.findViewById(R.id.search_button);
        Button search_icon_button = (Button)view.findViewById(R.id.search_icon_button);

        search_icon_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return view;
    }
}