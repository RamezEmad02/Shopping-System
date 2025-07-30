package ShoppingSystem;

public abstract class User {
    private String name;
    private int ID;
    private String phone;
    private String email;
    private int age;
    private static int numberOfUsers;
    private String address;
    private static User [] userArray = new User[0];

    public User(String name, String phone, String email, int age, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if(age<18){
            throw new IllegalAgeException();
        }
        else {
            this.age = age;
        }
        this.address = address;
        numberOfUsers ++;
        ID = numberOfUsers; //create unique ID
        addUserToArray();
    }

    private void addUserToArray(){
        User [] tempArr = new User[userArray.length + 1];
        for(int i=0; i<userArray.length; i++){
            tempArr[i] = userArray[i];
        }
        tempArr[userArray.length] = this;
        userArray = tempArr;
    }

    public static User findUser(String name, int ID){       //parameters sent from GUI
        for(int i=0; i<userArray.length; i++){
            if( (userArray[i].ID == ID) && (userArray[i].name.equals(name)) ){
                return userArray[i];
            }
        }
        throw new UserNotFoundException();
        //return null;    ////Exception handling
    }
    public static User[] getUserArray() {
        return userArray;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public static int getNumberOfUsers() {
        return numberOfUsers;
    }

    public String getAddress() {
        return address;
    }
}
