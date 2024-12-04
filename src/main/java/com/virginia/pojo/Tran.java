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
 * Entity for transaction table
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tran implements Serializable {
    /**
     * Primary key, automatic growth, transaction id
     */
    private Integer id;

    /**
     * Transaction number
     */
    @Size(max = 255, message = "TranNo cannot exceed 255 characters!")
    @NotBlank(message = "TranNo is required!")
    private String tranNo;

    /**
     * Customer id
     */
    @NotNull(message = "CustomerId is required!")
    private Integer customerId;

    /**
     * Transaction amount
     */
    @NotNull(message = "Amount is required!")
    private BigDecimal amount;

    /**
     * Currency unit，1-万元，2-万円，3-thousand USD
     */
    @NotNull(message = "CurrencyUnit is required!")
    @Pattern(regexp = "^(万元|万円|thousand USD)$", message = "Currency unit must be 万元, 万円 or thousand USD!")
    private String currencyUnit;

    /**
     * Estimated closing date
     */
    @NotNull(message = "ExpectedDate is required!")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expectedDate;

    /**
     * Transaction stage
     */
    @NotNull(message = "Stage is required!")
    private Integer stage;

    /**
     * Transaction description
     */
    @Size(max = 1024, message = "Description cannot exceed 1024 characters!")
    @NotBlank(message = "Description is required!")
    private String description;

    /**
     * Next contact time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "NextContactTime is required!")
    private LocalDateTime nextContactTime;

    /**
     * creation time
     */
    private LocalDateTime createTime;

    /**
     * Id of the person who created the data
     */
    private Integer createBy;

    /**
     * Edit time
     */
    private LocalDateTime editTime;

    /**
     * Id of the person who edited the data
     */
    private Integer editBy;

    /**
     * Country and region, 1-China, 2-Japan, 3-USA, 4-Others
     */
    @NotNull(message = "Region is required!")
    private Integer region;

    /**
     * whether the data is deleted, 0-not deleted, 1-deleted
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}