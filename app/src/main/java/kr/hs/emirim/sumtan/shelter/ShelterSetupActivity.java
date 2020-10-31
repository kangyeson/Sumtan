package kr.hs.emirim.sumtan.shelter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import kr.hs.emirim.sumtan.R;

public class ShelterSetupActivity extends AppCompatActivity {

    private EditText shelterTele;
    private EditText shelterName;
    private EditText shelterPre;
    private Button setup_btn;
    private String user_id;
    private String shelter_tele;
    private String shelter_name;
    private String shelter_pre;
    private String shelter_address;

    private FirebaseAuth firebaseAuth;
    private ArrayAdapter adapter;
    String item1, item2, item3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_setup);

        firebaseAuth = FirebaseAuth.getInstance();

        shelterTele = (EditText) findViewById(R.id.shelterTele);
        shelterName = (EditText) findViewById(R.id.shelterName);
        shelterPre = (EditText) findViewById(R.id.shelterPre);
        setup_btn = (Button) findViewById(R.id.setup_btn);
        user_id = firebaseAuth.getCurrentUser().getUid();

        final Spinner[] spinner1 = {(Spinner) findViewById(R.id.shelterAddress1)};
        final Spinner[] spinner2 = {(Spinner) findViewById(R.id.shelterAddress2)};
        final Spinner[] spinner3 = {(Spinner) findViewById(R.id.shelterAddress3)};
        spinner1[0] = (Spinner) findViewById(R.id.shelterAddress1);
        adapter = ArrayAdapter.createFromResource(this, R.array.address1, android.R.layout.simple_spinner_dropdown_item);
        spinner1[0].setAdapter(adapter);

        spinner1[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item1 = spinner1[0].getSelectedItem().toString(); //item1에 스피너1(시) 값을 받아옴.
                //서울시
                if (position == 0) {
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seoul, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //강남구
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangnam, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                                //강동구
                            } else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangdong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //강북구
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangbuk, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //강서구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangseo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //관악구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwanak, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //광진구
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwangjin, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //구로구
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Guro, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //금천구
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Geumcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //노원구
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nowon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //도봉구
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dobong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동대문구
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dongdaemun, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동작구
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dongjak, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //마포구
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Mapo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서대문구
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seodaemun, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서초구
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seocho, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //성동구
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongdong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //성북구
                            else if (position == 16) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongbuk, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //송파구
                            else if (position == 17) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Songpa, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //양천구
                            else if (position == 18) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yangcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영등포구
                            else if (position == 19) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongdeungpo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //용산구
                            else if (position == 20) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yongsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //은평구
                            else if (position == 21) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Eunpyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //종로구
                            else if (position == 22) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jongno, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중구
                            else if (position == 23) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jung1, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중랑구
                            else if (position == 24) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jungnang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();   //item2에 스피너2(구) 값을 받아옴.
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    }); //end of 서울
                }
                //경기도
                else if (position == 1) {
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeonggi, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //가평군
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gapyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                                //고양시 덕양구
                            } else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Goyang_Deogyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //고양시 일산동구
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.ilsandong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //고양시 일산서구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.ilsanseo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //과천시
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwacheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //광명시
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwangmyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //광주시
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwangju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //구리시
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Guri, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //군포시
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gunpo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //김포시
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gimpo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //남양주시
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Namyangju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동두천시
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dongducheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //부천시
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Bucheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //분당구
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Bundang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //수정구
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Sujeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중원구
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Jungwon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //권선구
                            else if (position == 16) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Gwonseon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영통구
                            else if (position == 17) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Yeongtong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //장안구
                            else if (position == 18) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Jangan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //팔달구
                            else if (position == 19) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongnam_Paldal, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //시흥시
                            else if (position == 20) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Siheung, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //단원구
                            else if (position == 21) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ansan_Danwon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //상록구
                            else if (position == 22) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ansan_Sangloggu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //안성시
                            else if (position == 23) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Anseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동안구
                            else if (position == 24) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Anyang_Dongan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //만안구
                            else if (position == 25) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Anyang_Manan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //양주시
                            else if (position == 26) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yangju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //양평군
                            else if (position == 27) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yangpyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //여주시
                            else if (position == 28) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeoju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //연천군
                            else if (position == 29) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeoncheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //오산시
                            else if (position == 30) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Osan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //기흥구
                            else if (position == 31) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Giheung, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //수지구
                            else if (position == 32) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Suji, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //처인구
                            else if (position == 33) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheoin, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //의왕시
                            else if (position == 34) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Uiwang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //의정부시
                            else if (position == 35) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Uijeongbu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //이천시
                            else if (position == 36) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Icheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //파주시
                            else if (position == 37) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Paju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //평택시
                            else if (position == 38) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Pyeongtaek, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //포천시
                            else if (position == 39) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Pocheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //하남시
                            else if (position == 40) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hanam, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //화성시
                            else if (position == 41) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hwaseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                } //end of 경기도
                //인천
                else if(position == 2){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Incheon, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //강화군
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ganghwa, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //계양구
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //남동구
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Namdong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dong1, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //미추홀구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Michuhol, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //부평구
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Bupyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서구
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seo1, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //연수구
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeonsu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //옹진구
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ongjin, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중구
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jung2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 인천
                //강원
                else if(position == 3){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangwon, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //강릉시
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangneung, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //고성군
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Goseong1, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동해시
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Donghae, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //삼척시
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Samcheok, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //속초시
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sokcho, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //양구군
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yanggu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //양양군
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yangyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영월군
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongwol, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //원주시
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Wonju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //인제군
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Inje, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //정선군
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeongseon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //철원군
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheorwon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //춘천시
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Chuncheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //태백시
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Taebaek, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //평창군
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Pyeongchang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //홍천군
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hongcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //화천군
                            else if (position == 16) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hwacheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //횡성군
                            else if (position == 17) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hoengseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 강원
                //대전
                else if(position == 4){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Daejeon, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //대덕구
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Daedeok, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //동구
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dong2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서구
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seo2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //유성구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yuseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jung3, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 대전
                //세종
                else if(position == 5){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sejong1, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //세종시
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sejong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 세종
                //충남
                else if(position == 6){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Chungnam, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //계룡시
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeryong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //공주시
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //금산군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Geumsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //논산시
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nonsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //당진시
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dangjin, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //보령시
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Boryeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //부여군
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buyeo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서산시
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seosan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서천군
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seocheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //아산시
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Asan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //예산군
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yesan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동남구
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheonan_Dongnam, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서북구
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheonan_Seobuk, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //청양군
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheongyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //태안군
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Taean, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //홍성군
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hongseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 충남
                //충북
                else if(position == 7){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Chungbuk, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //괴산군
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Goesan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //단양군
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Danyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //보은군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Boeun, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영동군
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongdong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //옥천군
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Okcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //음성군
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Eumseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //제천시
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jecheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //증평군
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeungpyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //진천군
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jincheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //상당구
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sangdang_Cheongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서원구
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seowon_Cheongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //청원구
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheongwon_Cheongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //흥덕구
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Heungdeok_Cheongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //충주시
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Chungju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 충북
                //부산
                else if(position == 8){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Busan, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //강서구
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangseo2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //금정구
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Geumjeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //기장군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gijang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //남구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nam1, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dong3, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동래구
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dongnae, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //부산직구
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Busan_direct_district, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //북구
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buk1, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //사상구
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sasang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //사하구
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Saha, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서구
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seo3, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //수영구
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Suyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //연제구
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeonje, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영도구
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongdo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중구
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jung4, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //해운대구
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Haeundae, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 부산
                //울산
                else if(position == 9){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ulsan, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //남구
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nam2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //동구
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dong4, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //북구
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buk2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //울주군
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ulju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jung5, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 울산
                //경남
                else if(position == 10){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeongnam, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //거제시
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Geoje, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //거창군
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Geochang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //고성군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Goseong2, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //김해시
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gimhae, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //남해군
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Namhae, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //밀양시
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Miryang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //사천시
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sacheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //산청군
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sancheong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //양산시
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yangsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //의령군
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Uiryeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //진주시
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jinju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //창녕군
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Changnyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //마산합포구
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Masanhappo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //마산회원구
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Masanhoewon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //성산구
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //의창구
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Uichang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //진해구
                            else if (position == 16) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jinhae, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //통영시
                            else if (position == 17) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Tongyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //하동군
                            else if (position == 18) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hadong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //함안군
                            else if (position == 19) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Haman, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //함양군
                            else if (position == 20) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hamyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //합천군
                            else if (position == 21) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hapcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 경남
                //경북
                else if(position == 11){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeongbuk, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //경산시
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeongsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //경주시
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gyeongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //고령군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Goryeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //구미시
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gumi, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //군위군
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gunwi, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //김천시
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gimcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //문경시
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Mungyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //봉화군
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Bonghwa, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //상주시
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sangju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //성주군
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //안동시
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Andong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영덕군
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongdeok, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영양군
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영주시
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영천시
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongcheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //예천군
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yecheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //울릉군
                            else if (position == 16) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Ulleung, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //울진군
                            else if (position == 17) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Uljin, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //의성군
                            else if (position == 18) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Uiseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //청도군
                            else if (position == 19) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheongdo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //청송군
                            else if (position == 20) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Cheongsong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //칠곡군
                            else if (position == 21) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Chilgok, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //포항시 남구
                            else if (position == 22) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nam_Pohang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //포항시 북구
                            else if (position == 23) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buk_Pohang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 경북
                //대구
                else if(position == 12){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Daegu, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //남구
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nam3, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //달서구
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dalseo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //달성군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dalseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dong5, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //북구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buk3, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서구
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seo4, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //수성구
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Suseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //중구
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jung6, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 대구
                //광주
                else if(position == 13){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwangju1, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //광산구
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwangsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //남구
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Nam4, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //동구
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Dong6, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //북구
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buk4, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //서구
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seo5, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 광주
                //전남
                else if(position == 14){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeonnam, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //강진군
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gangjin, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //고흥군
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Goheung, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //곡성군
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gokseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //광양시
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gwangyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //구례군
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gurye, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //나주시
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Naju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //담양군
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Damyang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //목포시
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Mokpo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //무안군
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Muan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //보성군
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Boseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //순천시
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Suncheon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //신안군
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sinan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //여수시
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeosu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영광군
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeonggwang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //영암군
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Yeongam, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //완도군
                            else if (position == 15) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Wando, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //장성군
                            else if (position == 16) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jangseong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //장흥군
                            else if (position == 17) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jangheung, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //진도군
                            else if (position == 18) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jindo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //함평군
                            else if (position == 19) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hampyeong, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //해남군
                            else if (position == 20) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Haenam, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //화순군
                            else if (position == 21) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Hwasun, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 전남
                //전북
                else if(position == 15){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeonbuk, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //고창군
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gochang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //군산시
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gunsan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //김제시
                            else if (position == 2) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Gimje, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //남원시
                            else if (position == 3) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Namwon, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //무주군
                            else if (position == 4) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Muju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //부안군
                            else if (position == 5) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Buan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //순창군
                            else if (position == 6) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Sunchang, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //완주군
                            else if (position == 7) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Wanju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //익산시
                            else if (position == 8) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Iksan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //임실군
                            else if (position == 9) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Imsil, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //장수군
                            else if (position == 10) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jangsu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //덕진구
                            else if (position == 11) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Deokjingu, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //완산구
                            else if (position == 12) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Wansan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //정읍시
                            else if (position == 13) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeongeup, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            //진안군
                            else if (position == 14) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jinan, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 전북
                //제주
                else if(position == 16){
                    spinner2[0] = (Spinner) findViewById(R.id.shelterAddress2);
                    adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeju1, android.R.layout.simple_spinner_dropdown_item);
                    spinner2[0].setAdapter(adapter);

                    spinner2[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //서귀포시
                            if (position == 0) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Seogwipo, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });

                            }
                            //제주시
                            else if (position == 1) {
                                spinner3[0] = (Spinner) findViewById(R.id.shelterAddress3);
                                adapter = ArrayAdapter.createFromResource(ShelterSetupActivity.this, R.array.Jeju, android.R.layout.simple_spinner_dropdown_item);
                                spinner3[0].setAdapter(adapter);
                                spinner3[0].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        item3 = spinner3[0].getSelectedItem().toString();   //item3에 스피너3(군구) 값을 받아옴.
                                        shelter_address = item1 + " "+ item2 + " " + item3;
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                    }
                                });
                            }
                            item2 = spinner2[0].getSelectedItem().toString();
                            shelter_address = item1 + " "+ item2 + " " + item3;
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } //end of 제주
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shelter_tele = shelterTele.getText().toString();
                shelter_name = shelterName.getText().toString();
                shelter_pre = shelterPre.getText().toString();

                if (!TextUtils.isEmpty(shelter_tele) && !TextUtils.isEmpty(shelter_name) && !TextUtils.isEmpty(shelter_pre)) {
                    adduser();
                }
            }
        });
    }

    private void adduser() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Shelter shelter=new Shelter(shelter_name, shelter_tele, shelter_pre, shelter_address);

        Map<String, String> userMap = new HashMap<>();
        userMap.put("tele", shelter.getTele());
        userMap.put("sname", shelter.getSName());
        userMap.put("pre", shelter.getPre());
        userMap.put("address", shelter.getAddress());

        final String pre=shelter.getPre();

        db.collection("Users").document(user_id).set(shelter).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ShelterSetupActivity.this, "유저 정보가 등록됨", Toast.LENGTH_SHORT).show();
                    if(pre!=null){
                        startActivity(new Intent(ShelterSetupActivity.this, MainActivity_shelter.class));
                        Toast.makeText(ShelterSetupActivity.this, "쉘터 메인으로! : "+pre, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(ShelterSetupActivity.this, "Firestore Error : " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}