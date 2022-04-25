package net.iessochoa.danielabellan.practica5pmdm.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DiarioDao {

    /*
    * Método que controla la inserción de nuevos contactos y, si ya existían, su reemplazo
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DiaDiario diaDiario);

    /*
    * Método encargado de llevar el control del borrado de los elementos
    */
    @Query("DELETE FROM "+ DiaDiario.NOMBRE_TABLA)
    void deleteAll();

    /*
    * Método que borra un DiaDiario según el que sea pasado por parámetro
    */
    @Delete
    void deleteByDiaDiario(DiaDiario diaDiario);

    /*
    * Método con el que se controla la actualización de Días de un Diario ya existente
    */
    @Update
    void update(DiaDiario diaDiario);

    /*
    * Selección de todos los DíaDiarios que tengamos en la base de datos
    */
    @Query("SELECT * FROM " + DiaDiario.NOMBRE_TABLA + " ORDER BY DiaDiario._id")
    LiveData<List<DiaDiario>> getAllDiaDiario();

    /*
    * Método que obtiene el número total de Días de un Diario
    */
    @Query("SELECT COUNT(*) from diaDiario")
    int countDiaDiario();

    /*
    * Método que devuelve todos los Días en un Diario ordenados por la fecha
    */
    @Query("SELECT * FROM " + DiaDiario.NOMBRE_TABLA + " ORDER BY fecha")
    LiveData<List<DiaDiario>> getDiaDiarioOrderByFecha();

    /*
    * Método que devuelve todos los Días en un Diario
    */
    @Query("SELECT COUNT(*) FROM "+ DiaDiario.NOMBRE_TABLA)
    Single<Integer> geTotalDiaDiario();
}