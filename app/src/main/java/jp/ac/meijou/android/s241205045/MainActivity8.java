package jp.ac.meijou.android.s241205045;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205045.databinding.ActivityMain7Binding;
import jp.ac.meijou.android.s241205045.databinding.ActivityMain8Binding;

public class MainActivity8 extends AppCompatActivity {

    private ActivityMain8Binding binding;
    private ConnectivityManager connectivityManager;

    private ImageView inputImage;
    private Button processButton;
    private TextView resultText;

    private ImageClassifier imageClassifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main8);

        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}