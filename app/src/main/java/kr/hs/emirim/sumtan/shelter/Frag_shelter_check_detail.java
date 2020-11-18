package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
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

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.user.User;

public class Frag_shelter_check_detail extends Fragment {

    private View view;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    FirebaseFirestore db=null;
    private String user_id;
    private String userId;
    private String getUserName;

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

        DocumentReference docShel=db.collection("Users").document(user_id);
        docShel.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    if(documentSnapshot.exists()){
                        User user=documentSnapshot.toObject(User.class);
                        userId=user.getUserid();

                        TextView userName=view.findViewById(R.id.courseTitle);
                        getUserName=userName.getText().toString();
                    }
                }
            }
        });

        return view;
    }
}