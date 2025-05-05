package com.bankofShalom.BankProject.Service;

import com.bankofShalom.BankProject.DAO.CustomerDAOImpl;
import com.bankofShalom.BankProject.DTO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankServiceImpl {
    private CustomerDAOImpl dao;

    @Autowired
    public BankServiceImpl (CustomerDAOImpl dao){
        this.dao = dao;
    }

    public Customer Login(int id){
        return dao.Login(id);
    }

    public Customer addCustomer(Customer customer){
        return dao.AddCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        dao.UpdateCustomer(customer);
    }

    public void updateBalance(Customer customer){
        dao.UpdateBalance(customer);
    }

    public void deleteCustomer(int userID, String Password){
        dao.DeleteCustomer(userID,Password);
    }

}
