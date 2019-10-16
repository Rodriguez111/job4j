package sellcars.models;

import javax.persistence.*;

@Entity
@Table(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "transmission_type", length = 60, nullable = false, unique = true)
    private String transmissionType;

    public Transmission() {
    }

    public Transmission(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
