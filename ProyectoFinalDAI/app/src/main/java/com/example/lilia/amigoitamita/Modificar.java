package com.example.lilia.amigoitamita;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    private EditText correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        correo=(EditText)findViewById(R.id.etCorreoMod);
    }
    public void inicioAlumnoRedirectMod(View v){
        Intent intent=new Intent(this, InicioAlumno.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("claveUnica",extras.getString("cU"));
        startActivity(intent);

    }
    public void modificar(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion", null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        Bundle extras=getIntent().getExtras();
        ContentValues registro=new ContentValues();
        registro.put("correo", correo.getText().toString());
        int res=db.update("alumnos",registro,"cu="+extras.getString("cU"),null);
        db.close();
        if(res==1)
            Toast.makeText(this, "Modificación exitosa", Toast.LENGTH_LONG).show();
    }
}
