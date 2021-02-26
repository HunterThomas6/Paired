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
            System.out.println(bk2.toString());

            Patron pt = new Patron("1");
            System.out.println(pt.toString());

            Properties w = new Properties();
            w.setProperty("name", "Ron Weez");
            w.setProperty("address", "UR moms House");
            w.setProperty("city", "Hogwarts");
            w.setProperty("stateCode", "En");
            w.setProperty("zip", "14420");
            w.setProperty("email", "rononurmom@wizzyconnect.com");
            w.setProperty("dateOfBirth", "2/12/1999");
            w.setProperty("status", "Inactive");

            Patron pt2 = new Patron(w);
            pt2.update();
            System.out.println(pt2.toString());

            BookCollection bkc = new BookCollection();
            bkc.getAuthor("Jesus");

            System.out.println(bkc.toString());




        }
        catch (Exception ex)
        {
            System.out.println("Error in accessing database: " + ex.toString());
        }
    }
}
