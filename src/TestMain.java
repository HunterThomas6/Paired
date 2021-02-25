import model.*;

import java.util.Properties;

public class TestMain{
    public static void main(String[] args)
    {
        try
        {
            //JDBCBroker connect = new JDBCBroker();
            Book bk = new Book("1");
            System.out.println(bk.toString());

            Properties p = new Properties();
            p.setProperty("bookTitle", "I Love Sandeep Mitra");
            p.setProperty("author", "Hunter The Man Thomas");
            p.setProperty("pubYear", "2021");
            p.setProperty("status", "Active");

            Book bk2 = new Book(p);

            bk2.update();






        }
        catch (Exception ex)
        {
            System.out.println("Error in accessing database: " + ex.toString());
        }
    }
}
