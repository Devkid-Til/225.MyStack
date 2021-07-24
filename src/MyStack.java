class MyStack {

    private LoopQueue<Integer> data;
    private int tailValue;
    /** Initialize your data structure here. */
    public MyStack() {
        data = new LoopQueue<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        data.enqueue(x);
        tailValue = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Empty stack.");
        }
        int sz = data.getSize();
        int tp;
        for (int i = 0; i < sz - 1; i ++) {
            tp = data.dequeue();
            if (i == sz - 2) {
                tailValue = tp;
            }
            data.enqueue(tp);
        }
        return data.dequeue();
    }

    /** Get the top element. */
    public int top() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Empty stack.");
        }
        return tailValue;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return data.isEmpty();

    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack: size = %d Capacity: %d\n", data.getSize(), data.getCapacity()));
        res.append("[");
        int sz = data.getSize();
        int tp;
        for (int i = 0; i < sz; i ++) {
            tp = data.dequeue();
            res.append(tp);
            data.enqueue(tp);
            if (i < sz - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
    public static void main(String[] args) {
        MyStack obj = new MyStack();
        for (int i = 0; i < 10; i ++) {
            obj.push(i);
            System.out.println(obj);
        }
        System.out.println();
        for (int i = 0; i < 5; i ++) {
            System.out.println("pop:" + obj.pop());
            System.out.println(obj);
        }
        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();

        System.out.println(obj);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
