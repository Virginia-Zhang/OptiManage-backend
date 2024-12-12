package com.virginia.query;

import com.virginia.pojo.User;
import com.virginia.validation.ValidationGroups;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Query object for adding/editing a user and role relationship
 * @author Virginia
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleQuery extends User {
    @NotEmpty(groups = {ValidationGroups.AddUserGroup.class}, message = "Role IDs is required!")
    private List<Integer> roleIds;
}
