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
 * Entity for t_clue table
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clue implements Serializable {
    /**
     *Primary key, automatic growth, lead ID
     */
    @NotNull(groups = {ValidationGroups.EditClueGroup.class}, message = "ID is required!")
    private Integer id;

    /**
     *ID of the clue owner
     */
    private Integer ownerId;

    /**
     *Activity ID
     */
    private Integer activityId;

    /**
     * Client's name
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 64, message = "Full name cannot exceed 64 characters!")
    private String fullName;

    /**
     * Client's gender, 1 male, 2 female
     */
    @Min(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 1, message = "Gender must be 1 or 2!")
    @Max(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 2, message = "Gender must be 1 or 2!")
    private Integer gender;

    /**
     *Phone number
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 30, message = "Phone cannot exceed 30 characters!")
    private String phone;

    /**
     *Mail
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 128, message = "Email cannot exceed 128 characters!")
    @Email(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, message = "Invalid email format")
    private String email;

    /**
     *Other contact details, including QQ, WeChat, Line, What's App, etc.
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 500, message = "OtherContactDetails cannot exceed 500 characters!")
    private String OtherContactDetails;

    /**
     *age
     */
    private Integer age;

    /**
     *Profession
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 64, message = "Job cannot exceed 64 characters!")
    private String job;

    /**
     *Annual income
     */
    private BigDecimal yearIncome;

    /**
     * Currency unit for annual income
     */
    @NotNull(groups = {ValidationGroups.AddClueGroup.class}, message = "Currency unit is required!")
    @Pattern(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, regexp = "^(万元|万円|thousand USD)$", message = "Currency unit must be 万元, 万円 or thousand USD!")
    private String currencyUnit;

    /**
     *address
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 128, message = "Address cannot exceed 128 characters!")
    private String address;

    /**
     *Whether the client needs a loan (0 not required, 1 required)
     */
    @Min(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 0, message = "NeedLoan value must be 0 or 1!")
    @Max(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 1, message = "NeedLoan value must be 0 or 1!")
    private Integer needLoan;

    /**
     * Client's intention on buying products, 0 no intention, 1 has intention, 2 intention unknown
     */
    @Min(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 0, message = "intentionState must be between 0 and 2!")
    @Max(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 2, message = "intentionState must be between 0 and 2!")
    private Integer intentionState;

    /**
     *Intentional product
     */
    private Integer intentionProduct;

    /**
     * Clue/Lead status
     */
    @Min(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 1, message = "intentionState must be between 1 and 8!")
    @Max(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, value = 8, message = "intentionState must be between 1 and 8!")
    private Integer state;

    /**
     * Source of clues
     */
    private Integer source;

    /**
     *Clue description
     */
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 1024, message = "Description cannot exceed 1024 characters!")
    private String description;

    /**
     *Next contact time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextContactTime;

    /**
     *Creation time
     */
    private LocalDateTime createTime;

    /**
     *Creator
     */
    private Integer createBy;

    /**
     *Editing time
     */
    private LocalDateTime editTime;

    /**
     *Editor
     */
    private Integer editBy;

    /**
     *Region, 1 China, 2 Japan, 3 United States, 4 others
     */
    @NotNull(groups = {ValidationGroups.AddClueGroup.class}, message = "Region is required!")
    private Integer region;

    /**
     *Whether it has been deleted, 0 has not been deleted, 1 has been deleted
     */
    private Integer isDeleted;

    /**
     *Non-database fields
     */
    // Clue owner's login_act
    private String ownerAct;
    // Activity name that the clue belongs to
    private String activityName;
    // Product's name that client wants to buy
    private String intentionProductName;
    // Clue creator's login_act
    private String createByAct;
    // Clue editor's login_act
    private String editByAct;

    private static final long serialVersionUID = 1L;


}