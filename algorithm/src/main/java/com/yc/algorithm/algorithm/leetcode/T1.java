/*
package com.yc.algorithm.algorithm.leetcode;

public class T1 {
    class Solution {
        public int rob(int[] nums) {
            // param check 参数已经满足
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            if (n == 2) {
                return Math.max(nums[0], nums[1]);
            }

            // i [0, n-1] n>=3
            // max[0, n-2] max[1, n-1] 取最大值
            int lMax = getMaxRange(nums, 0, n-2);
            int rMax = getMaxRange(nums, 1, n-1);

            return Math.max(lMax, rMax);
        }

        // [left, right] [0,2] [1,3]
        private int getMaxRange(int[] nums, int left, int right) {
            if (right == left) {
                return nums[left];
            }
            if (right - left == 1) {
                return Math.max(nums[left], nums[right]);
            }

            // dp[i] 表示考虑偷到第i个房主的时候的最大值 max(nums[i]+dp[i-2], dp[i-1])
            int[] dp = new int[right-left+1]; // + 1; right-left+1
            dp[0] = nums[left];
            dp[1] = Math.max(nums[left], nums[left + 1]);
            for (int i = left + 2; i <= right; i++) {
                dp[i-left] = Math.max(nums[i]+dp[i-left-2], dp[i-left-1]);
            }


            return dp[dp.length-1];
        }
    }



    class Solution2 {
        public int rob(int[] nums) {
            // param check 参数已经满足
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            if (n == 2) {
                return Math.max(nums[0], nums[1]);
            }

            // i [0, n-1] n>=3
            // max[0, n-2] max[1, n-1] 取最大值
            int lMax = getMaxRange(nums, 0, n-2);
            int rMax = getMaxRange(nums, 1, n-1);

            return Math.max(lMax, rMax);
        }

        // [left, right] right > left
        private int getMaxRange(int[] nums, int left, int right) {
            // dp[i] 表示考虑偷到第i个房主的时候的最大值 max(nums[i]+dp[i-2], dp[i-1])
            int[] dp = new int[right-left+1]; // + 1; right-left+1
            dp[0] = nums[left];
            dp[1] = Math.max(nums[left], nums[left + 1]);
            for (int i = left + 2; i <= right; i++) {
                dp[i-left] = Math.max(nums[i]+dp[i-left-2], dp[i-left-1]);
            }

            return dp[dp.length-1];
        }
    }


    public class Test {
        main() {
            // test case[]
            // null, 0, 1; 1,2; 2,3,2; 2,3,2,4;
            int[] nums = new int[]{2,3,2,4};
            Solution s = new Solution();
            int res = s.rob(nums);
            // mock assert
            sout(res == 7);
        }

        class Solution {
            public int rob(int[] nums) {
                // param check 参数已经满足
                int n = nums.length;
                if (n == 1) {
                    return nums[0];
                }
                if (n == 2) {
                    return Math.max(nums[0], nums[1]);
                }

                // i [0, n-1] n>=3
                // max[0, n-2] max[1, n-1] 取最大值
                int lMax = getMaxRange(nums, 0, n-2);
                int rMax = getMaxRange(nums, 1, n-1);

                return Math.max(lMax, rMax);
            }

            // [left, right] right > left
            private int getMaxRange(int[] nums, int left, int right) {
                // dp[i] 表示考虑偷到第i个房主的时候的最大值 max(nums[i]+dp[i-2], dp[i-1])
                int[] dp = new int[right-left+1]; // + 1; right-left+1
                dp[0] = nums[left];
                dp[1] = Math.max(nums[left], nums[left + 1]);
                for (int i = left + 2; i <= right; i++) {
                    dp[i-left] = Math.max(nums[i]+dp[i-left-2], dp[i-left-1]);
                }

                return dp[dp.length-1];
            }
        }
}
*/
