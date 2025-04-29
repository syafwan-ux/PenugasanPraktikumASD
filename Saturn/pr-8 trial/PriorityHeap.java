import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Airplane {
    int insertionOrder; 

    String airlineName;
    String flightNumber;
    String destination;
    int priority;

    public Airplane(String airlineName, String flightNumber, String destination, int priority, int insertionOrder) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.priority = priority;
        this.insertionOrder = insertionOrder; 
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "Maskapai='" + airlineName + '\'' +
                ", nomorPenerbangan='" + flightNumber + '\'' +
                ", kotaTujuan='" + destination + '\'' +
                ", priority=" + priority +
                '}';
    }
}

class MaxHeap {
    private Airplane[] heap;
    private int size;

    public MaxHeap(int capacity) {
        heap = new Airplane[capacity];
        size = 0;
    }

    public void insert(Airplane airplane) { 
        airplane.insertionOrder = size; 
        if (size >= heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = airplane;
        size++;
        heapifyUp(size - 1);
    }

    public Airplane removeMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        Airplane max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size; 
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].priority > heap[parentIndex].priority || 
                (heap[index].priority == heap[parentIndex].priority && heap[index].insertionOrder < heap[parentIndex].insertionOrder)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largestIndex = index;

            if (leftChildIndex < size && (heap[leftChildIndex].priority > heap[largestIndex].priority || 
                (heap[leftChildIndex].priority == heap[largestIndex].priority && heap[leftChildIndex].insertionOrder < heap[largestIndex].insertionOrder))) {
                largestIndex = leftChildIndex;
            }
            if (rightChildIndex < size && (heap[rightChildIndex].priority > heap[largestIndex].priority || 
                (heap[rightChildIndex].priority == heap[largestIndex].priority && heap[rightChildIndex].insertionOrder < heap[largestIndex].insertionOrder))) {
                largestIndex = rightChildIndex;
            }
            if (largestIndex != index) {
                swap(index, largestIndex);
                index = largestIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int index1, int index2) {
        Airplane temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}

public class PriorityHeap {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(20);

        try (BufferedReader br = new BufferedReader(new FileReader("dataPesawat.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String airlineName = data[0].trim();
                String flightNumber = data[1].trim();
                String destination = data[2].trim();
                int priority = Integer.parseInt(data[3].trim()); // Mengambil prioritas dari file, prioritas diberikan secara randoms
                int insertionOrder = maxHeap.getSize(); 

                Airplane airplane = new Airplane(airlineName, flightNumber, destination, priority, insertionOrder);
                maxHeap.insert(airplane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Pesawat yang take off berdasarkan prioritas:");
        while (!maxHeap.isEmpty()) {
            Airplane airplane = maxHeap.removeMax();
            System.out.println(airplane);
        }
    }
}

