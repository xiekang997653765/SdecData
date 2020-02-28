package cn.fudan.lib.stream.database;

import cn.fudan.lib.dto.DataItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DaoMapper {
    List<DataItem> readData (@Param ("param") QueryParameter parameter);

}
