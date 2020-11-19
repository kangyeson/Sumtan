package kr.hs.emirim.sumtan.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private TextView tv_shelterName;
    private String shelterName="";
    private String shelterTele;
    private Button btn_call;
    private TextView btn_end;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db=null;
    private FirebaseUser currentUser=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_frag_my_user, container, false);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        currentUser= mAuth.getCurrentUser();
        if(currentUser!=null){
            user_id=currentUser.getUid();
            showImfor();
        }else{

        }

        apply=(TextView)v.findViewById(R.id.apply);
        btn_logout=(Button)v.findViewById(R.id.btn_logout);
        user_name=(TextView)v.findViewById(R.id.user_name);
        btn_remove=(Button)v.findViewById(R.id.btn_remove);
        tv_shelterName=(TextView)v.findViewById(R.id.tv_shelterName);
        btn_call=(Button)v.findViewById(R.id.btn_call);
        btn_end=(TextView)v.findViewById(R.id.btn_end);

        btn_logout.setOnClickListener(this);
        btn_remove.setOnClickListener(this);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+shelterTele));
                startActivity(intent);
            }
        });

        DocumentReference userRef=db.collection("Users").document(user_id);

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document=task.getResult();
                            if(document.exists()){
                                String nowResume= (String) document.get("NowResume");
                                if(nowResume!=null){
                                    if(Integer.parseInt(nowResume)==1){
                                        db.collection("Users").whereEqualTo("NowResume",1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if(task.isSuccessful()){
                                                    for(DocumentSnapshot docu:task.getResult()){
                                                        userRef.update("shelter_name", "");
                                                        userRef.update("shelter_tele", "");
                                                        userRef.update("shelter_id", "");
                                                        userRef.update("NowResume", 0);
                                                    }
                                                    Toast.makeText(getActivity(), "재능 기부가 종료되었습니다", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }else{
                                        Toast.makeText(getActivity(), "먼저 재능기부할 보호소를 선택해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getActivity(), "먼저 재능기부할 보호소를 선택해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                });
            }
        });

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

        db.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        String shelterID= (String) document.get("shelter_id");
                        if(shelterID!=null){
                            db.collection("Users").whereEqualTo("NowResume", 1).whereEqualTo("shelter_id", shelterID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    for(DocumentSnapshot doc:task.getResult()){
                                        shelterName= (String) doc.get("shelter_name");
                                        tv_shelterName.setText(shelterName);
                                        shelterTele= (String) doc.get("shelter_tele");
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            db.collection("Users").document(user_id).delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    db.collection("Resume").document(user_id).delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(), "계정 삭제 성공!", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("error : ", "Error deleting document", e);
                                                }
                                            });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("error : ", "Error deleting document", e);
                                }
                            });
                        }
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
