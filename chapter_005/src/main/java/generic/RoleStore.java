package generic;

public class RoleStore extends AbstractStore {
    public RoleStore(int size) {
        baseArray = new SimpleArray<>(size);
    }

    @Override
    public void add(Base model) {
        Role role = (Role) model;
        baseArray.add(role) ;
    }


}
