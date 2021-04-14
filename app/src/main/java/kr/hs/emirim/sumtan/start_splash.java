package kr.hs.emirim.sumtan;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import kr.hs.emirim.sumtan.Register.LoginActivity;

public class start_splash extends AppCompatActivity {//

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        StartAnimations();

    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.fromleft_text);
        anim.reset();
        anim2.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.splash_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        LinearLayout l2 = (LinearLayout) findViewById(R.id.textsplash);
        l2.clearAnimation();
        l2.startAnimation(anim2);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imgsplash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 7000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(start_splash.this,
                            LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    start_splash.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    start_splash.this.finish();
                }
            }
        };
        splashTread.start();
    }
}