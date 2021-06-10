import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number;
        String type;
        String name;
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the name of the band");
        name = console.nextLine();
        System.out.println("Enter the type of the group"+"\n1. Singing" +"\n2. Dancing" +"\n3. Mixed");
        type = console.nextLine();
        System.out.println("Enter the number of people in the group");
        number = console.nextInt();
        MusicBand band= MusicBand.newBuilder().setName(name).setNumberOfPersons(number).setType(type).build();
        band.info();
    }
}

class MusicBand {
    private final int numberOfPersons;
    private final String typeOfBand;
    private final String nameOfBand;

    private MusicBand(int numberOfPersons, String typeOfBand, String nameOfBand) {
        this.numberOfPersons = numberOfPersons;
        this.typeOfBand = typeOfBand;
        this.nameOfBand = nameOfBand;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public String getTypeOfBand() {
        return typeOfBand;
    }

    public String getNameOfBand() {
        return nameOfBand;
    }

    public void info(){
        System.out.println("Type of group - "+this.typeOfBand+", Name of group - " + this.nameOfBand + ", Number of people in the group - " + this.numberOfPersons);
    }

    public static Builder newBuilder(){
        return new Builder();
    }


    public static class Builder implements BandBuilder {
        private int numberOfPersons;
        private String typeOfBand;
        private String nameOfBand;
        public Builder(){
        };

        @Override
        public MusicBand build() {
            return new MusicBand(numberOfPersons, typeOfBand, nameOfBand);
        }

        @Override
        public BandBuilder setType(String typeOfBand) {
            this.typeOfBand=typeOfBand;
            return this;
        }

        @Override
        public BandBuilder setName(String nameOfBand) {
            this.nameOfBand = nameOfBand;
            return this;
        }

        @Override
        public BandBuilder setNumberOfPersons(int numberOfPersons) {
            this.numberOfPersons = numberOfPersons;
            return this;
        }
    }

}

interface BandBuilder {
    MusicBand build();
    BandBuilder setType(String typeOfBand);
    BandBuilder setName(String nameOfBand);
    BandBuilder setNumberOfPersons(int numberOfPersons);
}
