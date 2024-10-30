package com.virginia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Paginated query result encapsulation
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    // Total number of records
    private Long total;
    // Records per page
    private List<?> rows;
}
