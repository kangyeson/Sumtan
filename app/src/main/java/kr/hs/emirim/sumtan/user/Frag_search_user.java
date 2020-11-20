package kr.hs.emirim.sumtan.user;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.Register.LoginActivity;
import kr.hs.emirim.sumtan.shelter.MainActivity_shelter;
import kr.hs.emirim.sumtan.shelter.Shelter;

public class Frag_search_user extends Fragment {
    private String TAG = "Frag_search_user";
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView FirestoreList;
    private Button submission_Button;
    private FirestoreRecyclerAdapter adapter;
    private View view;

    private FirebaseAuth mAuth;
    FirebaseFirestore db=null;
    private FirebaseUser currentUser=null;
    private String user_id;
    private String Sr_shelterName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.activity_frag_search_user,container,false);
        FirestoreList = (RecyclerView) view.findViewById(R.id.firestore_list);
        firebaseFirestore = FirebaseFirestore.getInstance();

        mAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        currentUser= mAuth.getCurrentUser();

        Query query = firebaseFirestore.collection("Users").orderBy("sname");
        FirestoreRecyclerOptions<Shelter> options = new FirestoreRecyclerOptions.Builder<Shelter>()
                .setQuery(query, Shelter.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Shelter, ShelterViewHolder>(options) {
            @NonNull
            @Override
            public ShelterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);

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
                searchbtn.setOnClickListener(this);

            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public void DialogClick(View view) {
        DocumentReference docRef=db.collection("Resume").document(user_id);
        DocumentReference userRef=db.collection("Users").document(user_id);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("이력서를 제출하시겠습니까?");
        builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getContext(), "아니오 누름", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getContext(), "예 누름", Toast.LENGTH_SHORT).show();
                //이제 shelter로 옮기기 데이터 resume...
                userRef.update("NowResume", 1);
                DocumentReference docShel=db.collection("Users").document(user_id);
                docShel.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            assert document != null;
                            if (document.exists()) {
                                Shelter shelter=document.toObject(Shelter.class);
                                assert shelter != null;
                                String shelterId=shelter.getShelterid();
                                TextView shelterName = view.findViewById(R.id.courseTitle);
                                Sr_shelterName=shelterName.getText().toString();

                                db.collection("Users").whereEqualTo("sname", Sr_shelterName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for(QueryDocumentSnapshot document:task.getResult()){
                                                String shelterId=document.getId();
                                                String shelterTele= (String) document.get("tele");
                                                docRef.update("shelter_name", Sr_shelterName);
                                                docRef.update("shelter_id", shelterId);
                                                userRef.update("shelter_name", Sr_shelterName);
                                                userRef.update("shelter_id", shelterId);
                                                userRef.update("shelter_tele", shelterTele);
                                            }
                                        }else{

                                        }
                                    }
                                });

                            }
                        }
                    }
                });

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

        if(currentUser!=null){
            user_id=currentUser.getUid();
        }else{

        }
    }
}