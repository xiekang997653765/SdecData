package cn.fudan.lib.stream.read;

import cn.fudan.lib.stream.core.DataHandler;
import cn.fudan.lib.stream.pojo.SubscribeItem;
import cn.fudan.lib.stream.utils.DataTypeConstants;

import java.util.HashSet;
import java.util.Set;

public class StreamDataSubscribe {
    private              DataHandler        handler;
    private              Set<SubscribeItem> SubscribeDataTypes;
    private              Set<ReadData>      tasks;
    private static final Object             lock = new Object();

    private StreamDataSubscribe () {
    }

    public StreamDataSubscribe (DataHandler handler, SubscribeItem... dataTypes) {
        super();
        checkDataTypes(dataTypes);
        this.handler = handler;
        this.tasks = new HashSet<>();
    }

    public void start () {
        for (final SubscribeItem dataType : SubscribeDataTypes) {
            new Thread(new Runnable() {
                @Override
                public void run () {
                    ReadData readData = new ReadData(dataType);
                    readData.read(handler);
                    synchronized (lock) {
                        tasks.add(readData);
                    }
                }
            }).start();
        }
    }

    public void stop () {
        if (this.tasks != null && tasks.size() > 0) {
            for (ReadData task : tasks) {
                task.stop();
            }
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
