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
public class GetClueRemarksQuery extends SelectAllQuery {
    @NotNull(message = "ClueId is required!")
    private Integer clueId;
}
