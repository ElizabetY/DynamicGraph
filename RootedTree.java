import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    private Tree_node _root;

    public RootedTree(){
        this._root = null;
    }

    public Tree_node set_root(int key) {
        _root = new Tree_node(key);
        return _root;
    }

    private void printNum(DataOutputStream out, int n) throws IOException {
        out.write(Integer.toString(n).getBytes());
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (_root==null) return;
        Queue q = new Queue();
        q.enq(new QueueNode(_root));
        while (!q.is_empty()) {
            Queue next_level = new Queue();
            while (!q.is_empty()) {
                Tree_node node = q.deq().tree_node;
                printNum(out,node.get_key());
                if (!q.is_empty()) {
                    out.write(',');
                }
                for (Tree_node child = node.get_child();child!=null;child = child.get_brother()) {
                    next_level.enq(new QueueNode(child));
                }
            }
            q = next_level;
            if (!q.is_empty()) {
                out.write(System.lineSeparator().getBytes());
            }
        }
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        if (_root!=null) {
            preorderPrint(out,_root);
        }
    }

    private void preorderPrint(DataOutputStream out, Tree_node node) throws IOException {
        printNum(out,node.get_key());
        if (node.get_child()!=null) {
            out.write(',');
            preorderPrint(out,node.get_child());
        }
        if (node.get_brother()!=null) {
            out.write(',');
            preorderPrint(out,node.get_brother());
        }
    }
}
