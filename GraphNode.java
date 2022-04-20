
public class GraphNode {
    private int _key;
    private int _deg_in;
    private int _deg_out;
     GraphNode _next;
     GraphNode _prev;
     GraphEdge _first_edge_in;
     GraphEdge _first_edge_out;
    boolean visited;


    public GraphNode(int key){
        this._key = key; // primary key
        this._deg_in = 0; // degree in
        this._deg_out = 0;
        this._next = null;
        this._prev = null;
        this._first_edge_in = null;
        this._first_edge_out = null;
    }

    public int getKey() {return _key;}

    public int getInDegree() {return _deg_in;}

    public int getOutDegree() {return _deg_out;}

    public void add_deg_in(int toAdd) {this._deg_in += toAdd;}

    public void add_deg_out(int toAdd) {this._deg_out += toAdd;}

}