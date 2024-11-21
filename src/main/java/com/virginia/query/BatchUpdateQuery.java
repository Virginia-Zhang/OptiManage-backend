package com.virginia.query;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *Parameters encapsulation for BatchUpdate feature
 * @author Virginia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchUpdateQuery {
    // Ids to be updated
    @NotNull(message = "IDs are required!")
    @Size(min = 1, message = "At least one ID is required!")
    private List<Integer> ids;
    // To set is_deleted field to the value below
    @NotNull(message = "Value is required!")
    private Integer isDeletedValue;

    // Check if isDeletedValue is 0 or 1
    @AssertTrue(message = "IsDeletedValue must be 0 or 1!")
    public boolean isIsDeletedValueValid() {
        return isDeletedValue != null && (isDeletedValue == 0 || isDeletedValue == 1);
    }
}
