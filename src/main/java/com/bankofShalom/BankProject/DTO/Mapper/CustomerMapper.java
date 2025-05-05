package com.bankofShalom.BankProject.DTO.Mapper;

import com.bankofShalom.BankProject.DTO.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int Index) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("firstname"));
        customer.setLastName(rs.getString("lastname"));
        customer.setUserName(rs.getString("username"));
        customer.setPassword(rs.getString("password"));
        customer.setBalance(rs.getBigDecimal("balance"));
        return customer;
    }
}
