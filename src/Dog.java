public class Dog {

    private final String name;
    private final long microchipNumber;
    private final String breed;
    private final char sex;
    private boolean deSexed;
    private final int age;

    /**
     * Contstructor Class for Dog related fields
     * @param name The name of the dog
     * @param microchipNumber a 9 digit unique number
     * @param breed the dog breed
     * @param sex the dog's gender
     * @param deSexed desexed or not
     * @param age the dog age
     */
    public Dog(String name, long microchipNumber, String breed, char sex, boolean deSexed, int age) {
        this.name = name;
        this.microchipNumber = microchipNumber;
        this.breed = breed;
        this.sex = sex;
        this.deSexed = deSexed;
        this.age = age;
    }

    // *---- Setters and Getters ----*
    public void setDeSexed(boolean deSexed) {
        this.deSexed = deSexed;
    }

    public String getName() {
        return name;
    }

    public long getMicrochipNumber() {
        return microchipNumber;
    }

    public String getBreed() {
        return breed;
    }

    public char getSex() {
        return sex;
    }

    public boolean isDeSexed() {
        return deSexed;
    }

    public int getAge() {
        return age;
    }

    /**
     * Check if dog breed is the same as another dog
     * @param dog Dog object passed in to be compared
     * @return boolean True if breeds are the same
     */
    public boolean sameBreed(Dog dog) {
        return breed.equals(dog.getBreed());
    }

    /**
     * Method to check wheter a dog is the same gender
     * @param dog a dog object for comparison
     * @return true if the dogs are the same gender
     */
    public boolean sameSex(Dog dog){
        return sex==dog.getSex();
    }

    /**
     * A method for determining wheter the dog's age falls within the acceptable age range
     * @param min The minimum age of the dog
     * @param max the maximum age of the dog
     * @return true/false if the dog's age falls within the correct range
     */
    public boolean withinAgeRange(int min, int max){
        return this.age >= min && this.age <= max;
    }

    /**
     * A method for comparing the desexed status of two dog objects
     * @param dog A dog object for comparison
     * @return true/false if desexed status is the same
     */
    public boolean sameDesexed(Dog dog){
        return this.deSexed == (dog.isDeSexed());
    }




}



