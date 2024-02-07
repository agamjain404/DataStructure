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

### Majority Element || (https://leetcode.com/problems/majority-element-ii/)

```
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

 

Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]


Since we need to find the elements which are present more than 
n/3 times then we need to divide the array length by 3. If we divide any number by 3 then remainder will always be 0, 1 or 2. This means that if we pair 3 unique items in array then the count of remaining elements will always be 0, 1 or 2.

So, this time to create the pairs we can take two values and two counts

Step 1:- 
Create value1 initialised with arr[0] and count1 initialised with 1
Create value2 initialised with any value(because this will be firstly recognised and intialised while looping) and count2 initialised with 0

Step 2:-
Run a loop from i = 1 to arr.length
  Inside loop
  Step 2a:- Check if value1 is equal to arr[i]
      This means that same value encountered hence there will be increase in count1

  Step 2b:- else if check value2 is equal to arr[i]
      This means that same value encountered hence there will be increase in count2

  Step 2c:- else
      if count1 equals to zero
        this means we have an empty value1 So initialise value1 with arr[i] and count1 with 1

      else if count2 equals zero
        this means we have an empty value2 So initialise value2 with arr[i] and count2 with 1

      else
        this means we have both value1 and value2 and we are ready to pair them with encountered arr[i], So decrement 1 , 1 by count1 and count2 and pair three values

Step 3:- When loop ends we will be having 2 potential candidates to be majority element from array as value1 and value2

Step 4:- Check(By looping over the array and getting if value's count is greater then n/3) if value1 is majority element and then push it into the arraylist

Step 5:- Check if value1 not equals to value2 and then check if value2 is majority element or not and then push it into the arraylist

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int val1 = nums[0];
        int count1 = 1;
        
        int val2 = nums[0];
        int count2 = 0;
        
        for (int i = 1; i < nums.length; i++) {
           if (nums[i] == val1) {
               count1++;
           } else if (nums[i] == val2) {
               count2++;
           } else {
               if (count1 == 0) {
                   val1 = nums[i];
                   count1 = 1;
               } else if (count2 == 0) {
                   val2 = nums[i];
                   count2 = 1;
               } else {
                   count1--;
                   count2--;
               }
           }
        }
        
        List<Integer> ans = new ArrayList<>();
        
        if (isMajority(nums, val1) ) {
            ans.add(val1);    
        }
        
        if (val1 != val2 && isMajority(nums, val2)) {
            ans.add(val2);
        } 
        return ans;
    }
    
    public boolean isMajority(int[] nums, int value) {
        int count = 0;
        for (int i=0; i< nums.length; i++) {
            if (nums[i] == value) {
                count++;
            }
        }
        
        if (count > nums.length/3) {
            return true;
        }
        return false;
    }
}
```

### Majority elements General

```
Given a array fo size N and an element K
Task is to find all the elements that appears more then n/K times in array
Return all the elements in a sorted order


N = 8
ar = [3, 1, 2, 2, 1, 2, 3, 3]
k = 4
Output = [2, 3]

Solution :- Take a Hashmap put all the values as keys and there frequencies as values.At the end get all the keys whose frequencies are more then n/k in an array and then sort the array

// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 3, 2, 2, 3, 1};
        int k = 4;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for (int key: map.keySet()) {
            if (map.get(key) > arr.length/k) {
                ans.add(key);
            }
        }
        Collections.sort(ans);
        
        System.out.println(ans);
    }
}
```

### Next Greater Element 3 (https://leetcode.com/problems/next-greater-element-iii)

