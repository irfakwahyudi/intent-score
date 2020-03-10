package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static id.putraprima.skorbola.MainActivity.Data_KEY;

public class MatchActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 1 & 2;
    public static final String WINNER = "winner";
    //    public static final String imgbitmap1 = "logohome", imgbitmap2 = "logoaway";
    private int homeScore, awayScore;
    private ImageView homelogo, awaylogo;
    private TextView nameHome;
    private TextView nameAway;
    private TextView score_home;
    private TextView score_away;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        nameHome = findViewById(R.id.txt_home);
        nameAway = findViewById(R.id.txt_away);
        homelogo = findViewById(R.id.home_logo);
        awaylogo = findViewById(R.id.away_logo);
        score_home = findViewById(R.id.score_home);
        score_away = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();

        Data Data = getIntent().getParcelableExtra(Data_KEY);
        if (Data != null) {
            nameHome.setText(Data.getHomeName());
            nameAway.setText(Data.getAwayName());
        }
        Bitmap bmp = extras.getParcelable("imgbitmap1");
        homelogo.setImageBitmap(bmp);

        Bitmap bit = extras.getParcelable("imgbitmap2");
        awaylogo.setImageBitmap(bit);

    }
    //TODO
    //1.Menampilkan detail match sesuai Data dari main activity
    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    public void addedScoreHome(int scoreHome1) {
        score_home.setText(String.valueOf(scoreHome1));
    }

    public void addedScoreAway(int scoreAway2) {
        score_away.setText(String.valueOf(scoreAway2));
    }

    public void addScoreHome(View view) {
        homeScore = homeScore + 1;
        addedScoreHome(homeScore);
    }

    public void addScoreAway(View view) {
        awayScore = awayScore + 1;
        addedScoreAway(awayScore);

    }

    public void resultClick(View view) {
        String winner;
        if (homeScore > awayScore) {
            winner = nameHome.getText().toString();
        } else if (awayScore > homeScore) {
            winner = nameAway.getText().toString();
        } else {
            winner = "DRAW";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(WINNER, winner);
        startActivity(intent);
    }
}
