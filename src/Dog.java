public class Dog {

    private final String name;
    private final long microchipNumber;
    private final String breed;
    private final char sex;
    private final int age;
    private boolean deSexed;

    public Dog(String name, long microchipNumber, String breed, char sex, int age, boolean deSexed) {
        this.name = name;
        this.microchipNumber = microchipNumber;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public boolean isDeSexed() {
        return deSexed;
    }

    public void setDeSexed(boolean deSexed) {
        this.deSexed = deSexed;
    }

    public boolean sameBreed(Dog dog){
        return breed.equals(dog.getBreed());
    }

    public boolean sameSex(Dog dog){
        return sex=dog.getSex();
    }

    public boolean withingAgeRange(int min, int max){
        return this.age>=min && this.age<=max;
    }

    public boolean sameDeSexed(Dog dog){
        return this.deSexed ==(dog.isDeSexed());
    }

}
