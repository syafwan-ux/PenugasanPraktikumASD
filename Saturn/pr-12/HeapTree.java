public class HeapTree {
	private int[] heap;
	private int size;
	private int capacity;
	private boolean isMaxHeap; // true:max-heap, false: min-heap

	public HeapTree(int capacity, boolean isMaxHeap) {
    	this.capacity = capacity;
    	this.size = 0;
    	this.heap = new int[capacity];
    	this.isMaxHeap = isMaxHeap;
    }

	private int parent(int i) { return (i-1)/2; }
	private int leftChild(int i) { return 2*i + 1; }
	private int rightChild(int i) { return 2*i + 2; }

	private void swap(int i, int j) {
    	int temp = heap[i];
    	heap[i] = heap[j];
    	heap[j] = temp;
	}

	private boolean compare(int a, int b) {
    	return isMaxHeap ? a > b : a < b;
	}

	public void insert(int key) {
    	if (size == capacity) {
        	System.out.println("Heap is full!");
        	return;
    	}
    	heap[size] = key;
    	int current = size;
    	size++;

    	while (current != 0 && compare(heap[current], heap[parent(current)])) {
        	swap(current, parent(current));
        	current = parent(current);
    	}
	}

	public int extractRoot() {
    	if (size <= 0) {
        	System.out.println("Heap is empty!");
        	return Integer.MIN_VALUE;
    	}
    	if (size == 1) {
        	size--;
        	return heap[0];
    	}

    	int root = heap[0];
    	heap[0] = heap[size - 1];
    	size--;
    	heapify(0);

    	return root;
	}

	private void heapify(int i) {
    	int left = leftChild(i);
    	int right = rightChild(i);
    	int extreme = i;

    	if (left < size && compare(heap[left], heap[extreme])) {
        	extreme = left;
    	}
    	if (right < size && compare(heap[right], heap[extreme])) {
        	extreme = right;
    	}
    	if (extreme != i) {
        	swap(i, extreme);
        	heapify(extreme);
    	}
	}

	public void printHeap() {
    	for (int i = 0; i < size; i++) {
        	System.out.print(heap[i] + " ");
    	}
    	System.out.println();
	}

	public int getSize() {
    	return size;
	}

	public int getRoot() {
    	if (size > 0) {
        	return heap[0];
    	}
    	return Integer.MIN_VALUE;
	}

	public void replaceRoot(int val) {
    	if (size > 0) {
        	heap[0] = val;
        	heapify(0);
    	}
	}

	public static void main(String[] args) {
    	// Buat Max-Heap
    	HeapTree maxHeap = new HeapTree(15, true);
    	int[] maxHeapElements = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
    	for (int elem : maxHeapElements) {
        	maxHeap.insert(elem);
    	}
    	System.out.print("Max-Heap: ");
    	maxHeap.printHeap();

    	// Buat Min-Heap
    	HeapTree minHeap = new HeapTree(15, false);
    	int[] minHeapElements = {4, 50, 7, 55, 90, 87, 2};
    	for (int elem : minHeapElements) {
        	minHeap.insert(elem);
    	}
    	System.out.print("Min-Heap: ");
    	minHeap.printHeap();
	}
}
