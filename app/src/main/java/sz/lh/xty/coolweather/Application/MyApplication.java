package sz.lh.xty.coolweather.Application;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

import sz.lh.xty.coolweather.utils.LogUtils;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("MyApplication: ","进入到MyApplication");
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext() {
        return context;
    }

}
