package controller;

import java.util.ArrayList;
import model.UserDTO;

public class UserController {
	private ArrayList<UserDTO> uList;
	
	public UserController(){
		uList=new ArrayList<>();
	}
	public ArrayList<UserDTO> selectAll(){
		return uList;
	}
	public UserDTO selectOne(String id) {
		return uList.get(uList.indexOf(new UserDTO(id)));
	}
	public void insert(UserDTO u) {
		uList.add(u);
	}
	public void delete(String id) {
		uList.remove(selectOne(id));
	}
	public void update(UserDTO u) {
		uList.set(uList.indexOf(u), u);
	}
}
