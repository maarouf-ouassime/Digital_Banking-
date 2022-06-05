package org.sid.ebankingbackend.web;


import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.Customer;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.sid.ebankingbackend.repositories.CustomerRepository;
import org.sid.ebankingbackend.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test")
public class TestController {
    private BankAccountService bankAccountService;

    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

//    @GetMapping("/test/{id}")
//    public List<BankAccount> test1(@PathVariable(name = "id") Long customerId) {
//        System.out.println("Hey===> " + customerId);
//        return bankAccountService.findByCustomerId(customerId);
//    }

    @GetMapping("/search/{kw}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Customer> test1(@PathVariable(name = "kw") String keyword) {
        System.out.println("Hey===> " + keyword);
        List<Customer> customers=customerRepository.searchCustomer(keyword);

        return customers;
    }

    @GetMapping("/test2")
    public String test2() {
        return "hey22";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
