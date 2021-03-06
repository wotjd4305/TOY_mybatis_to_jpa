package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.BasicResponse;
import com.example.demo.dto.User;
import com.example.demo.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	//private final IJWTService jwtService;

	@Override
	public ResponseEntity<BasicResponse<User>> insertUser(User user) {
		ResponseEntity<BasicResponse<User>> responseEntity = null;
		BasicResponse response = new BasicResponse();

		User encUser = null;
		try {
			encUser = SHA256Util.sha256(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		User res = userDao.save(encUser);

		if (res != null) {
			response.isSuccess = true;
			response.message = "Success in signup";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			response.isSuccess = false;
			response.message = "Fail in signup";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}

		return responseEntity;
	}

//	@Override
//	public ResponseEntity<BasicResponse> getEmpNum(int empNum) {
//		ResponseEntity<BasicResponse> responseEntity = null;
//		BasicResponse response = new BasicResponse();
//
//		User userRes = userDao.getEmpNum(empNum);
//
//		if (userRes == null) {
//			response.isSuccess = false;
//			response.message = "No Duplication of Employee Number";
//			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//
//		} else {
//			response.isSuccess = true;
//			response.message = "Duplication of Employee Number";
//			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		return responseEntity;
//	}
	
//	@Override
//	public ResponseEntity<BasicResponse> getUserLogin(UserLoginRequest userLoginRequest) {
//		ResponseEntity<BasicResponse> responseEntity = null;
//		BasicResponse response = new BasicResponse();
//
//		User user = User.builder()
//				.empNum(userLoginRequest.getEmpNum())
//				.password(userLoginRequest.getPassword()).build();
//
//
//		User encodePassword = null;
//		try {
//			encodePassword = SHA256Util.sha256(user);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//
//		User userRes = userDao.getUserLogin(encodePassword);
//
//		if (userRes == null) {
//			response.isSuccess = false;
//			response.message = "Discorrect";
//			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			response.isSuccess = true;
//			response.message = "Correct";
//
//			try {
//				String token = jwtService.makeJwt(userRes);
//
//				response.data = token;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		return responseEntity;
//	}
//
//	@Override
//	public ResponseEntity<BasicResponse> updateUser(UserEditRequest userEditRequest) {
//		ResponseEntity<BasicResponse> responseEntity = null;
//		BasicResponse response = new BasicResponse();
//
//		User user = User.builder()
//				.empNum(userEditRequest.getEmpNum())
//				.password(userEditRequest.getPassword())
//				.department(userEditRequest.getDepartment()).build();
//
//		User encodePassword = null;
//		try {
//			encodePassword = SHA256Util.sha256(user);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//
//		int res = userDao.updateUser(encodePassword);
//
//		if (res > 0) {
//
//			try {
//				User userRes = userDao.getUserLogin(encodePassword);
//
//				String token = jwtService.makeJwt(userRes);
//
//				response.data = token;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			response.isSuccess = true;
//			response.message = "Modify";
//			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//
//		} else {
//			response.isSuccess = false;
//			response.message = "Unable to Modify";
//			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		return responseEntity;
//	}
}
