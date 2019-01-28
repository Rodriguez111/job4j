package generic;

public class AbstractStore<T> implements Store {
    SimpleArray<? extends Base> baseArray;


    @Override
    public void add(Base model) {

    }

    @Override
    public boolean replace(String id, Base model) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return baseArray.remove(Integer.parseInt(id));
    }

    @Override
    public Base findById(String id) {
        return null;
    }
}
