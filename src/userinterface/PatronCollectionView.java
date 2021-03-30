package userinterface;

// system imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Vector;
import java.util.Enumeration;

// project imports
import impresario.IModel;

import model.Patron;
import model.PatronCollection;


//==============================================================================
public class PatronCollectionView extends View
{
    protected TableView<PatronTableModel> tableOfPatrons;
    protected Button cancelButton;
    protected Button submitButton;

    protected MessageView statusLog;


    //--------------------------------------------------------------------------
    public PatronCollectionView(IModel lib)
    {
        super(lib, "PatronCollectionView");

        // create a container for showing the contents
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        // create our GUI components, add them to this panel
        container.getChildren().add(createTitle());
        container.getChildren().add(createFormContent());

        // Error message area
        container.getChildren().add(createStatusLog("                                            "));

        getChildren().add(container);

        populateFields();
    }

    //--------------------------------------------------------------------------
    protected void populateFields()
    {
        getEntryTableModelValues();
    }

    //--------------------------------------------------------------------------
    protected void getEntryTableModelValues()
    {

        ObservableList<PatronTableModel> tableData = FXCollections.observableArrayList();
        try
        {
            PatronCollection patronCollection = (PatronCollection)myModel.getState("PatronList");

            Vector entryList = (Vector)patronCollection.getState("Patron");
            System.out.println(entryList.size());
            Enumeration entries = entryList.elements();

            while (entries.hasMoreElements() == true)
            {
                Patron nextPatron = (Patron)entries.nextElement();
                Vector<String> view = nextPatron.getEntryListView();

                // add this list entry to the list
                PatronTableModel nextTableRowData = new PatronTableModel(view);
                tableData.add(nextTableRowData);

            }

            tableOfPatrons.setItems(tableData);
        }
        catch (Exception e) {//SQLException e) {
            // Need to handle this exception
        }
    }

    // Create the title container
    //-------------------------------------------------------------
    private Node createTitle()
    {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);

        Text titleText = new Text(" Patron Collection View ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setWrappingWidth(300);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);
        container.getChildren().add(titleText);

        return container;
    }

    // Create the main form content
    //-------------------------------------------------------------
    private VBox createFormContent()
    {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text prompt = new Text("LIST OF PATRONS");
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        tableOfPatrons = new TableView<PatronTableModel>();
        tableOfPatrons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn patronIdColumn = new TableColumn("patronId") ;
        patronIdColumn.setMinWidth(25);
        patronIdColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("patronId"));

        TableColumn nameColumn = new TableColumn("name") ;
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("name"));

        TableColumn addressColumn = new TableColumn("address") ;
        addressColumn.setMinWidth(25);
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("address"));

        TableColumn cityColumn = new TableColumn("city") ;
        cityColumn.setMinWidth(100);
        cityColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("city"));

        TableColumn stateCodeColumn = new TableColumn("stateCode") ;
        stateCodeColumn.setMinWidth(100);
        stateCodeColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("stateCode"));

        TableColumn zipColumn = new TableColumn("zip") ;
        zipColumn.setMinWidth(25);
        zipColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("zip"));

        TableColumn emailColumn = new TableColumn("email") ;
        emailColumn.setMinWidth(25);
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("email"));

        TableColumn dateOfBirthColumn = new TableColumn("dateOfBirth") ;
        dateOfBirthColumn.setMinWidth(25);
        dateOfBirthColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("dateOfBirth"));

        TableColumn statusColumn = new TableColumn("status") ;
        statusColumn.setMinWidth(25);
        statusColumn.setCellValueFactory(
                new PropertyValueFactory<PatronTableModel, String>("status"));

        tableOfPatrons.getColumns().addAll(patronIdColumn, nameColumn, addressColumn, cityColumn, stateCodeColumn, zipColumn
                , emailColumn, dateOfBirthColumn, statusColumn);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(115, 150);
        scrollPane.setContent(tableOfPatrons);

        submitButton = new Button("Submit");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                clearErrorMessage();
                // do the inquiry
                processPatronSelected();

            }
        });

        cancelButton = new Button("Back");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                myModel.stateChangeRequest("patronSearch", null);
            }
        });

        HBox btnContainer = new HBox(100);
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.getChildren().add(submitButton);
        btnContainer.getChildren().add(cancelButton);

        vbox.getChildren().add(grid);
        vbox.getChildren().add(scrollPane);
        vbox.getChildren().add(btnContainer);

        return vbox;
    }



    //--------------------------------------------------------------------------
    public void updateState(String key, Object value)
    {
    }

    //--------------------------------------------------------------------------
    protected void processPatronSelected()
    {
        PatronTableModel selectedItem = tableOfPatrons.getSelectionModel().getSelectedItem();

        if(selectedItem != null)
        {
            String selectedItemBookId = selectedItem.getPatronId();

            myModel.stateChangeRequest("PatronSelected", selectedItem.getPatronId());
        }
    }

    //--------------------------------------------------------------------------
    protected MessageView createStatusLog(String initialMessage)
    {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }


    /**
     * Display info message
     */
    //----------------------------------------------------------
    public void displayMessage(String message)
    {
        statusLog.displayMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage()
    {
        statusLog.clearErrorMessage();
    }
	/*
	//--------------------------------------------------------------------------
	public void mouseClicked(MouseEvent click)
	{
		if(click.getClickCount() >= 2)
		{
			processAccountSelected();
		}
	}
   */

}