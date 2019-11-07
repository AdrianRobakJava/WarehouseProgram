package application;

import java.util.HashMap;

public class WareList {
	
	private static HashMap<String, Ware> wareList;
	private static int size;
	
	public WareList() {
		wareList = new HashMap<>();
	}
	public boolean addWare(Ware ware) {
		if(wareList.get(ware.getCode()) == null) {
			wareList.put(ware.getCode(), ware);
			size++;
			return true;
		}else {
			return false;
		}
	}
	public boolean removeWare(String code) {
		if(wareList.get(code) != null) {
			wareList.remove(code);
			size--;
			return true;
		}else {
			return false;
		}
	}
	public Ware getWare(String code) {
		return wareList.get(code);
	}
	public int getNumberOfWare() {
		return size;
	}
	public HashMap<String, Ware> getWareList(){
		return wareList;
	}
}
