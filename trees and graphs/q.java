static int minimumSplitS (int n, int[] x, int[] type){
    long hero = 1;
    int result = 0;
    
    PriorityQueue<Integer> splitPriority = new PriorityQueue<Integer>(n, Collections.reverseOrder());
    
    for(int i = 0; i < n; i++) {
        if (i == 0 && type[i] == 2 && hero <= x[i])
            return -1;
        else if(type[i] == 1) {
            splitPriority.add(x[i]);
            continue;
        }

        while(hero <= x[i] && !splitPriority.isEmpty()){
            hero -= 1;
            hero += splitPriority.remove();
            result += 1;
        }


        if(hero <= x[i])
            return -1;
    }

    return result;
}