package ShoppingSystem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.control;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Login page");

        Label laUserName = new Label("Username: ");
        Label laID = new Label("ID: ");

        TextField tfName = new TextField();
        TextField tfID = new TextField();

        VBox User = new VBox();
        User.setSpacing(20);
        User.getChildren().addAll(laUserName, laID);
        User.setAlignment(Pos.CENTER);

        VBox ID = new VBox();
        ID.setSpacing(9);
        ID.getChildren().addAll(tfName, tfID);
        ID.setAlignment(Pos.CENTER);

        HBox box = new HBox();
        box.setSpacing(5);
        box.getChildren().addAll(User, ID);
        box.setAlignment(Pos.CENTER);

        Button btLogin = new Button("Login");                                   /////
        Button btCreateAccount = new Button("Create account");                  /////

        HBox btBox = new HBox(btCreateAccount, btLogin);
        btBox.setAlignment(Pos.CENTER);
        btBox.setSpacing(10);

        BorderPane panel = new BorderPane();
        panel.setCenter(box);
        panel.setBottom(btBox);
        panel.setPadding(new Insets(20, 20, 20, 20));

        Scene login = new Scene(panel, 380, 300);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Label laUserName2 = new Label("User Name: ");
        Label laPhone = new Label("Phone number: ");
        Label laEmail = new Label("Email address: ");
        Label laAge = new Label("Age: ");
        Label laAddress = new Label("Address: ");

        TextField tfUserNameIn = new TextField();
        TextField tfPhoneIn = new TextField("");
        TextField tfEmailIn = new TextField("");
        TextField tfAgeIn = new TextField("");
        TextField tfAddressIn = new TextField("");

        VBox laBuyer = new VBox();
        laBuyer.setSpacing(20);
        laBuyer.getChildren().addAll(laUserName2, laPhone, laEmail, laAge, laAddress);
        laBuyer.setAlignment(Pos.CENTER);

        VBox tfBuyer = new VBox();
        tfBuyer.setSpacing(12);
        tfBuyer.getChildren().addAll(tfUserNameIn, tfPhoneIn, tfEmailIn, tfAgeIn, tfAddressIn);
        tfBuyer.setAlignment(Pos.CENTER);

        HBox infoBuyer = new HBox();
        infoBuyer.setSpacing(5);
        infoBuyer.getChildren().addAll(laBuyer, tfBuyer);
        infoBuyer.setAlignment(Pos.CENTER);

        Button btFinishBuyer = new Button("Buyer");                                   /////
        Button btFinishSeller = new Button("Seller");

        HBox btBox3 = new HBox(btFinishBuyer, btFinishSeller);
        btBox3.setAlignment(Pos.CENTER);
        btBox3.setSpacing(30);

        BorderPane accountPanel = new BorderPane();
        accountPanel.setCenter(infoBuyer);
        accountPanel.setBottom(btBox3);
        accountPanel.setPadding(new Insets(20, 20, 20, 20));

        Scene AccountCreation = new Scene(accountPanel, 380, 300);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btCreateAccount.setOnAction(e -> {
            primaryStage.setScene(AccountCreation);
        });
        btLogin.setOnAction(e -> {
            try {
                User temp = ShoppingSystem.User.findUser(tfName.getText(), Integer.parseInt(tfID.getText()));
                if (temp instanceof Buyer) {
                    buyerStage(temp);
                    primaryStage.close();
                } else if (temp instanceof Seller) {
                    sellerStage(temp);
                    primaryStage.close();
                }
            }
            catch (NumberFormatException f){
                primaryStage.close();
                start(primaryStage);
                errorStage ("Login credentials is not correct");
            }
            catch (UserNotFoundException f){
                primaryStage.close();
                start(primaryStage);
                errorStage (f.getMessage());
            }
        });
        btFinishBuyer.setOnAction(e -> {
            try {
                if((tfUserNameIn.getText()).isEmpty()|| tfPhoneIn.getText().isEmpty()||tfEmailIn.getText().isEmpty() ||  tfAddressIn.getText().isEmpty()) throw new NullPointerException();
                Buyer temporary = new Buyer(tfUserNameIn.getText(), tfPhoneIn.getText(), tfEmailIn.getText(), Integer.parseInt(tfAgeIn.getText()), tfAddressIn.getText());

                primaryStage.close();
                start(primaryStage);
                IDSHowCase(temporary);
            }
            catch (NullPointerException f){
                errorStage("Not enough information");
            }
            catch(NumberFormatException f)
            {
                errorStage("Use numbers for age");
            }
            catch (IllegalAgeException f){
                errorStage(f.getMessage());
            }

        });
        btFinishSeller.setOnAction(e -> {
            try{
                if((tfUserNameIn.getText()).isEmpty()|| tfPhoneIn.getText().isEmpty()||tfEmailIn.getText().isEmpty() ||  tfAddressIn.getText().isEmpty()) throw new NullPointerException();
                Seller temporary = new Seller(tfUserNameIn.getText(), tfPhoneIn.getText(), tfEmailIn.getText(), Integer.parseInt(tfAgeIn.getText()), tfAddressIn.getText());

                primaryStage.close();
                start(primaryStage);
                IDSHowCase(temporary);
            }
            catch(NumberFormatException f)
            {
                errorStage("Use numbers for age");
            }
            catch (IllegalAgeException f){
                errorStage(f.getMessage());
            }
            catch (Exception f){
                errorStage("Not enough information");
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        primaryStage.setScene(login);
        primaryStage.show();
    }
    public void IDSHowCase(User temporary) {
        Label IDShowCase = new Label("your ID: "+Integer.toString(temporary.getID()));
        Button btFinish = new Button("ok");
        VBox IDbox = new VBox(IDShowCase,btFinish);
        IDbox.setAlignment(Pos.CENTER);
        IDbox.setSpacing(40);
        Scene IDScene = new Scene(IDbox,150,150);
        Stage ShowID =new Stage();
        ShowID.setScene(IDScene);
        ShowID.show();
        btFinish.setOnAction(f->{ShowID.close();});
    }

    public void buyerStage(User temp) {
        Stage buyerWindow = new Stage();
        AtomicInteger i = new AtomicInteger(0);
        Product[] items = Product.getItems();
        BorderPane BuyerPanel = new BorderPane();
        BuyerPanel.setPadding(new Insets(20,20,20,20));
        if(items.length == 0) {
            Label laNoItems = new Label("No Items are sold being sold");
            BuyerPanel.setCenter(laNoItems);
            BorderPane.setAlignment(laNoItems,Pos.CENTER);
        }
        else {
            BorderPane.setAlignment(showcaseItem(items, i.get()),Pos.CENTER);
            BuyerPanel.setCenter(showcaseItem(items, i.get()));

        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Label laItem = new Label("Enter number of item: ");
        TextField ifItemNUmber = new TextField();

        HBox ItemNo = new HBox(laItem,ifItemNUmber);
        ItemNo.setSpacing(10);
        ItemNo.setAlignment(Pos.CENTER);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button btNextItem = new Button("Next");
        Button btPreviousItem = new Button("Previous");
        Button btAddItem = new Button("Add to shopping cart");
        Button btLogOut = new Button("Log out");
        Button btViewCart = new Button("Show Cart");
        Button btSort = new Button("Sort");
        Button btMyOrders = new Button("My orders");
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        HBox btAddAndView = new HBox(btAddItem,btViewCart,btMyOrders);
        btAddAndView.setSpacing(10);
        btAddAndView.setAlignment(Pos.CENTER);

        HBox btLogAndSort = new HBox(btSort,btLogOut);
        btLogAndSort.setSpacing(10);
        btLogAndSort.setAlignment(Pos.CENTER);

        VBox boxes = new VBox(ItemNo,btAddAndView);
        boxes.setSpacing(10);

        BuyerPanel.setBottom(boxes);
        BuyerPanel.setLeft(btPreviousItem);
        BorderPane.setAlignment(btPreviousItem,Pos.CENTER);
        BuyerPanel.setRight(btNextItem);
        BorderPane.setAlignment(btNextItem,Pos.CENTER);
        BuyerPanel.setTop(btLogAndSort);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Scene BuyerFeatures = new Scene(BuyerPanel, 500,480);
        buyerWindow.setScene(BuyerFeatures);
        buyerWindow.show();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btNextItem.setOnAction(e->{
            try {
                BuyerPanel.setCenter(showcaseItem(items, i.incrementAndGet()));
            }
            catch (ArrayIndexOutOfBoundsException f){
                i.decrementAndGet();
                errorStage("No more items");
            }
        });
        btPreviousItem.setOnAction(e->{
            try {
                BuyerPanel.setCenter(showcaseItem(items, i.decrementAndGet()));
            }
            catch (ArrayIndexOutOfBoundsException f){
                i.incrementAndGet();
                errorStage("No more items");
            }
        });
        btAddItem.setOnAction(e->{
            try {
                ((Buyer)temp).addItem(items[i.get()],Integer.parseInt(ifItemNUmber.getText()));
            }
            catch (NumberFormatException f){
                errorStage("No items added\nUse numbers");
            }
            catch (IndexOutOfBoundsException f){
                errorStage("No items are available");
            }
            catch (StockException f){
                errorStage(f.getMessage());
            }

        });
        btLogOut.setOnAction(e->{
            buyerWindow.close();
            start(new Stage());
        });
        btViewCart.setOnAction(e->{
            buyerWindow.close();
            cartStage(temp);
        });
        btSort.setOnAction(e->{
            try {
                Product.sortItems();
                i.set(0);
                BuyerPanel.setCenter(showcaseItem(items, i.get()));
                BorderPane.setAlignment(showcaseItem(items, i.get()), Pos.CENTER);
            }
            catch(IndexOutOfBoundsException f){
                errorStage("No items to sort");
            }
        });
        btMyOrders.setOnAction(e->{
            buyerWindow.close();
            orderStage(temp);
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    void cartStage(User temp){
        Stage cartStage = new Stage();
        BorderPane cartPane = new BorderPane();
        cartPane.setPadding(new Insets(20,20,20,20));
        Product[] items = ((Buyer)temp).getShoppingCart().getItems();
        AtomicInteger i = new AtomicInteger(0);

        Button btNextItem = new Button("Next");
        Button btPreviousItem = new Button("Previous");
        Button LogOut = new Button("Log Out");
        Button btBack = new Button("Back");
        Button btRemoveItem = new Button("Remove Item");
        Button btConfirmOrder = new Button("Confirm Order");

        HBox upperBt = new HBox(btBack,LogOut);
        upperBt.setAlignment(Pos.CENTER);
        upperBt.setSpacing(20);
        VBox downBt = new VBox(btRemoveItem,btConfirmOrder);
        downBt.setAlignment(Pos.CENTER);
        downBt.setSpacing(10);

        if(items.length == 0) {
            Label laNoItems = new Label("No Items are in cart");
            cartPane.setCenter(laNoItems);
            BorderPane.setAlignment(laNoItems,Pos.CENTER);
        }
        else {
            cartPane.setCenter(showcaseItem(items, i.get()));
            //Label laOrderItem = new Label("Number of ordered items: "+ ((Buyer)temp).getShoppingCart().getNoOfItemsOfIndexi(i.get())+" items");
            //Label laTotalPrice = new Label("Total cost: "+ ((Buyer)temp).getShoppingCart().getTotalPrice()+" LE");
            downBt = new VBox(btRemoveItem,btConfirmOrder);
            downBt.setAlignment(Pos.CENTER);
            downBt.setSpacing(10);
        }

        cartPane.setBottom(downBt);
        BorderPane.setAlignment(downBt,Pos.CENTER);
        cartPane.setLeft(btPreviousItem);
        BorderPane.setAlignment(btPreviousItem,Pos.CENTER);
        cartPane.setRight(btNextItem);
        BorderPane.setAlignment(btNextItem,Pos.CENTER);
        cartPane.setTop(upperBt);
        BorderPane.setAlignment(upperBt,Pos.CENTER);

        Scene cartScene = new Scene(cartPane,500,480);
        cartStage.setScene(cartScene);
        cartStage.show();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        VBox finalDownBt = downBt;

        btNextItem.setOnAction(e->{
            try {
                // finalDownBt.getChildren().removeFirst();
                cartPane.setCenter(showcaseItem(items, i.incrementAndGet()));
                //cartPane.setBottom(numberoforder(temp, finalDownBt, i.get()));
            }
            catch(IndexOutOfBoundsException f){
                i.decrementAndGet();
                errorStage("No more items");
            }

        });
        btPreviousItem.setOnAction(e->{
            try {
                //finalDownBt.getChildren().removeFirst();
                cartPane.setCenter(showcaseItem(items, i.decrementAndGet()));
                //cartPane.setBottom(numberoforder(temp, finalDownBt, i.get()));
            }
            catch(IndexOutOfBoundsException f){
                i.incrementAndGet();
                errorStage("No more items");
            }
        });
        LogOut.setOnAction(e->{
            cartStage.close();
            start(new Stage());
        });
        btBack.setOnAction(e->{
            cartStage.close();
            buyerStage(temp);
        });
        btRemoveItem.setOnAction(e->{
            try {
                ((Buyer) temp).removeItem(items[i.get()]);
                cartStage.close();
                cartStage(temp);
            }
            catch (IndexOutOfBoundsException f){
                errorStage("No items to remove");
            }
        });
        btConfirmOrder.setOnAction(e->{
            try {
                if(items.length == 0) throw new NullPointerException();
                ((Buyer) temp).addOrder();
                cartStage.close();
                buyerStage(temp);
            }
            catch(NullPointerException f){
                errorStage("No items to confirm order");
            }
        });
    }
    void orderStage(User temp){
        Stage orderStage = new Stage();

        Button btBack =new Button("Back");
        Button btLogOut = new Button("Log Out");
        Button btNextItem = new Button("Next Item");
        Button btPrev = new Button("previous Item");

        AtomicInteger i = new AtomicInteger(0);

        BorderPane or = new BorderPane();

        or.setCenter(arf(temp,i.get()));
        HBox log =new HBox(btBack,btLogOut);
        log.setAlignment(Pos.CENTER);
        log.setSpacing(10);
        or.setTop(log);

        or.setLeft(btPrev);
        BorderPane.setAlignment(btPrev,Pos.CENTER);
        or.setRight(btNextItem);
        BorderPane.setAlignment(btNextItem,Pos.CENTER);
        Scene first = new Scene(or);

        orderStage.setScene(first);
        orderStage.show();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btBack.setOnAction(e->{
            orderStage.close();
            buyerStage(temp);
        });
        btLogOut.setOnAction(e->{
            orderStage.close();
            start(new Stage());
        });
        btNextItem.setOnAction(e->{
            or.setCenter(arf(temp,i.incrementAndGet()));
        });
        btPrev.setOnAction(e->{
            or.setCenter(arf(temp,i.decrementAndGet()));
        });

    }

    VBox arf(User temp, int i) {
        Orders[] ored = ((Buyer)temp).getOrders();
        if(ored.length ==0){
            VBox errorPane = new VBox(new Label("no orders"));
            errorPane.setPadding(new Insets(20));
            errorPane.setSpacing(20);
            errorPane.setAlignment(Pos.CENTER);
            return errorPane;
        }
        else {
            Product[] items = ored[i].getItems();
            int[] itemsno = ored[i].getNoOfItems();
            String la = "";
            for (int k = 0; k < items.length; k++) {
                if (items[i] instanceof Car) {
                    la = la + "\n" + ((Car) items[i]).getBrand();
                } else {
                    la = la + "\n" + ((Accessories) items[i]).getType();
                }
            }
            VBox trr = new VBox();
            return trr;
        }
    }
    VBox numberoforder (User temp,VBox downbt,int i){
        Label btngn= new  Label("Number of ordered items: "+ ((Buyer)temp).getShoppingCart().getNoOfItemsOfIndexi(i)+" items");
        downbt.getChildren().addAll(btngn,downbt);
        downbt.setAlignment(Pos.CENTER);
        return downbt;
    }
    HBox showcaseItem (Product[] items, int i){

        if(items[i] instanceof Accessories){

            //(double price, String condition, int stock, String origin, String type)
            Label laType = new Label("Accessorie type: ");
            Label laOrigin = new Label("Origin: ");
            Label laCondition = new Label("Condition: ");
            Label laStock = new Label("Stock: ");
            Label laPrice = new Label("Price: ");

            Label laTypeOfProduct = new Label(((Accessories) items[i]).getType());
            Label laOriginOfProduct = new Label(((Accessories) items[i]).getOrigin());
            Label laConditionOfProduct = new Label(items[i].getCondition());
            Label laStockOfProduct = new Label(Integer.toString( items[i].getStock()));
            Label laPriceOfProduct = new Label(Double.toString(items[i].getPrice()));

            VBox laWords = new VBox(laType,laOrigin,laCondition,laStock,laPrice);
            laWords.setSpacing(10);
            laWords.setAlignment(Pos.CENTER);
            VBox laData = new VBox(laTypeOfProduct,laOriginOfProduct,laConditionOfProduct,laStockOfProduct,laPriceOfProduct);
            laData.setSpacing(10);
            laData.setAlignment(Pos.CENTER);
            HBox allLabels = new HBox(laWords,laData);
            allLabels.setSpacing(15);
            allLabels.setPadding(new Insets(20,20,20,20));
            allLabels.setAlignment(Pos.CENTER);
            return allLabels;
        }                              ///Accessorie details pane
        else if (items[i] instanceof Car) {
            //(double price, String condition, int stock, *String brand, *String model, *int productionYear, *double horsePower, *String color)
            Label laBrand = new Label("Car Brand: ");
            Label laModel = new Label("Model: ");
            Label laProductionYear = new Label("Production year: ");
            Label laHorsePower = new Label("Horse Power: ");
            Label laColor = new Label("Colour: ");
            Label laCondition = new Label("Condition: ");
            Label laStock = new Label("Stock: ");
            Label laPrice = new Label("Price: ");

            Label laBrandOfProduct = new Label(((Car) items[i]).getBrand());
            Label laModelOfProduct = new Label(((Car) items[i]).getModel());
            Label laProductionYearOfProduct = new Label(Integer.toString(((Car) items[i]).getProductionYear()));
            Label laHorsePowerOfProduct = new Label(Double.toString(((Car) items[i]).getHorsePower()));
            Label laColorOfProduct = new Label(((Car) items[i]).getColor());
            Label laConditionOfProduct = new Label(items[i].getCondition());
            Label laStockOfProduct = new Label(Integer.toString( items[i].getStock()));
            Label laPriceOfProduct = new Label(Double.toString(items[i].getPrice()));

            VBox laWords = new VBox(laBrand,laModel,laProductionYear,laHorsePower,laColor,laCondition,laStock,laPrice);
            laWords.setSpacing(10);
            laWords.setAlignment(Pos.CENTER);
            VBox laData = new VBox(laBrandOfProduct,laModelOfProduct,laProductionYearOfProduct,laHorsePowerOfProduct,laColorOfProduct,laConditionOfProduct,laStockOfProduct,laPriceOfProduct);
            laData.setSpacing(10);
            laData.setAlignment(Pos.CENTER);
            HBox allLabels = new HBox(laWords,laData);
            allLabels.setSpacing(15);
            allLabels.setAlignment(Pos.CENTER);
            return allLabels;

        }                               ///car details pane

        return null;
    }

    public void errorStage (String message) {
        //OGStage.close();
        Stage errorStage = new Stage();
        errorStage.setTitle("Error");
        Text errorMessage = new Text(message);
        Button btOK = new Button("OK");
        VBox errorPane = new VBox(errorMessage,btOK);
        errorPane.setPadding(new Insets(20));
        errorPane.setSpacing(20);
        errorPane.setAlignment(Pos.CENTER);
        Scene error = new Scene(errorPane,250,250);
        errorStage.setScene(error);
        // start(OGStage);
        errorStage.show();
        btOK.setOnAction(f->{errorStage.close();});
    }

    public void sellerStage(User temp){
        Stage sellerStage = new Stage();
        AtomicInteger i = new AtomicInteger(0);
        BorderPane sellerPane = new BorderPane();
        sellerPane.setPadding(new Insets(20,20,20,20));
        Product[] items = ((Seller)temp).getProducts();

        Button btCarCreate = new Button("Sell Car");
        Button btAccessoriesCreate = new Button("Sell Accessorie");
        Button btNextItem = new Button("Next");
        Button btPreviousItem = new Button("Previous");
        Button LogOut = new Button("Log Out");
        Button btRemoveItem = new Button("Remove Item");

        if(items.length == 0) {
            Label laNoItems = new Label("No Items are sold by you");
            sellerPane.setCenter(laNoItems);
        }
        else {
            sellerPane.setCenter(showcaseItem(items, i.get()));
        }

        HBox btProductCreate = new HBox(btCarCreate,btAccessoriesCreate);
        btProductCreate.setSpacing(20);
        btProductCreate.setAlignment(Pos.CENTER);

        VBox btProductManage = new VBox(btRemoveItem,btProductCreate);
        btProductManage.setSpacing(10);
        btProductManage.setAlignment(Pos.CENTER);

        sellerPane.setBottom(btProductManage);
        BorderPane.setAlignment(btProductManage,Pos.CENTER);
        sellerPane.setLeft(btPreviousItem);
        BorderPane.setAlignment(btPreviousItem,Pos.CENTER);
        sellerPane.setRight(btNextItem);
        BorderPane.setAlignment(btNextItem,Pos.CENTER);
        sellerPane.setTop(LogOut);
        BorderPane.setAlignment(LogOut,Pos.CENTER);

        Scene sellerFirstScene = new Scene(sellerPane,500,480);
        sellerStage.setScene(sellerFirstScene);
        sellerStage.show();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btNextItem.setOnAction(e->{
            try {
                sellerPane.setCenter(showcaseItem(items, i.incrementAndGet()));
            }
            catch (ArrayIndexOutOfBoundsException f){
                i.decrementAndGet();
                errorStage("No more items");
            }
        });
        btPreviousItem.setOnAction(e->{
            try {
                sellerPane.setCenter(showcaseItem(items, i.decrementAndGet()));
            }
            catch (ArrayIndexOutOfBoundsException f){
                i.incrementAndGet();
                errorStage("No more items");
            }
        });
        LogOut.setOnAction(e->{
            sellerStage.close();
            start(new Stage());
        });
        btCarCreate.setOnAction(e->{
            sellerStage.close();
            carPane(temp);

        });
        btAccessoriesCreate.setOnAction(e->{
            sellerStage.close();
            AccessoryPane(temp);
        });
        btRemoveItem.setOnAction(e->{
            try {
                ((Seller) temp).removeItem(items[i.get()]);
                sellerStage.close();
                sellerStage(temp);
            }
            catch (IndexOutOfBoundsException f){
                errorStage("No item to remove");
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    void carPane (User temp) {
        BorderPane carPane = new BorderPane();
        Button btFinish = new Button("Finish");
        carPane.setPadding(new Insets(20,20,20,20));

        //(double price, String condition, int stock, String brand, String model, int productionYear, double horsePower, String color)
        Label laBrand = new Label("Car Brand: ");
        Label laModel = new Label("Model: ");
        Label laProductionYear = new Label("Production year: ");
        Label laHorsePower = new Label("Horse Power: ");
        Label laColor = new Label("Colour: ");
        Label laCondition = new Label("Condition: ");
        Label laStock = new Label("Stock: ");
        Label laPrice = new Label("Price: ");

        TextField tfBrand = new TextField();
        TextField tfModel = new TextField();
        TextField tfProductionYear = new TextField();
        TextField tfHorsePower = new TextField();
        TextField tfColor = new TextField();
        TextField tfCondition = new TextField();
        TextField tfStock = new TextField();
        TextField tfPrice = new TextField();

        VBox labelsBox = new VBox(laBrand,laModel,laProductionYear,laHorsePower,laColor,laCondition,laStock,laPrice);
        labelsBox.setSpacing(20);
        VBox tfBox = new VBox(tfBrand,tfModel,tfProductionYear,tfHorsePower,tfColor,tfCondition,tfStock,tfPrice);
        tfBox.setSpacing(10);
        HBox allBox = new HBox(labelsBox,tfBox);
        allBox.setAlignment(Pos.CENTER);

        carPane.setBottom(btFinish);
        BorderPane.setAlignment(btFinish,Pos.CENTER);
        carPane.setCenter(allBox);

        Stage sellerStage = new Stage();
        Scene sellerCreateCar = new Scene(carPane);
        sellerStage.setScene(sellerCreateCar);
        sellerStage.show();

        btFinish.setOnAction(e->{
            try {
                if(tfPrice.getText().isEmpty()|| tfCondition.getText().isEmpty()||  tfBrand.getText().isEmpty()|| tfModel.getText().isEmpty()|| tfColor.getText().isEmpty()||tfPrice.getText().isEmpty()||tfStock.getText().isEmpty()||tfProductionYear.getText().isEmpty()||tfHorsePower.getText().isEmpty())throw new NullPointerException();

                ((Seller) temp).addCar(Double.parseDouble(tfPrice.getText()), tfCondition.getText(), Integer.parseInt(tfStock.getText()), tfBrand.getText(), tfModel.getText(), Integer.parseInt(tfProductionYear.getText()), Double.parseDouble(tfHorsePower.getText()), tfColor.getText());
                sellerStage.close();
                sellerStage(temp);
            }
            catch (NullPointerException f) {
                errorStage("Not enough info");
            }
            catch (NumberFormatException f) {
                errorStage("Use numbers for price, stock, production year and horse power");
            }
            catch (StockException f){
                errorStage(f.getMessage());
            }
        });

    }
    void AccessoryPane (User temp){
        BorderPane carPane = new BorderPane();
        Button btFinish = new Button("Finish");
        carPane.setPadding(new Insets(20,20,20,20));

        //(double price, String condition, int stock, String origin, String type)
        Label laType = new Label("Accessorie type: ");
        Label laOrigin = new Label("Origin: ");
        Label laCondition = new Label("Condition: ");
        Label laStock = new Label("Stock: ");
        Label laPrice = new Label("Price: ");

        TextField tfType = new TextField();
        TextField tfOrigin = new TextField();
        TextField tfCondition = new TextField();
        TextField tfStock = new TextField();
        TextField tfPrice = new TextField();

        VBox labelsBox = new VBox(laType,laOrigin,laCondition,laStock,laPrice);
        labelsBox.setSpacing(20);
        VBox tfBox = new VBox(tfType, tfOrigin,tfCondition,tfStock,tfPrice);
        tfBox.setSpacing(10);
        HBox allBox = new HBox(labelsBox,tfBox);
        allBox.setAlignment(Pos.CENTER);

        carPane.setBottom(btFinish);
        BorderPane.setAlignment(btFinish,Pos.CENTER);
        carPane.setCenter(allBox);

        Stage sellerStage = new Stage();
        Scene sellerCreateCar = new Scene(carPane);
        sellerStage.setScene(sellerCreateCar);
        sellerStage.show();

        btFinish.setOnAction(e->{
            try {
                if(tfPrice.getText().isEmpty()||tfCondition.getText().isEmpty()||tfStock.getText().isEmpty()|| tfOrigin.getText().isEmpty()|| tfType.getText().isEmpty())throw  new NullPointerException();
                //(double price, String condition, int stock, String origin, String type)
                ((Seller) temp).addAccessories(Double.parseDouble(tfPrice.getText()), tfCondition.getText(), Integer.parseInt(tfStock.getText()), tfOrigin.getText(), tfType.getText());
                sellerStage.close();
                sellerStage(temp);
            }
            catch (NullPointerException f){
                errorStage("Not enough info");
            }
            catch (NumberFormatException f){
                errorStage("Use numbers for price and stock");
            }
            catch (StockException f){
                errorStage(f.getMessage());
            }
        });

    }
}