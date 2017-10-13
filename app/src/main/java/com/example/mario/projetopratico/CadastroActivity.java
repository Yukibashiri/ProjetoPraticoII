package com.example.mario.projetopratico;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {


    private ConstraintLayout tela;
    private LinearLayout telaLogin;
    private Button btnCadastro;
    private Button btnVoltar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        tela = (ConstraintLayout) findViewById(R.id.tela_de_cadastro);
        telaLogin = (LinearLayout) findViewById(R.id.tela_login);
        btnVoltar = (Button) findViewById(R.id.btn_already);
        btnCadastro = (Button) findViewById(R.id.btn_signup);


        btnCadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent novaTela = new Intent(CadastroActivity.this, LoginActivity.class);
                //CadastroActivity.this.startActivity(novaTela);
                setResult(RESULT_OK);
                finish();
            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }


}
