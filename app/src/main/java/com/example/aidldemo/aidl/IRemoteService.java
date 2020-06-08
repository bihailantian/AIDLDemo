package com.example.aidldemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidldemo.IMyAidlInterface;

public class IRemoteService extends Service {

    private static final String ACTION_RESULT = "com.example.aidldemo_IRemoteService_start";
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(ACTION_RESULT);
        intent.putExtra("name",IRemoteService.class.getSimpleName());
        sendOrderedBroadcast(intent, null);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    private IBinder mIBinder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };
}
