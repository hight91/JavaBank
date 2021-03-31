package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.converters.AccountDTO;
import org.academiadecodigo.javabank.converters.CustomerDTO;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        List<CustomerDTO> listDTO = new LinkedList<>();
        for (Customer customer : customerService.list()) {
            listDTO.add(getCustomers(customer));
        }
        model.addAttribute("customers", listDTO);
        return "customer/list";
    }

    // Can serve URLs like http://www.someserver.org/someapp/hello?name=catarina
    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public String customerShow(Model model, @RequestParam("id") Integer id) {
        Customer customer = customerService.get(id);
        CustomerDTO customerDTO = getCustomers(customer);
        List<AccountDTO> listDTO = new LinkedList<>();
        for (Account account : customer.getAccounts()) {
            listDTO.add(getAccounts(account));

        }
        model.addAttribute("customer", customerDTO);
        model.addAttribute("accounts", listDTO);
        return "customer/id";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editCustomer(Model model, @RequestParam("id") Integer id) {
        Customer customer = customerService.get(id);
        CustomerDTO customerDTO = getCustomers(customer);
        model.addAttribute("customer", customerDTO);
        return "customer/edit";
    }
    @RequestMapping(method = RequestMethod.GET, path = "delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "customer/edit";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/submit", "/submit/{id}"})
    public String submitCustomer(@PathVariable(required = false) Integer id ,@ModelAttribute CustomerDTO customerDto)
    {
        Customer user;

        if(id == null){
            user = new Customer();
        }
        else{
            user = customerService.get(id);
        }
        user.setFirstName(customerDto.getFirstName());
        user.setLastName(customerDto.getLastName());
        user.setEmail(customerDto.getEmail());
        user.setPhone(customerDto.getPhone());
        customerService.add(user);
        return "redirect:/";
    }


    private CustomerDTO getCustomers(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }
    private AccountDTO getAccounts(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountType(account.getAccountType());
        return accountDTO;
    }

}
