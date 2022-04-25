package net.iessochoa.danielabellan.practica5pmdm.ui;
import net.iessochoa.danielabellan.practica5pmdm.R;
import net.iessochoa.danielabellan.practica5pmdm.model.DiaDiario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EdicionDiaActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_DIA = "EdicionDiaActiviy.EXTRA_DIA";

    private TextView tvFechaElegida;
    private Button btnSeleccionFecha;
    private EditText etResumenBreveDia;
    private TextView tvValoracionDia;
    private Spinner spValoracionDia;
    private EditText etResumenDia;
    private FloatingActionButton fabGuardarNuevoDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_dia);

        tvFechaElegida = findViewById(R.id.tvFechaElegida);

        btnSeleccionFecha = findViewById(R.id.btnSeleccionFecha);
        btnSeleccionFecha.setOnClickListener(this);

        etResumenBreveDia = findViewById(R.id.etResumenBreveDia);
        tvValoracionDia = findViewById(R.id.tvValoracionDia);
        spValoracionDia = findViewById(R.id.spValoracionDia);
        spValoracionDia.setSelection(4);
        etResumenDia = findViewById(R.id.etResumenDia);

        fabGuardarNuevoDia = findViewById(R.id.fabGuardarNuevoDia);
        fabGuardarNuevoDia.setOnClickListener(this);

        //Aquí comprobamos en qué forma ha sido llamada la Activity. Si se ha llamado para editar, cargamos todos los datos del Día que ha sido llamado. En caso contrario, simplemente dejamos
        //todos los valores por defecto y colocamos la fecha del nuevo día a la actual del Sistema.
        DiaDiario entradaExistente = getIntent().getParcelableExtra(EXTRA_DIA);
        if(entradaExistente != null){
            tvFechaElegida.setText(entradaExistente.getFechaFormatoLocal());
            etResumenDia.setText(entradaExistente.getResumen());
            tvValoracionDia.setText(entradaExistente.getValoracionDia());
            spValoracionDia.setSelection((entradaExistente.getValoracionDia())-1);
            etResumenDia.setText(entradaExistente.getResumen());
        }
        else{
            tvFechaElegida.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSeleccionFecha:
                Calendar calendarioNuevo = Calendar.getInstance();

                DatePickerDialog selectorFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar miCalendario = Calendar.getInstance();
                        miCalendario.set(year, monthOfYear, dayOfMonth);
                        tvFechaElegida.setText(dayOfMonth+"/"+ monthOfYear+"/"+year);
                    }

                }, calendarioNuevo.get(Calendar.YEAR), calendarioNuevo.get(Calendar.MONTH), calendarioNuevo.get(Calendar.DAY_OF_MONTH));
                selectorFecha.show();
                break;


            case R.id.fabGuardarNuevoDia:
                if(comprobarCamposVacios(etResumenBreveDia, etResumenDia)){
                    avisoRellenarCampos();
                }
                else{
                    //java.sql.Date fechaEnFormatoSql = java.sql.Date.valueOf(tvFechaElegida.getText().toString());
                    //Intent intentRetorno = new Intent();
                    //DiaDiario diaRetorno = new DiaDiario(fechaEnFormatoSql, Integer.parseInt(spValoracionDia.getSelectedItem().toString()), etResumenBreveDia.getText().toString(), etResumenDia.getText().toString());
                    //intentRetorno.putExtra(EXTRA_DIA, (Parcelable) diaRetorno);
                    //setResult(RESULT_OK, intentRetorno);
                    finish();
                }
                break;


            default:
                break;
        }
    }

    /*
    * Método encargado de comprobar si todos los campos necesarios para la creación de un nuevo Dia en el Diario han sido rellenados.
    */
    public boolean comprobarCamposVacios(EditText resumenBreve, EditText resumenGeneral){
        boolean resultado = false;

        if(resumenBreve.getText().toString().equals("") || resumenGeneral.getText().toString().equals("")){
            resultado = true;
        }

        return resultado;
    }

    /*
    * Método que muestra un aviso al usuario para que rellene todos los campos necesarios para la creación de un nuevo Dia en el Diario.
    */
    public void avisoRellenarCampos(){
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);
        aviso.setTitle("Campos vacíos");
        aviso.setMessage("Se han detectado uno o más campos sin rellenar. Por favor, rellénelos todos antes de guardar el día");
        aviso.setPositiveButton(android.R.string.ok, null);
        aviso.show();
    }
}