package managementsystem;

// generic abstract user

public abstract class User {
	private String name;
	//private String username;
	//private String password;
	private int id;
	
	public User (String nome, /*String username, String password,*/ int id) {
		this.name = nome;
//		this.username = username;
//		this.password = password;
		this.id = id;
	}
	
    public int getId() {
		return id;
	}
    
    public String getNome() {
    	return name;
    }

}
