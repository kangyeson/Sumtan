package kr.hs.emirim.sumtan.shelter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import kr.hs.emirim.sumtan.Register.LoginActivity;
import kr.hs.emirim.sumtan.R;

import static android.app.Activity.RESULT_OK;

public class Frag_my_shelter extends Fragment implements View.OnClickListener {

    private View view;
    private static final String TAG="My Tag";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=null;
    private FirebaseStorage storage;
    private DatabaseReference databaseReference;

    private String user_id;
    private TextView shelter_name;
    private TextView shelter_tele;
    private TextView shelter_pre;
    private TextView shelter_address;
    private Button btn_logout;
    private Button btn_remove;

    private FirebaseUser currentUser=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_my_shelter,container,false);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser= mAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();

        shelter_name=(TextView)view.findViewById(R.id.shelter_name);
        shelter_tele=(TextView)view.findViewById(R.id.shelter_tele);
        shelter_pre=(TextView)view.findViewById(R.id.shelter_pre);
        shelter_address=(TextView)view.findViewById(R.id.shelter_address);


        btn_logout=(Button)view.findViewById(R.id.btn_logout);
        btn_remove=(Button)view.findViewById(R.id.btn_remove);

        btn_logout.setOnClickListener(this);
        btn_remove.setOnClickListener(this);

        return view;
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
                        //User user=document.toObject(User.class);
                        Shelter shelter=document.toObject(Shelter.class);

                        shelter_name.setText(shelter.getSName());
                        shelter_tele.setText(shelter.getTele());
                        shelter_pre.setText(shelter.getPre());
                        shelter_address.setText(shelter.getAddress());
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

    
    private void logout() {
        mAuth.signOut();
        sendToLogin();
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
                            db.collection("Users").document(user_id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                    Toast.makeText(getActivity(), "계정 삭제 성공!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("error : ", "Error deleting document", e);
                                }
                            });
                        }

                    }
                });
    }

    private void sendToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
