package com.lz.mydemo01.dagger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lz.mydemo01.MyApplication;
import com.lz.mydemo01.R;

import javax.inject.Inject;

import dagger.Component;

public class DaggerActivity extends AppCompatActivity implements MySetInt {

    @Inject
    Person person;
    @Inject
    Person2 person2;

    @Inject
    Person3 person3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        System.out.println(person3);
        PersonModule module = new PersonModule();
//        module.setMySetInt(222);
        PersonComponent component = DaggerPersonComponent.builder()
//                .personModule(module)
//                .setInt(22)
                .setString( "sdfsdfsdf")
                .build();
//        PersonComponent component = MyApplication.getApplication().getComponent();
        component.inject(this);

//        System.out.println("DaggerActivity - - - - - - - " + "   " + component.getInt());
//        startActivity(new Intent(this, Dagger2Activity.class));

    }

    @Override
    public void setInt(int i) {
        System.out.println("");
    }
}
