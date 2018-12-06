package com.example.lilia.amigoitamita;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    private EditText cu, contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        cu=(EditText)findViewById(R.id.etClaveUnicaIni);
        contra=(EditText)findViewById(R.id.etContraIni);
    }
    public void altaRedirect(View v){
        startActivity(new Intent(this,AltaAlumno.class));
    }
    public void ingresar(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String claveUnica=cu.getText().toString();
        Cursor fila=db.rawQuery("select contra from alumnos where cu="+claveUnica, null);
        if(fila.moveToFirst()){
            if(fila.getString(0).equals(contra.getText().toString())){
                Intent intent=new Intent(this,InicioAlumno.class);
                intent.putExtra("claveUnica",claveUnica);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Contraseña equivocada", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "No existe alumno registrado con esa clave única", Toast.LENGTH_LONG).show();
        }
        db.close();
    }
}
