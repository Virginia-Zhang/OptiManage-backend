package com.virginia.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data filtering query SQL statement encapsulation
 * @author Virginia
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFilterQuery {
    private String filterSQL;
}
