package win.yuanhongbao.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import win.yuanhongbao.application.AppClose;
import win.yuanhongbao.fragment.CartFragment;
import win.yuanhongbao.fragment.FinshFragment;
import win.yuanhongbao.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    private TextView mHomeTv, mCartTv, mFinshTv;
    private HomeFragment mHomeFg;
    private CartFragment mCartFg;
    private FinshFragment mFinshFg;
    private FragmentManager fm;
    private Drawable mMainImg, mMainCheckImg,
            mCartImg, mCartCheckImg,
            mFinshImg, mFinshCheckImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 将该Activity添加到退出APP的数组
        AppClose.getClose().addActivity(this);

        bindId();

        initData();

        setDefaultFragment();// 设置默认的Fragment
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            newDialog();
        }

        return true;
    }

    private void bindId() {
        mHomeTv = (TextView) findViewById(R.id.main_tv_home);
        mCartTv = (TextView) findViewById(R.id.main_tv_cart);
        mFinshTv = (TextView) findViewById(R.id.main_tv_finsh);
    }

    private void newDialog() {
        // 添加选择对话框
        new AlertDialog.Builder(MainActivity.this).setTitle("提示")
                .setMessage("确认退出吗？").setPositiveButton("取消", null)
                .setNegativeButton("确定",
                        // 点击“确定”时删除
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0,
                                                int arg1) {
                                AppClose.getClose().exit();//调用退出APP方法
                                System.exit(0);//退出Java虚拟机
                            }
                            // 重要的show方法！
                        }).show();
    }

    private void initData() {
        fm = getFragmentManager();

        mMainImg = getResources().getDrawable(R.drawable.main);
        mMainCheckImg = getResources().getDrawable(R.drawable.main_check);
        mCartImg = getResources().getDrawable(R.drawable.shopping);
        mCartCheckImg = getResources().getDrawable(R.drawable.shopping_check);
        mFinshImg = getResources().getDrawable(R.drawable.completed);
        mFinshCheckImg = getResources().getDrawable(R.drawable.completed_check);

        mMainImg.setBounds(0, 0, mMainImg.getMinimumWidth(), mMainImg.getMinimumHeight());
        mMainCheckImg.setBounds(0, 0, mMainCheckImg.getMinimumWidth(), mMainCheckImg.getMinimumHeight());
        mCartImg.setBounds(0, 0, mCartImg.getMinimumWidth(), mCartImg.getMinimumHeight());
        mCartCheckImg.setBounds(0, 0, mCartCheckImg.getMinimumWidth(), mCartCheckImg.getMinimumHeight());
        mFinshImg.setBounds(0, 0, mFinshImg.getMinimumWidth(), mFinshImg.getMinimumHeight());
        mFinshCheckImg.setBounds(0, 0, mFinshCheckImg.getMinimumWidth(), mFinshCheckImg.getMinimumHeight());
    }

    private void setDefaultFragment() {
        mHomeTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColorCheck));
        mCartTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColor));
        mFinshTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColor));
        mHomeTv.setCompoundDrawables(null, mMainCheckImg, null, null);
        mCartTv.setCompoundDrawables(null, mCartImg, null, null);
        mFinshTv.setCompoundDrawables(null, mFinshImg, null, null);
        FragmentTransaction ft = fm.beginTransaction();
        mHomeFg = new HomeFragment();
        ft.replace(R.id.main_fl, mHomeFg);
        ft.commit();
    }

    public void home(View view) {
        setDefaultFragment();
    }

    public void cart(View view) {
        mHomeTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColor));
        mCartTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColorCheck));
        mFinshTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColor));
        mHomeTv.setCompoundDrawables(null, mMainImg, null, null);
        mCartTv.setCompoundDrawables(null, mCartCheckImg, null, null);
        mFinshTv.setCompoundDrawables(null, mFinshImg, null, null);
        FragmentTransaction ft = fm.beginTransaction();
        mCartFg = new CartFragment();
        ft.replace(R.id.main_fl, mCartFg);
        ft.commit();
    }

    public void finsh(View view) {
        mHomeTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColor));
        mCartTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColor));
        mFinshTv.setTextColor(this.getResources().getColor(R.color.colorMainTextColorCheck));
        mHomeTv.setCompoundDrawables(null, mMainImg, null, null);
        mCartTv.setCompoundDrawables(null, mCartImg, null, null);
        mFinshTv.setCompoundDrawables(null, mFinshCheckImg, null, null);
        FragmentTransaction ft = fm.beginTransaction();
        mFinshFg = new FinshFragment();
        ft.replace(R.id.main_fl, mFinshFg);
        ft.commit();
    }
}
