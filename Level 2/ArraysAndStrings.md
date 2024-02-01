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

