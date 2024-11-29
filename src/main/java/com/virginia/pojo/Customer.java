package com.virginia.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.virginia.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Customer entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    /**
     *Primary key, automatic growth, customer ID
     */
    @NotNull(groups = {ValidationGroups.EditCustomerGroup.class}, message = "ID is required!")
    private Integer id;

    /**
     *Lead ID
     */
    @NotNull(groups = {ValidationGroups.AddCustomerGroup.class}, message = "Clue ID is required!")
    private Integer clueId;

    /**
     *Product id that the customer wants to buy
     */
    @NotNull(groups = {ValidationGroups.AddCustomerGroup.class}, message = "Product is required!")
    private Integer product;

    /**
     *Customer description
     */
    @NotBlank(groups = {ValidationGroups.AddCustomerGroup.class, ValidationGroups.EditCustomerGroup.class}, message = "Description is required!")
    @Size(groups = {ValidationGroups.AddCustomerGroup.class, ValidationGroups.EditCustomerGroup.class}, max = 1000, message = "Description cannot exceed 1000 characters!")
    private String description;

    /**
     *Next contact time
     */
    @NotNull(groups = {ValidationGroups.AddCustomerGroup.class}, message = "Next contact time is required!")
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
     *1-China, 2-Japan, 3-USA, 4-Others
     */
    @NotNull(groups = {ValidationGroups.AddCustomerGroup.class}, message = "Region is required!")
    private Integer region;

    /**
     *0-Not deleted, 1-Deleted
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}