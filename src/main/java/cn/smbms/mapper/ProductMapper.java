package cn.smbms.mapper;


import cn.smbms.pojo.Product;
import cn.smbms.pojo.User;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * findByQuery  (动态查询+分页)
     * @param map
     * @return
     */
    List<Product> findByQuery(Map<String, Object> map);

    /**
     * countByQuery  (总条数)
     * @param map
     * @return
     */
    Integer countByQuery(Map<String, Object> map);
}