package wll.com.imclient;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.net.Socket;

import wll.com.imclient.databinding.ActivityMainBinding;
import wll.com.imclient.net.SocketUtils;
import wll.com.imclient.rx.BaseSubscriber;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        SocketUtils.setUpSocket(new BaseSubscriber<Socket>() {
            @Override
            public void onNext(Socket socket) {
                super.onNext(socket);
                MainActivity.this.socket = socket;
                Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void doRequest() {
        if (socket == null) return;

        SocketUtils.doRequest(socket, binding.editText.getText().toString(), new BaseSubscriber<String>() {
            @Override
            public void onNext(String result) {
                super.onNext(result);
                Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_LONG).show();
                binding.res.append(result + "\r\n");
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void send(View view) {
        doRequest();
    }

    public void over(View view) {
        SocketUtils.closeSocket(socket);
    }
}