package com.virginia.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Encapsulation of querying parameters
 * @author Virginia
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCluesQuery extends SelectAllQuery{
    // Clue id
    private Integer id;
    // Clue owners' ids
    private List<Integer> owners;
    // Activity ids which clue belongs to
    private List<Integer> activities;
    // Client's full name
    @Size(max = 64, message = "Full name cannot exceed 64 characters!")
    private String fullName;
    // Client's gender
    @Min(value = 1, message = "Gender must be 1 or 2!")
    @Max(value = 2, message = "Gender must be 1 or 2!")
    private Integer gender;
    // Whether the client needs loan to buy the product
    @Min(value = 0, message = "NeedLoan value must be 0 or 1!")
    @Max(value = 1, message = "NeedLoan value must be 0 or 1!")
    private Integer needLoan;
    // Whether the client wants to buy the product
    @Min(value = 0, message = "intentionState must be between 0 and 2!")
    @Max(value = 2, message = "intentionState must be between 0 and 2!")
    private Integer intentionState;
    // Products which client wants to buy
    private List<Integer> intentionProducts;
    // States of the clue
    private List<Integer> states;
    // Sources of the clue
    private List<Integer> sources;
    // Regions that the clues belong to
    // 1 China, 2 Japan, 3 USA, 4 Others
    private List<Integer> regions;
}
