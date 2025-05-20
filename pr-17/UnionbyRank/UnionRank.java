package UnionbyRank;

public class UnionRank {
    private int[] parent;
	private int[] rank;

	public UnionRank(int size) {
    	parent = new int[size];
    	rank = new int[size];
    	for (int i = 0; i < size; i++) {
        	parent[i] = i;  // Setiap elemen adalah root awalnya
        	rank[i] = 0;	// Rank awal = 0
    	}
	}

	public int find(int x) {
    	if (parent[x] != x) {
        	return find(parent[x]); // Rekursif
            	}
    	return parent[x];
	}

    public void union(int x, int y) {
    	int rootX = find(x);
    	int rootY = find(y);

    	if (rootX == rootY) return; // Sudah dalam set yang sama

    	// Gabungkan pohon yang lebih pendek ke pohon yang lebih tinggi
    	if (rank[rootX] > rank[rootY]) {
        	parent[rootY] = rootX;
    	} else if (rank[rootX] < rank[rootY]) {
        	parent[rootX] = rootY;
    	} else {
        	// Jika rank sama, pilih salah satu dan increment rank
        	parent[rootY] = rootX;
        	rank[rootX]++;
    	}
	}
}

