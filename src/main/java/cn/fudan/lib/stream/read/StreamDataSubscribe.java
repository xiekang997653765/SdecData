package cn.fudan.lib.stream.read;

import cn.fudan.lib.stream.core.DataHandler;
import cn.fudan.lib.stream.pojo.SubscribeItem;
import cn.fudan.lib.stream.utils.DataTypeConstants;

import java.util.HashSet;
import java.util.Set;

public class StreamDataSubscribe {
    private DataHandler        handler;
    private Set<SubscribeItem> SubscribeDataTypes;

    private StreamDataSubscribe () {
    }

    public StreamDataSubscribe (DataHandler handler, SubscribeItem... dataTypes) {
        super();
        checkDataTypes(dataTypes);
        this.handler = handler;
    }

    public void start () {
        for (final SubscribeItem dataType : SubscribeDataTypes) {
            new Thread(new Runnable() {
                @Override
                public void run () {
                    new ReadData(dataType).read(handler);
                }
            }).start();

        }
    }

    private void checkDataTypes (SubscribeItem[] dataTypes) {
        if (dataTypes.length <= 0) {
            throw new IllegalArgumentException("Enter at least one data type parameter");
        }

        SubscribeDataTypes = new HashSet<>();

        for (SubscribeItem dataType : dataTypes) {
            if (DataTypeConstants.isValidDataType(dataType.getDataType()))
                SubscribeDataTypes.add(dataType);
        }

        if (SubscribeDataTypes.size() <= 0) {
            throw new IllegalArgumentException("Enter at least one data type parameter");
        }

    }

}
