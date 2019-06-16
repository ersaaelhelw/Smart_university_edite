package com.example.momen.smart_university.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Momen on 6/15/2019.
 */

public class GetNoteModel extends AndroidViewModel {

    private LiveData<List<NoteEntry>> listLiveData;

    public GetNoteModel(@NonNull Application application) {
        super(application);
        DatabaseRoom databaseRoom = DatabaseRoom.getsInstance(this.getApplication());
        listLiveData = databaseRoom.tableDio().getAllNote();
    }

    public LiveData<List<NoteEntry>> getNote(){
        return listLiveData;
    }

}

