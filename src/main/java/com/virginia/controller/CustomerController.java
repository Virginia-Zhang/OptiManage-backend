package com.virginia.controller;

import com.virginia.pojo.Customer;
import com.virginia.result.R;
import com.virginia.service.CustomerService;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
