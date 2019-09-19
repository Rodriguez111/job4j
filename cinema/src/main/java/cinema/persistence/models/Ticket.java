package cinema.persistence.models;

public class Ticket {
    private int row;
    private int place;
    private String name;
    private String surname;
    private String phone;
    private float cost;
    private boolean canceled;


    public Ticket(int row, int place, String name, String surname, String phone, float cost) {
        this.row = row;
        this.place = place;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.cost = cost;
    }

    public Ticket(int row, int place, boolean canceled) {
        this.row = row;
        this.place = place;
        this.canceled = canceled;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public float getCost() {
        return cost;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
