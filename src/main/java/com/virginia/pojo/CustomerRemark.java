package com.virginia.pojo;

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
 * Entity for customer remark
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRemark implements Serializable {
    /**
     * Primary key, automatic growth, customer remark/note id
     */
    @NotNull(groups = {ValidationGroups.EditCustomerRemarkGroup.class}, message = "ID is required!")
    private Integer id;

    /**
     * Customer id
     */
    @NotNull(groups = {ValidationGroups.AddCustomerRemarkGroup.class}, message = "Customer ID is required!")
    private Integer customerId;

    /**
     * Contact Method
     */
    @NotNull(groups = {ValidationGroups.AddCustomerRemarkGroup.class}, message = "Contact method is required!")
    private Integer contactMethod;

    /**
     * Note content
     */
    @Size(max = 5000, message = "Note content cannot exceed 5000 characters!")
    @NotBlank(groups = {ValidationGroups.AddCustomerRemarkGroup.class}, message = "Note content is required!")
    private String noteContent;

    /**
     * Id of the user who created the note
     */
    private Integer createBy;

    /**
     * Create time
     */
    private LocalDateTime createTime;

    /**Edit time
     */
    private LocalDateTime editTime;

    /**
     * Id of the user who edited the note
     */
    private Integer editBy;

    /**
     * Deletion status (0 normal, 1 deleted)
     */
    private Integer isDeleted;

    /**
     * Non database fields
     */
    // Creator's login account
    private String createByAct;
    // Editor's login account
    private String editByAct;

    private static final long serialVersionUID = 1L;
}