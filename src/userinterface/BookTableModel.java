package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;
public class BookTableModel {
    private final SimpleStringProperty bookId;
    private final SimpleStringProperty author;
    private final SimpleStringProperty pubYear;
    private final SimpleStringProperty title;
    private final SimpleStringProperty status;

    //----------------------------------------------------------------------------
    public BookTableModel(Vector<String> bookData)
    {
        bookId =  new SimpleStringProperty(bookData.elementAt(0));
        title =  new SimpleStringProperty(bookData.elementAt(1));
        pubYear =  new SimpleStringProperty(bookData.elementAt(2));
        author =  new SimpleStringProperty(bookData.elementAt(3));
        status =  new SimpleStringProperty(bookData.elementAt(4));
    }

    //----------------------------------------------------------------------------
    public String getBookId() {
        return bookId.get();
    }

    //----------------------------------------------------------------------------
    public void setBookId(String number) {
        bookId.set(number);
    }

    //----------------------------------------------------------------------------
    public String getAuthor() {
        return author.get();
    }

    //----------------------------------------------------------------------------
    public void setAuthor(String aut) { author.set(aut); }

    //----------------------------------------------------------------------------
    public String getPubYear() {
        return pubYear.get();
    }

    //----------------------------------------------------------------------------
    public void setPubYear(String year) {
        pubYear.set(year);
    }

    //----------------------------------------------------------------------------
    public String getTitle() {
        return title.get();
    }

    //----------------------------------------------------------------------------
    public void setTitle(String bTitle)
    {
        title.set(bTitle);
    }

    public String getStatus()
    {
        return status.get();
    }

    public void setStatus(String stat)
    {
        status.set(stat);
    }

}