```
Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.

 

Example 1:

Input: n = 12
Output: 21
Example 2:

Input: n = 21
Output: -1
 

Constraints:

1 <= n <= 2^31 - 1


Solution:- Here we need to find the just greater number then the provided number who has same digits

Approach :- 
Step 1:- Convert the given number into a character array

Step 2:- Then start a loop which runs from end-1 index and checks if the current element is greater then its next element.

  like if (arr[i] >= arr[i+1])
  With this we are able to see that till when there is a increase in number. for example a number like 321, We can't have any number greater than this number who has same digits.So, in same way we will check for other numbers like till which digit there is no dip.

Step 3:- If i becomes -1 then we can't create any number greater then the given number with the same digits

Step 4:- If we encounter a dip inside numbers then we need to find which is the just greater number then the dip and then swap it. For this we can run another loop from last index of the character array and check if at any point of time arr[j] > arr[i], At the same time we need to break from the loop and swap the elements of present at ith index and jth index.

Step 5:- Then we need to reverse the array post ith index to convert the number into next greater value. This we can do by creating a empty string and adding values till the ith index and then adding remaining values in reverse order till ith index itself

Step 6:- convert the created string into long and check if the value is less then or equal to Integer Max . If yes then return the value otherwise return -1;

class Solution {
    public int nextGreaterElement(int n) {
        String str = String.valueOf(n);
        
        char[] arr = str.toCharArray();
        
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i+1]) {
            i--;
        }
        
        if ( i == -1) {
            return -1;
        }
        
        int j = arr.length -1;
        
        while (j >= 0 && arr[j] <= arr[i]) {
            j--;
        }
        
        if (j == -1) {
            return  -1;
        }
        
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
        String ans  = "";
        
        for (int index = 0; index <= i; index++) {
            ans += arr[index];
        }
        
        for (int index = arr.length - 1; index >= i+1; index--) {
            ans += arr[index];
        }
        
        
        long val = Long.parseLong(new String(ans));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;

    }
}
```

### Product of Array expect itself(https://leetcode.com/problems/product-of-array-except-self)

```
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 

Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


Solution :- 

Approach 1:- Create two arrays with leftPrefixProduct and rightSuffixProduct. Then create a answer array, then for i == 0, The rightSuffixProduct[i+1] must be the value as it is the product till index 1. And vice versa, for i == n-1 , the value must be leftPrefixProduct[i-1] as it is the product till i-1. Other than these for mid values leftPrefix[i-1] * rightSuffixProduct[i+1]

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int left[] = new int[nums.length];
        int right[] = new int[nums.length];
        
        int ans[] = new int[nums.length];
        
        int mult = 1;
        for (int i = 0; i < nums.length; i++) {
            left[i] = mult*nums[i];
            mult = left[i];
        }
        
        mult = 1;
        for (int i = right.length-1; i >=0; i--) {
            right[i] = mult*nums[i];
            mult = right[i];
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                ans[i] = right[i+1];
            } else if (i == nums.length -1) {
                ans[i] = left[i-1];
            } else {
                ans[i] = left[i-1]*right[i+1];
            }
        }
        
        return ans;
    }
}

Approach 2(Without extra space) :-

Calculate the leftPrefixArray in the answer array itself and manage rightSufixArray in a product variable. Keep updating the answer array while traversing through it

int ans[] = new int[nums.length];
        
int mult = 1;
for (int i = 0; i < nums.length; i++) {
    ans[i] = mult*nums[i];
    mult = ans[i];
}

int product = 1;
for (int i = nums.length-1; i >= 0; i--) {
    if (i == 0) {
        ans[i] = product;
    } else if (i == nums.length -1) {
        ans[i] = ans[i-1];
    } else {
        ans[i] = product*ans[i-1];
    }
    product *= nums[i];
}

return ans;

```

### Max chunks to make array sorted(https://leetcode.com/problems/max-chunks-to-make-sorted/)

```
You are given an integer array arr of length n that represents a permutation of the integers in the range [0, n - 1].

We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.

Return the largest number of chunks we can make to sort the array.

 

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
 

Constraints:

n == arr.length
1 <= n <= 10
0 <= arr[i] < n
All the elements of arr are unique.


Solution:-
First thing come into mind is you will divide the array in array.length parts. But it doesn't work because on sorting those partitions doesn't return a sorted array
Ex :- [4, 3, 2, 1, 0]
    Divide :- [4], [3], [2], [1], [0] // Can't be sorted

If it is to find minimum number of chunks then it will be always the complete array itself 

Correct approach(Chaining method):- With this method we can divide array into different chunks.
As it is given in question that array will always be filled with the values of 0 to n-1.
so for ex:- [1, 0, 2, 3, 4]

The value present at 0th index is 1 and its impact in the array will be till 1st index, Going forward the value present on 1st index is 0 and its impact in the array will also till 1st index. We can store this as chunk. 
    [1, 0]

Going forward the value present at 2nd index is 2 and its impact is also till 2. So this can be a chunk
Similarly we can find chunks which on sorting gives a sorted array.

Step 1:- Define a max variable with 0 and chunkCount with 0

Step 2:- Start looping on array keep updating the max by comparing the values with arr[i].
  At anytime i becomes equal to max then increment the chunkCount

Step 3:- Return chunkCount;

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            
            if (i == max) {
                count++;
            }
        }
        
        return count;
    }
}
```

