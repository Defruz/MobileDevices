package com.example.portalnoticias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.MarkerEdgeTreatment;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;


// Clase con la que se crea la nueva actividad tras pulsar el boton login desde la pantalla principal
public class LoginForm extends AppCompatActivity {
    EditText username, password;
    TextView error;
    Button loginButton;
    FloatingActionButton login, close;
    CheckBox recuerdame;



    // Se crearan los cajas de texto editable donde se introducira el usuario y la contraseña, asi como
    // un checkbox recuerdame para mantener la sesion iniciada entre sesiones.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        error = findViewById(R.id.failLog);
        login = findViewById(R.id.login);
        close = findViewById(R.id.close);
        recuerdame = findViewById(R.id.checkBox_recuerdame);

        // Se obtiene la apikey en caso de que antes se hubiera guardado eligiendo el campo recuerdame.
        final SharedPreferences recordatorio = getSharedPreferences("recuerdame", Context.MODE_PRIVATE);

        // Si la informacion introducida para el login es correcta se llamara al metodo login de la clase Rest
        // creada para realizar el login.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL url = null;
                try {
                    url = new URL("https://sanger.dia.fi.upm.es/pmd-task/login");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Rest.login(url,username.getText().toString(), password.getText().toString());
                if (!Rest.getCabecera().equals("noLog")){
                    MainActivity.cambiarLogin();
                    if(recuerdame.isChecked())
                    {
                        SharedPreferences.Editor editor = recordatorio.edit();
                        editor.putString("apikey", Rest.getCabecera());
                        editor.apply();
                    }
                    finish();
                }
                else{
                    error.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
