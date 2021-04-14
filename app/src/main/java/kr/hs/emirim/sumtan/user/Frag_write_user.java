package kr.hs.emirim.sumtan.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import kr.hs.emirim.sumtan.R;

public class Frag_write_user extends Fragment {//

    private View view;

    private String info;
    private String car;

    private EditText information;
    private EditText career;
    private TextView user_email;
    private TextView user_tele;
    private TextView user_name;
    private Button btn_write;
    private String randomName;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    private String user_id;

    FirebaseFirestore db=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_write_user, container, false);

        mAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        currentUser= mAuth.getCurrentUser();

        information=(EditText)view.findViewById(R.id.informationEditText);
        career=(EditText)view.findViewById(R.id.careerEditText);
        user_email=(TextView)view.findViewById(R.id.user_email);
        user_tele=(TextView)view.findViewById(R.id.user_tele);
        user_name=(TextView)view.findViewById(R.id.user_name);

        btn_write=(Button)view.findViewById(R.id.btn_write);

        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                info=information.getText().toString();
                car=career.getText().toString();
                if(!TextUtils.isEmpty(info) && !TextUtils.isEmpty(car)){
                    db.collection("Resume").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot document=task.getResult();
                                if(document.exists()){
                                    modifyResume();
                                }else{
                                    setmodifyResume();
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "소개, 경력 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
//
//                if(!TextUtils.isEmpty(info) && !TextUtils.isEmpty(car)){
//                    if(currentUser!=null) {
//                        randomName= FieldValue.serverTimestamp().toString();
//                        user_id = currentUser.getUid();
//                        modifyResume();
//                    }
//                }

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(currentUser!=null){
            user_id=currentUser.getUid();
            showETele();
            showResume();
        }else{

        }

    }

    private void modifyResume(){
        Resume resume = new Resume(info, car);

        Map<String, Object> resumeMap = new HashMap<>();
        resumeMap.put("info", resume.getInfo());
        resumeMap.put("career", resume.getCareer());
        resumeMap.put("timestamp", FieldValue.serverTimestamp());

        db.collection("Resume").document(user_id).update(resumeMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "신청서 업데이트 완료", Toast.LENGTH_SHORT).show();
                information.setText(resume.getInfo());
                career.setText(resume.getCareer());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Firestore Error : "+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setmodifyResume(){
        info=information.getText().toString();
        car=career.getText().toString();
        Resume resume = new Resume(info, car);

        Map<String, Object> resumeMap = new HashMap<>();
        resumeMap.put("user_id", user_id);
        resumeMap.put("info", resume.getInfo());
        resumeMap.put("career", resume.getCareer());
        resumeMap.put("user_email", currentUser.getEmail());
        resumeMap.put("timestamp", FieldValue.serverTimestamp());

        db.collection("Resume").document(user_id).set(resumeMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "신청서 등록 완료", Toast.LENGTH_SHORT).show();
                    information.setText(resume.getInfo());
                    career.setText(resume.getCareer());
                }else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getActivity(), "Firestore Error : "+error, Toast.LENGTH_SHORT).show();
                    Log.d("에러 : ", error);
                }
            }
        });
    }


    private void showETele() {
        DocumentReference docRef=db.collection("Users").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        User user=document.toObject(User.class);
                        user_name.setText(user.getName());
                        user_email.setText(currentUser.getEmail());
                        user_tele.setText(user.getTele());
                    }else{
                        Log.d("LoginActivity => ", "No such document");
                    }
                }else{

                }
            }
        });
    }

    private void showResume() {
        DocumentReference docRef=db.collection("Resume").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        Resume resume=document.toObject(Resume.class);
                        information.setText(resume.getInfo());
                        career.setText(resume.getCareer());
                    }else{
                        Log.d("LoginActivity => ", "No such document");
                    }
                }else{

                }
            }
        });
    }
}