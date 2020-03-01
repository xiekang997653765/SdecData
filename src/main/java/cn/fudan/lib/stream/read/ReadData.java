package cn.fudan.lib.stream.read;

import cn.fudan.lib.dao.MySqlSessionFactory;
import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.DataHandler;
import cn.fudan.lib.stream.database.DaoMapper;
import cn.fudan.lib.stream.database.QueryParameter;
import cn.fudan.lib.stream.pojo.SubscribeItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadData extends TimerTask {
    private              DateTime          beginDate;
    private              DateTime          endDate;
    private              int               maxDataSize;
    private static final DateTimeFormatter dtf             = DateTimeFormat.forPattern("yyyy-MM-dd");
    private              SqlSessionFactory sqlSessionFactory;
    private              SubscribeItem     dataType;
    private static final Object            lock            = new Object();
    private static final ExecutorService   executorService = Executors.newFixedThreadPool(30);

    //开始时间到现在时间的差值
    private long timeDiff = 0;

    private static final Long period = 30*1000L;// 每半分钟查一次

    private void init () {
        beginDate = dtf.parseDateTime(dataType.getStartDate());
        DateTime now = new DateTime();
        beginDate = beginDate.plusHours(now.getHourOfDay());
        beginDate = beginDate.plusMinutes(now.getMinuteOfHour());
        beginDate = beginDate.plusSeconds(now.getMinuteOfHour());
        timeDiff = new DateTime().getMillis() - beginDate.getMillis();

        String endDatePro = dataType.getEndDate();
        if (StringUtils.isBlank(endDatePro)) {
            endDatePro = "2019-05-01";
        }
        endDate = dtf.parseDateTime(endDatePro);

        maxDataSize = dataType.getMaxSize();

        initDataBaseMapper();
    }

    private void initDataBaseMapper () {
        sqlSessionFactory = MySqlSessionFactory.createSqlSession();
    }

    private ReadData () {
    }

    public ReadData (SubscribeItem dataType) {
        this.dataType = dataType;
        init();
        startRedData();

    }

    private void startRedData () {
        new Timer().scheduleAtFixedRate(this, 0, period);
    }

    private final Set<DataItem> DataPool = new HashSet<>();

    @Override
    public void run () {
        QueryParameter parameter = installQueryParameter();

        if (parameter.getBeginDate().getTime() - (System.currentTimeMillis() - this.timeDiff) > 3600*1000) {
            System.out.println("数据池时间已经超过一个小时，暂停拉取数据。");
            return;
        }

        if (parameter.getBeginDate().getTime() > endDate.getMillis()) return;

        if (parameter.getEndDate().getTime() > endDate.getMillis()) {
            parameter.setEndDate(endDate.toDate());
        }

        SqlSession sqlSession = sqlSessionFactory.openSession();
        DaoMapper mapper = sqlSession.getMapper(DaoMapper.class);
        List<DataItem> dataItemList = mapper.readData(parameter);
        System.out.println(dataItemList.size());
        if (dataItemList.size() > 0) {
            Collections.sort(dataItemList);
            this.beginDate = new DateTime(dataItemList.get(0).getUpTimestamp());
            synchronized (lock) {
                DataPool.addAll(dataItemList);
            }
        } else {
            //否则，开始时间直接指向下一次结束时间
            this.beginDate = new DateTime(parameter.getEndDate());
        }
        dataItemList.clear();
        sqlSession.close();
    }

    private QueryParameter installQueryParameter () {
        QueryParameter parameter = new QueryParameter();
        List<Long> dataPoolDataIds = getDataPoolDataIds();
        if (dataPoolDataIds.size() <= 0) dataPoolDataIds.add(0L);
        parameter.setExcludeIds(dataPoolDataIds);
        parameter.setBeginDate(beginDate.toDate());
        parameter.setEndDate(beginDate.plusSeconds(60*60).toDate()); //1小时之后的数据
        parameter.setTableName(this.dataType.getDataType());

        return parameter;
    }

    protected List<Long> getDataPoolDataIds () {
        List<Long> ids = new ArrayList<>();
        synchronized (lock) {
            for (DataItem dataItem : DataPool) {
                ids.add(dataItem.getId());
            }
        }
        return ids;
    }

    public void read (final DataHandler handler) {
        final DataItem item = null;
        int hadReadItemsCount = 0;
        boolean isLimit = maxDataSize > 0;

        List<DataItem> needRemoveLit = new ArrayList<>();
        long now;
        for (; ; ) {

            synchronized (lock) {
                if (DataPool.size() <= 0) continue;
                now = System.currentTimeMillis() - timeDiff;
                needRemoveLit.clear();
                for (final DataItem dataItem : DataPool) {
                    if (dataItem.getUpTimestamp().getTime() <= now) {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run () {
                                try {
                                    handler.handle(dataItem, dataType.getDataType());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        needRemoveLit.add(dataItem);

                        if (isLimit) {
                            hadReadItemsCount++;
                        }
                    }
                }

                DataPool.removeAll(needRemoveLit);
            }
            if (isLimit && hadReadItemsCount >= maxDataSize) {
                break;
            }
        }

        System.out.printf("模拟数据读取完成！");
    }

}
