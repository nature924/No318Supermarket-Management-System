package cn.smbms.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;
import cn.smbms.tools.Page;

public interface ProviderService {
	
	Page<Provider> findProviderByPage(Map<String,Object> map);
	
	Integer delectProvider(@Param("id")Integer id);
	
	Integer addProvider(Provider provider);
	
	Integer updateProvider(Provider provider);
	
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
