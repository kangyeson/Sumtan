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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import kr.hs.emirim.sumtan.Register.LoginActivity;
import kr.hs.emirim.sumtan.R;

public class Frag_my_shelter extends Fragment implements View.OnClickListener {

    private View view;
    private static final String TAG="My Tag";
    private Button btn_logout;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=null;

    private String user_id;
    private TextView shelter_name;
    private TextView shelter_tele;
    private TextView shelter_pre;
    private TextView shelter_address;

    private FirebaseUser currentUser=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_my_shelter,container,false);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        currentUser= mAuth.getCurrentUser();

        Button iv_modify=(Button)view.findViewById(R.id.iv_modify);
        shelter_name=(TextView)view.findViewById(R.id.shelter_name);
        shelter_tele=(TextView)view.findViewById(R.id.shelter_tele);
        shelter_pre=(TextView)view.findViewById(R.id.shelter_pre);
        shelter_address=(TextView)view.findViewById(R.id.shelter_address);

        iv_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Modify_shelter.class));
            }
        });

        btn_logout=(Button)view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(currentUser!=null){
            user_id=currentUser.getUid();
            showImfor();
        }else{

        }
    }

    private void showImfor() {
        DocumentReference docRef=db.collection("Users").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        //User user=document.toObject(User.class);
                        Shelter shelter=document.toObject(Shelter.class);

                        shelter_name.setText(shelter.getName());
                        shelter_tele.setText(shelter.getTele());
                        shelter_pre.setText(shelter.getPre());
                        shelter_address.setText(shelter.getAddress());
                    }else{
                        Log.d("LoginActivity => ", "No such document");
                    }
                }else{

                }
            }
        });
//        db.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for(QueryDocumentSnapshot document:task.getResult()){
//                        Shelter shelter=document.toObject(Shelter.class);
//
//                        shelter_name.setText(shelter.getName());
//                        shelter_tele.setText(shelter.getTele());
//                        shelter_pre.setText(shelter.getPre());
//                    }
//                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
