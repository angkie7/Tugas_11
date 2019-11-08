package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Button resetButton = (Button)findViewById(R.id.btnReset);
        resetButton.setOnClickListener( btnResetListener );

        Button btnBoom = (Button)findViewById(R.id.btnBoom);
        resetButton.setOnClickListener( btnCustomDialog );

        Button btnSetting = (Button) findViewById( R.id.btnSettings );
        btnSetting.setOnClickListener( myBtnSettingClick );
        SharedPreferences prefs =
                MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(myBtnLoginClick);
        if (statusLogin != null){
            btnLogin.setText("Logout");
        }else{
            btnLogin.setText("Login");
        }

    }

    private View.OnClickListener myBtnSettingClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent( MainActivity.this, SettingsActivity.class );
            startActivity( intent );
        }

        ;
    };

    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs =
                    MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();
            Button btnLogin = (Button)findViewById(R.id.btnLogin);
            if (statusLogin != null){
                edit.putString("isLogin",null);
                btnLogin.setText("Login");
            }else{
                edit.putString("isLogin","Admin");
                btnLogin.setText("Logout");
            }
            edit.commit();
        }
    };

    private View.OnClickListener btnResetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
            builder.setMessage( "Apakah anda yakin untuk reset nilai ?" ).setNegativeButton( "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Toast.makeText( MainActivity.this, "Tidak Jadi", Toast.LENGTH_SHORT ).show();
                }
            } )
                    .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Toast.makeText( MainActivity.this, "Melakukan Reset", Toast.LENGTH_SHORT ).show();
                        }
                    } );

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    private View.OnClickListener btnCustomDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog( MainActivity.this );
            dialog.setContentView( R.layout.custom_dialog );
            dialog.setTitle( "Custom Dialog" );
            Button btnDialog = (Button) dialog.findViewById( R.id.btnBoom );
            btnDialog.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            } );
            dialog.show();
        }
    };
}



