package kr.hs.emirim.sumtan.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.hs.emirim.sumtan.R;

public class Course extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.search_course,container,false);

        return view;
        //        submission_Button=findViewById(R.id.submission_Button);
//        submission_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogClick();
//            }
//        });
    }


//    private void DialogClick() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("다이얼로그").setMessage("다이얼로그가 보인다면 성공입니다. 축하합니다!");
//        builder.setPositiveButton("성공", new DialogInterface.OnClickListener() {
//            @Override public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Yeah!!", Toast.LENGTH_LONG).show();
//            }
//        });
//        builder.setNegativeButton("실패", new DialogInterface.OnClickListener() {
//            @Override public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"Try again!", Toast.LENGTH_LONG).show();
//            }
//        });
//        builder.setNeutralButton("Nuetral", new DialogInterface.OnClickListener() {
//            @Override public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"neutral click", Toast.LENGTH_LONG).show();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}