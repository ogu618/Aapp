package jp.ac.meijou.android.s241205045;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.vision.classifier.Classifications;
import org.tensorflow.lite.task.vision.classifier.ImageClassifier;

import java.io.IOException;
import java.util.List;

import jp.ac.meijou.android.s241205045.databinding.ActivityMain7Binding;
import jp.ac.meijou.android.s241205045.databinding.ActivityMain8Binding;

public class MainActivity8 extends AppCompatActivity {

    private ActivityMain8Binding binding;
    private ImageClassifier imageClassifier;
    private ConnectivityManager connectivityManager;

    private ImageView inputImage;
    private Button processButton;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // EfficientNet モデルをロード
        try {
            imageClassifier = ImageClassifier.createFromFile(this, "efficientnet-lite0-int8.tflite");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ボタンのクリック処理
        binding.button14.setOnClickListener(view -> {
            classifyImage();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }



    private void classifyImage() {
        // Drawable から画像を取得（例：sample_image が res/drawable にある）
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baseline_brightness_5_24);

        // Bitmap を TensorImage に変換
        TensorImage image = TensorImage.fromBitmap(bitmap);

        // 推論実行
        List<Classifications> results = imageClassifier.classify(image);

        // 結果を画面に表示
        if (results != null && results.size() > 0) {
            // 最初の分類セット（通常は1つ）
            List<Category> categories = results.get(0).getCategories();
            if (categories != null && categories.size() > 0) {
                Category topResult = categories.get(0);
                String label = topResult.getLabel();
                float score = topResult.getScore();
                String resultText = "予測: " + label + "\n信頼度: " + (score * 100) + " %";
                binding.resultText.setText(resultText);
            } else {
                binding.resultText.setText("分類結果なし");
            }
        } else {
            binding.resultText.setText("予測できませんでした");
        }
    }
}