### Max chunks to make array sorted -|| (https://leetcode.com/problems/max-chunks-to-make-sorted-ii/)

```
You are given an integer array arr.

We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.

Return the largest number of chunks we can make to sort the array.

 

Example 1:

Input: arr = [5,4,3,2,1]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
Example 2:

Input: arr = [2,1,3,4,4]
Output: 4
Explanation:
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.

Constraints:

1 <= arr.length <= 2000
0 <= arr[i] <= 108


Solution :- So, Here also we can apply our chaining process again but with a slight change in it. 

Here we need to create a leftMax array and a right min array

For ex:- 
  arr = [30, 10, 20, 40, 60, 50, 75, 70]

Now here we need to divide this array in max chunks that it gets sorted.
Create a leftMAx arrray 

  leftMax = [30, 30, 30, 40, 60, 60, 75, 75]

Create a rightMin array(Add a +Infinite for last element)

  rightMin = [10, 10, 20, 40, 50, 50, 70, 70, +Infinite]

Now start traversing with leftMax array And check if 
  leftMax <= rightMin[i+1]  ---> count++;

return count;

Why this is working :- In this approach we are checking the impact of max value inside a chunk that till which index it exists. Also covering the case of equal values. Means that ones the max value of chunk become lesser or equal to its rightMin then we should seperate the chunk. Equal values are also seperated as per the question given.

Code:- We can manage leftMax in a variable and rightMin as a array

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int lmax = Integer.MIN_VALUE;
        int count = 0;
        
        int rMin[] = new int[arr.length+1];
        
        int min = Integer.MAX_VALUE;
        rMin[rMin.length-1] = min;
        for (int i = rMin.length-2; i>=0; i--) {
            min = Math.min(min, arr[i]);
            rMin[i] = min;
        }
        
        for (int i = 0; i<arr.length; i++) {
            lmax = Math.max(lmax, arr[i]);
            if (lmax <= rMin[i+1]) {
                count++;
            }
        }
        
        return count;
    }
}
```

### Partition Labels(https://leetcode.com/problems/partition-labels/)

```
You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.


Solution :- Create a Hashmap with keys as string character and values as last occuring index. Then initialise last_index with zero  in starting and update it accordingly. So start looping through the string and then update the last_index according to str[i]. Then loop till i != last_index and any of the character doesn't update last_index as there last_index are lesser then the previous character. Ones i becomes equal to last_index update the ans arraylist and prev. Now prev, is firstly initialised with -1, then it is being to used to keep track of the string length which can be partitioned. That's why when i becomes equal to last_index then update the ans by adding last_index - prev in array and updating the prev with current last_index.

class Solution {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = s.length()-1; i >= 0; i--) {
            if (map.containsKey(s.charAt(i))) {
                continue;
            } else {
                map.put(s.charAt(i), i);
            }
        }
        
        
        int last_index = 0;
        int prev = -1;
        String str = "";
        List<Integer> ans = new ArrayList<>();
        
        for (int i = 0; i <s.length(); i++) {
            if (last_index < map.get(s.charAt(i))) {
                last_index = map.get(s.charAt(i));
            }
            if (i == last_index) {
                ans.add(last_index - prev);
                prev = last_index;
            }
        }
        
        return ans;
    }
}

```

### Partition Array into Disjoint Intervals(https://leetcode.com/problems/partition-array-into-disjoint-intervals/)

