package com.example.lilia.amigoitamita;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AltaAlumno extends AppCompatActivity {

    private EditText cU, nombre, contra, dep,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumno);
        cU=(EditText)findViewById(R.id.etClaveUnica);
        nombre=(EditText)findViewById(R.id.etNombre);
        contra=(EditText)findViewById(R.id.etContra);
        dep=(EditText)findViewById(R.id.etDepartamento);
        correo=(EditText)findViewById(R.id.etCorreo);
    }
    public void limpia(View v){
        cU.setText("");
        nombre.setText("");
        contra.setText("");
        dep.setText("");
        correo.setText("");
    }
    public void alta(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String claveUnica=cU.getText().toString();
        String nom=nombre.getText().toString();
        String depa=dep.getText().toString();
        String con=contra.getText().toString();
        String cor=correo.getText().toString();

        ContentValues registro=new ContentValues();
        registro.put("cu", claveUnica);
        registro.put("nombre", nom);
        registro.put("departamento", depa);
        registro.put("contra", con);
        registro.put("correo",cor);
        db.insert("alumnos",null, registro);
        db.close();
        limpia(v);
        Toast.makeText(this, "Se cargaron todos los datos correctamente", Toast.LENGTH_LONG).show();
    }
    public void regresar(View v){
        startActivity(new Intent(this,Inicio.class));
    }
}
