package com.lz.myjlib;

import android.app.Activity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/7.
 */

public class ButterKnife {

    static final Map<Class<?>, InjectInterface<Object>> INJECTORS = new LinkedHashMap<>();
    public void bind(Activity activity) {
        InjectInterface<Object> injectInterface = findInjector(activity);
        injectInterface.inject(Finder.Activity, activity);
    }

    private InjectInterface<Object> findInjector(Object object) {
        Class _class = object.getClass();
        InjectInterface<Object> injectInterface = INJECTORS.get(_class);
        if (injectInterface == null) {
            try {
                Class injectClass = Class.forName(_class.getName() + "$$" + ProxyInfo.PROXY);
                injectInterface = (InjectInterface<Object>) injectClass.newInstance();
                INJECTORS.put(_class, injectInterface);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return injectInterface;
    }
}
