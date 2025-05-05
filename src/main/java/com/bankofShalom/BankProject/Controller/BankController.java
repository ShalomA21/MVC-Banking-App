package com.bankofShalom.BankProject.Controller;

import com.bankofShalom.BankProject.DAO.CustomerDAOImpl;
import com.bankofShalom.BankProject.DTO.Customer;
import com.bankofShalom.BankProject.Service.BankServiceImpl;
import com.bankofShalom.BankProject.UI.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


    @Component
    public class BankController {
        private BankServiceImpl service;
        private View view;
        private CustomerDAOImpl dao;

        @Autowired
        public BankController(BankServiceImpl service, View view, CustomerDAOImpl dao) {
            this.service = service;
            this.view = view;
            this.dao = dao;
        }

        public void run() {
            view.WelcomeBanner();

            while (true) {
                int input = view.printMenuAndGetSelection();

                switch (input) {
                    case 1: //Login
                        displaybankDetails();
                        break;
                    case 2: // add user
                        addCustomer();
                        break;
                    case 3:// Transfer money
                        transfer();
                        break;
                    case 4: // Update user
                        updateCustomer();
                        break;
                    case 5: // Delete user
                       deleteCustomer();
                        break;
                    case 6: // Exit
                        view.DisplayExit();
                        System.exit(0);
                        break;
                    default:
                        view.DisplayError("Unknown Command");
                        break;
                }
            }
        }

        public void displaybankDetails() {
            int userId = view.getUserId();
            Customer customer = dao.Login(userId);
            if (customer != null) {
                view.displayBankDetails(customer);
            } else {
                view.DisplayError("User does not exist or login details may be incorrect " +
                        "pls try again");
            }
        }
        public void addCustomer() {
            Customer newCustomer = view.getnewCustomer();
            service.addCustomer(newCustomer);
            view.addUser();
        }
        public void transfer() {
            int userId = view.getUserId();
            Customer customer1 = dao.Login(userId);
            int userId2 = view.SelectAccount();
            Customer customer2 = dao.Login(userId2);
            if (customer1 != null && customer2 != null) {
                BigDecimal amount = view.readInput();
                customer1.setBalance(customer1.getBalance().subtract(amount));
                customer2.setBalance(customer2.getBalance().add(amount));
                service.updateBalance(customer1);
                service.updateBalance(customer2);
                view.ChangeMoney();
                view.transferMoney(customer1, amount);
            } else {
                view.DisplayError("User does not exist");
            }
        }
        public void updateCustomer(){
            int UpdateuserId = view.getUserId();

            Customer updateCustomer = service.Login(UpdateuserId);

            if (updateCustomer != null) {
                updateCustomer = view.updateCustomer(updateCustomer);
                service.updateCustomer(updateCustomer);
                view.ChangeCustomer();
            } else {
                view.DisplayError("Customer does not exist");
            }
        }
        public void deleteCustomer(){
            int DeleteuserId = view.getUserId();
            String password = view.getPassword();

            Customer deleteCustomer = service.Login(DeleteuserId);
            if (deleteCustomer != null && deleteCustomer.getPassword().equals(password)) {
                service.deleteCustomer(deleteCustomer.getCustomerID(), deleteCustomer.getPassword());
                view.deleteUser();
            } else {
                view.DisplayError("Customer does not exist");
            }
        }
    }
