package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveWare {
	
	private WareList wareList;
	private String name;
	private String dir = System.getProperty("user.dir");
	
	public SaveWare(WareList wL) {
		wareList = wL;
		
		for (Ware waree : wareList.getWareList().values()) {
			name = waree.getName();
			save(waree);
		}
	}
	//Zapis towarów do plików
	public void save(Ware ware) {
		File file = new File(dir + "/Files" + "/" + name + ".txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.println(ware.getName());
			pw.println(ware.getGroup());
			pw.println(ware.getCode());
			pw.println(ware.getUnit());
			pw.println(ware.getQuantity());
			pw.println(ware.getSellPrice());
			pw.println(ware.getBuyPrice());
			pw.println(ware.getImagePath());
	
			
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			pw.close();
		}
	}

}
