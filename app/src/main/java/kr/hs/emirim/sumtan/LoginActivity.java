package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db= null;

    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private TextView loginRegText;
    private String shelter_pre;
    final static Shelter shelter=new Shelter();
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        loginEmailText=(EditText) findViewById(R.id.login_email);
        loginPassText=(EditText) findViewById(R.id.login_password);
        loginBtn=(Button)findViewById(R.id.login_btn);
        loginRegText=(TextView)findViewById(R.id.login_reg);
        loginProgress=(ProgressBar)findViewById(R.id.login_progress);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail=loginEmailText.getText().toString();
                String loginPass=loginPassText.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){
                    loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendToMain();
                            }else{
                                String errorMessage=task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "error : "+errorMessage, Toast.LENGTH_SHORT).show();
                            }
                           loginProgress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });

        loginRegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser= mAuth.getCurrentUser();

        if(currentUser!=null){
            sendToMain();
        }else{

        }

    }

    private void sendToMain() {
        db.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        Shelter shelter=document.toObject(Shelter.class);
                        shelter_pre=shelter.getPre();
                        if(shelter_pre!=null){
                            Toast.makeText(LoginActivity.this, "pre : "+shelter_pre, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity_shelter.class));
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "pre : "+shelter_pre, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }
            }
        });



//        if(shelter_pre==null) {
//            Toast.makeText(LoginActivity.this, "pre : "+shelter_pre, Toast.LENGTH_LONG).show();
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }else if(shelter_pre!=null){
//            Toast.makeText(LoginActivity.this, "pre : "+shelter_pre, Toast.LENGTH_LONG).show();
//
//            startActivity(new Intent(LoginActivity.this, MainActivity_shelter.class));
//            finish();
//        }
    }
}