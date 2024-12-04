package com.virginia.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.virginia.validation.ValidationGroups;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Marketing activity entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity implements Serializable {
    /**
     *Primary key, automatic growth, activity ID
     * Verify that the field is not empty when editing.
     */
    @NotNull(groups = {ValidationGroups.EditActivityGroup.class}, message = "Marketing activity ID is required!")
    private Integer id;

    /**
     *  Activity owner ID
     *  Verify that the field is not empty when editing.
     */
    @NotNull(groups = {ValidationGroups.EditActivityGroup.class}, message = "Owner ID is required!")
    private Integer ownerId;
/**
     *Activity name
     */
    @NotBlank(groups = {ValidationGroups.AddActivityGroup.class}, message = "Name is required!")
    @Size(groups = {ValidationGroups.AddActivityGroup.class, ValidationGroups.EditActivityGroup.class}, max = 128, message = "Name cannot exceed 128 characters!")
    private String name;

    /**
     *Activity start time
     */
    @NotNull(groups = {ValidationGroups.AddActivityGroup.class}, message = "Start time is required!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     *Activity end time
     */
    @NotNull(groups = {ValidationGroups.AddActivityGroup.class}, message = "End time is required!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     *Activity description
     */
    @NotBlank(groups = {ValidationGroups.AddActivityGroup.class}, message = "Description is required!")
    @Size(groups = {ValidationGroups.AddActivityGroup.class, ValidationGroups.EditActivityGroup.class}, max = 1024, message = "Description cannot exceed 1024 characters!")
    private String description;

    /**
     *Activity created time
     */
    private LocalDateTime createTime;

    /**
     *Activity creator
     */
    private Integer createBy;

    /**
     *Activity edited time
     */
    private LocalDateTime editTime;

    /**
     *Activity Editor
     */
    private Integer editBy;

    /**
     * region, 1 China, 2 Japan, 3 USA, 4 others
     */
    @NotNull(groups = {ValidationGroups.AddActivityGroup.class}, message = "Region is required!")
    private Integer region;

    /**
     *Whether the activity has been deleted, 0 not deleted, 1 deleted
     */
    private Integer isDeleted;

    /**
     *  Activity budget
     *  Verify the data type, which must be a positive number less than 2 decimal places.
     */
    @Digits(groups = {ValidationGroups.AddActivityGroup.class, ValidationGroups.EditActivityGroup.class}, integer = 15, fraction = 2, message = "Cost must be a positive number with at most two decimal places!")
    @DecimalMin(groups = {ValidationGroups.AddActivityGroup.class, ValidationGroups.EditActivityGroup.class}, value="0.01", message = "Cost must be a positive number with at most two decimal places!")
    @NotNull(groups = {ValidationGroups.AddActivityGroup.class}, message = "Cost is required!")
    private BigDecimal cost;

    /**
     * Currency unit of activity budget, including USD, RMB and JPY
     * Validate whether the value is USD, RMB or JPY.
     */
    @NotBlank(groups = {ValidationGroups.AddActivityGroup.class}, message = "CurrencyUnit is required!")
    @Pattern(groups = {ValidationGroups.AddActivityGroup.class, ValidationGroups.EditActivityGroup.class}, regexp = "^(USD|RMB|JPY)$", message = "Currency unit must be USD, RMB or JPY!")
    private String currencyUnit;

    /**
     *Non-database fields
     */
    // owner's login account
    private String ownerAct;
    // creator's login account
    private String createByAct;
    // editor's login account
    private String editByAct;

    private static final long serialVersionUID = 1L;
}