package com.lz.mydemo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.lz.mydemo01.dao.DataBaseManager;
import com.lz.mydemo01.dao.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MyRoomActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room);

        ButterKnife.bind(this);

        RxView.clicks(tv)
                .subscribe( aVoid -> {

                    System.out.println(aVoid);
                });




        new Thread(new Runnable() {
            @Override
            public void run() {
               User u =  DataBaseManager.getInstance().getDatabase().userDao().getAll().get(0);

//            DataBaseManager.getInstance().getDatabase().userDao().insertAll(new User("user1"));
            System.out.println("****************DataBase****************"
                    + DataBaseManager.getInstance().getDatabase().userDao().getAll().get(0).value);
//                System.out.println("****************DataBase****************"
//                        + DataBaseManager.getInstance().getDatabase().userDao().getAll().get(1).value);
            }
        }).start();


    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}
