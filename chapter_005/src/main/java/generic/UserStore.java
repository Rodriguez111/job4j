package generic;

import java.util.Iterator;

public class UserStore extends AbstractStore implements Iterable<User> {



    public UserStore(int size) {
        baseArray = new SimpleArray<User>(size);
    }


    @Override
    public void add(Base model) {
        User user = (User) model;
        baseArray.add(user) ;
    }

    @Override
    public boolean replace(String id, Base model) {
        User user = (User) model;
        return baseArray.set(Integer.parseInt(id), user);
    }

    @Override
    public Base findById(String id) {
        return (User)baseArray.get(Integer.parseInt(id));
    }

    @Override
    public Iterator<User> iterator() {
        return new Iterator<User>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < baseArray.getGlobalIndex();
            }

            @Override
            public User next() {
                return (User) baseArray.array[index++];
            }
        };
    }
}
