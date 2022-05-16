package net.iessochoa.danielabellan.practica5pmdm.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.iessochoa.danielabellan.practica5pmdm.model.DiaDiario;
import net.iessochoa.danielabellan.practica5pmdm.repository.DiarioRepositorio;

import java.util.List;

public class DiarioViewModel extends AndroidViewModel {
    private DiarioRepositorio mRepositorio;
    private LiveData<List<DiaDiario>> mAllDias;

    public DiarioViewModel(@NonNull Application application) {
        super(application);
        mRepositorio = DiarioRepositorio.getInstance(application);
        mAllDias = mRepositorio.getAllDias();
    }

    public LiveData<List<DiaDiario>> getAllDias() {
        return mAllDias;
    }

    /*
    * La inserción y el borrado los realizamos utilizando el observador que hemos instanciado previamente
    */
    public void insert(DiaDiario dia){
        mRepositorio.insert(dia);
    }

    public void delete(DiaDiario dia){
        mRepositorio.delete(dia);
    }
}
