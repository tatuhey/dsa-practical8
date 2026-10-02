public class DSAHeap{
    private int count;
    private int capacity;
    private DSAHeapEntry[] heap;

    //region constructor
    public DSAHeap(int size) {
        capacity = size;
        heap = new DSAHeapEntry[capacity];
        count = 0;
    }
    //endregion

    //taken from https://www.geeksforgeeks.org/dsa/binary-heap/
    public void add(int prio, Object val) {
        if(count == capacity)
            throw new IllegalStateException ("Heap is full.");

        heap[count] = new DSAHeapEntry(prio, val);

        trickleUp(count);
        count++;
    }

    public DSAHeapEntry remove() {
        if(count == 0)
            throw new IllegalStateException("Heap is empty.");

        DSAHeapEntry toRemove = heap[0];

        count--;

        heap[0] = heap[count];
        heap[count] = null;

        if(count > 0)
            trickleDown(0, count);
        
        return toRemove;
    }

    public void display() {
        for(int i = 0; i < count; i++)
            System.out.println(heap[i].toString());
    }

    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    private int leftChild(int idx) {
        return idx * 2 + 1;
    }

    private int rightChild(int idx) {
        return idx * 2 + 2;
    }
    
    private void swap(DSAHeapEntry[] arr, int a, int b){
        DSAHeapEntry temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void trickleUp(int idx) { // ITERATIVE
        int parentIdx = parent(idx);
       
        while(idx > 0 && heap[idx].getPriority() > heap[parentIdx].getPriority()) {
            swap(heap, parentIdx, idx);
            idx = parentIdx;
            parentIdx = parent(idx);
        }
    }


    private void trickleDown(int idx, int c) { // ITERATIVE
        int leftCIdx = leftChild(idx);
        int rightCIdx = rightChild(idx);
        boolean keepGoing = true;

        while(keepGoing && leftCIdx < c) {
            keepGoing = false;
            int largeIdx = leftCIdx;
            
            if(rightCIdx < count) {
                if(heap[leftCIdx].getPriority() < heap[rightCIdx].getPriority()) {
                    largeIdx = rightCIdx;
                }
            }
            if(heap[largeIdx].getPriority() > heap[idx].getPriority()) {
                swap(heap, largeIdx, idx);
                keepGoing = true;
            }
            idx = largeIdx;
            leftCIdx = leftChild(idx);
            rightCIdx = rightChild(idx);
        }
    }

    private void heapify(int count) {
        int calc = (count / 2) - 1;
        for(int i = calc; i >= 0; i--){
            trickleDown(i, count);
        }
    }

    public void heapSort(int count) {
        heapify(count);
        for(int i = count-1; i >= 0; i--){
            swap(heap, 0, i);
            trickleDown(0, i);
        }
    }
}