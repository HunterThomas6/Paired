package model;

import java.util.Properties;
import java.util.Vector;
import javafx.scene.Scene;

// project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;

import impresario.IView;

import userinterface.View;
import userinterface.ViewFactory;

public class BookCollection extends EntityBase {
    private static final String myTableName = "Book";

    private Vector<Book> books;
    // GUI Components

    public BookCollection(){
        super(myTableName);
        books = new Vector<Book>();
    }
    // constructor for this class
    //----------------------------------------------------------

    public void getAuthor(String stAuthor) {
        String query = "SELECT * FROM " + myTableName + " WHERE (author LIKE '%" + stAuthor + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    //----------------------------------------------------------
    public void getStatus(String stStatus) {
        String query = "SELECT * FROM " + myTableName + " WHERE (stutus LIKE '%" + stStatus + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    //----------------------------------------------------------
    public void getDateBefore(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (pubYear < '" + date + "')";
        try {
            queryer(query);
        }
        catch (Exception x){
            System.out.println("Error" + x);
        }
    }

    //----------------------------------------------------------
    public void getDateAfter(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (pubYear > '" + date + "')";
        try {
            queryer(query);
        }
        catch (Exception x){
            System.out.println("Error" + x);
        }
    }

    //----------------------------------------------------------
    public void getTitle(String stTitle){
        String query = "SELECT * FROM " + myTableName + " WHERE (bookTitle LIKE '%" + stTitle + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    //----------------------------------------------------------
    public void getPubYear(String stYear) {
        String query = "SELECT * FROM " + myTableName + " WHERE (pubYear LIKE '%" + stYear + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    //----------------------------------------------------------
    public void queryer(String d) throws InvalidPrimaryKeyException {
        Vector allDataRetrieved = getSelectQueryResult(d);

        if (allDataRetrieved != null)
        {
            books = new Vector<Book>();

            for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
            {
                Properties nextBookData = (Properties)allDataRetrieved.elementAt(cnt);

                Book book = new Book(nextBookData);

                if (book != null)
                {
                    addBook(book);
                }
            }

        }
        else
        {
            throw new InvalidPrimaryKeyException("No books that match criteria");
        }
    }

    //----------------------------------------------------------------------------------
    private void addBook(Book a)
    {
        //accounts.add(a);
        int index = findIndexToAdd(a);
        books.insertElementAt(a,index); // To build up a collection sorted on some key
    }

    //----------------------------------------------------------------------------------
    private int findIndexToAdd(Book a)
    {
        //users.add(u);
        int low=0;
        int high = books.size()-1;
        int middle;

        while (low <=high)
        {
            middle = (low+high)/2;

            Book midSession = books.elementAt(middle);

            int result = Book.compare(a,midSession);

            if (result ==0)
            {
                return middle;
            }
            else if (result<0)
            {
                high=middle-1;
            }
            else
            {
                low=middle+1;
            }


        }
        return low;
    }


    /**
     *
     */
    //----------------------------------------------------------
    public Object getState(String key)
    {
        if (key.equals("Books"))
            return books;
        else
        if (key.equals("BookList"))
            return this;
        return null;
    }

    //----------------------------------------------------------------
    public void stateChangeRequest(String key, Object value)
    {

        myRegistry.updateSubscribers(key, this);
    }

    //----------------------------------------------------------
    public Book retrieve(String bookId)
    {
        Book retValue = null;
        for (int cnt = 0; cnt < books.size(); cnt++)
        {
            Book nextBk = books.elementAt(cnt);
            String nextBkNum = (String)nextBk.getState("bookId");
            if (nextBkNum.equals(bookId  ) == true)
            {
                retValue = nextBk;
                return retValue; // we should say 'break;' here
            }
        }

        return retValue;
    }

    /** Called via the IView relationship */
    //----------------------------------------------------------
    public void updateState(String key, Object value)
    {
        stateChangeRequest(key, value);
    }


    //------------------------------------------------------
   /* protected void createAndShowView()
    {

        Scene localScene = myViews.get("AccountCollectionView");

        if (localScene == null)
        {
            // create our new view
            View newView = ViewFactory.createView("AccountCollectionView", this);
            localScene = new Scene(newView);
            myViews.put("AccountCollectionView", localScene);
        }
        // make the view visible by installing it into the frame
        swapToView(localScene);

    }


    */
    //-----------------------------------------------------------------------------------
    protected void initializeSchema(String tableName)
    {
        if (mySchema == null)
        {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public Vector getBooks(){
        return books;
    }

    public String toString(){
        String returnValue = "";
        for (int cnt = 0; cnt < books.size(); cnt++)
            returnValue += books.get(cnt).toString() + "\n";
        return returnValue;
    }
}
