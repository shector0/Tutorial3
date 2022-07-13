import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

public class AdoptionRequest {
    private static int userMinAge = -1;
    private static int userMaxAge = -1;

    public static Dog getUserInput(){

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your preferred breed");
        String breed = keyboard.nextLine().toLowerCase();

        System.out.println("Please enter your preferred sex");
        String sexFemaleMale = keyboard.nextLine();
        char sex;
        if(sexFemaleMale.equalsIgnoreCase("male")) sex = 'M';
        else sex = "F";

        System.out.println("What is the minimum age you'd like to adopt? ");
        while(userMinAge < 0){
            try{
                userMinAge = keyboard.nextInt();
                keyboard.nextLine();
            }catch(NumberFormatException e){
                System.out.println("Please enter a valid number greater than or equal to 0");

            }
        }

        System.out.println("What is the maximum age you'd like to adopt? ");
        while(userMaxAge < userMinAge){
            try{
                userMaxAge = keyboard.nextInt();
                keyboard.nextLine();
            }catch(NumberFormatException e){
                System.out.println("Please enter a valid number greater than or equal to "+ userMinAge);

            }



    }

        System.out.println("Would you prefer the dog to be desexed? (yes/no)");
        String deSexedStatus = keyboard.nextLine();
        boolean deSexed = deSexedStatus.equalsIgnoreCase("yes");

        return new Dog("",0,breed, sex,0,deSexed);

    public static Set<Dog> loadDogData(String filePath) {
        Set<Dog> allDogs = new HashSet<>();

        Path path = Path.of(filePath);
        List<String> fileContents = null;

        try {
            fileContents = Files.readAllLines(path);
        } catch (IOException io) {
            System.out.println("File could not be loaded" + io);
            System.exit(0);
        }

        for(int i=1; i<fileContents>.size();i++){
            String[] dogInfo = fileContents.get(i).split(",");
            String name = dogInfo[0];
            long microchipNumber = Long.parseLong(dogInfo[1]);
            char sex;
            if (dogInfo[2].equalsIgnoreCase("male")) sex = 'M';
            else sex = 'F';

            boolean deSexed = dogInfo[3].equalsIgnoreCase("yes");

            int age = -1;
            try {
                age = Integer.parseInt(dogInfo[4]);
            } catch (NumberFormatException e) {
                System.out.println("Age could not be loaded " + e);
                System.exit(0);
            }

            String breed = dogInfo[5].toLowerCase();

            Dog d = new Dog(name, microchipNumber, breed, sex, age, deSexed);
            allDogs.add(d);


        }
        return  allDogs;
    }
}
