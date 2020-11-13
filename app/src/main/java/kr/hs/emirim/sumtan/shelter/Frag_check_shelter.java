package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.user.Frag_search_user;
import kr.hs.emirim.sumtan.user.User;

public class Frag_check_shelter extends Fragment {

    private RecyclerView FirestoreList;
    private View view;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    FirebaseFirestore db=null;
    private FirestoreRecyclerAdapter adapter;
    private String user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_check_shelter,container,false);
        FirestoreList=(RecyclerView)view.findViewById(R.id.checkr_list);
        firebaseFirestore=FirebaseFirestore.getInstance();

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        db= FirebaseFirestore.getInstance();

        Query query=firebaseFirestore.collection("Users").orderBy("name");
        FirestoreRecyclerOptions<User> options=new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter=new FirestoreRecyclerAdapter<User, UserViewHolder>(options){
            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_check, parent, false);


                return new UserViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User user) {
                db.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot docu=task.getResult();
                            if(docu.exists()){
                                Log.d("shelterid=====>", String.valueOf(docu.getDate("shelterid")));
                            }
                        }
                    }
                });
               // db.collection("Users").whereEqualTo("shelterid",user_id).whereEqualTo("")
                holder.uesrTitle.setText(user.getName());
                holder.userTele.setText(user.getTele());
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