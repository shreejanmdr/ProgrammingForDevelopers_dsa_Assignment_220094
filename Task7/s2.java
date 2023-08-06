package Task7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class s2 extends JFrame {
    private GraphCanvas canvas;
    private JToolBar toolbar;
    private JButton selectModeButton;
    private JButton addNodeButton;
    private JButton addEdgeButton;
    private JTextField searchField;

    private Map<String, Node> nodes;
    private ArrayList<Edge> edges;
    private Node selectedNode;

    public s2() {
        setTitle("Social Network Graph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvas = new GraphCanvas();
        add(canvas, BorderLayout.CENTER);

        toolbar = new JToolBar();
        selectModeButton = new JButton("Select Mode");
        addNodeButton = new JButton("Add Node");
        addEdgeButton = new JButton("Add Edge");
        searchField = new JTextField(20);

        toolbar.add(selectModeButton);
        toolbar.add(addNodeButton);
        toolbar.add(addEdgeButton);
        toolbar.add(new JLabel("Search:"));
        toolbar.add(searchField);

        add(toolbar, BorderLayout.NORTH);

        pack();
        setVisible(true);

        nodes = new HashMap<>();
        edges = new ArrayList<>();

        // Read user data from a file and create nodes and edges accordingly

        // Example data
        Node node1 = new Node("User1", "Profile1.jpg", 100, 100, 100);
        Node node2 = new Node("User2", "Profile2.jpg", 200, 300, 200);
        Node node3 = new Node("User3", "Profile3.jpg", 300, 500, 300);
        nodes.put(node1.getName(), node1);
        nodes.put(node2.getName(), node2);
        nodes.put(node3.getName(), node3);

        Edge edge1 = new Edge(node1, node2, 50);
        Edge edge2 = new Edge(node1, node3, 70);
        edges.add(edge1);
        edges.add(edge2);

        canvas.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(s2::new);
    }

    private class GraphCanvas extends JPanel {
        public GraphCanvas() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (Node node : nodes.values()) {
                        if (node.contains(e.getPoint())) {
                            if (selectedNode != null) {
                                selectedNode.setSelected(false);
                            }
                            node.setSelected(true);
                            selectedNode = node;
                            repaint();
                            break;
                        }
                    }
                }
            });

            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Edge edge : edges) {
                edge.draw(g);
            }

            for (Node node : nodes.values()) {
                node.draw(g);
            }
        }
    }

    private class Node {
        private String name;
        private String profilePicture;
        private int followers;
        private int x;
        private int y;
        private boolean selected;

        public Node(String name, String profilePicture, int followers, int x, int y) {
            this.name = name;
            this.profilePicture = profilePicture;
            this.followers = followers;
            this.x = x;
            this.y = y;
            this.selected = false;
        }

        public String getName() {
            return name;
        }

        public void draw(Graphics g) {
            g.setColor(Color.BLUE);
            if (selected) {
                g.setColor(Color.RED);
            }
            g.fillOval(x, y, 50, 50);

            // Draw profile picture and other visual elements
            // Use the name, profile picture, and followers to display node information
            // Highlight the node if it is selected
        }

        public boolean contains(Point point) {
            // Check if the given point is within the boundaries of the node shape
            return new Rectangle(x, y, 50, 50).contains(point);
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    private class Edge {
        private Node node1;
        private Node node2;
        private int strength;

        public Edge(Node node1, Node node2, int strength) {
            this.node1 = node1;
            this.node2 = node2;
            this.strength = strength;
        }

        public void draw(Graphics g) {
            g.setColor(Color.GRAY);
            g.drawLine(node1.x + 25, node1.y + 25, node2.x + 25, node2.y + 25);
            g.drawString(String.valueOf(strength), (node1.x + node2.x) / 2, (node1.y + node2.y) / 2);
        }
    }
}
