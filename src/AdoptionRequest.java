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

    public static void main(String[] args) {

        Set<Dog> allDogs = loadDogData("./allDogs.txt");

        for(Dog d: allDogs ){
            if(d.getMicrochipNumber()==989343556) d.setDeSexed(true);
        }

        Dog dreamDog = getUserInput();

        System.out.println("Dogs with matching Breed");
        Set<Dog> matching = getMatchingDogsBreed(allDogs, dreamDog);

        for(Dog d:matching) System.out.println(d.getName()+" "+d.getMicrochipNumber()+" "+d.getAge()
              +d.isDeSexed()+" "+d.getSex());

        System.out.println("Dogs with matching breed within age range");
        matching = getMatchingDogsAge(matching,userMinAge,userMaxAge);
        for(Dog d:matching) System.out.println(d.getName()+" "+d.getMicrochipNumber()+" "+d.getAge()
                +d.isDeSexed()+" "+d.getSex());

        System.out.println("\nDogs with matching breed, within age range with matching sex:");
        matching = getMatchingDogsSex(matching,dreamDog);
        for(Dog d:matching) System.out.println(d.getName()+" ("+d.getMicrochipNumber()+") is "+d.getAge()+" years old. De-sexed: "+d.isDeSexed() +". Sex: "+d.getSex()+".");

        System.out.println("\nDogs that meet all criteria:");
        matching = getMatchingDogsDeSexed(matching,dreamDog);
        for(Dog d:matching) System.out.println(d.getName()+" ("+d.getMicrochipNumber()+") is "+d.getAge()+" years old. De-sexed: "+d.isDeSexed() +". Sex: "+d.getSex()+".");




    }
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
                userMaxAge = keyboard.nextInt();
                keyboard.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number greater than or equal to " + userMinAge + ": ");
                System.exit(0);
            }

        }
        System.out.println("Would you prefer the dog to be deSexed?: (yes/no) ");
        String deSexedStatus = keyboard.nextLine();
        boolean deSexed = deSexedStatus.equalsIgnoreCase("yes");

        return new Dog("",0,breed,sex,0,deSexed);
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

            boolean deSexed = dogInfo[3].equalsIgnoreCase("yes"); // boolean set to true if string equals yes. Else it's false
            int age = -1;   // create variable to store age. set to negative 1 as a flag to check if it is actually used
            try {
                age = Integer.parseInt(dogInfo[4]);   // Error handling
            } catch (NumberFormatException e) {
                System.out.println("Invalid number " + e);
                System.exit(0);
            }
            String breed = dogInfo[5].toLowerCase();   // convert String to lower case for ease of working with

            Dog d = new Dog(name, microchipNumber,breed,sex,age,deSexed);  // create new Dog object 'd' for each line in CSV file
            allDOgs.add(d);  // add dog objects to Hashset
        }
        return allDOgs;  // return the Hashset
    }

    public static Set<Dog> getMatchingDogsBreed(Set<Dog> allDogs,Dog preference){

        Set<Dog> matching = new HashSet<>();

        for(Dog dog: allDogs){
            if(dog.sameBreed(preference)) matching.add(dog);
        }return matching;
    }

    public static Set<Dog> getMatchingDogsAge(Set<Dog> allDogs, int min, int max){
        Set<Dog> matching = new HashSet<>();
        for(Dog dog: allDogs){
            if(dog.withinAgeRange(min,max)) matching.add(dog);
        }
        return matching;
    }

    public static Set<Dog> getMatchingDogsSex(Set<Dog> allDogs, Dog preference){
        Set<Dog> matching = new HashSet<>();
        for(Dog dog: allDogs){
            if(dog.sameSex(preference)) matching.add(dog);
        }
        return matching;
    }
    public static Set<Dog> getMatchingDogsDeSexed(Set<Dog> allDogs, Dog preference){
        Set<Dog> matching = new HashSet<>();
        for(Dog dog:allDogs){
            if(dog.sameDeSexed(preference)) matching.add(dog);
        }
        return matching;
    }



}
