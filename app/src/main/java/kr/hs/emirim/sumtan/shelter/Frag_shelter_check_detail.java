package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.user.User;

public class Frag_shelter_check_detail extends Fragment {

    private View view;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    FirebaseFirestore db=null;
    private String user_id;
    private EditText informationEditText;
    private EditText careerEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_shelter_check_detail,container,false);

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        db= FirebaseFirestore.getInstance();

        if(currentUser!=null){
            user_id=currentUser.getUid();
        }else{

        }

        informationEditText=view.findViewById(R.id.informationEditText);
        careerEditText=view.findViewById(R.id.careerEditText);

        db.collection("Resume").whereEqualTo("clickR",1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot docu:task.getResult()){
//                        User user=docu.toObject(User.class);
//                        Log.d("name====>", user.getName());
                        Log.d("docuId",docu.getId()+"=>"+docu.getData());
                        informationEditText.setText(docu.get("info").toString());
                        careerEditText.setText(docu.get("career").toString());
                    }
                }
            }
        });

        return view;
    }
}