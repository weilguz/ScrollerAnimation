package abc.com.scrolleronanimation.zxing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import abc.com.scrolleronanimation.R;

/**
 * 自定义zixing扫描的activit
 */

public class ZiDinZxActivity extends AppCompatActivity {

    //用来启动扫码和处理扫码结果的类
    private CaptureManager mManager;
    //扫码界面
    private DecoratedBarcodeView mDbv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_din_zx);

        //将扫码界面布局到我们自定义的界面的布局中
        mDbv = (DecoratedBarcodeView) findViewById(R.id.dbv_zixin);

        mManager = new CaptureManager(this, mDbv);
        mManager.initializeFromIntent(getIntent(),savedInstanceState);
        mManager.decode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mManager.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDbv.onKeyDown(keyCode,event) || super.onKeyDown(keyCode,event);
    }
}
