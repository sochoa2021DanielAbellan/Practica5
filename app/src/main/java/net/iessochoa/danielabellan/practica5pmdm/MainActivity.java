package net.iessochoa.danielabellan.practica5pmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos y asignamos la Toolbar que hemos creado en la Actividad.
        Toolbar miToolbar = findViewById(R.id.miToolbar);
        setSupportActionBar(miToolbar);
    }

    /*
    *Este código es necesario para que se muestre nuestro Menú previamente creado en la parte superior de la pantalla
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





}