```
Given an integer array nums, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.

Test cases are generated such that partitioning exists.

 

Example 1:

Input: nums = [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: nums = [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
 

Constraints:

2 <= nums.length <= 105
0 <= nums[i] <= 106
There is at least one valid answer for the given input.


Solution:- Using same approach as Max chunks to make array sorted -|| of chaining  method over here

For example:- 
[10, 6, 9, 3, 12, 15, 11, 13]

Create one leftMax which gives you the idea about the index till which impact of maximum value exists
leftMax : [10, 10, 10, 10, 12, 15, 15, 15]

Create one rightMin array which gives you the idea about the index till which impact of minimum value exists
rightMin : [3, 3, 3, 3, 11, 11, 11, 13, +Infinite]

Now start comparing both the arrays, by starting a loop on leftmax 

i = 0
leftMax[i] = 10
Compare leftMax[i] <= rightMin[i+1]; ---> This condition checks if impact of current leftMax is remaining or not on the given index. 
If condition satifies then we got the answer and by storing index we can break from loop

At the end we can return index + 1

class Solution {
    public int partitionDisjoint(int[] nums) {
        
        int n = nums.length;
        
        int[] rightMin = new int[n +1];
        rightMin[n] = Integer.MAX_VALUE;
        
        for (int i = n-1; i >= 0; i--) {
            rightMin[i] = Math.min(nums[i], rightMin[i+1]);
        }
        
        int lMax = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            lMax = Math.max(lMax, nums[i]);
            
            if (lMax <= rightMin[i+1]) {
                ans = i;
                break;
            }
        }
        
        return ans+1;
    }
}

Optimised approach with O(1) space complexity:

Approach:- Maintain two variables as greater and leftMax and find the partition

for example :- 
[7, 3, 9, 5, 10, 1, 15, 16, 19, 14, 30]

Now here if we see a sorted array of this then we will get to know about the index on which impact of each element exists.

[1, 3, 5, 7, 9, 10, 14, 15, 16, 19, 30]

Now create a leftmax intialiasing it with arr[0], and a greater variable initialising it with arr[0] and answer variable initialising it with 0

In every iteration check if leftMax < arr[i] else if greater > arr[i]

Why? By checking leftMax > arr[i], we can update the leftMax and also the answer because at this point the impact of the particular leftMAx ends and in this way we can get our partition. If this condition doesn't matches then we need to check greater < arr[i] and here we can update our greater value, So, that it can be used as leftMax in future because there can be chances that a value present less than the current leftMax

for i =0;
arr[i] = 7
since leftmax is not > arr[i] and greater is not < arr[i] continue

or i= 1;
arr[i] = 3
since leftMax is > arr[i] and greater is not < arr[i] so we can add it in our answer and get our first partition and also update leftMax to greater

i = 2;
arr[i] = 9
since leftMax is not > arr[i]  but greater is < arr[i] so, we can update the greater as 9

i = 3
arr[i] = 5
Now here we encounter the condition that leftMax > arr[i]. This means that 5 also wants to get included in the left array itself. Now we update our second partition and also leftMax as greater. Because of this scenario we need greater in this approach.

And so on...

int n = nums.length;
int leftMax = nums[0];
int greater = nums[0];
int ans = 0;


for (int i = 0; i < nums.length; i++) {
  if (nums[i] > greater) {
    greater = nums[i];
  } else if (nums[i] < leftMax) {
    ans = i;
    leftMax = greater;
  }
}

return ans + 1;

```

### Find the number of jumps to reach X in the number line from zero(https://www.geeksforgeeks.org/find-the-number-of-jumps-to-reach-x-in-the-number-line-from-zero/)

```
Given an integer X. The task is to find the number of jumps to reach a point X in the number line starting from zero. 
Note: The first jump made can be of length one unit and each successive jump will be exactly one unit longer than the previous jump in length. It is allowed to go either left or right in each jump. 
Examples: 
 

Input : X = 8
Output : 4
Explanation : 
0 -> -1 -> 1 -> 4-> 8 are possible stages.

Input : X = 9
Output : 5
Explanation : 
0 -> -1 -> -3 -> 0 -> 4-> 9 are 
possible stages


Approach :- 
Make the jumps as minimum as possible. 
Start jumping from 0 at number line and then increment jump gradually one by one until you reach the input pr reach ahead the input. Ones you reach ahead the input there will be chances that some jumps can be done at left side so that you can reach back to the input

For example :- 8
0 -> +1(+1) -> +3(+2) -> +6(+4) -> +10 

Now we are at the point where it is ahead then the given input
So, we need to take few steps to the left
So, what we can do is we can subtract the input from achieved sum. This is needed to be done to know how much steps we need to take left side. 

Post subtraction if value is even then we get our jumps

if value is odd then add one more jump and again subtract the input from achieved sum
Post substraction if value is even then we get our jumps

if value is again odd then add one more jump and we get our answer.


int X = 25;

int jump = 0;
while(getSum(jump) < X) {
    jump++;
}

if (getSum(jump) == X) {
    System.out.println(jump);
    System.exit(0);
}

if (getSum(jump) > X) {
    if ((getSum(jump) - X)%2 == 0) {
        System.out.println(jump);
        System.exit(0);
    } else {
        if ((getSum(jump+1) - X)%2 == 0) {
            System.out.println(jump+1);
            System.exit(0);
        } else {
            System.out.println(jump+2);
        }
    }
}

```

