package ru.job4j.bank;

import java.util.*;

public class Bank {

  private Map<User, List<Account>> usersInfo = new HashMap<>();

    /**
     *
     * @param user - user to add to the map.
     */

    public void addUser(User user) {
        usersInfo.putIfAbsent(user, new ArrayList<>());
    }

    /**
     *
     * @param user - user we want to delete from the map.
     * @return - result of the operation (true or false).
     */
    public boolean deleteUser(User user) {
        boolean userDeleted = false;
        if (usersInfo.containsKey(user)) {
            usersInfo.remove(user);
            userDeleted = true;
        }
        return userDeleted;
    }

    /**
     *
     * @param passport - passport of the user.
     * @param account - account which we want to add to this user.
     * @return - result of the operation (true or false).
     */
    public boolean addAccountToUser(String passport, Account account) {
        boolean accountAdded = false;
        for (Map.Entry<User, List<Account>> eachEntry : usersInfo.entrySet()) {
            if (eachEntry.getKey().getPassport().equals(passport)) {
                if (!eachEntry.getValue().contains(account)) {
                    eachEntry.getValue().add(account);
                    accountAdded = true;
                }
            }
        }
        return accountAdded;
    }

    /**
     *
     * @param passport - passport of the user.
     * @param account - account which we want to delete from this user.
     * @return - result of the operation (true or false).
     */
    public boolean deleteAccountFromUser(String passport, Account account) {
        boolean accountDeleted = false;
        for (Map.Entry<User, List<Account>> eachEntry : usersInfo.entrySet()) {
            if (eachEntry.getKey().getPassport().equals(passport)) {
                if (eachEntry.getValue().contains(account)) {
                    eachEntry.getValue().remove(account);
                    accountDeleted = true;
                }
            }
        }
        return accountDeleted;
    }

    /**
     *
     * @param passport - passport of the user.
     * @return - list of accounts of this user.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> listOfAccounts = new ArrayList<>();
        for (Map.Entry<User, List<Account>> eachEntry : usersInfo.entrySet()) {
            if (eachEntry.getKey().getPassport().equals(passport)) {
                listOfAccounts = eachEntry.getValue();
            }
        }
    return listOfAccounts;
    }

    /**
     *
     * @param srcPassport  - passport of the user from whose account we want to withdraw funds.
     * @param srcRequisite - requisites of the account we want to withdraw funds.
     * @param destPassport - passport of the user to whose account we want to transfer funds.
     * @param dstRequisite - requisites of the account where we want to transfer funds.
     * @param amount - amount of money to transfer.
     * @return
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean moneyTransfered = true;

        Optional<Account> sourceAccount = Optional.ofNullable(selectAccount(srcPassport, srcRequisite));
        Optional<Account> destinationAccount = Optional.ofNullable(selectAccount(destPassport, dstRequisite));

        Account source;
        Account destination;
        if (sourceAccount.isPresent() && destinationAccount.isPresent()) {
            source = sourceAccount.get();
            destination = destinationAccount.get();
            moneyTransfered = source.transfer(amount, destination);
        } else {
            moneyTransfered = false;
        }

            return moneyTransfered;
    }

    /**
     *
     * @param passport - passport of user whose account we are looking for.
     * @param requisite - requisites of user whose account we are looking for.
     * @return - found account.
     */
    private Account selectAccount(String passport, String requisite) {
        Account account = null;

        User user = selectUser(passport);
        if (!user.equals(new User("User not found", ""))) {

            List<Account> listOfUserAccounts = usersInfo.get(user);
            for (Account eachAccount : listOfUserAccounts) {
                if (eachAccount.getRequisites().equals(requisite)) {
                    account = eachAccount;
                }
            }
        }
        return account;
    }

    /**
     *
     * @param passport  - passport of the user we are looking for.
     * @return - found user.
     */
    private User selectUser(String passport) {
        Optional<User> user = Optional.ofNullable(new User("User not found", ""));

        for (Map.Entry<User, List<Account>> eachEntry : usersInfo.entrySet()) {
            if (eachEntry.getKey().getPassport().equals(passport)) {
                user = Optional.of(eachEntry.getKey());
            }
        }
        return user.get();
    }



}
