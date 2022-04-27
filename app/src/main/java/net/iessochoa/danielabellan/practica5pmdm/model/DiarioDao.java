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
     * Método encargado de llevar el control del borrado de los elementos
     */
    @Query("DELETE FROM "+ DiaDiario.NOMBRE_TABLA)
    void deleteAll();

    /*
    * Selección de todos los DíaDiarios que tengamos en la base de datos
    */
    @Query("SELECT * FROM " + DiaDiario.NOMBRE_TABLA)
    LiveData<List<DiaDiario>> getAllDiaDiario();

    /*
    * Selección de todos los DiaDiarios que contengan la palabra "resumen" en el contenido
    */
    @Query("SELECT * FROM " + DiaDiario.NOMBRE_TABLA + " WHERE " + DiaDiario.CONTENIDO + " LIKE '%' || :resumen || '%'")
    LiveData<List<DiaDiario>> getDiarioOrderBy(String resumen);

    /*
    * Selección de la media de las valoraciones de todos los días registrados, en forma de un objeto rxJava
     */
    @Query("SELECT AVG(valoracion_dia) FROM " + DiaDiario.NOMBRE_TABLA)
    Single<Float> getValoracionTotal();
}