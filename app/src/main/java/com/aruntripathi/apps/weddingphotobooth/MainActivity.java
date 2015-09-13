package com.aruntripathi.apps.weddingphotobooth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button _loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _loginBtn = ( Button ) findViewById(R.id.btn_login);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = ( EditText ) findViewById(R.id.fld_username);
                EditText password = ( EditText ) findViewById(R.id.fld_pwd);
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                boolean authToken = doAuth(usernameValue, passwordValue);

                if(!authToken) {
                    //Unsuccessful authentication
                    Toast.makeText(getBaseContext(), (String) "Username/Password don't match!",Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences prefs = getSharedPreferences("photobooth_app", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("key_for_username",usernameValue);
                editor.putString("key_for_password",passwordValue);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, WeddingActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean doAuth(String username, String password) {
        if(username.equalsIgnoreCase("user123") && password.equals("mypass")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
