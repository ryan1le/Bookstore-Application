package co528_project;

public class OwnerCust {

    private String name;
    private String pass;
    private int points;
    
    public OwnerCust(String name, String pass, int points){
        this.points = points;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
