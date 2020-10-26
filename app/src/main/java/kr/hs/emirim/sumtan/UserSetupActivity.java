package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserSetupActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userTele;
    private String user_id;
    private String user_name;
    private String user_tele;
    private String user_email;
    private Button setup_btn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setup);

        firebaseAuth=FirebaseAuth.getInstance();

        userName=(EditText)findViewById(R.id.userName);
        userTele=(EditText)findViewById(R.id.userTele);
        setup_btn=(Button)findViewById(R.id.setup_btn);
        user_id=firebaseAuth.getCurrentUser().getUid();

        setup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name=userName.getText().toString();
                user_tele=userTele.getText().toString();
                user_email=firebaseAuth.getCurrentUser().getEmail();

                if(!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(user_tele)){
                    adduser();
                }
            }
        });
    }

    private void adduser() {
        FirebaseFirestore db=FirebaseFirestore.getInstance();

        User user=new User(user_name, user_email);

        Map<String, String> userMap=new HashMap<>();
        userMap.put("email", user_email);
        userMap.put("name", user.getName());
        userMap.put("tele", user.getTele());

        db.collection("Users").document(user_id).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(UserSetupActivity.this, "유저 정보가 등록됨", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserSetupActivity.this, MainActivity.class));
                }else{
                    String error=task.getException().getMessage();
                    Toast.makeText(UserSetupActivity.this, "Firestore Error : "+error, Toast.LENGTH_SHORT).show();
                    Log.d("에러 : ", error);
                }
            }
        });
    }
}