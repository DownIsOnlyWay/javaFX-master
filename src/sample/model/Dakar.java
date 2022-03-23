package sample.model;

public class Dakar {

    private int id;
    private String teamName;
    private String nameSurname;
    private String sponsor;
    private String racingCars;
    private int members;

    public Dakar(int id, String teamName, String nameSurname, String sponsor, String racingCars, int members) {
        this.id = id;
        this.teamName = teamName;
        this.nameSurname = nameSurname;
        this.sponsor = sponsor;
        this.racingCars = racingCars;
        this.members = members;
    }

    public Dakar(String teamName, String nameSurname, String sponsor, String racingCars, int members) {
        this.teamName = teamName;
        this.nameSurname = nameSurname;
        this.sponsor = sponsor;
        this.racingCars = racingCars;
        this.members = members;
    }

    public Dakar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(String racingCars) {
        this.racingCars = racingCars;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Dakar{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", nameSurname='" + nameSurname + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", racingCars='" + racingCars + '\'' +
                ", members=" + members +
                '}';
    }
}
