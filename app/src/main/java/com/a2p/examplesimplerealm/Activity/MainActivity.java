package com.a2p.examplesimplerealm.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.a2p.examplesimplerealm.Adapter.AdapterMain;
import com.a2p.examplesimplerealm.Dao.DogDao;
import com.a2p.examplesimplerealm.Object.Dog;
import com.a2p.examplesimplerealm.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity{

    @InjectView(R.id.rcvLista)
    RecyclerView rcvLista;
    @InjectView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @InjectView(R.id.edtRaza)
    EditText edtRaza;

    private Realm myRealm;
    private DogDao dogDao;
    private RealmResults<Dog> realmResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        init();

        realmResults = dogDao.getAllDogs();
        rcvLista.setAdapter(new AdapterMain(realmResults, new ));
    }

    public void init(){
        dogDao = new DogDao(this);
    }

    @OnClick({R.id.rcvLista, R.id.fabAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rcvLista:
                break;
            case R.id.fabAdd:

                break;
        }
    }
}
