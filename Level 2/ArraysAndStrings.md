# Arrays and Strings

### Faulty Keyboard(https://leetcode.com/problems/long-pressed-name/)

```
Recognize that if name string can be made by typed string 

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.


Approach:-  Take two pointers i and j in which i is pointing to name string and j is pointing to typed string. Do a loop till i is less than name.length and j is less than typed.length. While looping check if name[i] is equals to typed[j] then increase both pointers, if name[i] is not equals to typed[j] then check if name[i-1] equals to typed[j]  then increase j(In this case we are increasing j since the word is faulty and is being pressed multiple times) otherwise we can directlry return false. 
If loop ends and j is still lesser then typed.length then loop over it and check all the remaining characters should be equals to last character of name string otgerwise return false.
If loop ends and i is still lesser then name.length, then we can't make answer as this means that typed .length is less than name.length. 

public boolean isLongPressedName(String name, String typed) {      
  if (name.length() > typed.length()) {
      return false;
  }
  
  int i =0;
  int j = 0;
  
  while (i < name.length() && j < typed.length()) {
      if (name.charAt(i) == typed.charAt(j)) {
          i++;
          j++;
      } else if (i > 0 && name.charAt(i-1) == typed.charAt(j)) {
          j++;
      } else {
          return false;
      }
  }
  
  while (j < typed.length()) {
      if (name.charAt(i-1) != typed.charAt(j))
          return false;
      j++;
  }
  
  return i < name.length() ? false : true;
}
```

### Range Addition

```
Assume you have an array of length n initialized with all 0’s and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex … endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]


Solution : Here what we need to do is 

Step 1 :- Create a new answer array of provided length

Step 2 :- Start a loop through queries and add the provided value to start index and subtract a provided value from end + 1 index.
Ex :- queries is [1, 2, 3] and array currently is [0, 0, 0, 0, 0] then post step 2 operation array will be [0, 3, 0, 0, -3]
If ending index given in query is the last index of array then just update the start index and ignore the end + 1 index.

Step 3 :- Post doing step 2 for all the queries calculate prefix sum of the ans array and update it accordingly. 
Prefix sum is nothing but adding elements present inside array gradually. Ex:- Prefix sum of [1, 2, 3] is [1, 3(1+2), 6(3+3)] = arr[i] = arr[i] + arr[i-1].

Then we have our answer in array

Why of step 2 :- In step 2 we are adding a impact of value at starting of index and then removing the impact of value at the end + 1 index. And then by prefix sum value is getting automatically added in all the places of array.

// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] queries = {
            {1,  3,  2},
            {2,  4,  3},
            {0,  2, -2}
            };

        <!-- Step 1 -->
        int[] ans = new int[n];
        
        <!-- Step 2 -->
        for (int i = 0; i < queries.length; i++) {
            ans[queries[i][0]] += queries[i][2];
            if (queries[i][1] < ans.length -1) {
                ans[queries[i][1] + 1] -= queries[i][2];
            }
        }
        
        <!-- Step 3 -->
        int sum = ans[0];
        for (int i=1; i < ans.length; i++) {
            ans[i] += sum;
            sum = ans[i];
        }
        
        <!-- Answer -->
        for (int i=0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}

```

### Container with Most Water(https://leetcode.com/problems/container-with-most-water/)

```
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

 

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Area = height * Width

Solution:- 

Step 1 :- take two pointer i and j  where i is pointing to start of array and j is pointing to end of array

Step 2 :-Initialise a ans variable with zero and then Run a loop while i < j

  Inside loop

  Step 3:- To find the height check the minimum value between height[i] and height[j] and then to find width check the difference between index (j - i). With the found height and width get the area and then check if found area is greater then ans then update the ans.

  Step 4:- Update the i and j according to the values, The pointer which has min value will get updated.

Step 5:- return answer

Why:- We are updating values of i and j according to the minimum height , because there will always be less to get max area with minimum height

class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        
        int ans = 0;
        while (i < j) {
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        
        return ans;
    }
}

```

### Squares of a sorted array(https://leetcode.com/problems/squares-of-a-sorted-array/)

```
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 

Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.


Solution: 

Step 1 :- Create a new ans array of provided length

Step 2 :- Create two pointer i and j where i is pointing to start index and j is pointing to end index of array

Step 3 :- Initialise another index with end index of ans array

Step 4:- Run a loop while i <= j and then initialise two values value 1 as nums[i]* nums[i] and value 2 as nums[j]*nums[j]. If value1 > value2 then assign ans[index] as value1 and increase i by 1 otherwise vice versa

Step 5:- return ans

Why:- We are filling ans here in decreasing order from the last index by checking at index i and j that which value is maximum and then accordingly updating the ans array and indexes

class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        
        int i = 0;
        int j = nums.length - 1;
        
        int index = ans.length -1;
        
        while (index >= 0 && i <= j) {
            int val1 = nums[i]*nums[i];
            int val2 = nums[j]*nums[j];
            
            if (val1 > val2) {
                ans[index] = val1;
                i++;
            } else {
                ans[index] = val2;
                j--;
            }
            
            index--;
        }
        
        return ans;
    }
}
```

### Majority Element 1(https://leetcode.com/problems/majority-element/)

```
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

 

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Solution:-
We need to think it of like pairing of two unique elements. 
Let's take an example [2,2,1,1,1,2,2], Here we need to pair unique elements and then check if there is any element present in array which is a majority element.

Take two variables value and count
Update value as arr[0] and count s 1

[2,2,1,1,1,2,2]

Start a loop from index 1
since value == arr[1] increase count by 1 ,value = 2 count = 2

index 2
since value != arr[i] decrease the count by 1, value = 2 count = 1  ----> 1 pair

index 3
since value != arr[i] decrease the count by 1, value = 2 count = 0  ----> 1 pair
Since count is 0 update the value by arr[i] and count by 1, because we need to pair another values with current arr[i]
value = 1, count = 1

index 4
since value == arr[i] increase count by 1 ,value = 1 count = 2

index 5
since value != arr[i] decrease the count by 1, value = 1 count = 1 ---> 1 pair

index 6
since value != arr[i] decrease the count by 1, value = 1 count = 1 ---> 1 pair
Since count is 0 update the value by arr[i] and count by 1, because we need to pair another values with current arr[i]
value = 2, count = 1

Loop ends
And value has  inside which is left as a element to be paired . Now 2 becomes a potential candidate to be a majority element.

Last step:-
this can be checked by looping over the loop and check if 2 is present more than n/2 times. If it is not present then no majority elements exist in array

In question it is given that there will always be a majority element available in array so we dont need to perform last step.

class Solution {
    public int majorityElement(int[] nums) {
        int value = nums[0];
        int count = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == value) {
                count++;
            } else {
                count--;
            }
            
            if (count == 0) {
                value = nums[i];
                count = 1;
            }
        }
        
        return value;
    }
}

```


