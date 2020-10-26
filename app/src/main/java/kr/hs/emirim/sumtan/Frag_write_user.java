package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Frag_write_user extends Fragment {

    private View view;

    private String info;
    private String car;

    private EditText information;
    private EditText career;
    private Button btn_write;
    private String randomName;

    private FirebaseAuth firebaseAuth;
    private String current_user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_write_user, container, false);

        firebaseAuth=FirebaseAuth.getInstance();
        current_user_id=firebaseAuth.getCurrentUser().getUid();

        information=(EditText)view.findViewById(R.id.informationEditText);
        career=(EditText)view.findViewById(R.id.careerEditText);
        btn_write=(Button)view.findViewById(R.id.btn_write);

        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                info=information.getText().toString();
                car=career.getText().toString();

                if(!TextUtils.isEmpty(info) && !TextUtils.isEmpty(car)){
                    randomName= FieldValue.serverTimestamp().toString();
                    addResume();
                }
            }
        });

        return view;
    }

    private void addResume(){
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        Map<String, Object> resumeMap=new HashMap<>();
        resumeMap.put("user_id", current_user_id);
        resumeMap.put("info", info);
        resumeMap.put("career", car);
        resumeMap.put("timestamp", FieldValue.serverTimestamp());

        db.collection("Resume").add(resumeMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "이력서 작성 완료", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getContext(), "Firestore Error : "+error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}