### Maximum Product of Three Numbers(https://leetcode.com/problems/maximum-product-of-three-numbers/)

```
Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

 

Example 1:

Input: nums = [1,2,3]
Output: 6
Example 2:

Input: nums = [1,2,3,4]
Output: 24
Example 3:

Input: nums = [-1,-2,-3]
Output: -6
 

Constraints:

3 <= nums.length <= 104
-1000 <= nums[i] <= 1000


Solution:
The most simple approach will be finding first three maximum values in array and then multiplying them. But this will not work when array contains negative element. So, to handle this case we need to find two minimum elements as firstMin and secondMin of array. We need to find two elements only because the product of three negative nubers will always be negative. So, there can be chances that first 2 mins can be negative and high.

Example : [1, 2, 3, 4, 5, 6, -7, -8]

Here, 
max1 = 6        min1 = -7
max2 = 5        min2 = -8
max3 = 4

Ans = Math.max(max1*max2*max3, min1*min2*max1);

To find these values can traverse the array and find all
All values initialised with Integer min and Integer max accordingly

value = nums[i]

if value >= max1
  max3 = max2
  max2 = max1
  max1 = value
else if value >= max2
  max3 = max2
  max2 = value
else if value >= max3
  max3 = value

if value <= min1
  min2 = min1
  min1 = value
else if value <= min2
  min2 = value

return Math.max(max1*max2*max3, min1*min2*max1);
```

### Sort array by Parity (https://leetcode.com/problems/sort-array-by-parity/)

```
Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.

 

Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
Example 2:

Input: nums = [0]
Output: [0]
 

Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000

Solution :- 

Take two pointers i =0  and j as arr.length - 1
Start a loop i < j
if numbers at both indexes are odd then decrement j

if numbers at both indexes are even then increment i

if number at ith index is odd and jth index is even
Then swap the number and increment i and decrement j

if number at ith index is event and jth index is odd
then increment i and decrement j


class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int[] ans = new int[nums.length];
        
        ans = Arrays.copyOf(nums, nums.length);
        
        
        int i = 0;
        int j = nums.length-1;
        
        while (i <= j) {
            if (ans[i] % 2 != 0 && ans[j] % 2 != 0) {
                j--;
            } else if (ans[i] % 2 == 0 && ans[j] % 2 == 0) {
                i++;
            } else if (ans[j] % 2 == 0 && ans[i] % 2 != 0) {
                int temp = ans[j];
                ans[j] = ans[i];
                ans[i] = temp;
            } else {
                i++;
                j--;
            }
        }
        
        return ans;
    }
} 
```

### Best Meeting Point(https://www.geeksforgeeks.org/best-meeting-point-2d-binary-array/)

```
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in a group. And the group of two or more people wants to meet and minimize the total travel distance. They can meet anywhere means that there might be a home or not.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x – p1.x| + |p2.y – p1.y|.
Find the total distance that needs to be traveled to reach the best meeting point (Total distance traveled is minimum).
Examples: 

Input : grid[][] = {{1, 0, 0, 0, 1}, 
                   {0, 0, 0, 0, 0},
                   {0, 0, 1, 0, 0}};
Output : 6
Best meeting point is (0, 2).
Total distance traveled is 2 + 2 + 2 = 6

Input : grid[3][5] = {{1, 0, 1, 0, 1},
                     {0, 1, 0, 0, 0}, 
                     {0, 1, 1, 0, 0}};
Output : 11


Solution:
The best meeting point can be found by finding a distance from median. Median can be found in sorted arrays only. We can fill the x-coordinates and y-coordinates in sorted order just by traversing the array accordingly.

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{1, 0, 1, 0, 1},
                     {0, 1, 0, 0, 0}, 
                     {0, 1, 1, 0, 0}};
        
        List<Integer> xArr = new ArrayList<>();
        List<Integer> yArr = new ArrayList<>();
        
        <!-- Finding x coordinates to get median of x -->
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    xArr.add(i);
                }
            }
        }
        
        <!-- Finding y coordinates to get median of y -->
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i] == 1) {
                    yArr.add(i);
                }
            }
        }
        
        <!-- Find the median of x and y(Both of the medians can be found for even and odd arrays in same way) -->
        int medianX = xArr.get((xArr.size())/2);
        int medianY = yArr.get((yArr.size())/2);
        int ans = 0;

        <!-- Find the ans by finding distance from both the medians -->
        for (int i = 0; i < xArr.size(); i++) {
            ans += Math.abs(xArr.get(i) - medianX);
        }
        
        for (int i = 0; i < yArr.size(); i++) {
            ans += Math.abs(yArr.get(i) - medianY);
        }
        
        System.out.println(ans);
    }
}
```

