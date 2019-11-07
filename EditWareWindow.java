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

public class EditWareWindow {
	
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
	private Ware currentWare;
	private int addPhoto = 0;
	private String imagePath;
	
	public EditWareWindow() {
		
	}
	public void initData(Ware cW) {
		currentWare = cW;
		initEditWareWindowFields();
	}
	
	@FXML
	private void initialize() {
		initAddPhotoWareButton();
		initSaveWareButton();
		initCloseButton();
	}
	
	private void initEditWareWindowFields() {
		
		addPhoto = 0;
		try {
			photoWareImageView.setImage(currentWare.getImage());
			
		} catch (Exception e) {
			System.out.println("Foto błąd");
		}
		nameWareField.setText(currentWare.getName());
		codeWareField.setText(currentWare.getCode());
		groupWareField.setText(currentWare.getGroup());
		quantityWareField.setText(currentWare.getQuantity()+ "");
		unitWareField.setText(currentWare.getUnit());
		buyPriceField.setText(currentWare.getBuyPrice()+ "");
		sellPriceField.setText(currentWare.getSellPrice()+ "");
		imagePath = currentWare.getImagePath();
	}
	
	private void initAddPhotoWareButton() {
		addPhotoWareButton.setOnAction((event)->{
			try {
				File file = fileChooser.showOpenDialog(null);
				if (file != null) {
					
					imagePath = file.toString();
					FileInputStream fis = new FileInputStream(file);
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
				//Zmienne do obliczenia wartości worth.
				double temporary1 = 0.0;
				double temporary2 = 0.0;
				//Nie można edytować nazwy i kodu towaru
				//(nameWareField.getText(), codeWareField.getText());
				currentWare.setGroup(groupWareField.getText());
				currentWare.setUnit(unitWareField.getText());
				if (isDigit(quantityWareField.getText())) {
					currentWare.setQuantity(Double.parseDouble(quantityWareField.getText()));
					temporary1 = Double.parseDouble(quantityWareField.getText());
				}
				if (isDigit(buyPriceField.getText())) {
					currentWare.setBuyPrice(Double.parseDouble(buyPriceField.getText()));	
				}
				if (isDigit(sellPriceField.getText())) {
					currentWare.setSellPrice(Double.parseDouble(sellPriceField.getText()));
					temporary2 = Double.parseDouble(sellPriceField.getText());
				}
				if (addPhoto >= 1) {
					currentWare.setImagePath(imagePath);
					currentWare.setImage(image);
					photoWareImageView.setFitHeight(50);
					photoWareImageView.setFitWidth(50);
					currentWare.setImageView(photoWareImageView);
				}
				currentWare.setWorth(temporary1 * temporary2);
				infoLabel.setTextFill(Color.GREEN);
				infoLabel.setText("Gotowe");
				
				
			} else {
				infoLabel.setTextFill(Color.RED);
				infoLabel.setText("Nie można zmienić nazwy oraz kodu produktu!!!");
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
