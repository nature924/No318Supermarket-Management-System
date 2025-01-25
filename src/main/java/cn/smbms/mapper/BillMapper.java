package cn.smbms.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;

public interface BillMapper {
	
	/**
	 * findBillByPage  (查询数据+分页)
	 * @param map
	 * @return
	 */
	List<Bill> findBillByPage(Map<String,Object> map);

	/**
	 * countByPage  (查询数据总条数)
	 * @param map
	 * @return
	 */
	Integer countByPage(Map<String,Object> map);

	/**
	 * delectBill  (通过ID删除订单信息)
	 * @param id
	 * @return
	 */
	Integer delectBill(@Param("id")Integer id);

	/**
	 * addBill  (添加订单信息)
	 * @param bill
	 * @return
	 */
	Integer addBill(Bill bill);

	/**
	 * updateBill  (更新订单信息)
	 * @param bill
	 * @return
	 */
	Integer updateBill(Bill bill);

	/**
	 * findBillByid  (通过ID获取订单信息)
	 * @param id
	 * @return
	 */
	Bill findBillByid(@Param("id")Integer id);

	/**
	 * showProviderinfo  (获取供应商订单信息)
	 * @return
	 */
	List<Provider> showProviderinfo();

    void deleteList(String[] ids);
}
