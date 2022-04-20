public class QueueNode {
    QueueNode next;
    Tree_node tree_node;
    GraphNode graphNode;

    public QueueNode(Tree_node tree_node, GraphNode graphNode) {
        this.tree_node = tree_node;
        this.graphNode = graphNode;
        next = null;
    }

    public QueueNode(Tree_node tree_node) {
        this.tree_node = tree_node;
        this.graphNode = null;
        next = null;
    }
}
