package model;

import exception.InvalidPrimaryKeyException;

import java.util.Properties;
import java.util.Vector;

public class PatronCollection extends EntityBase{
    private static final String myTableName = "Patron";

    private Vector<Patron> patronList;
    // GUI Components

    public PatronCollection(){
        super(myTableName);
        patronList = new Vector<Patron>();
    }
    // constructor for this class
    //----------------------------------------------------------

    public void getZip(String stZip) {
        String query = "SELECT * FROM " + myTableName + " WHERE (zip LIKE '%" + stZip + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    public void getStatus(String stStatus) {
        String query = "SELECT * FROM " + myTableName + " WHERE (stutus LIKE '%" + stStatus + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    public void getName(String stName){
        String query = "SELECT * FROM " + myTableName + " WHERE (name LIKE '%" + stName + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    public void getStateCode(String stStateCode) {
        String query = "SELECT * FROM " + myTableName + " WHERE (stateCode LIKE '%" + stStateCode + "%')";
        try {
            queryer(query);
        } catch (Exception x) {
            System.out.println("Error: " + x);
        }
    }

    public void getDOBBefore(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth < '" + date + "')";
        try {
            queryer(query);
        }
        catch (Exception x){
            System.out.println("Error" + x);
        }
    }

    public void getDOBAfter(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth > '" + date + "')";
        try {
            queryer(query);
        }
        catch (Exception x){
            System.out.println("Error" + x);
        }
    }

    public void queryer(String d) throws InvalidPrimaryKeyException {
        Vector allDataRetrieved = getSelectQueryResult(d);

        if (allDataRetrieved != null)
        {
            patronList = new Vector<Patron>();

            for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
            {
                Properties nextPatronData = (Properties)allDataRetrieved.elementAt(cnt);

                Patron patron = new Patron(nextPatronData);

                if (patron != null)
                {
                    addPatron(patron);
                }
            }

        }
        else
        {
            throw new InvalidPrimaryKeyException("No books that match criteria");
        }
    }

    //----------------------------------------------------------------------------------
    private void addPatron(Patron a)
    {
        //accounts.add(a);
        int index = findIndexToAdd(a);
        patronList.insertElementAt(a,index); // To build up a collection sorted on some key
    }

    //----------------------------------------------------------------------------------
    private int findIndexToAdd(Patron a)
    {
        //users.add(u);
        int low=0;
        int high = patronList.size()-1;
        int middle;

        while (low <=high)
        {
            middle = (low+high)/2;

            Patron midSession = patronList.elementAt(middle);

            int result = Patron.compare(a,midSession);

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
        if (key.equals("Patron"))
            return patronList;
        else
        if (key.equals("PatronList"))
            return this;
        else
        return null;
    }

    //----------------------------------------------------------------
    public void stateChangeRequest(String key, Object value)
    {

        myRegistry.updateSubscribers(key, this);
    }

    //----------------------------------------------------------
    public Patron retrieve(String patronId)
    {
        Patron retValue = null;
        for (int cnt = 0; cnt < patronList.size(); cnt++)
        {
            Patron nextPatron = patronList.elementAt(cnt);
            String nextPaNum = (String)nextPatron.getState("patronId");
            if (nextPaNum.equals(patronId) == true)
            {
                retValue = nextPatron;
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

    public String toString(){
        String returnValue = "";
        for (int cnt = 0; cnt < patronList.size(); cnt++)
            returnValue += patronList.get(cnt).toString() + "\n";
        return returnValue;
    }
}

