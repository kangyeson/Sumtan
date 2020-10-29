package kr.hs.emirim.sumtan.Register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kr.hs.emirim.sumtan.R;
import kr.hs.emirim.sumtan.shelter.ShelterSetupActivity;
import kr.hs.emirim.sumtan.user.MainActivity;
import kr.hs.emirim.sumtan.user.UserSetupActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText reg_email_field;
    private EditText reg_pass_field;
    private EditText reg_confirm_pass_field;
    private Button reg_btn_shelter;
    private Button reg_btn_user;
    private ProgressBar reg_progress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        reg_email_field=(EditText)findViewById(R.id.reg_email);
        reg_pass_field=(EditText)findViewById(R.id.reg_password);
        reg_confirm_pass_field=(EditText)findViewById(R.id.reg_confirm_pass);
        reg_btn_shelter=(Button)findViewById(R.id.reg_btn_shelter);
        reg_btn_user=(Button)findViewById(R.id.reg_btn_user);
        reg_progress=(ProgressBar)findViewById(R.id.reg_progress);

        reg_btn_shelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=reg_email_field.getText().toString();
                String pass=reg_pass_field.getText().toString();
                String confirm_pass=reg_confirm_pass_field.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirm_pass)){
                    if(pass.equals(confirm_pass)){
                        reg_progress.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(RegisterActivity.this, ShelterSetupActivity.class));
                                    finish();
                                }else{
                                    String errorMessage=task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error : "+errorMessage, Toast.LENGTH_SHORT).show();
                                }
                                reg_progress.setVisibility(View.INVISIBLE);
                            }
                        });
                    }else{
                        Toast.makeText(RegisterActivity.this, "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        reg_btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=reg_email_field.getText().toString();
                String pass=reg_pass_field.getText().toString();
                String confirm_pass=reg_confirm_pass_field.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirm_pass)){
                    if(pass.equals(confirm_pass)){
                        reg_progress.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(RegisterActivity.this, UserSetupActivity.class));
                                    finish();
                                }else{
                                    String errorMessage=task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error : "+errorMessage, Toast.LENGTH_SHORT).show();
                                }
                                reg_progress.setVisibility(View.INVISIBLE);
                            }
                        });
                    }else{
                        Toast.makeText(RegisterActivity.this, "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser!=null){
            sendToMain();
        }
    }

    private void sendToMain() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }
}