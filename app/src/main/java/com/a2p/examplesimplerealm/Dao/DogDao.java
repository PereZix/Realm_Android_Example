package com.a2p.examplesimplerealm.Dao;

import android.content.Context;

import com.a2p.examplesimplerealm.Object.Dog;

import java.nio.file.attribute.DosFileAttributes;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by alvaro.perez on 25/07/2018.
 */

public class DogDao {

    private String ID_DOG = "id";

    private Realm realm;

    public DogDao(Context context){
        if (realm == null){
            if (realm.isClosed()){
                this.realm = Realm.getDefaultInstance();
            }
        }
    }

    public void AddDog(final String nombreRaza){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = realm.createObject(Dog.class);
                dog.setId(incrementId());
                dog.setName(nombreRaza);
            }
        });
    }

    public void DeleteDog(final int idDog){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Dog> dogResult = realm.where(Dog.class).equalTo(ID_DOG, idDog).findAll();
                dogResult.clear();
            }
        });

    }

    public RealmResults<Dog> getAllDogs(){
        realm.beginTransaction();
        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        realm.commitTransaction();

        return dogs;
    }

    /**
     * Coge e incrementa el id del objeto
     * @return
     */
    private int incrementId(){
        Number currentId = realm.where(Dog.class).max(ID_DOG);
        int nextId;

        if (currentId == null){
            nextId = 1;
        }else {
            nextId = currentId.intValue() + 1;
        }

        return nextId;
    }

    public void closeRealm(){
        if(realm != null){
            realm.close();
        }
    }
}
