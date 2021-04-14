package kr.hs.emirim.sumtan.shelter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.user.User;

public class ShelterCdetailActivity extends AppCompatActivity {//

    private View view;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser=null;
    FirebaseFirestore db=null;
    private String user_id;
    private EditText informationEditText;
    private EditText careerEditText;
    private TextView userName;
    private EditText userEmail;
    private EditText userTele;
    private Button btn_back;
    LinearLayout change_lay;
    public static ShelterCdetailActivity firstActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_check_detail);

        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        db= FirebaseFirestore.getInstance();

        String checkUser=getIntent().getStringExtra("checkName");
        Log.d("checkUser=====>", checkUser);

        if(currentUser!=null){
            user_id=currentUser.getUid();
        }else{

        }

        firstActivity=ShelterCdetailActivity.this;

        informationEditText=findViewById(R.id.informationEditText);
        careerEditText=findViewById(R.id.careerEditText);
        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);
        userTele=findViewById(R.id.userTele);
        btn_back=findViewById(R.id.btn_back);

        db.collection("Users").whereEqualTo("NowResume",1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot docu:task.getResult()){
                        User user=docu.toObject(User.class);
                        String name=user.getName();
                        if(checkUser.equals(name)){
                            db.collection("Resume").whereEqualTo("user_name", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for(DocumentSnapshot doc:task.getResult()){
                                            String user_name= (String) doc.get("user_name");
                                            String user_email= (String) doc.get("user_email");
                                            String user_tele= (String) doc.get("user_tele");
                                            String info= (String) doc.get("info");
                                            String career= (String) doc.get("career");
                                            userName.setText(user_name);
                                            userEmail.setText(user_email);
                                            userTele.setText(user_tele);
                                            informationEditText.setText(info);
                                            careerEditText.setText(career);
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}