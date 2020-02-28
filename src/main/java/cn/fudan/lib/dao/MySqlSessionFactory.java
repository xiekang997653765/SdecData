package cn.fudan.lib.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MySqlSessionFactory {
    private static final String SQL_MAP_CONFIG_FILE = "mybatis/SqlMapConfig.xml";

    public static SqlSessionFactory createSqlSession () {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(SQL_MAP_CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

}
