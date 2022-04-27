package net.iessochoa.danielabellan.practica5pmdm.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

@Entity(tableName = DiaDiario.NOMBRE_TABLA, indices = {@Index(value = {DiaDiario.FECHA}, unique = true)})
public class DiaDiario implements Parcelable {
    public static final String NOMBRE_TABLA = "diaDiario";
    public static final String ID = BaseColumns._ID;
    public static final String FECHA = "fecha";
    public static final String VALORACION_DIA = "valoracion_dia";
    public static final String RESUMEN = "resumen";
    public static final String CONTENIDO = "contenido";
    public static final String FOTO_URI = "foto_uri";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = ID)
    private int id;

    @ColumnInfo(name = FECHA)
    private Date fecha;

    @ColumnInfo(name = VALORACION_DIA)
    private int valoracionDia;

    @ColumnInfo(name = RESUMEN)
    private String resumen;

    @ColumnInfo(name = CONTENIDO)
    private String contenido;

    @ColumnInfo(name = FOTO_URI)
    private String fotoUri;

    /*
    * Constructor de DiaDiario con todos los parámetros menos el ID y fotoUri
    */
    public DiaDiario(Date fecha, int valoracionDia, String resumen, String contenido){
        this.fecha = fecha;
        this.valoracionDia = valoracionDia;
        this.resumen = resumen;
        this.contenido = contenido;
        this.fotoUri = "";
    }

    /*
    * Constructor de DiaDiario con todos los parámetros menos el ID
    */
    //TODO: Cambiar posteriormente (quizás)
    @Ignore
    public DiaDiario(Date fecha, int valoracionDia, String resumen, String contenido, String fotoUri){
        this(fecha, valoracionDia, resumen, contenido);
        this.fotoUri = fotoUri;
    }

    /*
    * Método que devuelve un valor entero dependiendo del valor de valoracionDia. Es utilizado para llevar el control de la imagen mostrada.
    */
    public int getValoracionResumida(){
        if(valoracionDia < 5){
            return 1;
        }
        else if(valoracionDia < 8){
            return 2;
        }
        else{
            return 3;
        }

    }

    /*
    * Método idéntico al anterior, pero Estático y al que le pasamos el valor a comprobar por parámetro.
    */
    public static int getValoracionResumidaEstatico(int valor){
        if(valor < 5){
            return 1;
        }
        else if(valor< 8){
            return 2;
        }
        else{
            return 3;
        }
    }

    /*
    * Método que nos devuelve la fecha de DiaDiario en el formato del dispositivo que se esté utilizando.
    */
    public String getFechaFormatoLocal(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return df.format(fecha);
    }

    /*
    * Método idéntico al anterior, pero Estático y al que le pasamos la fecha que queremos formatear por parámetro.
    */
    public static String getFechaFormatoLocalEstatico(Date fechaParametro){
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return df.format(fechaParametro);
    }

    /*
    * Método que devuelve el campo "id" de un DiaDiario
    */
    public int getId() {
        return id;
    }

    /*
    * Método que asigna al campo "id" de un DiaDiario el valor int pasado por parámetro
    */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * Método que devuelve el campo "fecha" de un DiaDiario
     */
    public Date getFecha() {
        return fecha;
    }

    /*
     * Método que asigna al campo "fecha" de un DiaDiario el valor Date pasado por parámetro
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /*
     * Método que devuelve el campo "valoracionDia" de un DiaDiario
     */
    public int getValoracionDia() {
        return valoracionDia;
    }

    /*
     * Método que asigna al campo "valoracionDia" de un DiaDiario el valor int pasado por parámetro
     */
    public void setValoracionDia(int valoracionDia) {
        this.valoracionDia = valoracionDia;
    }

    /*
     * Método que devuelve el campo "resumen" de un DiaDiario
     */
    public String getResumen() {
        return resumen;
    }

    /*
     * Método que asigna al campo "resumen" de un DiaDiario el valor String pasado por parámetro
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /*
     * Método que devuelve el campo "contenido" de un DiaDiario
     */
    public String getContenido() {
        return contenido;
    }

    /*
     * Método que asigna al campo "contenido" de un DiaDiario el valor String pasado por parámetro
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /*
     * Método que devuelve el campo "fotoUri" de un DiaDiario
     */
    public String getFotoUri() {
        return fotoUri;
    }

    /*
     * Método que asigna al campo "fotoUri" de un DiaDiario el valor String pasado por parámetro
     */
    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    //Serie de métodos generados mediante la implementación de Parcelable

    protected DiaDiario(Parcel in) {
        id = in.readInt();
        valoracionDia = in.readInt();
        resumen = in.readString();
        contenido = in.readString();
        fotoUri = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(valoracionDia);
        dest.writeString(resumen);
        dest.writeString(contenido);
        dest.writeString(fotoUri);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiaDiario> CREATOR = new Creator<DiaDiario>() {
        @Override
        public DiaDiario createFromParcel(Parcel in) {
            return new DiaDiario(in);
        }

        @Override
        public DiaDiario[] newArray(int size) {
            return new DiaDiario[size];
        }
    };

    //Fin de la serie de métodos generados mediante la implementación de Parcelable
}
