import java.util.*;
class GFG{  // class name 

    static int []H = new int[50];
    static int size = -1;
    
    static int parent(int i) {  // parent of i 
        return i = (i-1)/2;
    }
    static int leftChild (int i) {  // left child of i
        return 2*i + 1;
    }

    static int rightChild(int i) {  // right child of i
        return 2*i + 2;
    }

    static void shiftUp(int i) {
        while (i > 0 && H[parent(i)] < H[i]) {
            int temp = H[parent(i)];
            H[parent(i)] = H[i];
            H[i] = temp;
            i = parent(i);
        }
    }
}