### Sieve of Eratosthenes(https://leetcode.com/problems/count-primes/)

```
Given an integer n, return the number of prime numbers that are strictly less than n.

 

Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example 2:

Input: n = 0
Output: 0
Example 3:

Input: n = 1
Output: 0

Solution:
Sieve of eratosthenes is a very popular algorithm which says that
we should precompute the prime numbers and then we can provide the
answer in very less time. 
So here we created one boolean array of length n+1 to calculate
which all numbers are prime. Intially the array is intialised
with true boolean value
Then we can start a loop from i = 2 to till square root of n and check which all numbers are intialised  as true

Example :
For i = 2,
if (isPrime[i] == true) // Yes it is true since complete array is initialised as true
that means , 2 is considered as prime and all factors of 2 will be marked as false, Since they are not prime

For i = 3,
isPrime[i] is equals to true
So, marking all the factors of 3 as non prime till n

For i = 4,
isPrime[i] ois equals to false since it is factor of 2. when we mark the factor of 2 as non prime. It will automatically mark the factors of 4 as non prime

... and vice versa

And then we can calculate the count of prime numbers which are less than n

class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n+1];
        
        Arrays.fill(isPrime, true);
        
        for (int i = 2; i*i <= isPrime.length; i++) {
            if (isPrime[i] == true) {
                for (int j = i+i; j < isPrime.length; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
``` 

### Transpose of a matrix M*N (https://leetcode.com/problems/transpose-matrix/)

```
Given a 2D integer array matrix, return the transpose of matrix.

The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]
Example 2:

Input: matrix = [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]


Solution:
Create a 2d array of N * M.
Fill it with oppposite indexes 
Like at (0, 1) position we need to fill the element present at (1, 0) position


class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] res = new int[n][m];
        
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = matrix[j][i];
            }
        }
        
        return res;
    }
}

```

### Transpose of a Matrix(N*N)(inplace)

```
Here we need to find transpose of a matrix who has same number 
of rows and columns. This can be easy as we need not to traverse 
the complete matrix to achieve this.

[
  [11, 12, 13],
  [21, 22, 23],
  [31, 32, 33]
]

Transpose would be

[
  [11, 21, 31],
  [12, 22, 32],
  [13, 23, 33]
]


So if we observe the diagonal elements are same and elements present
after diagonal are swapped with the elements present before diagonal

So, we can maintain the loop till diagonal and can swap the elements
with their opposite indexes

Psuedocode:
  for (int i =0; i < n; i++) {
    for (int j = i; j < n; j++) {
      swap matrix[i][j] with matrix[j][i]
    }
  }

At the end, the new matrix generated will be a transpose matrix

```

### Shortest unsorted continuous subarray(https://leetcode.com/problems/shortest-unsorted-continuous-subarray/)

