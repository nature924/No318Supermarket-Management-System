package cn.smbms.tools;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	public final Integer size = 5 ; 	
	private Integer index;
	private Integer count;
	private Integer total;
	private List<T> list = new ArrayList<T>();
	
	public Integer getSize() {
		return size;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		if(index <= 1){
			this.index = 1 ;
		}else if(index >= total){
			this.index = total;
		}else{
			this.index = index;
		}
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		if(count >= 0 ){
			Integer stotal = count%size==0?count/size:count/size+1;
			this.setTotal(stotal);
		}
		this.count = count;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		if(total == 0){
			this.total = 1;
		}else{
			this.total = total;
		}
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
