package scheduled_visits;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class visits extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table_1;
    private JTextField txtVisitor;
    private JTextField txtInmate;

    public void table_load() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jail_management_system", "root", "");
            Statement st = con.createStatement();
            String query = "SELECT svd.reason, v.fname AS visitor_fname, v.lname AS visitor_lname, p.fname AS prisoner_fname, p.lname AS prisoner_lname, vt.visit_date FROM schedule_visit_date svd INNER JOIN visitors v ON svd.visitor_id = v.visitor_id INNER JOIN prisoners p ON svd.prisoner_id = p.prisoner_id INNER JOIN visit_date vt ON svd.visit_id = vt.visit_id";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();

            model.setRowCount(0);

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++)
                colName[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colName);

            while (rs.next()) {
                String reason = rs.getString("reason");
                String visitor_fname = rs.getString("visitor_fname");
                String visitor_lname = rs.getString("visitor_lname");
                String prisoner_fname = rs.getString("prisoner_fname");
                String prisoner_lname = rs.getString("prisoner_lname");
                String visit_date = rs.getString("visit_date");

                String[] row = {reason, visitor_fname, visitor_lname, prisoner_fname, prisoner_lname, visit_date};
                model.addRow(row);
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    visits frame = new visits();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public visits() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1119, 788);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(134, 152, 915, 209);
        contentPane.add(scrollPane);

        table_1 = new JTable();
        scrollPane.setViewportView(table_1);

        JButton btnNewButton = new JButton("Retrieve Scheduled visits");
        btnNewButton.addActionListener(e -> {
            if (e.getSource() == btnNewButton) {
                table_load();
            }
        });
        btnNewButton.setBounds(134, 51, 132, 45);
        contentPane.add(btnNewButton);
        
        txtVisitor = new JTextField();
        txtVisitor.setText("VISITOR");
        txtVisitor.setBounds(286, 129, 306, 20);
        contentPane.add(txtVisitor);
        txtVisitor.setColumns(10);
        
        txtInmate = new JTextField();
        txtInmate.setText("INMATE");
        txtInmate.setColumns(10);
        txtInmate.setBounds(595, 129, 300, 20);
        contentPane.add(txtInmate);
    }
}
