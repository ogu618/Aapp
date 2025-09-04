package jp.ac.meijou.android.s241205045;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s241205045.databinding.ActivityMain6Binding;
import jp.ac.meijou.android.s241205045.databinding.ActivityMain7Binding;

public class MainActivity7 extends AppCompatActivity {

    private ActivityMain7Binding binding;
    private ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main7);

        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //p.43
        connectivityManager = getSystemService(ConnectivityManager.class);

        // p.46
        var currentNetwork = connectivityManager.getActiveNetwork();

        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    //day4 p.44
    private void updateTransport(Network network) {
        var caps = connectivityManager.getNetworkCapabilities(network);
        if (caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFi";
            } else {
                transport = "その他";

            }
            binding.textView72.setText(transport);
        }
    }


    private void updateIpAddress(Network network) {
        var linkProperties = connectivityManager.getLinkProperties(network);
        if (linkProperties != null) {
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.textView74.setText(addresses);
        }
    }
}