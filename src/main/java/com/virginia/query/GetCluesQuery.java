package com.virginia.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Encapsulation of querying parameters
 * @author Virginia
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCluesQuery extends SelectAllQuery{
    // Clue owners' ids
    private List<Integer> owners;
    // Activity ids which clue belongs to
    private List<Integer> activities;
    // Client's full name
    @Size(max = 64, message = "Full name cannot exceed 64 characters!")
    private String fullName;
    // Client's gender
    @Min(value = 1, message = "Gender must be 1 or 2!")
    @Max(value = 2, message = "Gender must be 1 or 2!")
    private Integer gender;
    // Whether the client needs loan to buy the product
    @Min(value = 0, message = "NeedLoan value must be 0 or 1!")
    @Max(value = 1, message = "NeedLoan value must be 0 or 1!")
    private Integer needLoan;
    // Whether the client wants to buy the product
    @Min(value = 0, message = "intentionState must be between 0 and 2!")
    @Max(value = 2, message = "intentionState must be between 0 and 2!")
    private Integer intentionState;
    // Products which client wants to buy
    private List<Integer> intentionProducts;
    // States of the clue
    //  { name: "已转客户", id: 1 },
    //	{ name: "虚假线索", id: 2 },
    //	{ name: "需要条件", id: 3 },
    //	{ name: "将来联系", id: 4 },
    //	{ name: "丢失线索", id: 5 },
    //	{ name: "试图联系", id: 6 },
    //	{ name: "未联系", id: 7 },
    //	{ name: "已联系", id: 8 },
    private List<Integer> states;
    // Sources of the clue
    //  { name: "易车网", id: 1 },
    //	{ name: "员工介绍", id: 2 },
    //	{ name: "官方网站", id: 3 },
    //	{ name: "公众号", id: 4 },
    //	{ name: "门店参观", id: 5 },
    //	{ name: "懂车帝", id: 6 },
    //	{ name: "朋友圈", id: 7 },
    //	{ name: "合作伙伴", id: 8 },
    //	{ name: "地图", id: 9 },
    //	{ name: "视频直播", id: 10 },
    //	{ name: "网络广告", id: 11 },
    //	{ name: "汽车之家", id: 12 },
    //	{ name: "车展会", id: 13 },
    //	{ name: "知乎", id: 14 },
    private List<Integer> sources;
    // Regions that the clues belong to
    // 1 China, 2 Japan, 3 USA, 4 Others
    private List<Integer> regions;
}
