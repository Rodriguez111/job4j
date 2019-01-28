package generic;

import java.util.Iterator;

public abstract class AbstractStore<T extends Base> implements Store<T>, Iterable<T> {
    SimpleArray<T> baseArray;

    public AbstractStore(int size) {
        this.baseArray = new SimpleArray(size);
    }

    @Override
    public void add(T model) {
        baseArray.add(model);
    }

    @Override
    public  boolean replace(String id, T model) {
       return baseArray.set(Integer.parseInt(id), model);
    }

    @Override
    public boolean delete(String id) {
        return baseArray.remove(Integer.parseInt(id));
    }

    @Override
    public  T findById(String id) {
        return baseArray.get(Integer.parseInt(id));

    };

    @Override
    public Iterator<T> iterator() {
        return baseArray.iterator();
    }
}
