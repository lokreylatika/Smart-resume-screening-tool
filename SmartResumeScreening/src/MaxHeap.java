public class MaxHeap {

    private Candidate[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {

        this.capacity = capacity;
        this.size = 0;

        heap = new Candidate[capacity];
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {

        Candidate temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(Candidate candidate) {

        if (size == capacity) {

            System.out.println(
                    "Heap is full");

            return;
        }

        heap[size] = candidate;

        int current = size;

        size++;

        while (current > 0 &&
                heap[current].getScore() >
                heap[parent(current)].getScore()) {

            swap(current,
                    parent(current));

            current =
                    parent(current);
        }
    }

    public Candidate extractMax() {

        if (size == 0) {

            return null;
        }

        Candidate max =
                heap[0];

        heap[0] =
                heap[size - 1];

        size--;

        heapify(0);

        return max;
    }

    private void heapify(int index) {

        int largest = index;

        int left =
                leftChild(index);

        int right =
                rightChild(index);

        if (left < size &&
                heap[left].getScore() >
                heap[largest].getScore()) {

            largest = left;
        }

        if (right < size &&
                heap[right].getScore() >
                heap[largest].getScore()) {

            largest = right;
        }

        if (largest != index) {

            swap(index,
                    largest);

            heapify(largest);
        }
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int getSize() {

        return size;
    }
}