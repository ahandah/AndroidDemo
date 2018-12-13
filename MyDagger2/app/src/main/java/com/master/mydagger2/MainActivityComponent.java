package com.master.mydagger2;

import dagger.Component;

@Component(modules = {UtilsModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
