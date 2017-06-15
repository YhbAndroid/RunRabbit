package win.yuanhongbao.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class AppClose extends Application {

    // 将所有的Activity放入到一个数组
    private List<Activity> mList = new ArrayList<Activity>();
    // 创建一个关闭run类的对象
    private static AppClose close;

    // 构造方法
    private AppClose() {

    }

    public static AppClose getClose() {

        // 如果RunClose对象为空时，再创建对象
        if (close == null) {
            close = new AppClose();
        }
        return close;
    }

    // 添加Activity的方法
    public void addActivity(Activity activity) {

        mList.add(activity);
    }

    public void exit() {

        // 遍历添加到数组中的Activity
        for (Activity activity : mList) {

            // 如果数组中的Activity不为空
            if (activity != null) {
                // 关闭Activity的方法
                activity.finish();
            }
            // 将数组设为空
            mList = null;

        }

    }
}
