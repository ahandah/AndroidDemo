package com.example.administrator.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.helloworld.greenDao.User;
import com.example.administrator.helloworld.greenDao.gen.UserDao;

import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private User mUser;
    private UserDao mUserDao;
    private Button btnQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        btnQuery = findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(this);
        init();
    }

    public void init(){
        mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
//        mUser = new User( 1l, "ewr", 12);
//        mUserDao.insert(mUser);

    }

    public void query(){

//        User user = mUserDao.load(1l);
//        List<User> users = mUserDao.loadAll();
//        String userName = "";
//        for (int i = 0; i < users.size(); i++) {
//            userName += users.get(i).getName()+",";
//        }
//        System.out.println("查询全部数据==>"+userName);
        User user = mUserDao.queryBuilder().where(UserDao.Properties.Id.eq("1")).build().unique();
        System.out.println("查询全部数据==>"+user.getName());
    }

    public void delete() {
//        List<User> userList = (List<User>) userDao.queryBuilder().where(UserDao.Properties.Id.le(10)).build().list();
//        for (User user : userList) {
//            userDao.delete(user);
//        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_query:

                query();
                break;
        }
    }
}
