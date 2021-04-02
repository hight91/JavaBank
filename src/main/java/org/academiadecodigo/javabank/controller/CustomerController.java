package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.converters.AccountDTO;
import org.academiadecodigo.javabank.converters.Converter;
import org.academiadecodigo.javabank.converters.CustomerDTO;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;
    private Converter converter;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Autowired
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {

        model.addAttribute("customers", converter.getCustomerDTOList());

        return "customer/list";
    }

    // Can serve URLs like http://www.someserver.org/someapp/hello?name=catarina
    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public String customerShow(Model model, @RequestParam("id") Integer id) {
        CustomerDTO customerDTO = converter.getCustomerDTO(id);

        model.addAttribute("customer", customerDTO);
        model.addAttribute("accounts", converter.getAccountrDTOList(id));
        model.addAttribute("balance", customerService.getBalance(id));
        return "customer/id";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editCustomer(Model model, @RequestParam("id") Integer id) {

        CustomerDTO customerDTO = converter.getCustomerDTO(id);
        model.addAttribute("customer", customerDTO);
        return "customer/edit";
    }
    @RequestMapping(method = RequestMethod.GET, path = "delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "customer/edit";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/submit", "/submit/{id}"})
    public String submitCustomer(@PathVariable(required = false) Integer id ,@Valid @ModelAttribute("customer") CustomerDTO customerDto, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            System.out.println("\n\n\nERROR\n\n\n");
            return "customer/edit";
        }
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




}
