package com.example.administrator.helloworld;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.helloworld.permission.MyPermission;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        MyPermission permission = new MyPermission(this);
//        if (permission.hasPermission(permission.ACCESS_COARSE_LOCATION)){
//
//        } else {
            permission.dynamicPermission(permission.ACCESS_COARSE_LOCATION);
//        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        System.out.println("111"+requestCode+"---"+perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        System.out.println("282");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("-"+requestCode+"--");
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
