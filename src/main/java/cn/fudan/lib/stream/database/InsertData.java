package cn.fudan.lib.stream.database;

import cn.fudan.lib.app.xr.DeviceInfoDataItem;
import cn.fudan.lib.dao.MySqlSessionFactory;
import cn.fudan.lib.dto.DataItem;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 设置为单例模式
 */
public enum InsertData {
    INSTANCE;

    public void insertException (InsertParameter parameter) {
//        checkParameter(parameter);
        SqlSession sqlSession = MySqlSessionFactory.createSqlSession().openSession();
        DaoMapper mapper = sqlSession.getMapper(DaoMapper.class);

        try {
            mapper.insertException(parameter);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("Caught Exception");
            System.out.println("getMessage():" + e.getMessage());
            System.out.println("getLocalizedMessage():" +
                    e.getLocalizedMessage());
            System.out.println("toString()" + e);
            System.out.println("printStackTrace():");
            e.printStackTrace(System.out);
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }

    }


    /**
     * 为了保证数据库的安全，查询数据的时候，必须设置时间范围(开始时间和结束时间)
     * 如果一次查询的数据量太大，可能会导致数据库服务器宕机
     */
    private void checkParameter (QueryParameter parameter) {
        if (parameter.getBeginDate() == null) {
            throw new IllegalArgumentException("Please set parameter: beginDate");
        }

        if (parameter.getEndDate() == null) {
            throw new IllegalArgumentException("Please set parameter: endDate");
        }

        if (parameter.getExcludeIds() != null && parameter.getExcludeIds().size() <= 0) {
            parameter.setExcludeIds(null);
        }
    }
}
