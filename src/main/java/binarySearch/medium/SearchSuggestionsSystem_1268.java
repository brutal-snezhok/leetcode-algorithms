package binarySearch.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/search-suggestions-system/description/
public class SearchSuggestionsSystem_1268 {
    // TODO: add other approaches for this task

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // Approach1: two pointers algorithm
        // time O(s*nlogn + n + m), s - avg length of string
        // space O(n), for sorting

        Arrays.sort(products);
        int l = 0;
        int r = products.length - 1;
        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i < searchWord.length(); i++) {
            char ch = searchWord.charAt(i);

            // products[l].length() <= i - does this word products[l] has an i character
            while(l <= r && (products[l].length() <= i || products[l].charAt(i) != ch))
                l++;

            while(l <= r && (products[r].length() <= i || products[r].charAt(i) != ch))
                r--;

            res.add(new ArrayList<>());
            int remain = r - l + 1;

            for(int j = 0; j < Math.min(3, remain); j++)
                res.get(res.size() - 1).add(products[l + j]);
        }

        return res;
    }
}
