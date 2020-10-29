package kr.hs.emirim.sumtan.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.shelter.Shelter;

public class Frag_search_user extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView FirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private View view;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    private String user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_search_user,container,false);
        FirestoreList = (RecyclerView) view.findViewById(R.id.firestore_list);
        firebaseFirestore = FirebaseFirestore.getInstance();

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();

        firebaseFirestore.collection("Users").document(user_id);
        Query query = firebaseFirestore.collection("Users");
        FirestoreRecyclerOptions<Shelter> options = new FirestoreRecyclerOptions.Builder<Shelter>()
                .setQuery(query, Shelter.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Shelter, ShelterViewHolder>(options) {
            @NonNull
            @Override
            public ShelterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_course, parent, false);
                return new ShelterViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ShelterViewHolder holder, int position, @NonNull Shelter shelter) {
                holder.courseTitle.setText(shelter.getName());
                holder.courseTele.setText(shelter.getTele());
                holder.courseAddress.setText(shelter.getAddress());
            }
        };

        FirestoreList.setHasFixedSize(true);
        FirestoreList.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirestoreList.setAdapter(adapter);

        //검색
        Button searchbtn = (Button)view.findViewById(R.id.search_button);
        Button search_icon_button = (Button)view.findViewById(R.id.search_icon_button);

        search_icon_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               // getFragmentManager().beginTransaction().replace(R.id.my_search_page, new Course()).commit();
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getActivity(), AddressCard.class);
               // startActivity(intent);
            }
        });


        return view;
    }

    private class ShelterViewHolder extends RecyclerView.ViewHolder{
        private TextView courseTitle;
        private TextView courseTele;
        private TextView courseAddress;
        public ShelterViewHolder(@NonNull View itemView){
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseTele = itemView.findViewById(R.id.courseTele);
            courseAddress = itemView.findViewById(R.id.courseAddress);
        }

    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart(){
        super.onStart();
        user_id=currentUser.getUid();
        adapter.startListening();
    }
}