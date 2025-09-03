package jp.ac.meijou.android.s241205045;

import android.content.Intent;
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
import jp.ac.meijou.android.s241205045.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {

    private ActivityMain4Binding binding;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main4);

        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //day3 p.42
        //main2からきた文字列を表示
        var editText = getIntent().getStringExtra("title");
        binding.textView6.setText(editText);


        //演習その3
        //okボタン
        binding.button9.setOnClickListener(v ->{
            var intent = new Intent();
            intent.putExtra("ret", "OK"); //retはMainActivity2に書いた名前,valueの文字列を返す
            setResult(RESULT_OK, intent);
            finish(); //finishは画面が残らない（レイヤーが生まれない→戻るボタンでアプリが落ちる）
        });

        //cancelボタン
        binding.button12.setOnClickListener(v ->{
            setResult(RESULT_CANCELED);
            finish();

        });


    }
}