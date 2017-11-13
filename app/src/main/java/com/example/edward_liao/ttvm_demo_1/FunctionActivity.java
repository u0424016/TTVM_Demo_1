package com.example.edward_liao.ttvm_demo_1;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class FunctionActivity extends AppCompatActivity {

    Button pay, balance, deposit, withdraw, transfer, changepassword, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        first();

    }

    //禁用系統返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }


    private void first() {
        pay = (Button) findViewById(R.id.button_pay);
        balance = (Button) findViewById(R.id.button_balance);
        deposit = (Button) findViewById(R.id.button_deposit);
        withdraw = (Button) findViewById(R.id.button_withdraw);
        transfer = (Button) findViewById(R.id.button_transfer);
        changepassword = (Button) findViewById(R.id.button_changepassword);
        logout = (Button) findViewById(R.id.button_logout);
    }

    //前往付款畫面
    public void setPay(View view) {
        finish();
        Intent PayActivity = new Intent(this, Pay.class);
        startActivity(PayActivity);

    }

    //前往餘額查詢畫面
    public void setBalance(View view) {
        finish();
        Intent Balance = new Intent(this, Balance.class);
        startActivity(Balance);

    }
    /*
        //前往存款畫面
        public void setDeposit(View view) {
            finish();
            Intent Deposit = new Intent(this, Deposit.class);
            startActivity(Deposit);

        }

        //前往提款畫面
        public void setWithdraw(View view) {
            finish();
            Intent Withdraw = new Intent(this, Withdraw.class);
            startActivity(Withdraw);

        }

        //前往轉帳畫面
        public void setTransfer(View view) {
            finish();
            Intent Transfer = new Intent(this, Transfer.class);
            startActivity(Transfer);
        }
    */
    //前往登出畫面
    public void setLogout(View view) {


        new AlertDialog.Builder(FunctionActivity.this)
                .setTitle("")
                .setMessage("確定登出?")
                .setIcon(R.mipmap.erro)
                .setPositiveButton("登出",
                        new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //確定登出
                                Logout();
                                // TODO Auto-generated method stub
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

    public void setChangepassword(View view) {
        finish();
        Intent changepassword = new Intent(this, ChangePasswordActivity.class);
        startActivity(changepassword);
    }


    //登出
    public void Logout() {

        finish();
        Intent Login = new Intent(this, MainActivity.class);
        startActivity(Login);

    }


}