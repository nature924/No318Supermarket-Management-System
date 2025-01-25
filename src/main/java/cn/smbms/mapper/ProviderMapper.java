package cn.smbms.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.smbms.pojo.Provider;

public interface ProviderMapper {

	/**
	 * findProviderByPage  (查询数据+分页)
	 * @param map
	 * @return
	 */
	List<Provider> findProviderByPage(Map<String,Object> map);

	/**
	 * countByPage  (查询数据总条数)
	 * @param map
	 * @return
	 */
	Integer countByPage(Map<String,Object> map);

	/**
	 * delectProvider  (通过ID删除供应商信息)
	 * @param id
	 * @return
	 */
	Integer delectProvider(@Param("id")Integer id);

	/**
	 * addProvider  (添加供应商信息)
	 * @param provider
	 * @return
	 */
	Integer addProvider(Provider provider);

	/**
	 * updateProvider  (更新供货商信息)
	 * @param provider
	 * @return
	 */
	Integer updateProvider(Provider provider);

	/**
	 * findBillByid  (通过ID获取供货商信息)
	 * @param id
	 * @return
	 */
	Provider findProviderByid(@Param("id")Integer id);
	
	/**
	 * findBillByProviderid  (通过供应商ID查询订单信息)
	 * @param id
	 * @return
	 */
	Integer findBillByProviderid(@Param("id")Integer id);
	
	/**
	 * 获取供应商列表信息
	 * @return
	 */
	List<Provider> showProviderInfo();

}
