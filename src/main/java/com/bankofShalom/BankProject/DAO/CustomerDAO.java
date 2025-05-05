package com.bankofShalom.BankProject.DAO;

import com.bankofShalom.BankProject.DTO.Customer;

import java.math.BigDecimal;

public interface CustomerDAO {
    Customer Login(int id);
    Customer AddCustomer(Customer customer);
    void UpdateCustomer(Customer customer);
    void UpdateBalance(Customer customer);
    void DeleteCustomer(int id, String Password);
}
