package com.virginia.controller;

import com.virginia.pojo.Clue;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCluesQuery;
import com.virginia.result.R;
import com.virginia.service.ClueService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clue")
public class ClueController {
    @Resource
    private ClueService clueService;

    @GetMapping("/list")
    public R getAllClues(@Validated GetCluesQuery query) {
        PageBean clues = clueService.getAllClues(query);
        return R.SUCCESS(clues);
    }

//    public R addClue(Clue clue) {
//        return clueService.addClue(clue);
//    }
//
//    public R editClue(Clue clue) {
//        return clueService.editClue(clue);
//    }
//
//    public R updateCluesByIds(Integer[] ids, Integer isDeletedValue) {
//        return clueService.updateCluesByIds(ids, isDeletedValue);
//    }
}
