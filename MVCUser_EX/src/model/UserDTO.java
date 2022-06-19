package model;

public class UserDTO {
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	
	
	public UserDTO(){}
	public UserDTO(String id){
		this.id=new String(id);
	}
	public UserDTO(UserDTO u){
		UserDTO temp=new UserDTO();
		temp.setId(id);
	}
	
	public boolean equals(Object o) {
		if(o instanceof UserDTO) {
			return this.id.equals(((UserDTO)o).id);
		}
		return false;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
