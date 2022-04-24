package net.iessochoa.danielabellan.practica5pmdm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iessochoa.danielabellan.practica5pmdm.R;
import net.iessochoa.danielabellan.practica5pmdm.model.DiaDiario;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar miToolbar;
    private SearchView svBusqueda;
    private RadioGroup miRadioGroup;
    private RadioButton rbAscendente;
    private RadioButton rbDescendente;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = findViewById(R.id.appBarLayout);

        //Asignamos la Toolbar que hemos creado en la Actividad.
        miToolbar = findViewById(R.id.miToolbar);
        setSupportActionBar(miToolbar);

        svBusqueda = findViewById(R.id.svBusqueda);

        miRadioGroup = findViewById(R.id.miRadioGroup);
        rbAscendente = findViewById(R.id.rbAscendente);
        rbDescendente = findViewById(R.id.rbDescendente);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, EdicionDiaActivity.class);
            startActivity(intent);
        });
    }

    /*
    *Este código es necesario para que se muestre nuestro Menú previamente creado en la parte superior de la pantalla
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            DiaDiario diaRecibido = (DiaDiario)
                    data.getParcelableExtra(EdicionDiaActivity.EXTRA_DIA);
            System.out.printf("huevo");


           /* if (requestCode == OPTION_REQUEST_NUEVO) {
                tareaViewModel.addTarea(tareaRecibida);

                //Si es una tarea ya creada se sobrescriben los datos
            } else if (requestCode == OPTION_REQUEST_EDITAR) {
                tareaViewModel.addTarea(tareaRecibida);
            }
            */

        }
    }





}