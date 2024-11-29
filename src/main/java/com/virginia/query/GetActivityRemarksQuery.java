package com.virginia.query;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetActivityRemarksQuery extends SelectAllQuery{
    @NotNull(message = "ActivityId is required!")
    private Integer activityId;

}
