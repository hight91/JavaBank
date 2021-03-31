package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    // Can serve URLs like http://www.someserver.org/someapp/hello?name=catarina
    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public String customerShow(Model model, @RequestParam("id") Integer id) {
        Customer user = customerService.get(id);
        model.addAttribute("customer", user);
        model.addAttribute("accounts", user.getAccounts());
        model.addAttribute("balance",customerService.getBalance(id));
        return "customer/id";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editCustomer(Model model, @RequestParam("id") Integer id) {
        Customer user = customerService.get(id);
        model.addAttribute("customer", user);
        return "customer/edit";
    }
    @RequestMapping(method = RequestMethod.GET, path = "delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addCustomer() {
        return "customer/addAccount";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/submit", "/submit/{id}"})
    public String submitCustomer(@PathVariable(required = false) Integer id ,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone) {
        Customer user;

        if(id == null){
            user = new Customer();
        }
        else{
            user = customerService.get(id);
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        customerService.add(user);
        return "redirect:/";
    }







}
