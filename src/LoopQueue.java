public class LoopQueue<E> implements Queue {

    private E[] data;
    private int front;
    private int tail;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail =0;
    }
    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }
    @Override
    public int getSize() {
//        if (front < tail) {
//            return tail - front;
//        } else {
//            return getCapacity() - front + tail;
//        }
        // 老师实现
        return tail >= front ? tail - front : tail - front + data.length;
    }
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(Object e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = (E) e;
        tail = (tail + 1) % data.length;
        

    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from a empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot get front from a empty queue.");
        }
        return data[front];
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length){
            res.append(data[i]);
            //老师写法：if((i + front + 1) % data.length != tail) ：最后一个元素
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < getSize(); i ++) {
            newData[i] = data[(i + front) % data.length];
        }
        tail = getSize();
        front = 0;
        data = newData;
    }
    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue2 = new LoopQueue<>();
        for (int i = 0; i < 20; i++) {
            loopQueue2.enqueue(i);
            System.out.println(loopQueue2.toString());

//            if (i % 3 == 2) {
//                loopQueue1.dequeue();
//                System.out.println(loopQueue1.toString());
//            }
        }
        for (int i = 0; i < 17; i++){
            loopQueue2.dequeue();
            System.out.println(loopQueue2.toString());
        }
        for (int i = 0; i < 20; i++) {
            loopQueue2.enqueue(i);
            System.out.println(loopQueue2.toString());

//            if (i % 3 == 2) {
//                loopQueue1.dequeue();
//                System.out.println(loopQueue1.toString());
//            }
        }

    }

}
