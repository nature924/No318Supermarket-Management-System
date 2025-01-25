package cn.smbms.mapper;


import cn.smbms.pojo.Stock;

import java.util.List;
import java.util.Map;

public interface StockMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    Integer countByQuery(Map<String, Object> map);

    List<Stock> findByQuery(Map<String, Object> map);
}