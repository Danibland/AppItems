package com.danielblandon.itembotons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.actions.NoteIntents;

public class MainActivity extends AppCompatActivity {


    Button google,alarma,llamada,panatalla2,foto,nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panatalla2 = findViewById(R.id.GoAct2);
        google = findViewById(R.id.Google);
        alarma = findViewById(R.id.CrearAlarma);
        llamada = findViewById(R.id.Llamar);
        foto = findViewById(R.id.Foto);
        nota = findViewById(R.id.crearNota);




        panatalla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNote("Hola","perrito");
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
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void tomarFoto (){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(takePictureIntent);
        }
    }

    public void createNote(String subject, String text) {
        Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE);
            intent.putExtra(NoteIntents.EXTRA_NAME, subject);
            intent.putExtra(NoteIntents.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



}

