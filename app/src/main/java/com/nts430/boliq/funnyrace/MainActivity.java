package com.nts430.boliq.funnyrace;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvScore;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;
    ImageButton btnPlay;
    CountDownTimer countDownTimer;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        timer_event();
        play_event();
        checkbox_event();

    }

    private void checkbox_event() {
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    // Uncheck checkbox 2 and 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    // Uncheck checkbox 1 and 3
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    // Uncheck checkbox 1 and 2
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });
    }

    /**
     * Handle when user clicks the Play button
     */
    private void play_event() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked())
                {
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);
                    btnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    ToggleCheckBox(false);
                    ToggleSeekBar(false);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please select a checkbox to bet before playing!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Handle event of timer
     */
    private void timer_event() {
        countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number      = 5;
                Random random   = new Random();
                int one         = random.nextInt(number);
                int two         = random.nextInt(number);
                int three       = random.nextInt(number);

                // Check win for the first animal
                if (sbOne.getProgress() >= sbOne.getMax())
                {
                    countDownTimer.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Snail won!", Toast.LENGTH_SHORT).show();

                    // Check bet
                    if (cbOne.isChecked())
                    {
                        score += 10;
                        Toast.makeText(MainActivity.this, "You guessed correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        score -= 5;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();
                    }

                    ToggleCheckBox(true);
                    ToggleSeekBar(true);
                }

                // Check win for the second animal
                else if (sbTwo.getProgress() >= sbTwo.getMax())
                {
                    countDownTimer.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Pig won!", Toast.LENGTH_SHORT).show();

                    // Check bet
                    if (cbTwo.isChecked())
                    {
                        score += 10;
                        Toast.makeText(MainActivity.this, "You guessed correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        score -= 5;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();
                    }

                    ToggleCheckBox(true);
                    ToggleSeekBar(true);
                }

                // Check win for the third animal
                else if (sbThree.getProgress() >= sbThree.getMax())
                {
                    countDownTimer.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Bird won!", Toast.LENGTH_SHORT).show();

                    // Check bet
                    if (cbThree.isChecked())
                    {
                        score += 10;
                        Toast.makeText(MainActivity.this, "You guessed correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        score -= 5;
                        Toast.makeText(MainActivity.this, "You guessed wrong!", Toast.LENGTH_SHORT).show();
                    }

                    ToggleCheckBox(true);
                    ToggleSeekBar(true);
                }

                tvScore.setText(score + "");

                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);

            }

            @Override
            public void onFinish() {

            }
        };
    }

    /**
     * Initialize views
     */
    private void init() {
        tvScore     = findViewById(R.id.textViewScore);
        cbOne       = findViewById(R.id.checkboxOne);
        cbTwo       = findViewById(R.id.checkboxTwo);
        cbThree     = findViewById(R.id.checkboxThree);
        sbOne       = findViewById(R.id.seekbarOne);
        sbTwo       = findViewById(R.id.seekbarTwo);
        sbThree     = findViewById(R.id.seekbarThree);
        btnPlay     = findViewById(R.id.imageButtonPlay);
    }

    private void ToggleCheckBox(boolean isEnabled)
    {
        cbOne.setEnabled(isEnabled);
        cbTwo.setEnabled(isEnabled);
        cbThree.setEnabled(isEnabled);
    }

    private void ToggleSeekBar(boolean isEnabled)
    {
        sbOne.setEnabled(isEnabled);
        sbTwo.setEnabled(isEnabled);
        sbThree.setEnabled(isEnabled);
    }
}
