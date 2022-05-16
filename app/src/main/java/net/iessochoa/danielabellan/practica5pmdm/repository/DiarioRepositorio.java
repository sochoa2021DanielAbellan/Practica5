package net.iessochoa.danielabellan.practica5pmdm.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.iessochoa.danielabellan.practica5pmdm.model.DiaDiario;
import net.iessochoa.danielabellan.practica5pmdm.model.DiarioDao;
import net.iessochoa.danielabellan.practica5pmdm.model.DiarioDatabase;

import java.util.List;

public class DiarioRepositorio {
    //Realizamos la implementación del patrón Repository
    private static volatile DiarioRepositorio INSTANCE;

    private DiarioDao mDiarioDao;
    private LiveData<List<DiaDiario>> mAllDias;

    /*
    * Inicializamos el patrón Singleton
    */
    public static DiarioRepositorio getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (DiarioRepositorio.class) {
                if (INSTANCE == null) {
                    INSTANCE=new DiarioRepositorio(application);
                }
            }
        }
        return INSTANCE;
    }

    /*
    * Inicializamos el repositorio y lo enlazamos a la base de datos.
    */
    private DiarioRepositorio(Application application){
        DiarioDatabase db = DiarioDatabase.getDatabase(application);

        //Obtenemos el DAO para poder realizar las consultas CRUD a la base de datos
        mDiarioDao =db.diarioDao();

        //Recuperamos toda la lista de dias en el diario mediante el uso de LiveData que hemos declarado como variable local
        mAllDias = mDiarioDao.getAllDiaDiario();
    }

    public LiveData<List<DiaDiario>> getAllDias(){
        return mAllDias;
    }

    /*
    public LiveData<List<Contacto>> getByNombre(String nombre){
        mAllContactos=mContactoDao.findByNombre(nombre);
        return mAllContactos;
    }
     */

    /*
    public LiveData<List<Contacto>> getByNombreFecha(String nombre, Date menorQue){
        mAllContactos=mContactoDao.findByNombreFecha(nombre,menorQue);
        return mAllContactos;
    }
     */

    /*
    public LiveData<List<Contacto>> getContactosOrderByNombre(){
        mAllContactos=mContactoDao.getContactosOrderByNombre();
        return mAllContactos;
    }
    public LiveData<List<Contacto>> getContactosOrderByFecha(){
        mAllContactos=mContactoDao.getContactosOrderByFecha();
        return mAllContactos;
    }

    public LiveData<List<Contacto>> getContactosOrderBy(String order_by, String order){
        mAllContactos=mContactoDao.getContactosOrderBy(order_by, order);
        return mAllContactos;
    }
    */

   /*
    public Single<Integer> geTotalContactos(){
        return mContactoDao.getTotalContactos();
    }
    */

    /*
    Para realizar una inserción, es necesario hacerlo como una tarea en segundo plano
    */
    public void insert(DiaDiario dia){
        DiarioDatabase.databaseWriteExecutor.execute(()->{
            mDiarioDao.insert(dia);
        });
    }

    /*
    Al igual que para insertar, para poder realizar borrados tendremos que hacerlo mediante tareas en segundo plano
    */
    public void delete(DiaDiario dia){
        DiarioDatabase.databaseWriteExecutor.execute(()->{
            mDiarioDao.deleteByDiaDiario(dia);
        });
    }
}
