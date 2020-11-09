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

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.user.User;

public class Frag_check_shelter extends Fragment {

    private RecyclerView FirestoreList;
    private View view;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_check_shelter,container,false);
        FirestoreList=(RecyclerView)view.findViewById(R.id.checkr_list);
        firebaseFirestore=FirebaseFirestore.getInstance();

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();

        Query query = firebaseFirestore.collection("Users").orderBy("name");
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        return view;
    }
}