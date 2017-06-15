package win.yuanhongbao.utils;


import android.content.Context;
import android.widget.Toast;

public class RunToast {

    public void Tshort(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public void Tlong(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
