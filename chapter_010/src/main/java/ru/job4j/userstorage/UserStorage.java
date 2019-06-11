package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
       boolean result = false;
       if (!this.users.contains(user)) {
           this.users.add(user);
           result = true;
       }
       return result;
    }

    private boolean update(User user) {
        boolean result;
        delete(user);
        result = add(user);
        return result;
    }

    private boolean delete(User user) {
        boolean result = false;
        if (this.users.remove(user)) {
            result = true;
        }
        return result;
    }

    private Optional<User> getUser(int id) {
        Optional<User> user = Optional.empty();
       for (User eachUser : this.users) {
           if (id == eachUser.getId()) {
               user = Optional.of(eachUser);
               break;
           }
       }
       return user;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        Optional<User> fromUser = getUser(fromId);
        Optional<User> toUser = getUser(toId);
        if (fromUser.isEmpty() || toUser.isEmpty()) {
            result = false;
        } else if (fromUser.get().getAmount() < amount) {
            result = false;
        } else {
            User from = chargeMoney(fromUser.get(), amount);
            User to = addMoney(toUser.get(), amount);
            update(from);
            update(to);
        }
       return result;
    }

    private User addMoney(User user, int amount) {
       user.setAmount(user.getAmount() + amount);
       return user;
    }

    private User chargeMoney(User user, int amount) {
        user.setAmount(user.getAmount() - amount);
        return user;
    }

}

