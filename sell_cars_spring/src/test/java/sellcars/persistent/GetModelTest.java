//package sellcars.persistent;
//
//import org.junit.Test;
//import sellcars.models.CarBody;
//import sellcars.models.CarBrand;
//import sellcars.models.Engine;
//import sellcars.models.Transmission;
//
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class GetModelTest {
//
//    @Test
//    public void whenFindAllBodiesThenReturnAllBodiesList() {
//        ModelGetter<CarBody> modelGetter = new GetModel<>();
//        List<CarBody> listOfBodies = modelGetter.getAll("CarBody");
//        assertThat(listOfBodies.size(), greaterThan(0));
//    }
//
//    @Test
//    public void whenFindAllBrandsThenReturnAllBrandsList() {
//        ModelGetter<CarBrand> modelGetter = new GetModel<>();
//        List<CarBrand> listOfBodies = modelGetter.getAll("CarBrand");
//        assertThat(listOfBodies.size(), greaterThan(0));
//    }
//
//    @Test
//    public void whenFindAllEnginesThenReturnAllEnginesList() {
//        ModelGetter<Engine> modelGetter = new GetModel<>();
//        List<Engine> listOfBodies = modelGetter.getAll("Engine");
//        assertThat(listOfBodies.size(), greaterThan(0));
//    }
//
//    @Test
//    public void whenFindAllTransmissionsThenReturnAllTransmissionsList() {
//        ModelGetter<Transmission> modelGetter = new GetModel<>();
//        List<Transmission> listOfBodies = modelGetter.getAll("Transmission");
//        assertThat(listOfBodies.size(), greaterThan(0));
//    }
//
//    @Test
//    public void whenFindModelByNameThenGetThisModel() {
//        ModelGetter<Transmission> modelGetter = new GetModel<>();
//        Transmission transmission = modelGetter.getByName("Transmission", "transmission_type", "Dual-Clutch");
//        String transmissionType = transmission.getTransmissionType();
//        assertThat(transmissionType, is("Dual-Clutch"));
//    }
//
//}