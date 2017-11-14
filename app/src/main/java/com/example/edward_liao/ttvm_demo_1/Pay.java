package com.example.edward_liao.ttvm_demo_1;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.*;

import org.json.JSONObject;
import org.json.JSONException;


public class Pay extends AppCompatActivity {
    private Button button_qrcode, button_yes, button_cancel;
    private TextView textView_enter;
    int money_pay, enter;
    private Activity pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        first();


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();

            System.out.print(scanContent);


            String str = "{\n" +
                    "                    \"MacAddress\": \"DC:36:99:F8:D2:3C\",\n" +
                    "                    \"Price\": 50\n" +
                    "}";

            try{
                JSONObject jsonObject = new JSONObject(str);

                String JMacAddress = jsonObject.getString("MacAddress");
                int JPrice = jsonObject.getInt("Price");
                enter = JPrice;


            }
            catch(JSONException e) {
                e.printStackTrace();

            }


            String temp = "金額為"+enter+"元";
            textView_enter.setText(temp);
            button_yes.setVisibility(View.VISIBLE);


        } else {
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
        }
    }


    //禁用系統返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    public void first() {
        this.pay = this;

        button_qrcode = (Button) findViewById(R.id.button_qrcode);
        button_yes = (Button) findViewById(R.id.button_yes);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        textView_enter = (TextView)findViewById(R.id.textView_enter);
    }


    public void setButton_qrcode(View view) {
        IntentIntegrator scanIntegrator = new IntentIntegrator(pay);
        scanIntegrator.initiateScan();
        button_qrcode.setVisibility(View.INVISIBLE);

    }


    public void setButton_yes(View view) {

        new AlertDialog.Builder(Pay.this)
                .setTitle("注意")
                .setMessage("即將進行交易。確定付款?")
                .setIcon(R.mipmap.erro)
                .setPositiveButton("確定付款",
                        new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                if (enter > money_pay) {

                                    new AlertDialog.Builder(Pay.this)
                                            .setTitle("錯誤")
                                            .setMessage("餘額不足。請重新輸入")
                                            .setIcon(R.mipmap.erro)
                                            .setNegativeButton("確定",
                                                    new DialogInterface.OnClickListener() {

                                                        @Override
                                                        public void onClick(DialogInterface dialog,
                                                                            int which) {
                                                            // TODO Auto-generated method stub
                                                        }
                                                    }).show();

                                } else {
                                    GlobalVariable gv = (GlobalVariable) getApplicationContext();
                                    money_pay = gv.getMoney_total();
                                    money_pay = money_pay - enter;
                                    gv.setMoney_total(money_pay);
                                    enter = 0;

                                    setStatus();

                                    setBack();
                                    // TODO Auto-generated method stub
                                }


                            }
                        })

                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub

                            }
                        }).show();


    }


    //回到功能頁面
    public void setButton_cancel(View view) {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);
    }

    //傳送付款成功訊息給Server
    public void setStatus() {

    }

    //回到功能頁面
    public void setBack() {
        finish();
        Intent back = new Intent(this, FunctionActivity.class);
        startActivity(back);
    }
}