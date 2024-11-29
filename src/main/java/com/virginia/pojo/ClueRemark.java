package com.virginia.pojo;

import com.virginia.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Marketing lead/clue tracking record entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClueRemark implements Serializable {
    /**
     *Primary key, automatic growth, clue remark ID
     */
    @NotNull(groups = {ValidationGroups.EditClueGroup.class}, message = "Id is required!")
    private Integer id;

    /**
     *Lead ID
     */
    @NotNull(groups = {ValidationGroups.AddClueGroup.class}, message = "ClueId is required!")
    private Integer clueId;

    /**
     *Tracking method
     *{ name: "Phone", value: 1 },
     *{ name: "SMS", value: 2 },
     *{ name: "Social Media", value: 3 },
     *{ name: "Mail", value: 4 },
     *{ name: "Other", value: 5 },
     */
    @NotNull(groups = {ValidationGroups.AddClueGroup.class}, message = "ContactMethod is required!")
    private Integer contactMethod;

    /**
     *Tracking content
     */
    @NotBlank(groups = {ValidationGroups.AddClueGroup.class}, message = "NoteContent is required!")
    @Size(groups = {ValidationGroups.AddClueGroup.class, ValidationGroups.EditClueGroup.class}, max = 5000, message = "NoteContent cannot exceed 5000 characters!")
    private String noteContent;

    /**
     *Tracking time
     */
    private LocalDateTime createTime;

    /**
     * creator's id
     */
    private Integer createBy;

    /**
     *Editing time
     */
    private LocalDateTime editTime;

    /**
     *Editor's id
     */
    private Integer editBy;

    /**
     *Deletion status (0 normal, 1 deleted)
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    /**
     *Non-database fields
     */
    // creator's login account
    private String createByAct;
    // editor's login account
    private String editByAct;

}