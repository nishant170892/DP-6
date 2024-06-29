// Time Complexity : O(NlogN), there will be more than n numbers in heap
// Space Complexity :  O(N) 
// Did this code successfully run on Leetcode : yes
//Approach : New ugly number is created from previous ugly number

class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int[] num = new int[] {2,3,5};

        set.add(1l);
        pq.add(1l);
        int count = 0;

        while(!pq.isEmpty()){
            long curr = pq.poll();
            count++;
            if(count == n){
                return (int)curr;
            }
            for(int i : num){
                long nextNum = curr * i;
                if(!set.contains(nextNum)){
                    set.add(nextNum);
                    pq.add(nextNum);
                }
            }
        }

        return -1;
    }
}

// Time Complexity : O(n)
// Space Complexity :  O(n) 
// Did this code successfully run on Leetcode : yes
//Approach : New ugly number is created from previous ugly number. Increase pointer whichever gives us the next ugly number.


class Solution {
    public int nthUglyNumber(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        int p2 = 0, p3 = 0 , p5 = 0; // pointer for each uglynumber
        int n2 = 2 ,  n3 = 3 , n5 = 5; // uglynumber produced by each pointer

        for(int i = 1; i < n ; i++){
            int min = Math.min(n2, Math.min(n3, n5));
            arr[i] = min; //next ugly number produced.

            if(n2 == min){
                p2++;
                n2 = arr[p2] * 2;
            }

            if(n3 == min){
                p3++;
                n3 = arr[p3] * 3;
            }

            if(n5 == min){
                p5++;
                n5 = arr[p5] * 5;
            }
        }

        return arr[n-1];
    }
}
