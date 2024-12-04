package com.virginia.controller;

import com.virginia.pojo.Tran;
import com.virginia.result.R;
import com.virginia.service.TranService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tran")
public class TranController {
    @Resource
    private TranService tranService;

    @PostMapping("/")
    public R addTran(@Validated @RequestBody Tran tran) {
        try {
            Integer result = tranService.addTran(tran);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Add tran failed! Please try again!");
        }catch(Exception e){
            e.printStackTrace();
            return R.FAIL("Add tran failed! Please try again!");
        }

    }
}
