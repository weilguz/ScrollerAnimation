package abc.com.scrolleronanimation.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import abc.com.scrolleronanimation.R;

/**
 * ZXing开发demo
 */

public class ZxingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);

        Button zxBtn = (Button) findViewById(R.id.btn_zxing);
        Button ziDin = (Button) findViewById(R.id.btn_zi_din);
        zxBtn.setOnClickListener(this);
        ziDin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_zxing:
                //开启扫描,使用原生扫描界面
                yuanSheng();
                break;
            case R.id.btn_zi_din:
                //开启自定义扫描界面
                ziDinYi();
                break;
        }
    }

    private void ziDinYi() {
        new IntentIntegrator(this)
                //设置自定义扫描界面的activity
                .setCaptureActivity(ZiDinZxActivity.class)
                //扫码的类型,可选：一维码，二维码，一/二维码
                .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
                //设置提示语
                .setPrompt("请对准二维码")
                //设置是否开启扫完后的声音提示
                .setBeepEnabled(false)
                //扫完码后生成二维码图片
                .setBarcodeImageEnabled(true)
                .initiateScan();
    }

    private void yuanSheng() {
        new IntentIntegrator(this)
                //扫码的类型,可选：一维码，二维码，一/二维码
                .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
                //设置提示语
                .setPrompt("请对准二维码")
                //设置是否开启扫完后的声音提示
                .setBeepEnabled(false)
                //扫完码后生成二维码图片
                .setBarcodeImageEnabled(true)
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                Log.e("weilgu","扫描失败... ...");
            }else{
                Log.e("weilgu","扫描成功 " + intentResult.getContents());
            }
        }else{

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
