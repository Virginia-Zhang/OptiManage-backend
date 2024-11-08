package com.virginia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private String name;

    /**
     *Activity start time
     */
    private Date startTime;

    /**
     *Activity end time
     */
    private Date endTime;

    /**
     *Activity budget, RMB
     */
    private BigDecimal costRmb;

    /**
     *Activity description
     */
    private String description;

    /**
     *Activity created time
     */
    private Date createTime;

    /**
     *Activity creator
     */
    private Integer createBy;

    /**
     *Activity edited time
     */
    private Date editTime;

    /**
     *Activity Editor
     */
    private Integer editBy;

    /**
     *Activity areas, 1 China, 2 Japan, 3 United States, 4 United Kingdom, 5 France, 6 Germany, 7 Singapore, 8 India, 9 Australia, 10 South Korea, 11 others
     */
    private Integer region;

    /**
     *Activity budget, USD
     */
    private BigDecimal costUsd;

    /**
     *Activity budget, Japanese yen
     */
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