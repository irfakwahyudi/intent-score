package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1 & 2;
    public static final String Data_KEY = "Data";
    public static final String imgbitmap1 = "logohome", imgbitmap2 = "logoaway";
    public ImageView homeLogo, awayLogo;
    private EditText homeText;
    private EditText awayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);

    }
    //TODO
    //Fitur Main Activity
    //1. Validasi Input Home Team
    //2. Validasi Input Away Team
    //3. Ganti Logo Home Team
    //4. Ganti Logo Away Team
    //5. Next Button Pindah Ke MatchActivity
    public void matchClick(View view) {
        String nameHome = homeText.getText().toString();
        String nameAway = awayText.getText().toString();

        Data Data = new Data(nameHome, nameAway);

        if (nameHome.isEmpty()) {
            homeText.setError("Home Team Name Must Be Filled");
        } else if (nameAway.isEmpty()) {
            awayText.setError("Away Team Name Must Be Filled");
        } else {
            Intent intent = new Intent(this, MatchActivity.class);
            Toast.makeText(getApplicationContext(), "Succesfully", Toast.LENGTH_SHORT).show();
            //homeLogo
            homeLogo.buildDrawingCache();
            Bitmap img1 = homeLogo.getDrawingCache();
            Bundle extras = new Bundle();
            extras.putParcelable("imgbitmap1", img1);
            intent.putExtras(extras);
            //awayLogo
            awayLogo.buildDrawingCache();
            Bitmap img2 = awayLogo.getDrawingCache();
            Bundle extras2 = new Bundle();
            extras2.putParcelable("imgbitmap2", img2);
            intent.putExtras(extras2);

            intent.putExtra("Data", Data);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent Data) {
        super.onActivityResult(requestCode, resultCode, Data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (Data != null) {
                try {
                    Uri imageUri = Data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }

        }
        if (requestCode == 2) {
            if (Data != null) {
                try {
                    Uri imageUri = Data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awayLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }

        }
    }
    public void changephotohome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void changephotoaway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}
