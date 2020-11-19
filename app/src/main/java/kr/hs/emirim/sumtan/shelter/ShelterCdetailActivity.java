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

public class ShelterCdetailActivity extends AppCompatActivity {

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

        db.collection("Resume").whereEqualTo("clickR",1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot docu:task.getResult()){
//                        User user=docu.toObject(User.class);
//                        assert user != null;
//                        Log.d("name====>", user.getName());
                        Log.d("docuId",docu.getId()+"=>"+docu.getData());
                        userName.setText(docu.get("user_name").toString());
                        userEmail.setText(docu.get("user_email").toString());
                        userTele.setText(docu.get("user_tele").toString());
                        informationEditText.setText(docu.get("info").toString());
                        careerEditText.setText(docu.get("career").toString());
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