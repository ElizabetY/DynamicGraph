public class DynamicGraph {
    private GraphNode _first_node;

    private void reset_visited() {
        for (GraphNode node=_first_node;node!=null;node=node._next) {
            node.visited = false;
        }
    }

    public DynamicGraph() {
        _first_node = null;
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode node = new GraphNode(nodeKey);
        node._next = _first_node;
        if (_first_node!=null) _first_node._prev = node;
        _first_node = node;
        return node;
    }

    public void deleteNode(GraphNode node) {
        if (node.getInDegree()>0 || node.getOutDegree()>0) {
            return;
        }
        if (node._prev!=null) {
            node._prev._next = node._next;
        } else {
            _first_node = node._next;
        }
        if (node._next!=null) {
            node._next._prev = node._prev;
        }
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        return new GraphEdge(from,to);
    }

    public void deleteEdge(GraphEdge edge) {
        edge.removeMe();
    }

    public RootedTree bfs(GraphNode source) {
        reset_visited();
        Queue q = new Queue();
        RootedTree tree = new RootedTree();
        Tree_node tree_node = tree.set_root(source.getKey());
        source.visited = true;
        q.enq(new QueueNode(tree_node,source));
        while (!q.is_empty()) {
            QueueNode queueNode = q.deq();
            GraphNode node = queueNode.graphNode;
            for (GraphEdge edge = node._first_edge_out; edge!=null; edge = edge._next_out) {
                GraphNode child = edge._secondNode;
                if (child.visited) continue;
                tree_node = queueNode.tree_node.add_last_child(child.getKey());
                child.visited = true;
                q.enq(new QueueNode(tree_node,child));
            }
        }
        return tree;
    }

    private GraphNode[] DFSorder() {
        int n=0;
        for (GraphNode node = _first_node; node!=null; node = node._next) n++;
        GraphNode[] order = new GraphNode[n];
        reset_visited();

        int index = n-1;
        for (GraphNode node = _first_node; node!=null; node = node._next) {
            if (node.visited) continue;
            index = DFS(node,order,index);
        }
        return order;
    }

    private int DFS(GraphNode node, GraphNode[] order, int index) {
        node.visited = true;
        for (GraphEdge edge = node._first_edge_out; edge!=null; edge = edge._next_out) {
            GraphNode child = edge._secondNode;
            if (child.visited) continue;
            index = DFS(child, order, index);
        }
        order[index] = node;
        return index-1;
    }

    public RootedTree scc() {
        GraphNode[] order = DFSorder();
        RootedTree tree = new RootedTree();
        Tree_node root = tree.set_root(0);
        reset_visited();
        for (GraphNode node : order) {
            if (node.visited) continue;
            DFSinverse(root,node);
        }
        return tree;
    }

    private void DFSinverse(Tree_node parent, GraphNode node) {
        Tree_node tree_node = parent.add_last_child(node.getKey());
        node.visited = true;
        for (GraphEdge edge = node._first_edge_in; edge!=null; edge = edge._next_in) {
            GraphNode child = edge._firstNode;
            if (child.visited) continue;
            DFSinverse(tree_node,child);
        }
    }
}
