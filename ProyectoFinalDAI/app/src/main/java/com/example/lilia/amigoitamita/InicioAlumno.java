package com.example.lilia.amigoitamita;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InicioAlumno extends AppCompatActivity {

    private TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_alumno);
        Bundle extras=getIntent().getExtras();
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor fila=db.rawQuery("select nombre from alumnos where cu = "+ extras.getString("claveUnica"),null);
        nombre=(TextView)findViewById(R.id.tvNombreIniA);
        fila.moveToFirst();
        nombre.setText(fila.getString(0));
        db.close();

    }
    public void pagWebRedirect(View v){
        Intent intent=new Intent(this, PaginaWeb.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("cU",extras.getString("claveUnica"));
        startActivity(intent);
    }
    public void modificarRedirect(View v){
        Intent intent=new Intent(this,Modificar.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("cU",extras.getString("claveUnica"));
        startActivity(intent);
    }
    public void buscarRedirect(View v){
        Intent intent=new Intent(this, BuscarAlumno.class);
        Bundle extras=getIntent().getExtras();
        intent.putExtra("cU",extras.getString("claveUnica"));
        startActivity(intent);
    }
    public void baja(View v){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db=admin.getWritableDatabase();
        Bundle extras=getIntent().getExtras();
        int res=db.delete("alumnos","cu="+extras.getString("claveUnica"),null);
        db.close();
        if(res==1){
            Toast.makeText(this,"Eliminación exitosa", Toast.LENGTH_LONG).show();
        }
        inicioRedirect(v);
    }
    public void inicioRedirect(View v){
        startActivity(new Intent(this, Inicio.class));
    }
}
