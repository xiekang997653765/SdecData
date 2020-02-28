package cn.fudan.lib.stream.core;

import cn.fudan.lib.dto.DataItem;

public abstract class SimpleStreamDataHandler extends StreamDataHandler {

    @Override
    void beforeHandle () {
    }

    @Override
    void afterHandle (DataItem in, String dataType) {
    }

}
