package com.virginia.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersQuery extends SelectAllQuery{
    // User's login account
    private String loginAct;
    // User's name
    private String name;
    // Regions that the users belong to
    // 1 China, 2 Japan, 3 USA, 4 Others
    private List<Integer> regions;
    // User's roles
    private List<Integer> roleList;
}
