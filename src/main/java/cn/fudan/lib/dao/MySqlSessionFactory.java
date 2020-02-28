package cn.fudan.lib.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MySqlSessionFactory {
    private static       SqlSessionFactory sqlSessionFactory   = null;
    private static final String            SQL_MAP_CONFIG_FILE = "mybatis/SqlMapConfig.xml";

    private MySqlSessionFactory () {
    }

    public static SqlSessionFactory createSqlSession () {
        if (sqlSessionFactory == null) {
            synchronized (MySqlSessionFactory.class) {
                if (sqlSessionFactory == null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = Resources.getResourceAsStream(SQL_MAP_CONFIG_FILE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return sqlSessionFactory;
    }

}