```
Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.

Return the shortest such subarray and output its length.

 

Example 1:

Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Example 2:

Input: nums = [1,2,3,4]
Output: 0
Example 3:

Input: nums = [1]
Output: 0
 

Constraints:

1 <= nums.length <= 104
-105 <= nums[i] <= 105

Solution:
we need to find a number which is smaller then any element 
present to its left

For ex:-[2,6,4,8,10,9,15], Here 9 is the first element which is 
smaller then the 10 present at its left. So, what we can do we can maintain a max variable from starting and keep checking that if there is any value which is smaller then the elements present in left

We need to find a number which is greater then any element present
to its right.

For ex:-[2,6,4,8,10,9,15], Here 6 is the first element which is
greater then the 4 present at its right. So, what we can do is we can maintain min variable from ending of array and keep checking that
if there is any value which is greater then the elements present in right

The difference between indexes of both these numbers + 1 will give us the length of shortest unsorted continuous subarray


class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int end = -1;
        int max = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (max > nums[i]) {
                end = i;
            } else {
                max = nums[i];
            }
        }
        
        int start = 0;
        int min = nums[nums.length-1];
        for (int i = nums.length-2; i >= 0; i--) {
            if (min < nums[i]) {
                start = i;
            } else {
                min = nums[i];
            }
        }
        
        return end - start + 1;
    }
}

```

### Rotate a matrix by 90 degree (https://leetcode.com/problems/rotate-image/submissions/)

```
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]


Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

Solution :-

Find transpose of a matrix .

[
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9]
]

Transpose :- 
[
  [1, 4, 7],
  [2, 5, 8],
  [3, 6, 9]
]

Then reverse every row of the matrix:

[
  [7, 4, 1],
  [8, 5, 2],
  [9, 6, 3]
]


class Solution {
    public void rotate(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j =  i;  j < arr[0].length; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        
        for (int i = 0; i < arr.length; i++) {
            int start = 0;
            int end = arr[0].length-1;
            
            while (start < end) {
                int temp = arr[i][start];
                arr[i][start] = arr[i][end];
                arr[i][end] = temp;
                start++;
                end--;
            }
        }
    }
}
```

### Reverse vowels of a string(https://leetcode.com/problems/reverse-vowels-of-a-string/)

```
Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

 

Example 1:

Input: s = "hello"
Output: "holle"
Example 2:

Input: s = "leetcode"
Output: "leotcede"
 

Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.

Solution:
Convert the given string into character array

And then traverse with two pointer approach recognising if there
is vowels then swap the left and right pointers. 

class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        
        int start = 0;
        int end = arr.length-1;
        
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('e', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('u', 1);
        map.put('A', 1);
        map.put('E', 1);
        map.put('I', 1);
        map.put('O', 1);
        map.put('U', 1);
        
        while (start < end) {
            if (map.containsKey(arr[start]) && map.containsKey(arr[end])) {
                char temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            } else if(map.containsKey(arr[start])) {
                end--;
            } else if(map.containsKey(arr[end])) {
                start++;
            } else {
                start++;
                end--;
            }
        }
        
        return new String(arr);
    }
}
```

### Wiggle Sort(https://www.codingninjas.com/studio/problems/wiggle-sort_3155169?leftPanelTabValue=PROBLEM&count=25&page=1&search=&sort_entity=order&sort_order=ASC&attempt_status=COMPLETED)

```
Problem statement
You are given an array ‘ARR’ containing ‘N’ integers, you need to sort the array such that a wiggle sequence is formed. A wiggle sequence satisfies the following property: ARR[0] ≤ ARR[1] ≥ ARR[2] ≤ ARR[3] ≥ ARR[4] ≤ ARR[5] …..

If there are multiple answers, you may print any of them.

Follow up :
Can you try to solve this problem in O(N) time without using extra space?
Custom Input :
Kindly use print statements to debug the code and print array.
Example :
If ‘N’ = 5 and ‘ARR’ = { 1, 2, 3, 4, 5 }

Then rearranging the input array to { 1, 4, 2, 5, 3 } create a wiggle sequence.

Other rearrangements like { 2, 4, 3, 5, 1 }, { 3, 5, 1, 4, 2} are also considered correct.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 ≤ T ≤ 10      
1 ≤ N ≤ 5000
-10^9 ≤ ARR[i] ≤ 10^9

Time limit: 1 sec
Sample Input 1 :
2
5
1 2 3 4 5
4
1 3 2 2 
Sample Output 1 :
1 4 2 5 3
1 3 2 2
Explanation For Sample Input 1 :
For test case 1 :
We will print {1, 4, 2, 5, 3} as it is a valid rearrangement of the input array and is a wiggle sequence.

For test case 2 : 
The input array is itself a wiggle sequence, so we can directly return it.
Sample Input 2 :
2
5
1 1 1 1 1
2
1 2 
Sample Output 2 :
1 1 1 1 1
1 2


Solution :
Sort a array in a wave traversal

arr[0] <= arr[1] >= arr[2] <= arr[3] >= arr[4]

Here uf we observe that every even index is smaller element and
every odd index is a bigger element. So, what we can do is we can
traverse the array and check if we are on even index or odd index.

if we are on even index we can check if next element is smaller. If 
it is then we need to swap it with current index

If we are on odd index we can check if next element is greater. If it is then we need to swap it with current index.

[1, 2, 3, 4, 5]

for i = 0
since 1 is <=2 we will continue

for i = 1
since 2 is <= 3 we will swap it. Now, since 2 is less then 3 and is greater then 1 already then it is obvious that 3 will be greater then 1. That's how this algo is managing the previous elements

import java.util.* ;
import java.io.*; 
public class Solution {
	public static int[] wiggleSort(int n, int[] arr) {
		// Write your code here.

		for (int i = 0; i < arr.length-1; i++) {
			if (i % 2 == 0) {
				if (arr[i+1] <= arr[i]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			} else {
				if (arr[i+1] >= arr[i]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
		}

		return arr;
	}
}
```

