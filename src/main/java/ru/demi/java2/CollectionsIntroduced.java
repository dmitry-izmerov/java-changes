package ru.demi.java2;

import java.util.*;

public class CollectionsIntroduced {

    // Collections framework was introduced.
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("third", "first", "second"));
        Collections.sort(list);
        System.out.println("sorted list: " + list);

        Set<Integer> set = new HashSet<>(Arrays.asList(5, 5, 2, 9));
        System.out.println("max of the set of numbers: " + Collections.max(set));

        Map<String, Integer> map = new HashMap<>();
        map.put("counter1", 0);
        map.put("counter2", 0);
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(map);
    }
}
