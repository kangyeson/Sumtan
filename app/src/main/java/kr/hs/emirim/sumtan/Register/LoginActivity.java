package kr.hs.emirim.sumtan.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.shelter.MainActivity_shelter;
import kr.hs.emirim.sumtan.shelter.Shelter;
import kr.hs.emirim.sumtan.user.MainActivity;
import kr.hs.emirim.sumtan.user.User;

public class LoginActivity extends AppCompatActivity {//

    FirebaseFirestore db= null;

    private String user_id;
    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private TextView loginRegText;
    private String shelterPre;
    private String userName;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgress;
    private FirebaseUser currentUser;


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


        currentUser= mAuth.getCurrentUser();

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
                                currentUser=mAuth.getCurrentUser();
                                if(currentUser!=null && currentUser.isEmailVerified()){
                                    currentUser=mAuth.getCurrentUser();
                                    user_id=currentUser.getUid();
                                    sendToMain(user_id);
                                }else{
                                    Toast.makeText(getApplicationContext(), "메일로 보낸 링크를 확인해주세요." , Toast.LENGTH_SHORT).show();
                                }

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

        currentUser=mAuth.getCurrentUser();

        if(currentUser!=null && currentUser.isEmailVerified()){
            currentUser=mAuth.getCurrentUser();
            user_id=currentUser.getUid();
            sendToMain(user_id);
        }
    }

    private void sendToMain(String user_id) {

        DocumentReference docRef=db.collection("Users").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        User user=document.toObject(User.class);
                        Shelter shelter=document.toObject(Shelter.class);
                        shelterPre=shelter.getPre();
                        userName=user.getName();

                        //Toast.makeText(LoginActivity.this, shelterPre, Toast.LENGTH_SHORT).show();
                        if(shelterPre!=null){
                            Toast.makeText(LoginActivity.this, shelterPre+"님 환영합니다", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity_shelter.class));
                            finish();
                        }else if(userName!=null){
                            Toast.makeText(LoginActivity.this, userName+"님 환영합니다", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }else{
                        Log.d("LoginActivity => ", "No such document");
                    }
                }else{

                }
            }
        });


    }
}
