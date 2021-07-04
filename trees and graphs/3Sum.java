// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
// such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
// Leetcode 15


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = nums.length;
        if (n < 3)
            return result;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || (i > 0 && !(nums[i] == nums[i - 1]))) {
                int start = i + 1, end = n - 1;
                while (start < end) {
                    if (nums[i] + nums[start] + nums[end] == 0) {
                        List<Integer> ijk = Arrays.asList(nums[i], nums[start], nums[end]);
                        result.add(ijk);
                        
                        while (start < end && nums[start] == nums[start + 1])
                            start++;
                        while (start < end && nums[end] == nums[end - 1])
                            end--;
                        start++;
                        end--;
                    } else if (nums[i] + nums[start] + nums[end] > 0)
                        end--;
                    else
                        start++;
                }
            }
        }
        return result;
    }
}