package com.virginia.controller;

import com.virginia.pojo.Customer;
import com.virginia.query.GetCustomersQuery;
import com.virginia.result.R;
import com.virginia.service.CustomerService;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    /**
     * Convert marketing leads/clues into customers
     * @param customer Customer information
     * @return The number of entries inserted into the database, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('customer:add')")
    @PostMapping("/")
    public R convertToCustomer(@Validated(ValidationGroups.AddCustomerGroup.class) @RequestBody Customer customer) {
        try {
            Integer result = customerService.convertToCustomer(customer);
            if (result > 0){
                return R.SUCCESS(result);
            }else {
                return R.FAIL("Convert customer failed!Please try again!");
            }
        }catch(Exception e){
            e.printStackTrace();
            return R.FAIL("Convert customer failed!Please try again!");
        }
    }

    /**
     * Get all undeleted/deleted customers with pagination and optional searching params
     * @param query GetCustomersQuery object, including searching params(optional)
     * @return Customer list, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('customer:list')")
    @GetMapping("/list")
    public R getAllCustomersByPage(@Validated GetCustomersQuery query) {
        return R.SUCCESS(customerService.getAllCustomersByPage(query));
    }

    /**
     * Get all undeleted customers without pagination and optional searching params
     * @param query GetCustomersQuery object, actually no params
     * @return Customer list without pagination, encapsulated into R: data
     */
    @GetMapping("/all")
    public R getAllCustomers(@Validated GetCustomersQuery query) {
        return R.SUCCESS(customerService.getAllCustomers(query));
    }
}
