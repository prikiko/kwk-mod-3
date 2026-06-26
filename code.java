class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        while (i < j) {
            if (nums[i] + nums[j] < k) {
                i ++;
            } else if (nums[i] + nums[j] > k) {
                j --;
            } else {
                count ++;
                i++;
                j --;
            }
        }
        return count;
    }

    private int mapMethod(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int curr = map.get(nums[i]);
                map.put(nums[i], ++curr);
            } else {
                map.put(nums[i], 1);
            }
        }

        int operations = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(k - nums[i])) {
                if (k - nums[i] != nums[i]) {
                    int x = map.get(k - nums[i]);
                    int y = map.get(nums[i]);
                    int add = Math.min(x, y);
                    operations += add;
                    map.put(k - nums[i], x - add);
                    map.put(nums[i], y - add);
                } else {
                    int m = map.get(nums[i]);
                    operations += m / 2;
                    map.put(nums[i], 0);
                }
            }
        }

        return operations;
    }
}
