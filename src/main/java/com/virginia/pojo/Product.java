package com.virginia.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Product entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    /**
     *Primary key, automatic growth, lead ID
     */
    @NotNull(message = "主键，自动增长，线索IDis not null")
    private Integer id;

    /**
     *product name
     */
    @Size(max = 128, message = "产品名称max length should less than 128")
    private String name;

    /**
     *Official starting price
     */
    private BigDecimal guidePriceS;

    /**
     *Official maximum price
     */
    private BigDecimal guidePriceE;

    /**
     *Dealer quotation
     */
    private BigDecimal quotation;

    /**
     *Status 0 on sale 1 sold out
     */
    private Integer state;

    /**
     *Creation time
     */
    private Date createTime;

    /**
     *Creator
     */
    private Integer createBy;

    /**
     *Editing time
     */
    private Date editTime;

    /**
     *Editor
     */
    private Integer editBy;

    /**
     *currency unit
     */
    @NotNull(message = "货币单位is not null")
    private String currencyUnit;

    /**
     *Country region, 1-China, 2-Japan, 3-USA, 4-Others
     */
    @NotNull(message = "国家地区，1-China, 2-Japan, 3-USA, 4-Othersis not null")
    private Integer region;

    private static final long serialVersionUID = 1L;
}