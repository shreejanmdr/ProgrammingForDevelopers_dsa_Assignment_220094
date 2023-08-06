/*
Task 7
    Assignment Title: Social Network Graph
    Task: Create a GUI application that allows users to visualize a social network graph.
Scenario: You have been hired by a social media company to create a tool that visualizes the connections between
users. The company wants to see how users are connected and which users have the most influence over others.

Requirements:
1. The application should have a window with a canvas where the graph will be drawn.
2. The nodes of the graph should represent users, and the edges should represent connections between users.
3. The application should read the user data from a file and create the graph accordingly.
4. Each node should display the user's name, profile picture, and the number of followers they have.
5. The edges should display the strength of the connection between the users, such as the number of likes,
    comments, or shares between them.
6. The user should be able to select and move nodes around the canvas.
7. The user should be able to delete nodes and edges by selecting them and pressing the delete key.
8. The application should have a toolbar with buttons for selecting mode, adding nodes, and adding edges.
9. The application should allow the user to search for a user and highlight their node and connections.
    
Grading Criteria:
1. The application should meet all the requirements mentioned above.
2. The user interface should be intuitive and easy to use.
3. The application should be bug-free and stable.
4. The application should be well-documented and commented.
5. Bonus points will be given for additional features, such as algorithms to find the most influential users or
    to calculate the shortest path between two users.

Submission:
Submit the source code of the application along with a short report describing the features and functionalities of
the application. The report should also include any known bugs or issues and suggestions for future improvements.
The code should be well-organized and properly commented.

[30 Marks] 
*/

package Task7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SocialNetworkGraphApp extends JFrame {
    private GraphCanvas canvas;
    private JToolBar toolbar;
    private JButton selectModeButton;
    private JButton addNodeButton;
    private JButton addEdgeButton;
    private JTextField searchField;

    public SocialNetworkGraphApp() {
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SocialNetworkGraphApp::new);
    }

    private class GraphCanvas extends JPanel {
        private ArrayList<Node> nodes;
        private ArrayList<Edge> edges;
        private Node selectedNode;

        public GraphCanvas() {
            nodes = new ArrayList<>();
            edges = new ArrayList<>();

            // Read user data from a file and create nodes and edges accordingly

            // Example data
            Node node1 = new Node("User1", "Profile1.jpg", 100);
            Node node2 = new Node("User2", "Profile2.jpg", 200);
            Node node3 = new Node("User3", "Profile3.jpg", 300);
            nodes.add(node1);
            nodes.add(node2);
            nodes.add(node3);

            Edge edge1 = new Edge(node1, node2, 50);
            Edge edge2 = new Edge(node1, node3, 70);
            edges.add(edge1);
            edges.add(edge2);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (Node node : nodes) {
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

            for (Node node : nodes) {
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

        public Node(String name, String profilePicture, int followers) {
            this.name = name;
            this.profilePicture = profilePicture;
            this.followers = followers;
            this.x = 0;
            this.y = 0;
            this.selected = false;
        }

        public void draw(Graphics g) {
            // Draw node shape and other visual elements
            // Use the name, profile picture, and followers to display node information
            // Highlight the node if it is selected
        }

        public boolean contains(Point point) {
            // Check if the given point is within the boundaries of the node shape
            return false;
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
            // Draw edge line and display the strength of the connection
        }
    }
}
