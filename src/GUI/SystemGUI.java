package GUI;
import ContactManagementSystem.Contact;
import ContactManagementSystem.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.util.UUID;

public  class  SystemGUI {
    private  JFrame frame;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private DefaultTableModel tableModel;
    private final Record contactsRecord=new Record();
     public SystemGUI(){
         init();
    }
    private void init(){
        createFrame();
        createLabel("My Contacts",25);
        createFormPanel();
        createTableContacts();
    }
    private void createFrame(){

        frame= new JFrame("Contact Management System");
        setStyleFrame(1500,1500,Color.PINK);
        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private JLabel createLabel(String l,int fontSize){
        JLabel label=new JLabel(l,JLabel.CENTER);
        this.setLabelStyle(label,fontSize);

        this.frame.add(label, BorderLayout.NORTH);
        return label;
    }
    private void createFormPanel(){
        JPanel panel = new JPanel();
        setFormPanelStyle(panel);

        JPanel nameGroup= createNameComponent();
        JPanel emailGroup=createEmailComponent();
        JPanel phoneGroup=createPhoneComponent();

        AddPanelComponents(panel, nameGroup, emailGroup, phoneGroup);

        createButtons(panel);


        JPanel bottomContainer = createBottomContainer();
        bottomContainer.add(panel);

        frame.add(bottomContainer, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    private void createTableContacts(){
        String[] columnNames = {"Name", "Email", "Phone"};
        JPanel tableContainer = getTableContainer(columnNames);

        frame.add(tableContainer, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

     private JPanel getTableContainer( String[] columnNames) {
         tableModel=new DefaultTableModel(new String[0][3],columnNames);
         JTable table = new JTable(tableModel);
         table.setRowHeight(30);
         table.setFillsViewportHeight(true);
         JScrollPane scrollPane = new JScrollPane(table);
         Dimension tableSize = new Dimension(900, 1400);
         scrollPane.setPreferredSize(tableSize);
         scrollPane.setMinimumSize(tableSize);
         JPanel tableContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

         tableContainer.setOpaque(false);
         tableContainer.setBorder(
                 BorderFactory.createEmptyBorder(150, 10, 10, 25)
         );
         tableContainer.add(scrollPane);
         return tableContainer;
     }


     private void createButtons(JPanel panel) {
         JButton addButton = new JButton("Add");
         JButton clearButton = new JButton("Clear");
         JPanel buttonsGroup=new JPanel();

         buttonsGroup.add(addButton);
         buttonsGroup.add(clearButton);
         panel.add(Box.createVerticalStrut(25));

         panel.add(buttonsGroup);
         handleAddBtn(addButton);
         handleClearBtn(clearButton);
     }
     private  void handleAddBtn(JButton addBtn){
         addBtn.addActionListener(
                 c->{
                     String name = nameField.getText();
                     String email = emailField.getText();
                     String phone=phoneField.getText();
                     if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty())
                     {
                         String uniqueId = UUID.randomUUID().toString();
                         Contact contact =new Contact(name,email,phone,uniqueId);
                         contactsRecord.addContact(contact);
                         tableModel.addRow(new String[]{name,email,phone});


                     }
                 }
         );
     }
     private void handleClearBtn(JButton clearBtn){
         clearBtn.addActionListener(
                 c->{
                     nameField.setText("");
                     emailField.setText("");
                     phoneField.setText("");

                 }
         );
     }
     private static JPanel createBottomContainer() {
         JPanel bottomContainer = new JPanel(
                 new FlowLayout(FlowLayout.LEFT, 10, 10)
         );
         bottomContainer.setPreferredSize(new Dimension(200, 400));

         bottomContainer.setOpaque(false);
         return bottomContainer;
     }

     private static void setFormPanelStyle(JPanel panel) {
         panel.setPreferredSize(new Dimension(350, 300));
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         panel.setBorder(
                 BorderFactory.createEmptyBorder(10, 10, 10, 10)
         );
     }

     private static void AddPanelComponents(JPanel panel, JPanel nameGroup, JPanel emailGroup, JPanel phoneGroup) {
         panel.add(nameGroup);
         panel.add(Box.createVerticalStrut(10));

         panel.add(emailGroup);

         panel.add(Box.createVerticalStrut(10));

         panel.add(phoneGroup);
     }

     private void setStyleFrame(int width,int height,Color color){
        frame.setSize(width, height);
        frame.getContentPane().setBackground(color);

    }
    private static void setLabelStyle(JLabel label,int fontSize){
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
    }
    private JPanel createNameComponent(){
        JLabel nameLabel=new JLabel("Name:");
        nameField = new JTextField();

        return getjPanel(nameLabel, nameField);
    }
    private  JPanel createEmailComponent(){
        JLabel emailLabel=new JLabel("Email:");

        emailField = new JTextField();
        return getjPanel(emailLabel, emailField);
    }
    private JPanel createPhoneComponent(){
        JLabel phoneLabel=new JLabel("Phone:");
        phoneField = new JTextField();
        return getjPanel(phoneLabel,phoneField);
    }
     private JPanel getjPanel(JLabel label, JTextField field) {
         setTextFieldSize(field,400,30);

         JPanel fieldGroup = new JPanel();
         fieldGroup.setLayout(new BoxLayout(fieldGroup, BoxLayout.Y_AXIS));
         fieldGroup.setOpaque(false);

         fieldGroup.setAlignmentX(Component.CENTER_ALIGNMENT);
         label.setAlignmentX(Component.LEFT_ALIGNMENT);
         field.setAlignmentX(Component.LEFT_ALIGNMENT);

         fieldGroup.add(label);
         fieldGroup.add(Box.createVerticalStrut(5));
         fieldGroup.add(field);
         return  fieldGroup;
     }

     private void setTextFieldSize(JTextField textField,int width,int height){
        textField.setPreferredSize(new Dimension(width, height));
        textField.setMaximumSize(new Dimension(width, height));
    }
}
