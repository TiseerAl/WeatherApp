package tiseer.edu.weatherapp.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tiseer.edu.weatherapp.MainActivity;
import tiseer.edu.weatherapp.R;

public class SplashActivity extends AppCompatActivity {

    private final static long DELAY_LENGTH = 2000;
    private TextView splashTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashTV = findViewById(R.id.splashText);
        startAnimation();

        new Handler().postDelayed(() -> {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                , DELAY_LENGTH + DELAY_LENGTH);
    }

    private void startAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(splashTV, "scaleX", 0f, 1f);
        scaleXAnimator.setDuration(DELAY_LENGTH);
        scaleXAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(splashTV, "scaleY", 0f, 1f);
        scaleYAnimator.setDuration(DELAY_LENGTH);
        scaleYAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }
}