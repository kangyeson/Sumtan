package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.user.User;

public class Frag_check_shelter extends Fragment {

    private RecyclerView FirestoreList;
    private View view;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    FirebaseFirestore db=null;
    private FirestoreRecyclerAdapter adapter;
    private String user_id;
    private ShelterCdetailActivity fcd;
    private String getUserName;
    private String userId="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_check_shelter,container,false);
        FirestoreList=(RecyclerView)view.findViewById(R.id.checkr_list);

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        db= FirebaseFirestore.getInstance();

        if(currentUser!=null){
            user_id=currentUser.getUid();
        }else{

        }

        Query query=db.collection("Users").whereEqualTo("NowResume", 1).whereEqualTo("shelter_id", user_id);
        FirestoreRecyclerOptions<User> options=new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter=new FirestoreRecyclerAdapter<User, UserViewHolder>(options){
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_check, parent, false);
//                db.collection("Users").whereEqualTo("NowResume", 1).whereEqualTo("shelter_id", user_id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                //Log.d("id====>", document.getId() + " => " + document.get("id"));
//                                User user=document.toObject(User.class);
//                                String name=user.getName();
//                                String tele=user.getTele();
//                                user_Name.add(name);
//                                user_Tele.add(tele);
//                            }
//                        } else {
//                            Log.d("error==>", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User user) {
                holder.uesrTitle.setText(user.getName());
                holder.userTele.setText(user.getTele());

                holder.uesrTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            db.collection("Users").whereEqualTo("name", user.getName()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for(QueryDocumentSnapshot docu:task.getResult()){
                                            db.collection("Users").whereEqualTo("name", user.getName()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        for(DocumentSnapshot docu:task.getResult()){
                                                            db.collection("Resume").document(docu.get("user_id").toString()).update("clickR",1);
                                                            db.collection("Resume").document(docu.get("user_id").toString()).update("user_name",user.getName());
                                                            db.collection("Resume").document(docu.get("user_id").toString()).update("user_tele",user.getTele());
                                                        }
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            });
                            Intent intent=new Intent(getActivity(), ShelterCdetailActivity.class);
                            intent.putExtra("checkTele", user.getTele());
//                            String checkid = user.getUserid();
//                            Log.d("ch===>", checkid);
                            startActivity(intent);

                    }
                });

                db.collection("Resume").whereEqualTo("clickR", 1).whereEqualTo("user_id", userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    }
                });

            }
        };

        FirestoreList.setHasFixedSize(true);
        FirestoreList.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirestoreList.setAdapter(adapter);

        return view;
    }

    private class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView uesrTitle;
        private TextView userTele;
        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            uesrTitle = itemView.findViewById(R.id.userTitle);
            userTele = itemView.findViewById(R.id.userTele);
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