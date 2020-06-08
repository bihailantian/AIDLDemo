package com.example.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidldemo.aidl.Person;

import java.util.List;

/**
 * AIDL跨进程传递对象
 * https://www.jianshu.com/p/0818f78c6e2f
 */
public class PersonActivity extends AppCompatActivity {
    private Button btn;
    private List<Person> persons;
    private DataTestAidlInterface mDataTestAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        //服务连接成功时回调
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDataTestAidlInterface = DataTestAidlInterface.Stub.asInterface(service);
        }

        //断开服务时回调
        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (mDataTestAidlInterface != null) {
                mDataTestAidlInterface = null;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person("张三", 10);
                try {
                    persons = mDataTestAidlInterface.getPersonListIn(person);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(PersonActivity.this, persons.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        bindService();
    }

    //绑定服务
    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.aidldemo",
                "com.example.aidldemo.aidl.IRemotePersonService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
