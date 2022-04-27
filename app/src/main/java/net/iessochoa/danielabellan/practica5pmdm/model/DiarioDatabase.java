package net.iessochoa.danielabellan.practica5pmdm.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//le indicamos las entidades de la base de datos y la versión
@Database(entities = {DiaDiario.class}, version = 1)
@TypeConverters({TransformaFechaSQLite.class}) //Con esta línea, conseguimos que nos transforme automáticamente las líneas a enteros mediante la clase que hemos creado previamente
public abstract class DiarioDatabase extends RoomDatabase {

    //Creamos una variable de tipo DiarioDao para poder acceder a los métodos CRUD
    public abstract DiarioDao diarioDao();

    //Declaramos la base de datos
    private static volatile DiarioDatabase INSTANCE;

    /*
    * Para utilizar una base de datos montada con CRUD, hemos de realizar las tareas CRUD mediante llamadas a hilos. En aquellas Select que hemos creado
    * y que devuelven LiveData, estos hilos ya son creados automáticamentes gracias a la estructura interna de Room, pero para el resto de operaciones
    * tendremos que crearlos manualmente. Para llevar el control de los mismos, utilizaremos la clase ExecutorService.
    */
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /*
    * Al utilizar el patrón Singleton, crearemos la base de datos a la hora de realizar una petición de obtenerla,
    * y se mantendrá constante durante el resto de la ejecución del mismo.
    */
    public static DiarioDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiarioDatabase.class) {
                if (INSTANCE == null) {
                    //Inicializamos la base de datos
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DiarioDatabase.class, "agenda_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    /*
    * En este método, declaramos una tarea en segundo plano que carga unos datos de ejemplo
    * para que la base de datos contenga algunos datos a la hora de abrirse
    */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                DiarioDao mDao=INSTANCE.diarioDao(); //Obtenemos en una variable la base de datos para poder realizar las operaciones sobre ella
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                DiaDiario diaDiario = null;
                try {
                    diaDiario = new DiaDiario(
                            formato.parse("20-4-2022"), 3,
                            "No ha sido gran cosa, he asistido al trabajo y poco más",
                            "PruebaPruebaPruebaPruebaPrueba");
                    mDao.insert(diaDiario);

                    diaDiario = new DiaDiario(
                            formato.parse("22-4-2022"), 5,
                            "Un día normal, y por fin es Viernes y comienza el fin de semana",
                            "FindeFindeFindeFindeFindeFinde");
                    mDao.insert(diaDiario);

                    diaDiario = new DiaDiario(
                            formato.parse("23-4-2022"), 8,
                            "Día estupendo, he quedado con los amigos y lo hemos pasado fenomenal",
                            "Jolgorio");
                    mDao.insert(diaDiario);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }

        /*
        * Este método dicta las operaciones que se realizan en el momento en el que se abre la base de datos,
        * por lo que si queremos que se realice alguna acción en concreto en esos momentos, las escribiremos dentro.
         */
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

}
