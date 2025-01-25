package cn.smbms.service;

import cn.smbms.pojo.Product;
import cn.smbms.pojo.User;
import cn.smbms.tools.Page;

import java.util.Map;

/**
 * @author xmy
 * @date 2021/3/28 11:34
 */
public interface ProductService {

    Page<Product> findByPage(Map<String, Object> map);

    Integer addProduct(Product product);

    Product findProductId(int id);

    Integer deleteProduct(int id);

    Integer updateProduct(Product product);
}
