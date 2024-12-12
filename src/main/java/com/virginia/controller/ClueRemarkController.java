package com.virginia.controller;

import com.virginia.pojo.ClueRemark;
import com.virginia.query.GetClueRemarksQuery;
import com.virginia.result.R;
import com.virginia.service.ClueRemarkService;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clueRemark")
public class ClueRemarkController {
    @Resource
    private ClueRemarkService clueRemarkService;

    @PreAuthorize("hasAuthority('clueRemark:add')")
    @PostMapping("/")
    public R addClueRemark(@Validated(ValidationGroups.AddClueRemarkGroup.class) @RequestBody ClueRemark clueRemark) {
        try {
            int result = clueRemarkService.addClueRemark(clueRemark);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Add marketing clue remark failed!Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Add marketing clue remark failed!Please try again!");
        }
    }

    @PreAuthorize("hasAuthority('clueRemark:list')")
    @GetMapping("/list")
    public R getAllClueRemarks(@Validated GetClueRemarksQuery query) {
        return R.SUCCESS(clueRemarkService.getAllClueRemarks(query.getClueId(), query.getPage(), query.getPageSize(), query.getIsDeleted()));
    }

    @PreAuthorize("hasAuthority('clueRemark:edit')")
    @PutMapping("/")
    public R editClueRemark(@Validated(ValidationGroups.EditClueRemarkGroup.class) @RequestBody ClueRemark clueRemark) {
        try {
            int result = clueRemarkService.editClueRemark(clueRemark);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Edit marketing clue remark failed!Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Edit marketing clue remark failed!Please try again!");
        }
    }

    @PreAuthorize("hasAuthority('clueRemark:delete')")
    @DeleteMapping("/{id}")
    public R deleteClueRemarkById(@PathVariable Integer id) {
        try {
            int result = clueRemarkService.deleteClueRemarkById(id);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Delete marketing clue remark failed!Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Delete marketing clue remark failed!Please try again!");
        }
    }
}
