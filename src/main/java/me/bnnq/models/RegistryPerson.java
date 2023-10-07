package me.bnnq.models;

import java.util.List;

public class RegistryPerson
{
    private final String fullname;
    private final String patronymic;
    private final String address;
    private final String phoneNumber;
    private final String passportNumber;
    private final List<Fine> fines;

    public RegistryPerson(String fullname, String patronymic, String address, String phoneNumber, String passportNumber, List<Fine> fines)
    {
        this.fullname = fullname;
        this.patronymic = patronymic;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
        this.fines = fines;
    }

    public String getFullname()
    {
        return fullname;
    }

    public String getPatronymic()
    {
        return patronymic;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getPassportNumber()
    {
        return passportNumber;
    }

    public List<Fine> getFines()
    {
        return fines;
    }

    @Override
    public String toString()
    {
        return "Fullname: " + fullname + "\n" +
                "Patronymic: " + patronymic + "\n" +
                "Address: " + address + "\n" +
                "Phone number: " + phoneNumber + "\n" +
                "Passport number: " + passportNumber + "\n" +
                "Fines: " + fines + "\n";
    }

}
