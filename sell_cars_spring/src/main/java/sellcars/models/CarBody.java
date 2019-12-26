package sellcars.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_body")
public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "body_type", length = 60, nullable = false, unique = true)
    private String bodyType;

    public CarBody() {
    }

    public CarBody(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBody carBody = (CarBody) o;
        return id == carBody.id
                && Objects.equals(bodyType, carBody.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bodyType);
    }
}
