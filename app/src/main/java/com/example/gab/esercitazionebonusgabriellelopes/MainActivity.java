package com.example.gab.esercitazionebonusgabriellelopes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText username, password;
    TextView errorText;
    Button insert;
    List<Login> BDD=new LinkedList<>();
    Login login=new Login();

    public static final String
            LOGIN_EXTRA="com.example.gab.esercitazionebonusgabriellelopes.Login";



    public void updateLogin()
    {
        login.setUsername(""+username.getText());
        login.setPassword(""+password.getText());
    }



    private boolean CheckInput(){
        int errors=0;


        if(username.getText() == null || username.getText().length() ==0) {
            username.setError("You must insert your username.");
            errors++;
        }
        else {
            username.setError(null);
        }


        if(password.getText() == null || password.getText().length() ==0) {
            password.setError("You must insert your password.");
            errors++;
        }
        else {
            password.setError(null);
        }

        int i=0;
        int j=0;
        for(Login l:BDD){
            if(username.getText().toString().equals(l.getUsername())){
                j++;
                if(password.getText().toString().equals(l.getPassword())){
                    i++;
                }
            }
        }

        if(j==0){
            username.setError("This username doesn't exist.");
            errors++;
        }
        else if (i == 0) {
                password.setError("Incorrect password.");
                errors++;
        }


        switch (errors) {
            case 0:
                errorText.setVisibility(View.GONE);
                errorText.setText("");
                break;
            case 1:

                errorText.setVisibility(View.VISIBLE);
                errorText.setText("There is an error.");
                break;
            default :
                errorText.setVisibility(View.VISIBLE);
                errorText.setText("There are " + errors+" errors.");
                break;
        }
        return errors==0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        insert = (Button)findViewById(R.id.insert);
        errorText=(TextView) findViewById(R.id.errorText);


        errorText.setVisibility(View.GONE);

        //FAKE DATABASE
        BDD.add(new Login());
        BDD.add(new Login());
        BDD.add(new Login());
        BDD.get(0).setUsername("John23x");
        BDD.get(0).setPassword("Cagliari1");
        BDD.get(1).setUsername("Shyshy");
        BDD.get(1).setPassword("Migraine");
        BDD.get(2).setUsername("MacGabver");
        BDD.get(2).setPassword("Gorby");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    updateLogin();

                    System.out.println(login.getUsername() + " " + login.getPassword());

                    Intent showResults = new Intent(MainActivity.this,
                            ShowResults.class);

                    showResults.putExtra(LOGIN_EXTRA, login);
                    startActivity(showResults);
                }
            }
        });
    }


}
