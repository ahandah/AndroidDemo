package com.example.administrator.helloworld.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/11/22.
 */

public class MyPermission {

    private Context mContext;

    public MyPermission(Context context){
        mContext = context;
    }

    public String[] PERMISSION = {
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE
    };

    public String[] permissionName = {
            "相机权限",
            "定位权限",
            "通过网络定位权限",
            "电话权限",
            "电话状态权限",
            "短信权限",
            "存储权限",
            "存储权限",
            "允许程序访问Wi-Fi网络状态"
    };

    public int CAMERA = 0;
    public int ACCESS_FINE_LOCATION = 1;
    public int ACCESS_COARSE_LOCATION = 2;
    public int CALL_PHONE = 3;
    public int READ_PHONE_STATE = 4;
    public int READ_SMS = 5;
    public int READ_EXTERNAL_STORAGE = 6;
    public int WRITE_EXTERNAL_STORAGE = 7;
    public int ACCESS_WIFI_STATE = 8;

    public String[] mainPERMISSION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private static final int RC_PERMISSION = 10000;
    private static final int RC_DYNPERMISSION = 10001;
    //  启动APP检测权限,未开启的权限引导用户开启
    @AfterPermissionGranted(RC_PERMISSION)
    public void mainPermission(){

        if (!hasMainPermission()){
            EasyPermissions.requestPermissions(
                    (Activity) mContext,
                    "麦厅需要如下权限!",
                    RC_PERMISSION,
                    mainPERMISSION);
        }
    }
    private boolean hasMainPermission() {
        return EasyPermissions.hasPermissions(mContext, mainPERMISSION);
    }

//    @AfD

    @AfterPermissionGranted(RC_DYNPERMISSION)
    public void dynamicPermission( int i){

        if (!hasPermission(i)){
            EasyPermissions.requestPermissions(
                    (Activity) mContext,
                    "麦厅需要"+permissionName[i]+"权限!",
                    RC_PERMISSION,
                    PERMISSION[i]);
        }
    }
    public boolean hasPermission(int i) {
        return EasyPermissions.hasPermissions(mContext, PERMISSION[i]);
    }

}
