// specify the package

package userinterface;

// system imports
import java.text.NumberFormat;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

// project imports
import impresario.IModel;
import model.Book;
import model.Librarian;

import javax.swing.*;

public class BookView extends View
{

    // GUI components
    protected TextField newAuthor;
    protected TextField newTitle;
    protected TextField pubYear;

    protected String[] status = {"Active, Inactive"};

    protected Button subButton;
    protected Button doneButton;

    // For showing error message
    private MessageView statusLog;

    // constructor for this class -- takes a model object
    //----------------------------------------------------------

    public BookView( IModel Book)
    {
        super(Book, "BookView");

        // create a container for showing the contents
        VBox container = new VBox(10);

        container.setPadding(new Insets(15, 5, 5, 5));

        // create a Node (Text) for showing the title
        container.getChildren().add(createTitle());

        // create a Node (GridPane) for showing data entry fields
        container.getChildren().add(createFormContent());

        // Error message area
        container.getChildren().add(createStatusLog("               "));

        getChildren().add(container);

        populateFields();

        // STEP 0: Be sure you tell your model what keys you are interested in
        //myModel.subscribe("LoginError", this);
    }

    // Create the label (Text) for the title of the screen
    //-------------------------------------------------------------
    private Node createTitle()
    {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);

        Text titleText = new Text(" New Book ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setWrappingWidth(300);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);
        container.getChildren().add(titleText);

        return container;
    }

    // Create the main form contents
    //-------------------------------------------------------------
    private VBox createFormContent()
    {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text prompt = new Text("BOOK INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        Text accNumLabel = new Text(" Book Author : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        accNumLabel.setFont(myFont);
        accNumLabel.setWrappingWidth(150);
        accNumLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(accNumLabel, 0, 1);

        newAuthor = new TextField();
        newAuthor.setEditable(true);
        grid.add(newAuthor, 1, 1);

        Text acctTypeLabel = new Text(" Title : ");
        acctTypeLabel.setFont(myFont);
        acctTypeLabel.setWrappingWidth(150);
        acctTypeLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(acctTypeLabel, 0, 2);

        newTitle = new TextField();
        newTitle.setEditable(true);
        grid.add(newTitle, 1, 2);

        Text balLabel = new Text(" Publication Year : ");
        balLabel.setFont(myFont);
        balLabel.setWrappingWidth(150);
        balLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(balLabel, 0, 3);

        pubYear = new TextField();
        pubYear.setEditable(true);
        grid.add(pubYear, 1, 3);

        final ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(
                "Active",
                "Inactive"
        );

        comboBox.setValue("Active");
        grid.add(comboBox, 1, 4);


        HBox doneCont = new HBox(10);
        doneCont.setAlignment(Pos.CENTER);
        subButton = new Button("Submit");
        subButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        subButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                clearErrorMessage();
                myModel.stateChangeRequest("Cancelled", null);
            }
        });

        doneButton = new Button("Done");
        doneButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        doneButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                clearErrorMessage();
                myModel.stateChangeRequest("Cancelled", null);
            }
        });
        doneCont.getChildren().add(subButton);
        doneCont.getChildren().add(doneButton);

        vbox.getChildren().add(grid);
        vbox.getChildren().add(doneCont);

        return vbox;
    }


    // Create the status log field
    //-------------------------------------------------------------
    private MessageView createStatusLog(String initialMessage)
    {

        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    //-------------------------------------------------------------

    public void populateFields()
    {

    }

    // This method processes events generated from our GUI components.
    // Make the ActionListeners delegate to this method
    //-------------------------------------------------------------

    public void processAction(Event evt)
    {
        // DEBUG: System.out.println("TellerView.actionPerformed()");



    }



    //---------------------------------------------------------
    public void updateState(String key, Object value)
    {
        // STEP 6: Be sure to finish the end of the 'perturbation'
        // by indicating how the view state gets updated.


    }

    /**
     * Display error message
     */
    //----------------------------------------------------------

    public void displayErrorMessage(String message)
    {
        statusLog.displayErrorMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------

    public void clearErrorMessage()
    {
        statusLog.clearErrorMessage();
    }

}
