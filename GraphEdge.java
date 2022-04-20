public class GraphEdge {

    GraphNode _firstNode;
    GraphNode _secondNode;
    private GraphEdge _prev_in;
    GraphEdge _next_in;
    private GraphEdge _prev_out;
    GraphEdge _next_out;


    public GraphEdge(GraphNode firstNode, GraphNode secondNode){
        this._firstNode = firstNode;
        this._secondNode = secondNode;

        _prev_in = null;
        _next_in = _secondNode._first_edge_in;
        if (_next_in!=null) {
            _next_in._prev_in = this;
        }
        _secondNode._first_edge_in = this;
        _secondNode.add_deg_in(1);

        _prev_out = null;
        _next_out = _firstNode._first_edge_out;
        if (_next_out!=null) {
            _next_out._prev_out = this;
        }
        _firstNode._first_edge_out = this;
        _firstNode.add_deg_out(1);
    }

    public void removeMe() {
        if (_prev_in!=null) {
            _prev_in._next_in = _next_in;
        } else {
            _secondNode._first_edge_in = _next_in;
        }
        if (_next_in!=null) {
            _next_in._prev_in = _prev_in;
        }
        _secondNode.add_deg_in(-1);

        if (_prev_out!=null) {
            _prev_out._next_out = _next_out;
        } else {
            _firstNode._first_edge_out = _next_out;
        }
        if (_next_out!=null) {
            _next_out._prev_out = _prev_out;
        }
        _firstNode.add_deg_out(-1);
    }


}
