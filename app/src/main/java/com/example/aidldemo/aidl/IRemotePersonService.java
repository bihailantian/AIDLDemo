package com.example.aidldemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


import com.example.aidldemo.DataTestAidlInterface;

import java.util.ArrayList;
import java.util.List;

public class IRemotePersonService extends Service {

    private static final String ACTION_RESULT = "com.example.aidldemo_IRemoteService_start";

    private List<Person> personList;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(ACTION_RESULT);
        intent.putExtra("name",IRemotePersonService.class.getSimpleName());
        sendOrderedBroadcast(intent, null);
    }


    @Override
    public IBinder onBind(Intent intent) {
        personList = new ArrayList<>();
        return mIBinder;
    }

    private IBinder mIBinder = new DataTestAidlInterface.Stub() {


        @Override
        public List<Person> getPersonListIn(Person person) throws RemoteException {
            personList.add(person);
            return personList;
        }
    };
}
