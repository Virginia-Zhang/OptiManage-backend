package com.virginia.query;

import jakarta.validation.constraints.AssertTrue;
import lombok.*;

/**
 * Parameters encapsulation for selectAll feature
 * @author Virginia
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectAllQuery extends DataFilterQuery {
    // Page number
    private Integer page;
    // Number of records per page
    private Integer pageSize;
    // Whether to select deleted records
    private Integer isDeleted;

    // Default values
    {
           if (page == null) {
               page = 1;
           }
           if (pageSize == null) {
               pageSize = 10;
           }
           if (isDeleted == null) {
               isDeleted = 0;
           }
       }

    // Check if isDeleted is 0 or 1
    @AssertTrue(message = "IsDeleted must be 0 or 1!")
    public boolean isIsDeletedValid() {
        return isDeleted != null && (isDeleted == 0 || isDeleted == 1);
    }
}
