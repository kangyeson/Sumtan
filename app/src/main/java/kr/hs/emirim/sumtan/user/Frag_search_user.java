package kr.hs.emirim.sumtan.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private String TAG = "Frag_search_user";
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView FirestoreList;
    private FirestoreRecyclerAdapter adapter;
    private View view;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.activity_frag_search_user,container,false);
        FirestoreList = (RecyclerView) view.findViewById(R.id.firestore_list);
        firebaseFirestore = FirebaseFirestore.getInstance();

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();

        Query query = firebaseFirestore.collection("Users").orderBy("sname");
        FirestoreRecyclerOptions<Shelter> options = new FirestoreRecyclerOptions.Builder<Shelter>()
                .setQuery(query, Shelter.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Shelter, ShelterViewHolder>(options) {
            @NonNull
            @Override
            public ShelterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_course, parent, false);

                submission_Button=view.findViewById(R.id.submission_Button);
                submission_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogClick(view);
                    }
                });

                return new ShelterViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ShelterViewHolder holder, int position, @NonNull Shelter shelter) {
                holder.courseTitle.setText(shelter.getSName());
                holder.courseTele.setText(shelter.getTele());
                holder.courseAddress.setText(shelter.getAddress());
            }
        };

        FirestoreList.setHasFixedSize(true);
        FirestoreList.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirestoreList.setAdapter(adapter);

        //검색
        EditText SearchField = (EditText)view.findViewById(R.id.search_text);
        Button searchbtn = (Button)view.findViewById(R.id.search_button); //지역검색
        Button search_icon_button = (Button)view.findViewById(R.id.search_icon_button); //일반검색 아이콘

        SearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged");
                Query query;
                if(s.toString().isEmpty()){
                    query = firebaseFirestore.collection("Users").orderBy("sname");
                    Log.d(TAG, "is epmpty " + s.toString());
                } else{
                    query = firebaseFirestore.collection("Users")
                            .orderBy("sname")
                            .startAt(s.toString()).endAt(s.toString()+"\uf8ff");

                    Log.d(TAG, "query " + s.toString());
                }
                FirestoreRecyclerOptions<Shelter> options = new FirestoreRecyclerOptions.Builder<Shelter>()
                        .setQuery(query, Shelter.class)
                        .build();

                adapter.updateOptions(options);
            }
        });

        search_icon_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), AddressCard.class);
                 startActivity(intent);
            }
        });


        return view;
    }

    public void DialogClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("다이얼로그").setMessage("다이얼로그가 보인다면 성공입니다. 축하합니다!");
        builder.setPositiveButton("성공", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Yeah!!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("실패", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Try again!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton("Nuetral", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"neutral click", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
        if(adapter != null){
            adapter.stopListening();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }
}