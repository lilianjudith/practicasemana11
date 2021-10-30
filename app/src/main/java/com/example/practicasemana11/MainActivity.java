package com.example.practicasemana11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText txt_usuario, txt_password;
    Button btn_iniciar_sesion;
    CheckBox chk_guardar_sesion;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();

        txt_usuario = findViewById(R.id.txt_usuario);
        txt_password = findViewById(R.id.txt_password);
        btn_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);
        chk_guardar_sesion = findViewById(R.id.chk_guardar_sesion);

        if (revisarSesion()){
            startActivity(new Intent(this, ActivityPrincipal.class));
            String mensaje2 = "Hay sesión activa del usuario";
            Toast.makeText(this,mensaje2, Toast.LENGTH_LONG).show();
        }else{
            String mensaje = "No hay sesión activa del usuario";
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }
        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarSesion(chk_guardar_sesion.isChecked());
                startActivity(new Intent(getApplicationContext(), ActivityPrincipal.class));
            }
        });
    }
    private boolean revisarSesion(){
        return this.preferences.getBoolean(llave, false);
    }

    private void guardarSesion(boolean checked) {
        editor.putBoolean(llave, checked);
        editor.apply();
    }

}