package model;

import exception.InvalidPrimaryKeyException;
    import model.EntityBase;
    import java.util.Properties;
    import java.sql.SQLException;
    import java.util.*;


    public class Book extends EntityBase {

        private static final String myTableName = "Book";
        protected Properties dependencies;
        private String updateStatusMessage = "";

        public Book(String bookId) throws InvalidPrimaryKeyException {
            super(myTableName);
            this.setDependencies();
            String query = "SELECT * FROM " + myTableName + " WHERE (bookId = " + bookId + ")";
            Vector allDataFromDB = this.getSelectQueryResult(query);
            if (allDataFromDB != null) {
                int dataLen = allDataFromDB.size();
                if (dataLen != 1) {
                    throw new InvalidPrimaryKeyException("Multiple books matching id : " + bookId + " found.");
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
                throw new InvalidPrimaryKeyException("No book matching id : " + bookId + " found.");
            }
        }

        public Book(Properties props) {
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

        public Book() {

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
            return "Book: ID: " + getState("bookId") + " Title: " + getState("bookTitle");
        }

        public static int compare(Book a, Book b) {
            String ba = (String)a.getState("bookTitle");
            String bb = (String)b.getState("bookTitle");
            return ba.compareTo(bb);
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
                    whereClause.setProperty("bookId", persistentState.getProperty("bookId"));
                    updatePersistentState(mySchema, persistentState, whereClause);
                    updateStatusMessage = "Book data for book number : " + persistentState.getProperty("bookId") + " updated successfully in database!";
                }
                else
                {
                    Integer bookId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                    persistentState.setProperty("bookId", "" + bookId);
                    updateStatusMessage = "Book data for new Book : " +  persistentState.getProperty("bookId")
                            + "installed successfully in database!";
                }
            }
            catch (SQLException ex)
            {
                System.out.println("Error: " + ex.toString());
                updateStatusMessage = "Error in installing Book data in database!";
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
