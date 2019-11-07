package id.kakarental.store.Model.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private MutableLiveData<Integer> size = new MutableLiveData<>();

    public void setSize(Integer input) {
        size.setValue(input);
    }

public LiveData<Integer> getSize(){
        return size;
}


}
