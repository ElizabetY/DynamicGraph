public class Queue {
    private QueueNode _first;
    private QueueNode _last;

    public Queue() {
        _first = null;
        _last = null;
    }

    public boolean is_empty() {
        return _first==null;
    }

    public void enq(QueueNode node) {
        if (is_empty()) {
            _first = node;
            _last = node;
        } else {
            _last.next = node;
            _last = node;
        }
    }

    // assume non-empty queue
    public QueueNode deq() {
        QueueNode node = _first;
        _first = node.next;
        return node;
    }
}
