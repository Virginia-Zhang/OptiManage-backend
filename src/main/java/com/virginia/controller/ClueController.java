package com.virginia.controller;

import com.virginia.pojo.Clue;
import com.virginia.pojo.PageBean;
import com.virginia.query.BatchUpdateQuery;
import com.virginia.query.GetCluesQuery;
import com.virginia.result.R;
import com.virginia.service.ClueService;
import com.virginia.utils.UserUtils;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/clue")
public class ClueController {
    @Resource
    private ClueService clueService;

    /**
     * Get all undeleted/deleted clues with pagination and optional searching params
     * @param query GetCluesQuery object, including searching params(optional)
     * @return Clue list, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('clue:list')")
    @GetMapping("/list")
    public R getAllClues(@Validated GetCluesQuery query) {
        PageBean clues = clueService.getAllClues(query);
        return R.SUCCESS(clues);
    }

    @PreAuthorize("hasAuthority('clue:add')")
    @PostMapping("/")
    public R addClue(@Validated(ValidationGroups.AddClueGroup.class) @RequestBody Clue clue, BindingResult bindingResult) throws MethodArgumentNotValidException {
        // Perform non-empty verification on the ownerId field. When the logged-in user's roleList includes admin and the ownerId is empty, the verification fails and an error message is prompted.
        List<String> roleList = Objects.requireNonNull(UserUtils.getLoggedInUserInfo()).getRoleList();
        if (roleList.contains("admin") && clue.getOwnerId() == null) {
            bindingResult.addError(new FieldError("clue","ownerId", "Owner ID is required!"));
        }

        // If the above field fails in verification, MethodArgumentNotValidException will be thrown manually.
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        try {
            int result = clueService.addClue(clue);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Add clue failed!Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Add clue failed!Please try again!");
        }
    }

    @PreAuthorize("hasAuthority('clue:edit')")
    @PutMapping("/")
    public R editClue(@Validated(ValidationGroups.EditClueGroup.class) @RequestBody Clue clue) {
        try {
            int result = clueService.editClue(clue);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Edit clue failed!Please try again!");
        }catch (Exception e){
            e.printStackTrace();
            return R.FAIL("Edit clue failed!Please try again!");
        }
    }


    /**
     * Batch remove/restore clues by IDs
     * @param query BatchUpdateQuery object, including IDs and isDeletedValue
     * @return R.success or R.fail
     */
    @PreAuthorize("hasAuthority('clue:delete')")
    @PutMapping("/updateClues")
    public R updateCluesByIds(@Validated @RequestBody BatchUpdateQuery query) {
        try {
            int result = clueService.updateCluesByIds(query.getIds(), query.getIsDeletedValue());
            return result == query.getIds().size() ? R.SUCCESS() : R.FAIL("Delete/Restore clues failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Delete/Restore clues failed!Please try again!");
        }
    }

    /**
     * Get all clues without pagination and optional searching params
     * @param query GetCluesQuery object
     * @return List of clues, without pagination, encapsulated into R: data
     */
    @GetMapping("/all")
    public R getAllCluesWithoutPagination(GetCluesQuery query) {
        List<Clue> clues = clueService.getAllCluesWithoutPagination(query);
        return R.SUCCESS(clues);
    }
}
