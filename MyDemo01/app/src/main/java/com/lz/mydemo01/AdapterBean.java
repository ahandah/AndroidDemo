package com.lz.mydemo01;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2018/7/24.
 */

public class AdapterBean extends SectionEntity<String> {
    private boolean isMore;
    public AdapterBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public AdapterBean(String s) {
        super(s);
    }
}
