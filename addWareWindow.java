package application;

import java.io.File;
import java.io.FileInputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class addWareWindow {
	
	@FXML
	private Button addPhotoWareButton;
	@FXML
	private Button saveWareButton;
	@FXML
	private Button closeButton;
	@FXML
	private TextField nameWareField;
	@FXML
	private TextField codeWareField;
	@FXML
	private TextField groupWareField;
	@FXML
	private TextField quantityWareField;
	@FXML
	private TextField unitWareField;
	@FXML
	private TextField buyPriceField;
	@FXML
	private TextField sellPriceField;
	@FXML
	private ImageView photoWareImageView;
	@FXML
	private Image image;
	@FXML
	private Label infoLabel;
	@FXML
	private FileChooser fileChooser = new FileChooser();
	private WareList wareList;
	private int addPhoto = 0;
	private String imagePath;

	
	public addWareWindow() {
		
	}
	public void initData(WareList wL) {
		wareList = wL;
	}
	
	@FXML
	private void initialize() {
		initAddPhotoWareButton();
		initSaveWareButton();
		initCloseButton();
	}
	
	private void initAddPhotoWareButton() {
		addPhotoWareButton.setOnAction((event)->{
			try {
				File file = fileChooser.showOpenDialog(null);
				if (file != null) {
					imagePath = file.toString();
					FileInputStream fis = new FileInputStream(imagePath);
					image = new Image(fis);
					photoWareImageView.setImage(image);
					addPhoto++;
				}
				
			} catch (Exception e) {
				System.out.println(e);
				
			}
			
		});
	}
	
	private void initSaveWareButton() {
		
		saveWareButton.setOnAction((event)->{
			if (allImput()) {
				double temporary1 = 0.0;
				double temporary2 = 0.0;
				Ware ware = new Ware(nameWareField.getText(), codeWareField.getText());
				ware.setGroup(groupWareField.getText());
				ware.setUnit(unitWareField.getText());
				if (isDigit(quantityWareField.getText())) {
					ware.setQuantity(Double.parseDouble(quantityWareField.getText()));
					temporary1 = Double.parseDouble(quantityWareField.getText());
				}
				if (isDigit(buyPriceField.getText())) {
					ware.setBuyPrice(Double.parseDouble(buyPriceField.getText()));	
				}
				if (isDigit(sellPriceField.getText())) {
					ware.setSellPrice(Double.parseDouble(sellPriceField.getText()));
					temporary2 = Double.parseDouble(sellPriceField.getText());
				}
				if (addPhoto >= 1) {
					ware.setImagePath(imagePath);
					ware.setImage(image);
					photoWareImageView.setFitHeight(50);
					photoWareImageView.setFitWidth(50);
					ware.setImageView(photoWareImageView);
				}
				ware.setWorth(temporary1 * temporary2);
				wareList.addWare(ware);
				infoLabel.setTextFill(Color.GREEN);
				infoLabel.setText("Gotowe");
				
				
			} else {
				infoLabel.setTextFill(Color.RED);
				infoLabel.setText("Nie podałeś nazwy lub kodu produktu!!!");
			}
			
		});
	}
	
	private void initCloseButton() {
		closeButton.setOnAction((event)->{
			Stage stage = (Stage) closeButton.getScene().getWindow();
			stage.close();
			
		});
	}
	
	private boolean allImput() {
		if(nameWareField.getText() != null && !nameWareField.getText().isEmpty()
				&& codeWareField.getText() != null && !codeWareField.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isDigit(String str) {
		try {
			@SuppressWarnings("unused")
			double temporary = Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

}
