package jjt;

import java.awt.*;

/**
 * Created by Azard on 2014/12/5.
 */
public class GuiNode{

    protected SimpleNode root;
    public GuiNode(SimpleNode n) {
        root = n;
    }
//    public void jjtOpen() {
//    }
//
//    public void jjtClose() {
//    }
//
//    public void jjtSetParent(Node n) { parent = n; }
//    public Node jjtGetParent() { return parent; }
//
//    public void jjtAddChild(Node n, int i) {
//        if (children == null) {
//            children = new Node[i + 1];
//        } else if (i >= children.length) {
//            Node c[] = new Node[i + 1];
//            System.arraycopy(children, 0, c, 0, children.length);
//            children = c;
//        }
//        children[i] = n;
//    }
//
//    public Node jjtGetChild(int i) {
//        return children[i];
//    }
//
//    public int jjtGetNumChildren() {
//        return (children == null) ? 0 : children.length;
//    }
//
//    public void jjtSetValue(Object value) { this.value = value; }
//    public Object jjtGetValue() { return value; }
//
//    public Token jjtGetFirstToken() { return firstToken; }
//    public void jjtSetFirstToken(Token token) { this.firstToken = token; }
//    public Token jjtGetLastToken() { return lastToken; }
//    public void jjtSetLastToken(Token token) { this.lastToken = token; }
//
//  /* You can override these two methods in subclasses of SimpleNode to
//     customize the way the node appears when the tree is dumped.  If
//     your output uses more than one line you should override
//     toString(String), otherwise overriding toString() is probably all
//     you need to do. */
//
//    public String toString() { return MyLangTreeTreeConstants.jjtNodeName[id]; }
//    public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */
    public void dump(String prefix){
        root.dump(prefix);
    }
    public void dumptoarea(String prefix,boolean end,TextArea area) {
        //System.out.println(toString(prefix));
        area.append(root.toString(prefix + "|-->")+"\n");
        if (root.children != null) {
            if(end) {
                for (int i = 0; i < root.children.length; ++i) {
                    GuiNode n = new GuiNode((SimpleNode) root.children[i]);
                    if (n.root != null) {
                        if (i == root.children.length - 1) {
                            String temp = "";
                            for (int j = 0; j < prefix.length(); j++) {
                                temp += ' ';
                            }
                            //prefix = temp;
                            n.dumptoarea(prefix + "    ", true, area);
                        } else {
                            n.dumptoarea(prefix + "    ", false, area);
                        }
                    }
                }
            }
            else{
                for (int i = 0; i < root.children.length; ++i) {
                    GuiNode n = new GuiNode((SimpleNode) root.children[i]);
                    if (n.root != null) {
                        if (i == root.children.length - 1) {
                            String temp = "";
                            for (int j = 0; j < prefix.length(); j++) {
                                temp += ' ';
                            }
                            //prefix = temp;
                            n.dumptoarea(prefix + "|   ", true, area);
                        } else {
                            n.dumptoarea(prefix + "|   ", false, area);
                        }
                    }
                }
            }
            //prefix=prefix.substring(4);
        }
    }
}
