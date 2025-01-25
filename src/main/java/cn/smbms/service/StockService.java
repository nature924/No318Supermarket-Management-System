package cn.smbms.service;

import cn.smbms.pojo.Product;
import cn.smbms.pojo.Stock;
import cn.smbms.tools.Page;

import java.util.Map;

/**
 * @author xmy
 * @date 2021/3/28 18:36
 */
public interface StockService {

    Page<Stock> findByPage(Map<String, Object> map);

    Integer addStock(Stock stock);

    Stock findStockId(int id);

    Integer deleteStock(int id);

    Integer updateStock(Stock stock);
}
