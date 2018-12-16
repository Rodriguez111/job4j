package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @throws InsufficientFundsException
     * @throws AccountNotFoundException
     * @throws UserNotFoundException
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) throws InsufficientFundsException, AccountNotFoundException, UserNotFoundException {
        boolean moneyTransfered = true;

        Account sourceAccount = selectAccount(srcPassport, srcRequisite);
        Account destinationAccount =  selectAccount(destPassport, dstRequisite);

        if (sourceAccount.getValue() < amount) {
            throw new InsufficientFundsException();
        }

        sourceAccount.setValue(sourceAccount.getValue() - amount);
        destinationAccount.setValue(destinationAccount.getValue() + amount);

            return moneyTransfered;
    }

    private Account selectAccount(String passport, String requisite) throws AccountNotFoundException, UserNotFoundException {
        Account account = null;
        User user = selectUser(passport);
        List<Account> listOfUserAccounts = usersInfo.get(user);
           for (Account eachAccount : listOfUserAccounts) {
              if (eachAccount.getRequisites().equals(requisite)) {
                    account =  eachAccount;
              }
            }
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    private User selectUser(String passport) throws UserNotFoundException {
        User user = null;
        for (Map.Entry<User, List<Account>> eachEntry : usersInfo.entrySet()) {
            if (eachEntry.getKey().getPassport().equals(passport)) {
                user = eachEntry.getKey();
            }
        }
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }



}
