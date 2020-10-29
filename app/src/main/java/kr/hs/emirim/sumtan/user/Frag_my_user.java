package kr.hs.emirim.sumtan.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import kr.hs.emirim.sumtan.Register.LoginActivity;
import kr.hs.emirim.sumtan.R;

public class Frag_my_user extends Fragment implements View.OnClickListener {

    private TextView apply;
    private TextView user_name;
    private Button btn_logout;
    private Button btn_remove;
    private String user_id;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db=null;
    private FirebaseUser currentUser=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_frag_my_user, container, false);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        currentUser= mAuth.getCurrentUser();

        apply=(TextView)v.findViewById(R.id.apply);
        btn_logout=(Button)v.findViewById(R.id.btn_logout);
        user_name=(TextView)v.findViewById(R.id.user_name);
        btn_remove=(Button)v.findViewById(R.id.btn_remove);

        btn_logout.setOnClickListener(this);
        btn_remove.setOnClickListener(this);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(currentUser!=null){
            user_id=currentUser.getUid();
            showImfor();
        }else{

        }
    }

    private void showImfor() {
        DocumentReference docRef=db.collection("Users").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        User user=document.toObject(User.class);

                        user_name.setText(user.getName());
                    }else{
                        Log.d("LoginActivity => ", "No such document");
                    }
                }else{

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                logout();
                break;
            case R.id.btn_remove:
                deleteAccountClicked();
                break;
        }
    }

    private void deleteAccountClicked() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("정말 계정을 삭제하시겠습니까?")
                .setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteAccount();
                            }
                        })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        dialog.show();
    }

    private void deleteAccount() {
//        AuthUI.getInstance()
//                .delete(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()) {
//                            Toast.makeText(getActivity(), "계정 삭제 성공!", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getActivity(), LoginActivity.class));
//                        } else {
//                            Toast.makeText(getActivity(), "계정 삭제 실패!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(), "계정 삭제 성공!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                });
    }

    private void logout() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}