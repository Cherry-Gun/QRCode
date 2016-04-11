package com.wyb.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity {

    private TextView tvResult;
    private EditText etText;
    private ImageView ivResult;
    private CheckBox logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tv_result);
        etText = (EditText) findViewById(R.id.et_text);
        ivResult = (ImageView) findViewById(R.id.iv_result);
        logo = (CheckBox) findViewById(R.id.cb_logo);
    }

    //解析二维码---start
    public void scan(View view) {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            tvResult.setText(result);
        }
    }
    //解析二维码---end

    //生成二维码---start
    public void make(View view) {
        //EncodingUtils.createQRCode()
        String input = etText.getText().toString();
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //Bitmap bitmap = EncodingUtils.createQRCode(input, 1000, 1000, null);  // (二维码内容, 二维码宽度, 二维码高度, 二维码中间LOGO)
            Bitmap bitmap = EncodingUtils.createQRCode(
                    input, 1000, 1000, logo.isChecked() ?
                            BitmapFactory.decodeResource(getResources(),
                                    R.mipmap.ic_launcher) : null);  // (二维码内容, 二维码宽度, 二维码高度, 二维码中间LOGO)
            ivResult.setImageBitmap(bitmap);
        }
    }
    //生成二维码---end

}
