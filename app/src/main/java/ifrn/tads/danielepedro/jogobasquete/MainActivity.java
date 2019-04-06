package ifrn.tads.danielepedro.jogobasquete;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private FrameLayout gameFrame;
    private LinearLayout startLayout;

    private ImageView box, bolaLaranja, bolaRosa;

    private int frameHeight, frameWidth, initialFrameWidth;


    private Drawable cesta;

    private int boxSize;

    private float boxX, boxY;
    private float orangeX, orangeY;
    private float pinkX, pinkY;

    // Score
    private TextView scoreLabel, highScoreLabel;
    private int score, highScore, timeCount;
    private SharedPreferences settings;

    // Class
    private Timer timer;
    private Handler handler = new Handler();

    // Status
    private boolean start_flg = false;
    private boolean action_flg = false;
    private boolean pink_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameFrame = findViewById(R.id.gameFrame);
        startLayout = findViewById(R.id.startLayout);
        box = findViewById(R.id.box);
        bolaLaranja = findViewById(R.id.bolaLaranja);
        bolaRosa = findViewById(R.id.bolaRosa);
        scoreLabel = findViewById(R.id.scoreLabel);
        highScoreLabel = findViewById(R.id.highScoreLabel);


        // High Score
        settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScore = settings.getInt("HIGH_SCORE", 0);
        highScoreLabel.setText("High Score : " + highScore);
    }

    public boolean hitCheck(float x, float y) {
        return boxX <= x && x <= boxX + boxSize &&
                boxY <= y && y <= frameHeight;
    }

    public void startGame(View view) {
        start_flg = true;
        startLayout.setVisibility(View.INVISIBLE);

        if (frameHeight == 0) {
            frameHeight = gameFrame.getHeight();
            frameWidth = gameFrame.getWidth();
            initialFrameWidth = frameWidth;

            boxSize = box.getHeight();
            boxX = box.getX();
            boxY = box.getY();
        }

        frameWidth = initialFrameWidth;

        box.setX(0.0f);
        bolaLaranja.setY(3000.0f);
        bolaRosa.setY(3000.0f);

        orangeY = bolaLaranja.getY();
        pinkY = bolaRosa.getY();

        box.setVisibility(View.VISIBLE);
        bolaLaranja.setVisibility(View.VISIBLE);
        bolaRosa.setVisibility(View.VISIBLE);

        timeCount = 0;
        score = 0;
        scoreLabel.setText("Score : 0");


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start_flg) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //changePos();
                        }
                    });
                }
            }
        }, 0, 20);
    }


}
