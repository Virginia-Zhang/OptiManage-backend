package com.virginia.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.virginia.validation.ValidCost;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *Marketing activity table
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {
    /**
     *Primary key, automatic growth, activity ID
     */
    private Integer id;

    /**
     *Activity owner ID
     */
    private Integer ownerId;

    /**
     *Activity name
     */
    @NotEmpty(message = "Name is required!")
    @Size(max = 128, message = "Name cannot exceed 128 characters!")
    private String name;

    /**
     *Activity start time
     */
    @NotNull(message = "Start time is required!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     *Activity end time
     */
    @NotNull(message = "End time is required!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     *  Activity budget, RMB
     *  Verify the data type, which must be an integer or two decimal places or less.
     *  The non-empty verification logic of this field is relatively complex and is placed in the controller.
     */
    @ValidCost
    private BigDecimal costRmb;

    /**
     *Activity description
     */
    @NotEmpty(message = "Description is required!")
    @Size(max = 1024, message = "Description cannot exceed 1024 characters!")
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
     *Activity areas, 1 China, 2 Japan, 3 United States, 4 United Kingdom, 5 France, 6 Germany, 7 Singapore, 8 India, 9 Australia, 10 South Korea, 11 others
     */
    @NotNull(message = "Region is required!")
    private Integer region;

    /**
     *  Activity budget, USD
     *  Verify the data type, which must be an integer or two decimal places or less.
     *  The non-empty verification logic of this field is relatively complex and is placed in the controller.
     */
    @ValidCost
    private BigDecimal costUsd;

    /**
     *  Activity budget, Japanese yen
     *  Verify the data type, which must be an integer or two decimal places or less.
     *  The non-empty verification logic of this field is relatively complex and is placed in the controller.
     */
    @ValidCost
    private BigDecimal costJpy;

    /**
     *Whether the activity has been deleted, 0 not deleted, 1 deleted
     */
    private Integer isDeleted;

    /**
     *Non-database fields
     */
    // owner's login account
    private String ownerAct;

    private static final long serialVersionUID = 1L;
}