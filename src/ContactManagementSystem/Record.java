package ContactManagementSystem;
import java.util.ArrayList;
public class Record {
    private ArrayList<Contact> contacts;
    public Record() {
        contacts = new ArrayList<>();
    }
    public void addContact(Contact contact){
        contacts.add(contact);
    }
    public  void removeContact(String id){
        contacts.removeIf(contact->contact.getId().equals(id) );
    }
    public  void modifyContact(int index, Contact newContact){
        Contact contact=contacts.get(index);
        contact.setName(newContact.getName());
        contact.setEmail(newContact.getEmail());
        contact.setPhone(newContact.getPhone());

    }
    public Contact searchByName(String name){
        for (Contact contact:contacts){
            if (contact.getName().equals(name)){
                return  contact;
            }
        }
        return  null;
    }
    public  ArrayList<Contact> getAllContacts(){
        return contacts;
    }
    public  String[][] getData(){
        String[][] data=new String[contacts.size()][3];
        for (int i=0; i<contacts.size();i++)
        {
            Contact contact =contacts.get(i);
            data[i][0]=contact.getName();
            data[i][1]=contact.getEmail();
            data[i][2]=contact.getPhone();

        }
        return data;

    }
    public  void sort(){
        contacts.sort((c1,c2)->c1.getName().compareToIgnoreCase(c2.getName()));
    }
}
