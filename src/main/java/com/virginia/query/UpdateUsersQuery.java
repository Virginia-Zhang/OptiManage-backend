package com.virginia.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *Parameter encapsulation of updateUsersByIds interface
 * @author Virginia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsersQuery {
    // User Ids to be updated
    private List<Integer> ids;
    // To set the user's account_enabled field to the value below
    private Integer accountEnabledValue;
}
