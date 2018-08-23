package com.a2p.examplesimplerealm.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.a2p.examplesimplerealm.Adapter.AdapterMain;
import com.a2p.examplesimplerealm.Dao.DogDao;
import com.a2p.examplesimplerealm.Interface.OnItemClickListener;
import com.a2p.examplesimplerealm.Object.Dog;
import com.a2p.examplesimplerealm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtRaza)
    EditText edtRaza;
    @BindView(R.id.rcvLista)
    RecyclerView rcvLista;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;

    private Realm myRealm;
    private DogDao dogDao;
    private RealmResults<Dog> realmResults;
    private AdapterMain adapterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        realmResults = dogDao.getAllDogs();
        adapterMain = new AdapterMain(realmResults, new OnItemClickListener() {
            @Override
            public void onItemClick(Dog item) {
//                Toast.makeText(MainActivity.this, "Id: " + item.getId() + " y nombre: " + item.getName(), Toast.LENGTH_SHORT).show();
                dogDao.DeleteDog(item.getId());
                adapterMain.notifyDataSetChanged();
            }
        });
        rcvLista.setLayoutManager(new LinearLayoutManager(this));
        rcvLista.setAdapter(adapterMain);
    }

    public void init() {
        dogDao = new DogDao(this);
    }

    @OnClick({R.id.rcvLista, R.id.fabAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:
                if (edtRaza.getText().toString().equals("")) {
                    Toast.makeText(this, "Debes introducir una raza de perro", Toast.LENGTH_SHORT).show();
                    return;
                }
                String razaPerro = edtRaza.getText().toString();
                dogDao.AddDog(razaPerro);
                Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();

                adapterMain.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dogDao.closeRealm();
    }
}
