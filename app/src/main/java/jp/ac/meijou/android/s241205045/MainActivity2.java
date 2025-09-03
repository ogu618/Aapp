package jp.ac.meijou.android.s241205045;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s241205045.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205045.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;


    //day3 p.52, github lesson8参考
    //返しの値を受け取る 演習その3
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret")) //retは名前
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textView7.setText(text)); // textView7は出力先のid
                    }
                    case RESULT_CANCELED -> {
                        binding.textView7.setText("Result: Canceled");
                    }
                    default -> {
                        binding.textView7.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // bindingはここより下に書く
//        setContentView(R.layout.activity_main2);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });

        //day3 p.28～
        //明示的
        binding.button3.setOnClickListener((view -> {
            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }));

        //暗黙的
        binding.button4.setOnClickListener((view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        }));

    //send button 演習その2
        binding.button10.setOnClickListener(view -> {
            var text = binding.editTextText2.getText().toString();
            var intent = new Intent(this, MainActivity4.class);
            intent.putExtra("title", text);
            startActivity(intent);
        });


        // 演習その3
        binding.button11.setOnClickListener(v -> {
            var intent =  new Intent(this, MainActivity4.class);
            getActivityResult.launch(intent);
        });


    }
}