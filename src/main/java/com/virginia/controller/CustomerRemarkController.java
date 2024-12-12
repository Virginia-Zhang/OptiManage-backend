package com.virginia.controller;

import com.virginia.pojo.CustomerRemark;
import com.virginia.query.GetClueRemarksQuery;
import com.virginia.query.GetCustomerRemarksQuery;
import com.virginia.result.R;
import com.virginia.service.CustomerRemarkService;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customerRemark")
public class CustomerRemarkController {
   @Resource
   private CustomerRemarkService customerRemarkService;

   @PreAuthorize("hasAuthority('customerRemark:add')")
   @PostMapping("/")
   public R addCustomerRemark(@Validated(ValidationGroups.AddCustomerRemarkGroup.class) @RequestBody CustomerRemark customerRemark) {
       try {
           Integer result = customerRemarkService.addCustomerRemark(customerRemark);
           return result > 0 ? R.SUCCESS(result) : R.FAIL("Add customer remark failed! Please try again!");
       }catch (Exception e){
           e.printStackTrace();
           return R.FAIL("Add customer remark failed! Please try again!");
       }
   }

   @PreAuthorize("hasAuthority('customerRemark:list')")
    @GetMapping("/list")
    public R getAllCustomerRemarks(@Validated GetCustomerRemarksQuery query) {
        return R.SUCCESS(customerRemarkService.getAllCustomerRemarks(query.getCustomerId(), query.getPage(), query.getPageSize(), query.getIsDeleted()));
    }

    @PreAuthorize("hasAuthority('customerRemark:edit')")
    @PutMapping("/")
    public R editCustomerRemark(@Validated(ValidationGroups.EditCustomerRemarkGroup.class) @RequestBody CustomerRemark customerRemark) {
        try {
            Integer result = customerRemarkService.editCustomerRemark(customerRemark);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Edit customer remark failed! Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Edit customer remark failed! Please try again!");
       }
    }

    @PreAuthorize("hasAuthority('customerRemark:delete')")
    @DeleteMapping("/{id}")
    public R deleteCustomerRemarkById(@PathVariable Integer id) {
        try {
            Integer result = customerRemarkService.deleteCustomerRemarkById(id);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Delete customer remark failed! Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Delete customer remark failed! Please try again!");
        }
    }
}
