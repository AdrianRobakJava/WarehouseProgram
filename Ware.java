package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ware {
	
	private String photo;
	private ImageView imageView;
	private String name;
	private String group;
	private String code;
	private double quantity;
	private String unit;
	private double buyPrice;
	private double sellPrice;
	private double worth;
	private Image image;
	private String imagePath;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getWorth() {
		return worth;
	}

	public void setWorth(double worth) {
		this.worth = worth;
	}
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Ware(String namee, String codee) {
		name = namee;
		code = codee;
	}
	public Ware(String namee, String codee, String photoo, String groupp, String unitt, double quantityy, double buyPricee, double sellPricee, double worthh) {
		name = namee;
		code = codee;
		photo = photoo;
		group = groupp;
		unit = unitt;
		quantity = quantityy;
		buyPrice = buyPricee;
		sellPrice = sellPricee;
		worth = worthh;
	}
	


}
