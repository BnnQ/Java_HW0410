package me.bnnq;

import me.bnnq.models.Fine;
import me.bnnq.models.RegistryPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinesInspectionDatabase
{
    private final List<RegistryPerson> registryPeople;

    public FinesInspectionDatabase()
    {
        this.registryPeople = new ArrayList<>();
    }

    //methods: 1. print full db 2. print data by specific code 3. print data by specific fine name 4. print data by specific person city 5. add new people 6. add new fines to specific people 7. deleting fine 8. replacing information about person and fines
    public void printFullDatabase()
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            System.out.println(registryPerson);
        }
    }

    public void printDataBySpecificCode(String code)
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            for (int i = 0; i < registryPerson.getFines().size(); i++)
            {
                if (registryPerson.getFines().get(i).code().equals(code))
                {
                    System.out.println(registryPerson);
                }
            }
        }
    }

    public void printDataBySpecificFineName(String fineName)
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            for (int i = 0; i < registryPerson.getFines().size(); i++)
            {
                if (registryPerson.getFines().get(i).reason().equals(fineName))
                {
                    System.out.println(registryPerson);
                }
            }
        }
    }

    public void printDataBySpecificPersonCity(String city)
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            if (registryPerson.getAddress().equals(city))
            {
                System.out.println(registryPerson);
            }
        }
    }

    public void addPerson(RegistryPerson registryPerson)
    {
        registryPeople.add(registryPerson);
    }

    public void addNewFineToPerson(String passportNumber, Fine fine)
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            if (registryPerson.getPassportNumber().equals(passportNumber))
            {
                registryPerson.getFines().add(fine);
            }
        }
    }

    public void deleteFine(String passportNumber, String code)
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            if (registryPerson.getPassportNumber().equals(passportNumber))
            {
                for (int i = 0; i < registryPerson.getFines().size(); i++)
                {
                    if (registryPerson.getFines().get(i).code().equals(code))
                    {
                        registryPerson.getFines().remove(i);
                    }
                }
            }
        }
    }

    public void replacePersonInformation(String passportNumber, String fullname, String patronymic, String address, String phoneNumber, List<Fine> fines)
    {
        for (RegistryPerson registryPerson : registryPeople)
        {
            if (registryPerson.getPassportNumber().equals(passportNumber))
            {
                registryPerson = new RegistryPerson(fullname, patronymic, address, phoneNumber, passportNumber, fines);
            }
        }
    }


}
