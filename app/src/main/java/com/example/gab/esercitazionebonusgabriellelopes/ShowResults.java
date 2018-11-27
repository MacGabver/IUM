package com.example.gab.esercitazionebonusgabriellelopes;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;



public class ShowResults extends AppCompatActivity {

    Login login;
    TextView usernameText;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        usernameText=(TextView) findViewById(R.id.username);

        Intent intent = getIntent();

        Serializable obj=intent.getSerializableExtra(MainActivity.LOGIN_EXTRA);

        if(obj instanceof Login) {
            login = (Login)obj;
        }
        else {
            login = new Login();
        }

        usernameText.setText(login.getUsername());

        back = (Button)findViewById(R.id.back);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(ShowResults.this,
                        MainActivity.class);
                startActivity(mainActivity);


            }
        });
    }
}
