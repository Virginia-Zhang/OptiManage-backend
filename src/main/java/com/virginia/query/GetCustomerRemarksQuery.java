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
public class GetCustomerRemarksQuery extends SelectAllQuery{
    @NotNull(message = "CustomerId is required!")
    private Integer customerId;
}
