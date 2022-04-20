public class Tree_node {
    private Tree_node _parent;
    private Tree_node _child;
    private Tree_node _brother;
    private Tree_node _lastChild;
    private int _key;

    public Tree_node(int key){
        this._key = key;
        _parent = null;
        _child = null;
        _brother = null;
        _lastChild = null;
    }

    public int get_key() {return _key;}
    public Tree_node get_brother() {return _brother;}
    public Tree_node get_child() {return _child;}
    public Tree_node get_parent() {return _parent;}

    public Tree_node add_last_child(int key) {
        Tree_node node = new Tree_node(key);
        node._parent = this;
        if (_child==null) {
            _child = node;
            _lastChild = node;
        } else {
            _lastChild._brother = node;
            _lastChild = node;
        }
        return node;
    }


}
