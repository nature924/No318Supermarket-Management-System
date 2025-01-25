package cn.smbms.service;

import java.util.List;
import java.util.Map;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.tools.Page;

public interface BillService {
	
	Page<Bill> findBillByPage(Map<String,Object> map);

	Integer delectBill(Integer id);
	
	Integer addBill(Bill bill);
	
	Integer updateBill(Bill bill);
	
	Bill findBillByid(Integer id);
	
	List<Provider> showProviderinfo();

    void deleteList(String[] ids);
}