### Wiggle Sort 2(https://leetcode.com/problems/wiggle-sort/)

```
Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

You may assume the input array always has a valid answer.

 

Example 1:

Input: nums = [1,5,1,1,6,4]
Output: [1,6,1,5,1,4]
Explanation: [1,4,1,5,1,6] is also accepted.
Example 2:

Input: nums = [1,3,2,2,3,1]
Output: [2,3,1,3,1,2]

Solution :- 

Create a new array of n size.
Copy all the elements of input array into new array

Then, Sort the newly created array.

Put a pointer at last of sorted array,
And then start filling the odd indexes of the input array by max 
value elements. 

Ones filled take yout pointer to the even indexes of the input array
and fill them by min value elements

Example :- 
input array - [1, 5, 1, 1, 6, 4]
sorted array - [1, 1, 1, 4, 5, 6]

Now putting j pointer ar the last of sorted array
Start a loop while j >= 0

Then start with i=1 and while i <  nums.length and j >= 0
Fill inputArray[i] = sortedArray[j] with decreasing j by 1 and increasing i by 2

Ones filled we can move to even indexes and start with i = 0 without changing the position of j, as j will be at the maximum value of the minimum value numbers

Start i = 0
while i < nums.length and j >= 0
Fill inputArray[i] = sortedArray[j] with decreasing j by 1 and increasing i by 2.

class Solution {
    public void wiggleSort(int[] nums) {
        int[] ans = new int[nums.length];
        
        ans = nums.clone();
        
        Arrays.sort(ans);
        
        int j = ans.length -1;
        while (j >= 0) {
            int i = 1;
            while (i < nums.length && j >= 0) {
                nums[i] = ans[j];
                j--;
                i+=2;
            }
            
            i = 0;
            while (i < nums.length && j >= 0) {
                nums[i] = ans[j];
                j--;
                i+=2;
            }
            
            j--;
        }
        
    }
}
```

### Add Strings(https://leetcode.com/problems/add-strings/)

```
Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.

 

Example 1:

Input: num1 = "11", num2 = "123"
Output: "134"
Example 2:

Input: num1 = "456", num2 = "77"
Output: "533"
Example 3:

Input: num1 = "0", num2 = "0"
Output: "0"

Solution :- 
Put two pointers i and j at the end of both inputs, Start traversing till the length of both inputs become zero and also the value of carry
should be 0. 

So, example 
num1 = "11"
num2 = "123"

i = 1 and j = 2

We need to calculate iVal = "1" - "0" = 1 and jVal = "3" - "0" = 3
So sum is iVal + jVal + carry
Then carry comes out to be division of sum by 10
And Res should be module of sum by 10

class Solution {
    public String addStrings(String num1, String num2) {
        String res = "";
        
        int i = num1.length()-1;
        int j = num2.length()-1;
        int carry = 0;
        
        while(i >= 0 || j >= 0 || carry != 0) {
            int iVal = 0;
            int jVal = 0;
            int sum = 0;

            
            if (i >= 0) iVal = num1.charAt(i) - '0';
            if (j >= 0) jVal = num2.charAt(j) - '0';
            sum += iVal + jVal + carry;
            carry = sum/10;
            res = (sum%10) + res;
            i--;
            j--;
        }
        
        return res;
    }
}
```


