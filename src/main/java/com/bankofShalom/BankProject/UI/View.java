package com.bankofShalom.BankProject.UI;

import com.bankofShalom.BankProject.DTO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class View {
    @Autowired
    UserIO IO;

    public void WelcomeBanner(){
        IO.print("Welcome to the Bank of Shalom Mobile banking app!");
    }
    public int printMenuAndGetSelection() {
        IO.print("Main Menu");
        IO.print("1. Login");
        IO.print("2. Add new User");
        IO.print("3. Transfer Money");
        IO.print("4. Update Details");
        IO.print("5. Delete User");
        IO.print("6. Exit");

        return IO.readInt("Please select from the above choices.", 1, 7);
    }

    public void ChangeMoney() {
        IO.print("Balance Updated Successfully");
    }

    public void transferMoney(Customer customer, BigDecimal amount) {
        IO.print("Transferring " + amount);
        IO.print("Your new balance is: " + customer.getBalance());
    }

    public int SelectAccount() {
        IO.print("");
        return IO.readInt("Select Account to transfer money to");
    }

    public BigDecimal readInput(){
        IO.print("");
        return IO.readDecimal("Enter the amount you want to transfer");
    }

    public void ChangeCustomer() {
        IO.print("Customer Updated Successfully");
    }
    public void deleteUser() {
        IO.print("User Deleted Successfully");
    }
    public void addUser() {
        IO.print("User created Successfully");
    }

  public int getUserId() {
        return IO.readInt("Enter your ID");
    }


    public void displayBankDetails(Customer customer){
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Details: ");
        sb.append("\n");
        sb.append("Customer ID: ");
        sb.append(customer.getCustomerID());
        sb.append("\n");
        sb.append("Customer Name: ");
        sb.append(customer.getFirstName());
        sb.append("\n");
        sb.append("Customer Last Name: ");
        sb.append(customer.getLastName());
        sb.append("\n");
        sb.append("Customer UserName: ");
        sb.append(customer.getUserName());
        sb.append("\n");
        sb.append("Customer Password: ");
        sb.append(customer.getPassword());
        sb.append("\n");
        sb.append("Customer Balance: ");
        sb.append(customer.getBalance());
        IO.print(sb.toString());
    }


    public void DisplayError(String error) {
        IO.print("ERROR: " + error);
    }
    public void DisplayExit() {
        IO.print("Goodbye!");
    }
    public Customer getnewCustomer(){
        IO.print("");
        IO.print("Enter new customer info");
        String firstname = IO.readString("firstname:");
        String lastname = IO.readString("lastname:");
        String username = IO.readString("Username:");
        String Password = IO.readString("password:");
        BigDecimal balance = IO.readDecimal("balance:");
        Customer customer = new Customer();
        customer.setFirstName(firstname);
        customer.setLastName(lastname);
        customer.setUserName(username);
        customer.setPassword(Password);
        customer.setBalance(balance);
        return customer;
    }

    public Customer updateCustomer(Customer updatecustomer){
        IO.print("Updating.....");
        String FirstName = IO.readString("Enter new first name (" + updatecustomer.getFirstName() + "):" );
        String LastName = IO.readString("Enter new last name (" + updatecustomer.getLastName() + "):" );
        String UserName = IO.readString("Enter new username (" + updatecustomer.getUserName() + "):" );
        String Password = IO.readString("Enter new password (" + updatecustomer.getPassword() + "):" );

        if(!FirstName.isBlank()){
            updatecustomer.setFirstName(FirstName);
        }
        if (!LastName.isBlank()){
            updatecustomer.setLastName(LastName);
        }
        if(!UserName.isBlank()){
            updatecustomer.setUserName(UserName);
        }
        if(!Password.isBlank()){
            updatecustomer.setPassword(Password);
        }
        return updatecustomer;
    }

    public String getPassword() {
        return IO.readString("Enter your password");
    }
}
