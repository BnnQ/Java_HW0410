package me.bnnq;

import me.bnnq.models.*;
import me.bnnq.utils.ConsoleUtilities;
import me.bnnq.utils.ScannerUtilities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        task3();
    }

    public static void task1() throws InterruptedException
    {
        BoatStop boatStop = new BoatStop();

        // Simulate the arrival of passengers
        for (int i = 0; i < 10; i++)
        {
            Passenger passenger = new Passenger("Passenger " + (i + 1));
            boatStop.addPassenger(passenger);
        }

        // Simulate the arrival of boats
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            Thread.sleep(1000);
            Boat boat = new Boat("Boat " + (i + 1), random.nextInt(3) + 1);
            boatStop.arriveBoat(boat);
        }

        LocalTime averageWaitingTime = boatStop.getAverageWaitingTime();
        System.out.println("\nAverage waiting time: " + averageWaitingTime);

        int passengerCount = boatStop.getAllPassengerCount();
        System.out.println("Number of all passengers that have been at stop: " + passengerCount);

        System.out.println("The time interval at which there will always be no more than 3 people at stop: " + boatStop.calculateBoatArrivalInterval(LocalTime.of(0, 0, 1), 3));

        boatStop.dispose();
    }

    public static void task2()
    {
        LanguageDictionary dictionary = new LanguageDictionary("English", "Ukrainian");
        dictionary.inputWords();

        //menu: 1. Show word and translation 2. Add word and translation 3. Delete word and translation 4. Add word 5. Replace word 6. Delete word 7. Add translation 8. Replace translation 9. Delete translation 10. Show top 10 most popular words 11. Show top 10 mot unpopular words Any other number. Exit

        while (true)
        {
            ConsoleUtilities.clearConsole();

            System.out.println("1. Show word and translation");
            System.out.println("2. Add word and translation");
            System.out.println("3. Delete word and translation");
            System.out.println("4. Add word");
            System.out.println("5. Replace word");
            System.out.println("6. Delete word");
            System.out.println("7. Add translation");
            System.out.println("8. Replace translation");
            System.out.println("9. Delete translation");
            System.out.println("10. Show top 10 most popular words");
            System.out.println("11. Show top 10 most unpopular words");
            System.out.println("Any other number. Exit");
            String choice = ScannerUtilities.scanString("\nEnter your choice: ");
            ConsoleUtilities.clearConsole();

            switch (choice)
            {
                case "1":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");
                    String translation = dictionary.getTranslationOrNull(word);

                    if (translation == null)
                    {
                        System.err.println("Word not found!");
                        break;
                    }

                    System.out.printf("%s - %s\n", word, translation);
                    ConsoleUtilities.pause();
                    break;
                }
                case "2":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");
                    String translation = ScannerUtilities.scanString("Enter translation: ");

                    dictionary.addWord(new Word(word, translation));
                    break;
                }
                case "3":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");

                    dictionary.removeWord(word);
                    break;
                }
                case "4":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");

                    dictionary.addWord(new Word(word, null));
                    break;
                }
                case "5":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");
                    String newWord = ScannerUtilities.scanString("Enter new word: ");

                    if (!dictionary.tryReplaceWord(word, newWord))
                    {
                        System.err.println("Word not found!");
                        ConsoleUtilities.pause();
                    }

                    break;
                }
                case "6":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");

                    dictionary.removeWord(word);
                    break;
                }
                case "7":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");
                    String translation = ScannerUtilities.scanString("Enter translation: ");

                    dictionary.addTranslation(word, translation);
                    break;
                }
                case "8":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");
                    String translation = ScannerUtilities.scanString("Enter translation: ");

                    if (!dictionary.tryReplaceTranslation(word, translation))
                    {
                        System.err.println("Word not found!");
                        ConsoleUtilities.pause();
                    }

                    break;
                }
                case "9":
                {
                    String word = ScannerUtilities.scanString("Enter word: ");

                    dictionary.removeTranslation(word);
                    break;
                }
                case "10":
                {
                    Word[] top10 = dictionary.getTop10PopularWords();

                    for (int i = 0; i < top10.length; i++)
                    {
                        Word word = top10[i];
                        System.out.printf("%d. %s - %s\n", i + 1, word.getWord(), word.getTranslation() != null ? word.getTranslation() : "<not added>");
                    }

                    ConsoleUtilities.pause();
                    break;
                }
                case "11":
                {
                    Word[] top10 = dictionary.getTop10UnpopularWords();

                    for (int i = 0; i < top10.length; i++)
                    {
                        Word word = top10[i];
                        System.out.printf("%d. %s - %s\n", i + 1, word.getWord(), word.getTranslation() != null ? word.getTranslation() : "<not added>");
                    }

                    ConsoleUtilities.pause();
                    break;
                }
                default:
                {
                    return;
                }
            }
        }

    }

    public static void task3()
    {
        FinesInspectionDatabase database = new FinesInspectionDatabase();

        while (true)
        {
            ConsoleUtilities.clearConsole();

            System.out.println("1. Print full database");
            System.out.println("2. Print data by specific code");
            System.out.println("3. Print data by specific fine name");
            System.out.println("4. Print data by specific person city");
            System.out.println("5. Add new people");
            System.out.println("6. Add new fines to specific people");
            System.out.println("7. Deleting fine");
            System.out.println("8. Replacing information about person and fines");
            System.out.println("Any other number. Exit");
            String choice = ScannerUtilities.scanString("\nEnter your choice: ");
            ConsoleUtilities.clearConsole();

            switch (choice)
            {
                case "1":
                {
                    database.printFullDatabase();
                    ConsoleUtilities.pause();
                    break;
                }
                case "2":
                {
                    String code = ScannerUtilities.scanString("Enter code: ");

                    database.printDataBySpecificCode(code);
                    ConsoleUtilities.pause();
                    break;
                }
                case "3":
                {
                    String fineName = ScannerUtilities.scanString("Enter fine name: ");

                    database.printDataBySpecificFineName(fineName);
                    ConsoleUtilities.pause();
                    break;
                }
                case "4":
                {
                    String city = ScannerUtilities.scanString("Enter city: ");

                    database.printDataBySpecificPersonCity(city);
                    ConsoleUtilities.pause();
                    break;
                }
                case "5":
                {
                    String fullname = ScannerUtilities.scanString("Enter fullname: ");
                    String patronymic = ScannerUtilities.scanString("Enter patronymic: ");
                    String address = ScannerUtilities.scanString("Enter address: ");
                    String phoneNumber = ScannerUtilities.scanString("Enter phone number: ");
                    String passportNumber = ScannerUtilities.scanString("Enter passport number: ");

                    database.addPerson(new RegistryPerson(fullname, patronymic, address, phoneNumber, passportNumber, new ArrayList<>()));
                    break;
                }
                case "6":
                {
                    String passportNumber = ScannerUtilities.scanString("Enter passport number: ");
                    String code = ScannerUtilities.scanString("Enter code: ");
                    String reason = ScannerUtilities.scanString("Enter reason: ");
                    double amount = ScannerUtilities.scanDouble("Enter amount: ");

                    database.addNewFineToPerson(passportNumber, new Fine(code, reason, amount));
                    break;
                }
                case "7":
                {
                    String passportNumber = ScannerUtilities.scanString("Enter passport number: ");
                    String code = ScannerUtilities.scanString("Enter code: ");

                    database.deleteFine(passportNumber, code);
                    break;
                }
                case "8":
                {
                    String passportNumber = ScannerUtilities.scanString("Enter passport number: ");
                    String fullname = ScannerUtilities.scanString("Enter fullname: ");
                    String patronymic = ScannerUtilities.scanString("Enter patronymic: ");
                    String address = ScannerUtilities.scanString("Enter address: ");
                    String phoneNumber = ScannerUtilities.scanString("Enter phone number: ");

                    int numberOfFines = ScannerUtilities.scanInt("Enter number of fines: ");
                    List<Fine> fines = new ArrayList<>();

                    for (int i = 0; i < numberOfFines; i++)
                    {
                        String code = ScannerUtilities.scanString("Enter code: ");
                        String reason = ScannerUtilities.scanString("Enter reason: ");
                        double amount = ScannerUtilities.scanDouble("Enter amount: ");

                        fines.add(new Fine(code, reason, amount));
                    }

                    database.replacePersonInformation(passportNumber, fullname, patronymic, address, phoneNumber, fines);
                    break;
                }
                default:
                {
                    return;
                }
            }
        }

    }
}