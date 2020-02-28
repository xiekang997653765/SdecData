package cn.fudan.lib.dao;

import cn.fudan.lib.dto.DataItem;
import org.apache.ibatis.annotations.Param;

public interface LibDataMapper {
    void CreateTableIfNotExists (@Param ("tableName") String tableName);

    String TableIsExist (@Param ("tableName") String tableName);

    void insertData (@Param("data") DataItem dataItem);

}
