package cn.smbms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderService;
import cn.smbms.tools.Page;
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper providerMapper;

	public void setProviderMapper(ProviderMapper providerMapper) {
		this.providerMapper = providerMapper;
	}

	public Page<Provider> findProviderByPage(Map<String, Object> map) {
		Integer count = providerMapper.countByPage(map);
		Page<Provider> pa = new Page<Provider>();
		pa.setCount(count);
		pa.setIndex(Integer.parseInt(map.get("index").toString()));
		map.put("index", (pa.getIndex()-1)*pa.getSize());
		map.put("size", pa.getSize());
		List<Provider> list = providerMapper.findProviderByPage(map);
		pa.setList(list);
		return pa;
	}

	public Integer delectProvider(Integer id) {
		return providerMapper.delectProvider(id);
	}

	public Integer addProvider(Provider provider) {
		return providerMapper.addProvider(provider);
	}

	public Integer updateProvider(Provider provider) {
		return providerMapper.updateProvider(provider);
	}

	public Provider findProviderByid(Integer id) {
		return providerMapper.findProviderByid(id);
	}

	public Integer findBillByProviderid(Integer id) {
		return providerMapper.findBillByProviderid(id);
	}

	public List<Provider> showProviderInfo() {
		return providerMapper.showProviderInfo();
	}
	

}
