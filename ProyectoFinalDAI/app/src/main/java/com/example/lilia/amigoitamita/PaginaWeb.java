package com.example.lilia.amigoitamita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class PaginaWeb extends AppCompatActivity {
    WebView wbITAM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_web);
        wbITAM=(WebView)findViewById(R.id.wbITAM);
        wbITAM.loadUrl("https://www.itam.mx/");
    }
    public void inicioAlumnoRedirectWeb(View v){
        Intent intent=new Intent(this, InicioAlumno.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("claveUnica",extras.getString("cU"));
        startActivity(intent);

    }
}
