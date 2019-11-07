package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoadWare {
	
	private WareList wareList;
	private String dir = System.getProperty("user.dir");
	private int lineNumber;
	
	private String name;
	private String group;
	private String code;
	private String unit;
	private String quantity;
	private String sellPrice;
	private String buyPrice;
	private String imagePath;
	
	
	public LoadWare(){
		try {
			wareList = new WareList();
			File file = new File(dir + "/Files");
			
			for(File f : file.listFiles()) {
				lineNumber = 0;
				Scanner scan = new Scanner(f);
				
				while(scan.hasNextLine() && lineNumber <=8 ) {
					lineNumber++;
					if(lineNumber == 1) {
						name = scan.nextLine();
					}
					if(lineNumber == 2) {
						group = scan.nextLine();
					}
					if(lineNumber == 3) {
						code = scan.nextLine();
					}
					if(lineNumber == 4) {
						unit = scan.nextLine();
					}
					if(lineNumber == 5) {
						quantity = scan.nextLine();
					}
					if(lineNumber == 6) {
						sellPrice = scan.nextLine();
					}
					if(lineNumber == 7) {
						buyPrice = scan.nextLine();
					}
					if(lineNumber == 8) {
						imagePath = scan.nextLine();
					}					
				}
				scan.close();
				newWare();
				
			}
		}catch(IOException e) {}
		
	}
	
	public void newWare() {
		
		Ware ware = new Ware(name, code);
		ware.setGroup(group);
		ware.setUnit(unit);
		ware.setQuantity(Double.parseDouble(quantity));
		ware.setBuyPrice(Double.parseDouble(buyPrice));
		ware.setSellPrice(Double.parseDouble(sellPrice));
		ware.setWorth(Double.parseDouble(sellPrice) * Double.parseDouble(quantity));
		ware.setImagePath(imagePath);
		wareList.addWare(ware);
		
	}
	
	public WareList getWareList() {
		return wareList;
	}
	
}
