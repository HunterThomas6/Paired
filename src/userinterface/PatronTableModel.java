package userinterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;


public class PatronTableModel{
    private final SimpleStringProperty patronId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;
    private final SimpleStringProperty city;
    private final SimpleStringProperty stateCode;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty email;
    private final SimpleStringProperty dateOfBirth;
    private final SimpleStringProperty status;

    //----------------------------------------------------------------------------
    public PatronTableModel(Vector<String> bookData)
    {
        patronId =  new SimpleStringProperty(bookData.elementAt(0));
        name =  new SimpleStringProperty(bookData.elementAt(1));
        address =  new SimpleStringProperty(bookData.elementAt(2));
        city =  new SimpleStringProperty(bookData.elementAt(3));
        stateCode =  new SimpleStringProperty(bookData.elementAt(4));
        zip =  new SimpleStringProperty(bookData.elementAt(5));
        email =  new SimpleStringProperty(bookData.elementAt(6));
        dateOfBirth =  new SimpleStringProperty(bookData.elementAt(7));
        status =  new SimpleStringProperty(bookData.elementAt(8));
        System.out.print(patronId + " " + dateOfBirth + " " + email);
    }

    //----------------------------------------------------------------------------
    public String getPatronId() {return patronId.get(); }

    //----------------------------------------------------------------------------
    public void setPatronId(String number) { patronId.set(number); }

    //----------------------------------------------------------------------------
    public String getName() { return name.get(); }

    //----------------------------------------------------------------------------
    public void setName(String nam) { name.set(nam); }

    //----------------------------------------------------------------------------
    public String getAddress() {
        return address.get();
    }

    //----------------------------------------------------------------------------
    public void setAddress(String add) {
        address.set(add);
    }

    //----------------------------------------------------------------------------
    public String getCity() {
        return city.get();
    }

    //----------------------------------------------------------------------------
    public void setCity(String cit)
    {
        city.set(cit);
    }

    //----------------------------------------------------------------------------
    public String getStateCode()
    {
        return stateCode.get();
    }

    //----------------------------------------------------------------------------
    public void setStateCode(String cod)
    {
        stateCode.set(cod);
    }

    //----------------------------------------------------------------------------
    public String getZip()
    {
        return zip.get();
    }

    //----------------------------------------------------------------------------
    public void setZip(String zi)
    {
        zip.set(zi);
    }
    //----------------------------------------------------------------------------
    public String getEmail()
    {
        return email.get();
    }

    //----------------------------------------------------------------------------
    public void setEmail(String em)
    {
        email.set(em);
    }

    //----------------------------------------------------------------------------
    public String getDateOfBirth()
    {
        return dateOfBirth.get();
    }

    //----------------------------------------------------------------------------
    public void setDateOfBirth(String dob)
    {
        dateOfBirth.set(dob);
    }

    //----------------------------------------------------------------------------
    public String getStatus() {
        return status.get();
    }

    //----------------------------------------------------------------------------
    public void setStatus(String bStatus)
    {
        status.set(bStatus);
    }
}