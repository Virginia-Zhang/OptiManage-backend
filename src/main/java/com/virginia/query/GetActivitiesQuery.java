package com.virginia.query;

import com.virginia.validation.ValidationGroups;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
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
    @Digits(groups = {ValidationGroups.SelectActivitiesGroup.class}, integer = 11, fraction = 2, message = "StartCost must be a number with at most two decimal places!")
    private BigDecimal startCost;
    @Digits(groups = {ValidationGroups.SelectActivitiesGroup.class}, integer = 11, fraction = 2, message = "EndCost must be a number with at most two decimal places!")
    private BigDecimal endCost;
    @Pattern(groups = {ValidationGroups.SelectActivitiesGroup.class}, regexp = "^(USD|RMB|JPY)$", message = "Currency unit must be USD, RMB or JPY!")
    private String currencyUnit;
    private List<Integer> regions;
}
