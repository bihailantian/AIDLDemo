// DataTestAidlInterface.aidl
package com.example.aidldemo;
import com.example.aidldemo.Person; //Person 是aidl文件夹下的Person.aidl
// Declare any non-default types here with import statements

interface DataTestAidlInterface {

     // in 表示客户端向服务端传递数据，当服务端的数据发生改变，不会影响到客户端
     // out表示服务端对数据修改，客户端会同步变动
     // inout表示客户端和服务端的数据总是同步的

    List<Person> getPersonListIn(in Person person);
}
