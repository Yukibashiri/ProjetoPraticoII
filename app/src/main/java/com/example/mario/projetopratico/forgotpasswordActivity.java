package com.example.mario.projetopratico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forgotpasswordActivity extends AppCompatActivity {

    Button btnRequisitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        btnRequisitar = (Button) findViewById(R.id.recuperar_senha);


        btnRequisitar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
