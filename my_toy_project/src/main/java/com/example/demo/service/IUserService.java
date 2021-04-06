package com.example.demo.service;


import com.example.demo.dto.BasicResponse;
import com.example.demo.dto.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
	public ResponseEntity<BasicResponse<User>> insertUser(User user);
	//public ResponseEntity<BasicResponse> getEmpNum(int empNum);
	//public ResponseEntity<BasicResponse> getUserLogin(UserLoginRequest userLogin);
	//public ResponseEntity<BasicResponse> updateUser(UserEditRequest userEdit);
}
