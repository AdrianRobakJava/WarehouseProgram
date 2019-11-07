package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MagazynPrimaryWindow {

	@FXML
	private Button addWareButton;
	@FXML
	private Button saveWareButton;
	@FXML
	private Button loadWareButton;
	@FXML
	private Button removeWareButton;
	@FXML
	private Button editWareButton;
	@FXML
	private TextField nameWareField;
	@FXML
	private TextField codeWareField;
	@FXML
	private TextField groupWareField;
	@FXML
	private TextField unitWareField;
	@FXML
	private TextField quantityWareField;
	@FXML
	private TextField buyPriceField;
	@FXML
	private TextField sellPriceField;
	@FXML
	private Label searchWareLabel;
	@FXML
	private ImageView img;
	@FXML
	private TableView<Ware> wareListView = new TableView<>();
	@FXML
	private TableColumn<Ware, ImageView> photoWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, String> nameWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, String> codeWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, String> groupWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, Double> quantityWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, String> unitWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, Double> buyPriceWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, Double> sellPriceWareTC = new TableColumn<>();
	@FXML
	private TableColumn<Ware, Double> worthWareTC = new TableColumn<>();
	private ObservableList<Ware> wareListViewData = FXCollections.observableArrayList();

	private Ware ware;
	private WareList wareList;
	private int firstStart = 0;

	public MagazynPrimaryWindow() {
		
		wareList = new WareList();
	}
	//Inicjacja
	@FXML
	private void initialize() {
		
		initAddWareButton();
		initRemoveWareButton();
		initEditWareButton();
		initTableView();
		initListSelect();
		initSearchWareField();
		initSaveWareButton();
		initLoadWareButton();
		
	}

	private void initAddWareButton() {
		
		addWareButton.setOnAction((event) -> {
			try {
				showAddWareWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void initRemoveWareButton() {
		
		removeWareButton.setOnAction((event) -> {
			wareList.removeWare(ware.getCode());
			initTableView();
		});
	}

	private void initEditWareButton() {
		
		editWareButton.setOnAction((event) -> {
			try {
				if (ware != null) {
					showEditWareWindow(ware);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	
	private void initSearchWareField() {
		
			nameWareField.setOnMouseClicked((event)->{
				searchByName();
			});
			codeWareField.setOnMouseClicked((event)->{
				searchByCode();
			});
	}
	
	private void initSaveWareButton() {
		
		saveWareButton.setOnAction((event) -> {
			new SaveWare(wareList);
		});
	}
	
	private void initLoadWareButton() {
		
		loadWareButton.setOnAction((event) -> {
			new LoadWare();
			initTableView();
		});
	}
	
	public Stage showAddWareWindow() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addWareWindow.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Okno dodawania nowego towaru");
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		addWareWindow controller = loader.<addWareWindow>getController();
		controller.initData(wareList);
		stage.show();
		stage.setOnHiding((event) -> {
			initTableView();
		});
		return stage;
	}
	
	public Stage showEditWareWindow(Ware waree) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EditWareWindow.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Okno edycji towaru");
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		EditWareWindow controller = loader.<EditWareWindow>getController();
		controller.initData(waree);
		stage.show();
		stage.setOnHiding((event) -> {
			initTableView();
		});
		return stage;
	}
	//Inicjacja zawartości tabeli
	public void initTableView() {
		
		int counter = 0;
		initColumns();
		
		//Jednorazowe wypełnienie tabeli gotowymi wartościami
		if (counter == 0) {
			//addWare();
			counter++;
		}
		
		try {
			addToList();	
		} catch (UnsupportedOperationException e) {
			System.out.println("Błąd " + e);
		}
		
	}
	//Dodaje kilka gotowych elementów do tabeli
	public void addWare() {
		
		try {
			Ware ware1 = new Ware("Jabłka", "101");
			ware1.setGroup("Owoce");
			ware1.setQuantity(150.0);
			ware1.setUnit("kg");
			ware1.setBuyPrice(2.44);
			ware1.setSellPrice(3.59);
			ware1.setWorth(ware1.getSellPrice() * ware1.getQuantity());
			FileInputStream fis1 = new FileInputStream("c:/DoJava/jablka.jpg");
			Image image1 = new Image(fis1);
			ware1.setImage(image1);
			ImageView imageView1 = new ImageView(image1);
			imageView1.setFitHeight(50);
			imageView1.setFitWidth(50);
			ware1.setImageView(imageView1);
			wareList.addWare(ware1);
			
			Ware ware2 = new Ware("Gruszki", "102");
			ware2.setGroup("Owoce");
			ware2.setQuantity(256.0);
			ware2.setUnit("kg");
			ware2.setBuyPrice(2.99);
			ware2.setSellPrice(4.99);
			ware2.setWorth(ware2.getSellPrice() * ware2.getQuantity());
			FileInputStream fis2 = new FileInputStream("c:/DoJava/gruszki.jpg");
			Image image2 = new Image(fis2);
			ware2.setImage(image2);
			ImageView imageView2 = new ImageView(image2);
			imageView2.setFitHeight(50);
			imageView2.setFitWidth(50);
			ware2.setImageView(imageView2);
			wareList.addWare(ware2);
			
			Ware ware3 = new Ware("Banany", "103");
			ware3.setGroup("Owoce");
			ware3.setQuantity(13.5);
			ware3.setUnit("kg");
			ware3.setBuyPrice(1.99);
			ware3.setSellPrice(3.75);
			ware3.setWorth(ware3.getSellPrice() * ware3.getQuantity());
			FileInputStream fis3 = new FileInputStream("c:/DoJava/banany.jpg");
			Image image3 = new Image(fis3);
			ware3.setImage(image3);
			ImageView imageView3 = new ImageView(image3);
			imageView3.setFitHeight(50);
			imageView3.setFitWidth(50);
			ware3.setImageView(imageView3);
			wareList.addWare(ware3);
			
			Ware ware4 = new Ware("Awokado", "104");
			ware4.setGroup("Owoce");
			ware4.setQuantity(320.0);
			ware4.setUnit("szt");
			ware4.setBuyPrice(3.99);
			ware4.setSellPrice(5.95);
			ware4.setWorth(ware4.getSellPrice() * ware4.getQuantity());
			FileInputStream fis4 = new FileInputStream("c:/DoJava/awokado.jpg");
			Image image4 = new Image(fis4);
			ware4.setImage(image4);
			ImageView imageView4 = new ImageView(image4);
			imageView4.setFitHeight(50);
			imageView4.setFitWidth(50);
			ware4.setImageView(imageView4);
			wareList.addWare(ware4);
			
			Ware ware5 = new Ware("Ananasy", "105");
			ware5.setGroup("Owoce");
			ware5.setQuantity(55.0);
			ware5.setUnit("szt");
			ware5.setBuyPrice(4.49);
			ware5.setSellPrice(7.75);
			ware5.setWorth(ware5.getSellPrice() * ware5.getQuantity());
			FileInputStream fis5 = new FileInputStream("c:/DoJava/ananas.jpg");
			Image image5 = new Image(fis5);
			ware5.setImage(image5);
			ImageView imageView5 = new ImageView(image5);
			imageView5.setFitHeight(50);
			imageView5.setFitWidth(50);
			ware5.setImageView(imageView5);
			wareList.addWare(ware5);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		

	}

	public void addToList() {
		
		
		wareListView.setItems(wareListViewData);
		wareListView.getItems().clear();
		for (Ware w : wareList.getWareList().values()) {
			//Dodawanie zdjęć do listy
			if (firstStart == 0) {
				try {
					FileInputStream fis = new FileInputStream(w.getImagePath());
					Image image = new Image(fis);
					w.setImage(image);
					ImageView imageView = new ImageView(image);
					imageView.setFitHeight(50);
					imageView.setFitWidth(50);
					w.setImageView(imageView);	
				} catch (Exception e) {
					
				}
			}
			
			
			//Podwijnie dodaje elementy do listy
			//wareListView.getItems().add(w);
			wareListViewData.add(w);
		}
		//firstStart++;
	}
	//Inicjacja kolumn w tabeli
	public void initColumns() {

		photoWareTC.setCellValueFactory(new PropertyValueFactory<>("imageView"));
		nameWareTC.setCellValueFactory(new PropertyValueFactory<>("name"));
		codeWareTC.setCellValueFactory(new PropertyValueFactory<>("code"));
		groupWareTC.setCellValueFactory(new PropertyValueFactory<>("group"));
		quantityWareTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		unitWareTC.setCellValueFactory(new PropertyValueFactory<>("unit"));
		buyPriceWareTC.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
		sellPriceWareTC.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
		worthWareTC.setCellValueFactory(new PropertyValueFactory<>("worth"));
		nameWareTC.setStyle("-fx-alignment: CENTER;");
		codeWareTC.setStyle("-fx-alignment: CENTER;");
		groupWareTC.setStyle("-fx-alignment: CENTER;");
		quantityWareTC.setStyle("-fx-alignment: CENTER;");
		unitWareTC.setStyle("-fx-alignment: CENTER;");
		buyPriceWareTC.setStyle("-fx-alignment: CENTER;");
		sellPriceWareTC.setStyle("-fx-alignment: CENTER;");
		worthWareTC.setStyle("-fx-alignment: CENTER;");
	}
	//Pobiera aktualną wartośc zaznaczoną w tabeli
	public void initListSelect() {
		wareListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
			if(newValue != null) {
				ware = newValue;
			}
		});
	}
	//Uaktualnia zawartość tabeli podczas wyszukiwania po nazwie
	public void searchByName() {
		
		
		FilteredList<Ware> filteredData = new FilteredList<>(wareListViewData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
		nameWareField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Ware> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(wareListView.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        wareListView.setItems(sortedData);
	}
	//Uaktualnia zawartość tabeli podczas wyszukiwania po kodzie
	public void searchByCode() {	
		
		FilteredList<Ware> filteredData = new FilteredList<>(wareListViewData, p -> true);
		codeWareField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getCode()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;                    
                } 
                return false;
            });
        });
        SortedList<Ware> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(wareListView.comparatorProperty());
        wareListView.setItems(sortedData);
	}
	
	

}
