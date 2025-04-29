import java.util.*;
public class AVLBST {
public static void main(String[] args) {
int iterations = 5;
int dataSize = 1000;
int searchSize = 100;
long totalInsertTime = 0;
long totalSearchTime = 0;
for (int i = 0; i < iterations; i++) {
TreeMap<Integer, String> treeMap = new TreeMap<>();
List<Integer> data = generateRandomList(dataSize);
List<Integer> searchData = getRandomSubset(data,

searchSize);

// Measure insert time
long insertStart = System.nanoTime();
for (int val : data) {
treeMap.put(val, "Value-" + val);
}
long insertEnd = System.nanoTime();
totalInsertTime += (insertEnd - insertStart);
// Measure search time
long searchStart = System.nanoTime();
for (int key : searchData) {
treeMap.get(key);
}
long searchEnd = System.nanoTime();
totalSearchTime += (searchEnd - searchStart);
}
System.out.println("Rata-rata waktu insert (ns): " +
(totalInsertTime / iterations));
System.out.println("Rata-rata waktu search (ns): " +
(totalSearchTime / iterations));
}
public static List<Integer> generateRandomList(int n) {
List<Integer> list = new ArrayList<>();
for (int i = 1; i <= n; i++) {
list.add(i);
}
Collections.shuffle(list);
return list;
}
public static List<Integer> getRandomSubset(List<Integer>
list, int size) {
Collections.shuffle(list);
return list.subList(0, Math.min(size, list.size()));
}}