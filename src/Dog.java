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
}


