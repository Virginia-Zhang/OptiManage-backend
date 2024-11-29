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
 *Marketing Activity Remark Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRemark implements Serializable {
    /**
     *Primary key, automatic growth, activity remark ID
     */
    @NotNull(groups = {ValidationGroups.EditActivityRemarkGroup.class}, message = "ID is required!")
    private Integer id;

    /**
     *Activity ID
     */
    @NotNull(groups = {ValidationGroups.AddActivityRemarkGroup.class, ValidationGroups.EditActivityGroup.class}, message = "Activity ID is required!")
    private Integer activityId;

    /**
     *Remark content
     */
    @Size(groups = {ValidationGroups.AddActivityRemarkGroup.class, ValidationGroups.EditActivityGroup.class}, max = 5000, message = "Note content cannot exceed 5000 characters!")
    @NotBlank(groups = {ValidationGroups.AddActivityRemarkGroup.class, ValidationGroups.EditActivityGroup.class}, message = "Note content is required!")
    private String noteContent;

    /**
     *Remark created time
     */
    private LocalDateTime createTime;

    /**
     *Remark creator
     */
    private Integer createBy;

    /**
     *Remark edited time
     */
    private LocalDateTime editTime;

    /**
     *Remark editor
     */
    private Integer editBy;

    /**
     *Deletion status (0 normal, 1 deleted)
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
    /**
     * Non-database fields
     */
    // Activity creator's login account
    private String createByAct;
    // Activity editor's login account
    private String editByAct;
}