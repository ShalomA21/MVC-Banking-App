package com.bankofShalom.BankProject.DAO;

import com.bankofShalom.BankProject.DTO.Customer;
import com.bankofShalom.BankProject.DTO.Mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDAOImpl implements CustomerDAO{

    @Autowired
    private final JdbcTemplate jdbc;

    @Autowired
    public CustomerDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Customer Login(int id) {
        try {
            final String LOGIN_DETAILS = "SELECT * FROM customers WHERE customer_id = ?";
            return jdbc.queryForObject(LOGIN_DETAILS, new CustomerMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Customer AddCustomer(Customer customer) {
        final String INSERT_CUSTOMER = "INSERT INTO customers (firstname, lastname, username, password, balance)" +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_CUSTOMER,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getPassword(),
                customer.getBalance());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        customer.setCustomerID(newId);
        return customer;
    }

    @Override
    public void UpdateCustomer(Customer customer) {
        final String UPDATE_CUSTOMER = "UPDATE customers SET firstname = ?, lastname = ?, username = ?, password = ?" +
                "WHERE customer_id = ?";
        jdbc.update(UPDATE_CUSTOMER,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUserName(),
                customer.getPassword(),
                customer.getCustomerID());
    }

    @Override
    public void UpdateBalance(Customer customer) {
        final String UPDATE_BALANCE = "UPDATE customers SET balance = ? WHERE customer_id = ?";
        jdbc.update(UPDATE_BALANCE,
                customer.getBalance(),
                customer.getCustomerID());
    }

    @Override
    public void DeleteCustomer(int id, String Password) {
        final String DELETE_CUSTOMER = "DELETE FROM customers WHERE customer_id = ? AND password = ?";
        jdbc.update(DELETE_CUSTOMER, id, Password);
    }
}