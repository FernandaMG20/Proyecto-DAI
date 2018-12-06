package com.example.lilia.amigoitamita;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class BuscarAlumno extends AppCompatActivity {

    private EditText departamento;
    private TextView busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_alumno);
        departamento=(EditText)findViewById(R.id.etDepartamentoBus);
        busqueda=(TextView) findViewById(R.id.tvAlumnos);
    }
    public void inicioAlumnoRedirectBus(View v){
        Intent intent=new Intent(this, InicioAlumno.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("claveUnica",extras.getString("cU"));
        startActivity(intent);
    }
    public void buscar(View v){
        busqueda.setText("");
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor fila=db.rawQuery("select nombre, correo from alumnos where departamento='"+departamento.getText().toString()+"'",null);
        if(fila.moveToFirst()){
            busqueda.setText(busqueda.getText()+fila.getString(0)+"\t \t"+fila.getString(1)+"\n");
            while(fila.moveToNext()){
                busqueda.setText(busqueda.getText()+fila.getString(0)+"\t \t"+fila.getString(1)+"\n");
            }
        }
        db.close();
    }
}
