package id.kakarental.store.Model.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import id.kakarental.store.Model.dasboard.DataDasboardJual;
import id.kakarental.store.Model.dasboard.DataIDasboardSewa;
import id.kakarental.store.Model.dasboard.DataIklan;

public class DasboardViewModel extends ViewModel {
    private MutableLiveData<List<DataDasboardJual>> jual = new MutableLiveData<>();
    private MutableLiveData<List<DataIDasboardSewa>> sewa = new MutableLiveData<>();
    private MutableLiveData<List<DataIklan>> iklan = new MutableLiveData<>();

    public void setJual(List<DataDasboardJual> jual){
        this.jual.setValue(jual);
    }

    public LiveData<List<DataDasboardJual>> getJual(){
        return jual;
    }
}
