package com.danielblandon.itembotons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.actions.NoteIntents;

public class MainActivity extends AppCompatActivity {


    Button google,alarma,llamada,panatalla2,foto,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panatalla2 = findViewById(R.id.GoAct2);
        google = findViewById(R.id.Google);
        alarma = findViewById(R.id.CrearAlarma);
        llamada = findViewById(R.id.Llamar);
        foto = findViewById(R.id.Foto);
        email = findViewById(R.id.enviarEmail);


        Intent ir = new Intent(MainActivity.this,MainActivity2.class);

        panatalla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ir.getAction();
            startActivity(ir);
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent goToGoogle = new Intent(Intent.ACTION_VIEW);
                 goToGoogle.setData(Uri.parse("https://www.google.com"));
                 startActivity(goToGoogle);
            }
        });

        alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearAlarma();
            }
        });

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialPhoneNumber("3006276182");
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               tomarFoto();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] destinatarios = {"danielblandon_01@hotmail.com"};

                // Asunto del correo electrónico
                String asunto = "Prueba";

                // Cuerpo del correo electrónico
                String mensaje = "Este es el contenido del correo electrónico.";

                // Crea un Intent para enviar el correo electrónico
                Intent EnviarE= new Intent(Intent.ACTION_SEND);
                EnviarE.setType("message/rfc822"); // Tipo MIME para correos electrónicos

                // Configura los destinatarios, el asunto y el mensaje
                EnviarE.putExtra(Intent.EXTRA_EMAIL, destinatarios);
                EnviarE.putExtra(Intent.EXTRA_SUBJECT, asunto);
                EnviarE.putExtra(Intent.EXTRA_TEXT, mensaje);

                try {
                    startActivity(Intent.createChooser(EnviarE, "Elige una aplicación de correo electrónico"));
                } catch (android.content.ActivityNotFoundException ex) {
                    // Si no hay ninguna aplicación de correo electrónico instalada, muestra un mensaje de error
                    Toast.makeText(MainActivity.this, "No se encontró una aplicación de correo electrónico.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void crearAlarma(){
        Intent alarma =new Intent(AlarmClock.ACTION_SET_ALARM);
        alarma.putExtra(AlarmClock.EXTRA_MESSAGE,"Gym");
        alarma.putExtra(AlarmClock.EXTRA_HOUR,10);
        alarma.putExtra(AlarmClock.EXTRA_MINUTES,30);

        if (alarma.resolveActivity(getPackageManager()) != null){
            startActivity(alarma);
        }
    }


    public void dialPhoneNumber(String phoneNumber) {
        Intent nota = new Intent(Intent.ACTION_DIAL);

        try {
            nota.setData(Uri.parse("tel:" + phoneNumber));
            if (nota.resolveActivity(getPackageManager()) != null) {
                startActivity(nota);
            }
        }catch (Exception e){
           System.out.print("hola");
        }

    }

    public void tomarFoto (){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(takePictureIntent);
        }
    }


}

