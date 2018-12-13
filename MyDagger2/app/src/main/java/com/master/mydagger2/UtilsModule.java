package com.master.mydagger2;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    private int i;

    public UtilsModule(int i) {
        this.i = i;
    }

    @Provides
    public int getI() {
        return i;
    }

}
