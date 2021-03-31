package org.academiadecodigo.javabank.converters;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Converter {


    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerDTO getCustomerDTO(int id){

        Customer customer = customerService.get(id);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }
    public AccountDTO getAccountDTO(Account account){


        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountType(account.getAccountType());
        return accountDTO;
    }


    public List<CustomerDTO> getCustomerDTOList(){
        List<CustomerDTO> listDTO = new LinkedList<>();
        List<Customer> list = customerService.list();
        for (Customer customer : list) {
            listDTO.add(getCustomerDTO(customer.getId()));
        }
        return listDTO;
    }

    public List<AccountDTO> getAccountrDTOList(Integer id){
        List<AccountDTO> accountDTOList = new LinkedList<>();
        List<Account> list = customerService.get(id).getAccounts();
        for (Account account : list) {
            accountDTOList.add(getAccountDTO(account));
        }
        return accountDTOList;
    }
}
