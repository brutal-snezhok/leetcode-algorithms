package binarySearch.medium;

import java.util.*;

// https://leetcode.com/problems/time-based-key-value-store/
public class TimeBasedKeyValueStore_981 {
    Map<String, List<Pair>> timeMap;

    public TimeBasedKeyValueStore_981() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!timeMap.containsKey(key))
            timeMap.put(key, new ArrayList<>());

        timeMap.get(key).add(new Pair(value, timestamp));
    }

    //  time O(logn), n - number of pair by key
    public String get(String key, int timestamp) {
        if(!timeMap.containsKey(key))
            return "";

        List<Pair> pairs = timeMap.get(key);
        int l = 0;
        int r = pairs.size() - 1;
        Pair res = new Pair("", Integer.MIN_VALUE);
        while(l <= r) {
            int mid = l + (r - l) / 2;

            Pair curr = pairs.get(mid);
            if(curr.timestamp > timestamp)
                r = mid - 1;
            else {
                if(curr.timestamp > res.timestamp)
                    res = curr;
                l = mid + 1;
            }
        }

        return res.val;
    }

    class Pair {
        String val;
        int timestamp;

        public Pair(String val, int timestamp) {
            this.val = val;
            this.timestamp = timestamp;
        }
    }

    // solution2
    // using TreeMap
    Map<String, TreeMap<Integer, String>> map; // {key, {timestamp, val}}

//    public TimeMap() {
//        map = new HashMap<>();
//    }

    public void set2(String key, String value, int timestamp) {
        if(!map.containsKey(key))
            map.put(key, new TreeMap<>());

        map.get(key).put(timestamp, value);
    }

    public String get2(String key, int timestamp) {
        if(!map.containsKey(key))
            return "";

        TreeMap<Integer, String> timeMap = map.get(key);
        Integer floorKey = timeMap.floorKey(timestamp);
        if(floorKey == null)
            return "";

        return timeMap.get(floorKey);
    }
}
