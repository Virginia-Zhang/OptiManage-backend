package com.virginia.pojo;

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
    private Integer id;

    /**
     *ID of the clue owner
     */
    @NotNull(message = "Clue owner id is required!")
    private Integer ownerId;

    /**
     *Activity ID
     */
    private Integer activityId;

    /**
     * Client's name
     */
    @Size(max = 64, message = "Full name cannot exceed 64 characters!")
    private String fullName;

    /**
     * Client's gender, 1 male, 2 female
     */
    @Min(value = 1, message = "Gender must be 1 or 2!")
    @Max(value = 2, message = "Gender must be 1 or 2!")
    private Integer gender;

    /**
     *Phone number
     */
    @Size(max = 30, message = "Phone cannot exceed 30 characters!")
    private String phone;

    /**
     *WeChat ID
     */
    @Size(max = 128, message = "Weixin cannot exceed 128 characters!")
    private String weixin;

    /**
     *QQ number
     */
    @Size(max = 20, message = "QQ cannot exceed 20 characters!")
    private String qq;

    /**
     *Mail
     */
    @Size(max = 128, message = "Email cannot exceed 20 characters!")
    @Email(message = "Invalid email format")
    private String email;

    /**
     *age
     */
    private Integer age;

    /**
     *Profession
     */
    @Size(max = 64, message = "Job cannot exceed 64 characters!")
    private String job;

    /**
     *Annual income
     */
    private BigDecimal yearIncome;

    /**
     *address
     */
    @Size(max = 128, message = "Address cannot exceed 128 characters!")
    private String address;

    /**
     *Whether the client needs a loan (0 not required, 1 required)
     */
    @Min(value = 0, message = "NeedLoan value must be 0 or 1!")
    @Max(value = 1, message = "NeedLoan value must be 0 or 1!")
    private Integer needLoan;

    /**
     * Client's intention on buying products, 0 no intention, 1 has intention, 2 intention unknown 
     */
    @Min(value = 0, message = "intentionState must be between 0 and 2!")
    @Max(value = 2, message = "intentionState must be between 0 and 2!")
    private Integer intentionState;

    /**
     *Intentional product
     */
    private Integer intentionProduct;

    /**
     * Clue/Lead status
     *{ name: "Transferred customer", id: 1 },
     *{ name: "False clue", id: 2 },
     *{ name: "Required conditions", id: 3 },
     *{ name: "Contact me in the future", id: 4 },
     *{ name: "Lost Clue", id: 5 },
     *{ name: "Attempt to contact", id: 6 },
     *{ name: "Not contacted", id: 7 },
     *{ name: "Contacted", id: 8 },
     */
    @Min(value = 1, message = "intentionState must be between 1 and 8!")
    @Max(value = 8, message = "intentionState must be between 1 and 8!")
    private Integer state;

    /**
     * Source of clues
     *{ name: "Bitauto.com", id: 1 },
     *{ name: "Employee Introduction", id: 2 },
     *{ name: "Official website", id: 3 },
     *{ name: "Official account", id: 4 },
     *{ name: "Store Visit", id: 5 },
     *{ name: "Understanding Car Emperor", id: 6 },
     *{ name: "Friends Circle", id: 7 },
     *{ name: "Partner", id: 8 },
     *{ name: "map", id: 9 },
     *{ name: "Live video", id: 10 },
     *{ name: "Online Advertising", id: 11 },
     *{ name: "Autohome", id: 12 },
     *{ name: "Auto Show", id: 13 },
     *{ name: "Zhihu", id: 14 },
     */
    private Integer source;

    /**
     *Clue description
     */
    @Size(max = 255, message = "Description cannot exceed 255 characters!")
    private String description;

    /**
     *Next contact time
     */
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
    @NotNull(message = "Region is required!")
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