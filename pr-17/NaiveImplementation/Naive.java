

public class Naive {
    private int[] parent;

    public Naive(int size) {
    	parent = new int[size];
    	// Setiap elemen adalah induknya sendiri awalnya
    	for (int i = 0; i < size; i++) {
        	parent[i] = i;
    	}
	}
    
	// Operasi find tanpa optimasi
	public int find(int x) {
    	if (parent[x] == x) {
        	return x;
    	}
    	return find(parent[x]);
	}
    
	// Operasi union tanpa optimasi
	public void union(int x, int y) {
    	int rootX = find(x);
    	int rootY = find(y);
    	if (rootX != rootY) {
        	parent[rootY] = rootX;
    	}
	}
}

