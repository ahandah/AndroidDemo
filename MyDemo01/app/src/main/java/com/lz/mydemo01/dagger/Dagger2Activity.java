package com.lz.mydemo01.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lz.mydemo01.MyApplication;
import com.lz.mydemo01.R;

import javax.inject.Inject;

public class Dagger2Activity extends AppCompatActivity {

    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

//        PersonComponent component = MyApplication.getApplication().getComponent();
//        component.inject(this);

        System.out.println("Dagger2Activity" + person);
    }
}
