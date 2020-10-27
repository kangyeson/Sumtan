package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag_home_user fh;
    private Frag_write_user fw;
    private Frag_search_user fs;
    private Frag_my_user fmp;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        MainActivity.this.setFrag(0);
                        break;
                    case R.id.action_write:
                        MainActivity.this.setFrag(1);
                        break;
                    case R.id.action_search:
                        MainActivity.this.setFrag(2);
                        break;
                    case R.id.action_my:
                        MainActivity.this.setFrag(3);
                        break;
                }
                return true;
            }
        });

        fh = new Frag_home_user();
        fw = new Frag_write_user();
        fs = new Frag_search_user();
        fmp = new Frag_my_user();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택.

    }

    //프래그먼트 교체가 일어나는 실행 함수수
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, fh);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, fw);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, fs);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, fmp);
                ft.commit();
                break;
        }
    }
}