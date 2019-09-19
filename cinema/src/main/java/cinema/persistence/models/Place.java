package cinema.persistence.models;

public class Place {
    private int row;
    private int place;
    private boolean engaged;
    private float cost;


    public Place(int row, int place, boolean engaged, float cost) {
        this.row = row;
        this.place = place;
        this.engaged = engaged;
        this.cost = cost;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public boolean isEngaged() {
        return engaged;
    }

    public float getCost() {
        return cost;
    }
}
