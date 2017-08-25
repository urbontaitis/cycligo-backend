package com.cycligo.backend.account;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Mindaugas Urbontaitis on 25/03/2017.
 * cycligo-rest-api
 */
//@Repository
public class JdbcAccountRepository implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public JdbcAccountRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void createAccount(Account user) throws UsernameAlreadyInUseException {
        try {
            jdbcTemplate.update(
                    "insert into account (first_name, last_name, username, password) values (?, ?, ?, ?)",
                    user.getFirstName(), user.getLastName(), user.getUsername(),
                    passwordEncoder.encode(user.getPassword()));
        } catch (DuplicateKeyException e) {
            throw new UsernameAlreadyInUseException(user.getUsername());
        }
    }

    @Override
    public Account findAccountByUsername(String username) {
        return jdbcTemplate.queryForObject("select username, first_name, last_name from account where username = ?",
                (rs, rowNum) -> new Account(rs.getString("username"), null, rs.getString("first_name"), rs
                        .getString("last_name")), username);
    }
}
