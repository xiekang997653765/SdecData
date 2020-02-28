package cn.fudan.lib;

import cn.fudan.lib.dao.LibDataMapper;
import cn.fudan.lib.dao.MySqlSessionFactory;
import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.utils.ReadFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static       SqlSessionFactory sqlSessionFactory;
    private static final Set<String>       tables = new HashSet<>();

    public static void main (String[] args) {
        if (args.length <= 0) {
            System.out.println("Please input file location!");
            return;
        }

        String fileName = args[0];
        List<String> data = readDataFromFile(fileName);
        if (data == null || data.size() <= 0) {
            System.out.println("No Data!");
            return;
        }

        sqlSessionFactory = MySqlSessionFactory.createSqlSession();

        if (data != null && data.size() > 0) {
            processData(data);
        }
    }

    private static List<String> readDataFromFile (String fileName) {
        List<String> data = null;
        try {
            data = ReadFile.readDataFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void processData (List<String> data) {
        int totalLines = data.size();
        int cursor = 1;
        for (String item : data) {
            processDataItem(item, cursor, totalLines);
            cursor++;
        }
        System.out.println("Task Over");
    }

    private static void processDataItem (String item, int cursor, int totalLines) {
        System.out.println("[" + cursor + "/" + totalLines + "]");
        DataItem dataItem = parseDataItemFromString(item);
        tableCheck(dataItem.getDeviceCode());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LibDataMapper mapper = sqlSession.getMapper(LibDataMapper.class);
        mapper.insertData(dataItem);
        sqlSession.close();
    }

    private static void tableCheck (String deviceCode) {
        if (!tables.contains(deviceCode)) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            LibDataMapper mapper = sqlSession.getMapper(LibDataMapper.class);
            String res = mapper.TableIsExist(deviceCode);
            if (StringUtils.isBlank(res)) {
                mapper.CreateTableIfNotExists(deviceCode);
            }
            sqlSession.close();
        }
    }

    private static DataItem parseDataItemFromString (String item) {
        String[] split = item.split("\t");
        DataItem r = new DataItem();
        r.setDeviceId(split[0]);
        r.setDeviceCode(split[1]);
        r.setProvider(split[2]);
        r.setUpTimestamp(new Date(Long.parseLong(split[3])*1000));
        r.setData(split[4]);
        r.setH(Integer.parseInt(split[6]));
        r.setD(Integer.parseInt(split[7]));
        r.setM(Integer.parseInt(split[8]));
        r.setY(Integer.parseInt(split[9]));
        return r;
    }

}
