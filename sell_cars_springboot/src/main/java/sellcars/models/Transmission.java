package sellcars.models;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return id == that.id
                && Objects.equals(transmissionType, that.transmissionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transmissionType);
    }
}
