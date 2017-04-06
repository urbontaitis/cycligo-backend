package com.cycligo.backend.account;

/**
 * Created by Mindaugas Urbontaitis on 25/03/2017.
 * cycligo-rest-api
 */
public interface AccountRepository {

    void createAccount(Account account) throws UsernameAlreadyInUseException;

    Account findAccountByUsername(String username);

}
