public class test {
    MinHeap* insert_minheap(MinHeap* heap, int element) {
        // Inserts an element to the min heap
        // We first add it to the bottom (last level)
        // of the tree, and keep swapping with it's parent
        // if it is lesser than it. We keep doing that until
        // we reach the root node. So, we will have inserted the
        // element in it's proper position to preserve the min heap property
        if (heap->size == heap->capacity) {
            fprintf(stderr, "Cannot insert %d. Heap is already full!\n", element);
            return heap;
        }
        // We can add it. Increase the size and add it to the end
        heap->size++;
        heap->arr[heap->size - 1] = element;
    
        // Keep swapping until we reach the root
        int curr = heap->size - 1;
        // As long as you aren't in the root node, and while the 
        // parent of the last element is greater than it
        while (curr > 0 && heap->arr[parent(curr)] > heap->arr[curr]) {
            // Swap
            int temp = heap->arr[parent(curr)];
            heap->arr[parent(curr)] = heap->arr[curr];
            heap->arr[curr] = temp;
            // Update the current index of element
            curr = parent(curr);
        }
        return heap; 
    }
}
