// Leetcode 1792
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        Queue<int[]> passRatioPriority = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare (int[] class1, int[] class2) {
                if (profitRatio(class1[0], class1[1]) < profitRatio(class2[0], class2[1]))
                    return 1;
                else if (profitRatio(class1[0], class1[1]) > profitRatio(class2[0], class2[1]))
                    return -1;
                else return 0;
            }
        });
        
        double passRatioTotal = 0.0;
        
        for (int[] eachClass: classes) {
            passRatioPriority.add(eachClass);
            passRatioTotal += profit(eachClass[0], eachClass[1]);
        }
        
        while (extraStudents-- > 0 && !passRatioPriority.isEmpty()) {
            int[] newClass = passRatioPriority.poll();
            passRatioTotal += profitRatio(newClass[0], newClass[1]);
            passRatioPriority.add(new int[] {newClass[0] + 1, newClass[1] + 1});
        }
        return (double) passRatioTotal / classes.length;
    }
    
    public double profit (int a, int b) {
        return (double) a / b;
    }
    
    public double profitRatio (int a, int b) {
        return profit(a + 1, b + 1) - profit(a, b);
    }
}