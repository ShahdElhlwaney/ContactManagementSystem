package ContactManagementSystem;

public class Contact {
    private String id;
    private String name;
    private String email;
    private String phone;

    public Contact(String name, String email, String phone,String id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return  this.email;
    }
    public String getPhone(){
        return this.phone;
    }

}
