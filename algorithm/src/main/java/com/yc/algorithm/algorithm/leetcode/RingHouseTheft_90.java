package com.yc.algorithm.algorithm.leetcode;

class Test2 {
    public static void main(String[] args) {
        RingHouseTheft_90 solution = new RingHouseTheft_90();
        //testcase1 [0] 0
        //testcase2 [1,2] 2
        //testcase3 [2,3,2] 3
        //testcase4 [1,2,3,1] 4
        int[] ts1 = new int[]{1,2,3,1};
        int ans = solution.rob(ts1);
        //检查
        System.out.println(ans == 4);
    }
}

/**
 *一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/PzWKhm
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RingHouseTheft_90 {

    //testcase1 [0] 0
    //testcase2 [1,2] 2
    //testcase3 [2,3,2]
    //testcase4 [1,2,3,1]
    public int rob(int[] nums) {
        //参数校验 已知 1 <= n <=100
        int n = nums.length;
        if (n == 1) {
            //只有一个元素，则直接返回这个元素
            return nums[0];
        }
        if (n == 2) {
            //只有两个元素，返回这两个元素中较大者
            return Math.max(nums[0], nums[1]);
        }
        //把数组分为[0, n-2]、[1, n-1]两段，求两段中最大值
        int lMax = getRangeMax(nums, 0, n - 2); //n = 4; left = 0; right = 2  ans = 4
        int rMax = getRangeMax(nums, 1, n - 1); //n = 4; left = 1; right = 3  ans = 3

        //返回上述两段中的最大值
        return Math.max(lMax, rMax); //4
    }

    /**
     * 不考虑环的情况，返回nums中[left, right]中偷到的最大值
     */
    //                        [1,2,3,1]    1         3
    private int getRangeMax(int[] nums, int left, int right) {
        //dp存储遍历到某个位置时能获取到的最高金额
        //i = left; dp[i] = nums[left]
        //i = left + 1; dp[i] = Math.max(nums[left], nums[left + 1])
        //i >= left + 2; dp[i] = Math.max(选了i，没选i) =  Math.max(dp[i - 2] + nums[i], dp[i - 1])
        int[] dp = new int[right + 1];  //int[4]
        dp[left] = nums[left];  //dp[1] = 2
        dp[left + 1] = Math.max(nums[left], nums[left + 1]);    //dp[2] = 3
        for (int i = left + 2; i <= right; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);   // i =3;dp[3] = 3
        }
        return dp[dp.length - 1];   //3
    }
}
