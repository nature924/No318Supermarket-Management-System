package cn.smbms.service.impl;

import cn.smbms.mapper.ProductMapper;
import cn.smbms.pojo.Product;
import cn.smbms.pojo.User;
import cn.smbms.service.ProductService;
import cn.smbms.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public ProductMapper getProductMapper() {
        return productMapper;
    }

    @Override
    public Page<Product> findByPage(Map<String, Object> map) {
        Integer count = productMapper.countByQuery(map);
        Page<Product> pa = new Page<Product>();
        pa.setCount(count);
        pa.setIndex(Integer.parseInt(map.get("index").toString()));
        map.put("index", (pa.getIndex()-1)*pa.getSize());
        map.put("size", pa.getSize());
        List<Product> list = productMapper.findByQuery(map);
        pa.setList(list);
        return pa;
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public Product findProductId(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer deleteProduct(int id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }
}
