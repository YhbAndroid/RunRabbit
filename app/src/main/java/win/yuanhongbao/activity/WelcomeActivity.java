package win.yuanhongbao.activity;

import android.content.Intent;
import android.os.Bundle;

import win.yuanhongbao.application.AppClose;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 将该Activity添加到退出APP的数组
        AppClose.getClose().addActivity(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }).start();
    }
}
