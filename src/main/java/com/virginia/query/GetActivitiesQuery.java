package com.virginia.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Encapsulation of querying parameters. Inherit DataFilterQuery class, used to add data filtering SQL to implement permission control
 * @author Virginia
 */
public class GetActivitiesQuery extends DataFilterQuery {
    private Integer page = 1;
    private Integer pageSize = 10;
    private List<Integer> ownerIds;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private BigDecimal startCostUSD;
    private BigDecimal endCostUSD;
    private BigDecimal startCostRMB;
    private BigDecimal endCostRMB;
    private BigDecimal startCostJPY;
    private BigDecimal endCostJPY;
    private List<Integer> regions;
}
