package cn.smbms.service.impl;

import cn.smbms.mapper.StockMapper;
import cn.smbms.pojo.Product;
import cn.smbms.pojo.Stock;
import cn.smbms.service.StockService;
import cn.smbms.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xmy
 * @date 2021/3/28 18:37
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public Page<Stock> findByPage(Map<String, Object> map) {
        Integer count = stockMapper.countByQuery(map);
        Page<Stock> pa = new Page<>();
        pa.setCount(count);
        pa.setIndex(Integer.parseInt(map.get("index").toString()));
        map.put("index", (pa.getIndex()-1)*pa.getSize());
        map.put("size", pa.getSize());
        List<Stock> list = stockMapper.findByQuery(map);
        pa.setList(list);
        return pa;
    }

    @Override
    public Integer addStock(Stock stock) {
        return stockMapper.insert(stock);
    }

    @Override
    public Stock findStockId(int id) {
        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteStock(int id) {
        return stockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateStock(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }
}
