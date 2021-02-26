package model;

import exception.InvalidPrimaryKeyException;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class Patron extends EntityBase {

    private static final String myTableName = "Patron";
    protected Properties dependencies;
    private String updateStatusMessage = "Table was updated successfully";

    public Patron(String patronId) throws InvalidPrimaryKeyException {
        super(myTableName);
        this.setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (patronId = " + patronId + ")";
        Vector allDataFromDB = this.getSelectQueryResult(query);
        if (allDataFromDB != null) {
            int dataLen = allDataFromDB.size();
            if (dataLen != 1) {
                throw new InvalidPrimaryKeyException("Multiple books matching id : " + patronId + " found.");
            } else {
                Properties bookData = (Properties)allDataFromDB.elementAt(0);
                this.persistentState = new Properties();
                Enumeration bookKeys = bookData.propertyNames();

                while(bookKeys.hasMoreElements()) {
                    String nextKey = (String)bookKeys.nextElement();
                    String nextValue = bookData.getProperty(nextKey);
                    if (nextValue != null) {
                        this.persistentState.setProperty(nextKey, nextValue);
                    }
                }

            }
        } else {
            throw new InvalidPrimaryKeyException("No book matching id : " + patronId + " found.");
        }
    }

    public Patron(Properties props) {
        super(myTableName);
        this.setDependencies();
        this.persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();

        while(allKeys.hasMoreElements()) {
            String one = (String)allKeys.nextElement();
            String two = props.getProperty(one);
            if (two != null) {
                this.persistentState.setProperty(one, two);
            }
        }
    }

    private void setDependencies(){
        this.dependencies = new Properties();
        this.myRegistry.setDependencies(this.dependencies);
    }

    @Override
    public Object getState(String key) {
        return persistentState.getProperty(key);
    }

    @Override
    public void stateChangeRequest(String key, Object value) {

    }

    public String toString()
    {
        return "Patron: ID: " + getState("patronId") + " name: " + getState("name") + " email: " + getState("email");
    }



    //-----------------------------------------------------------------------------------
    public void update()
    {
        updateStateInDatabase();
    }

    //-----------------------------------------------------------------------------------
    private void updateStateInDatabase()
    {
        try
        {
            if (persistentState.getProperty("bookId") != null)
            {

                Properties whereClause = new Properties();
                whereClause.setProperty("patronId", persistentState.getProperty("patronId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Patron data for patron number : " + persistentState.getProperty("patronId") + " updated successfully in database!";
            }
            else
            {
                Integer patronId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("patronId", "" + patronId);
                updateStatusMessage = "Patron data for new Patron : " +  persistentState.getProperty("patronId")
                        + "installed successfully in database!";
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error: " + ex.toString());
            updateStatusMessage = "Error in installing Patron data in database!";
        }
        //DEBUG System.out.println("updateStateInDatabase " + updateStatusMessage);
    }

    @Override
    //------------------------------------------------------------------------------------
    protected void initializeSchema(String tableName) {

        if (mySchema == null)
        {
            mySchema = getSchemaInfo(tableName);
        }
    }

}

