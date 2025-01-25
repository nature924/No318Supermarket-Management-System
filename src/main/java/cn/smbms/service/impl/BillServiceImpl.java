package cn.smbms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.tools.Page;

@Service("billService")
public class BillServiceImpl implements BillService{

	@Autowired
	private BillMapper billMapper;

	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}

	public Page<Bill> findBillByPage(Map<String, Object> map) {
		Integer count = billMapper.countByPage(map);
		Page<Bill> pa = new Page<Bill>();
		pa.setCount(count);
		pa.setIndex(Integer.parseInt(map.get("index").toString()));
		map.put("index", (pa.getIndex()-1)*pa.getSize());
		map.put("size", pa.getSize());
		List<Bill> list = billMapper.findBillByPage(map);
		pa.setList(list);
		return pa;
	}

	public Integer delectBill(Integer id) {
		return billMapper.delectBill(id);
	}

	public Integer addBill(Bill bill) {
		return billMapper.addBill(bill);
	}

	public Integer updateBill(Bill bill) {
		return billMapper.updateBill(bill);
	}

	public Bill findBillByid(Integer id) {
		return billMapper.findBillByid(id);
	}

	public List<Provider> showProviderinfo() {
		return billMapper.showProviderinfo();
	}

	@Override
	public void deleteList(String[] ids) {
		billMapper.deleteList(ids);
	}


}
