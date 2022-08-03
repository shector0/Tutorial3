import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AdoptionRequest {
    private static int userMinAge = -1;
    private static int userMaxAge = -1;
    public static Dog getUserInput() {
        // preferred breed, sex, minimum age, maximum age, and de-sexed status, assigning values to appropriate variables.
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your preferred Dog breed: ");
        String breed = keyboard.nextLine().toLowerCase();
        System.out.println("Please enter your preferred sex (male/female): ");
        String sexMaleFemale = keyboard.nextLine();
        char sex;
        if (sexMaleFemale.equalsIgnoreCase("male")) sex = 'M';
        else sex = 'F';
        System.out.println("Please enter the minimum age you'd like to adopt: ");
        while (userMinAge == -1) {
            try {
                userMinAge = keyboard.nextInt();
                keyboard.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number: ");
                System.exit(0);
            }
        }
        System.out.println("Please enter the maximum age you'd like to adopt: ");
        while (userMaxAge < userMinAge) {
            try {
                userMinAge = keyboard.nextInt();
                keyboard.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number greater than or equal to " + userMinAge + ": ");
                System.exit(0);
            }

        }
        System.out.println("Would you prefer the dog to be desexed?: (yes/no) ");
        String desexedStatus = keyboard.nextLine();
        boolean desexed = desexedStatus.equalsIgnoreCase("yes");

        return new Dog("",0,breed,sex,0,desexed);
    }
    public static Set<Dog> loadDogData(String filePath) {

        Set<Dog> allDOgs = new HashSet<>();

        Path path = Path.of(filePath);
        List<String> fileContents = null;

        try {
            fileContents = Files.readAllLines(path);
        } catch (IOException io) {
            System.out.println("File could not be loaded" + io);
            System.exit(0);
        }

        for (int i = 1; i < fileContents.size(); i++) {

            String[] dogInfo = fileContents.get(i).split(","); //create String array to load read file contents
            String name = dogInfo[0];       // name field
            long microchipNumber = Long.parseLong(dogInfo[1]); // microchip number parsed as long
            char sex;  //  initiate variable of type char to store gender
            // for loop to store correct M/F based on string "male" or "female"
            if (dogInfo[2].equalsIgnoreCase("male")) {
                sex = 'M';
            } else sex = 'F';

            boolean desexed = dogInfo[3].equalsIgnoreCase("yes"); // boolean set to true if string equals yes. Else it's false
            int age = -1;   // create variable to store age. set to negative 1 as a flag to check if it is actually used
            try {
                age = Integer.parseInt(dogInfo[4]);   // Error handling
            } catch (NumberFormatException e) {
                System.out.println("Invalid number " + e);
                System.exit(0);
            }
            String breed = dogInfo[5].toLowerCase();   // convert String to lower case for ease of working with

            Dog d = new Dog(name, microchipNumber,breed,sex,age,desexed);  // create new Dog object 'd' for each line in CSV file
            allDOgs.add(d);  // add dog objects to Hashset
        }
        return allDOgs;  // return the Hashset
    }



}
