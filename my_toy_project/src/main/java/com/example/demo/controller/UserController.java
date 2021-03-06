package com.example.demo.controller;


import com.example.demo.dto.BasicResponse;
import com.example.demo.dto.User;
import com.example.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	//private final IJWTService jwtService;
	//private final IImageService imageService;
	
	@PostMapping("/user")
	@ApiOperation("회원가입")
	public ResponseEntity<BasicResponse<User>> insertUser(@RequestBody User user) {
		log.info("UserController : insertUser");
		
		return userService.insertUser(user);
	}
	
//
//	@GetMapping("/user/{empNum}")
//	@ApiOperation("사번 중복 체크")
//	public ResponseEntity<BasicResponse> getEmpNum(@PathVariable int empNum) {
//		log.info("UserController : getEmpNum => {}", empNum);
//
//		return userService.getEmpNum(empNum);
//	}
//
//	@PostMapping("/user/login")
//	@ApiOperation("로그인")
//	public ResponseEntity<BasicResponse> getUserLogin(@RequestBody UserLoginRequest userLoginRequest){
//		log.info("UserController : getUserLogin");
//
//		return userService.getUserLogin(userLoginRequest);
//	}
//
//	@PostMapping("/user/token")
//	@ApiOperation("토큰 검증")
//	public ResponseEntity<BasicResponse> token(@RequestBody TokenRequest accessToken){
//		log.info("UserController : token");
//
//		ResponseEntity<BasicResponse> responseEntity = null;
//		BasicResponse response = new BasicResponse();
//		User userJwt = null;
//
//		try {
//			userJwt = jwtService.checkJwt(accessToken);
//
//			if (userJwt == null) {
//				response.isSuccess = false;
//				response.message = "Token Mismatch";
//				responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//
//			} else {
//				response.isSuccess= true;
//				response.message = "Token Match";
//				response.data = userJwt;
//				responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return responseEntity;
//	}
//
//	@PatchMapping("/user")
//	@ApiOperation("empNum으로 회원 정보(비밀번호, 부서) 수정하기")
//	public ResponseEntity<BasicResponse> updateUser(@RequestBody UserEditRequest userEditRequest){
//		log.info("UserController : updateUser");
//
//		return userService.updateUser(userEditRequest);
//	}
//
//
//
//	@PatchMapping("/user/{userId}/image")
//	@ApiOperation("유저 프로필 사진 변경하기")
//	public ResponseEntity<BasicResponse> updateUserImage(@RequestPart MultipartFile image, @PathVariable int userId) {
//		log.info("userController : updateUserImage");
//
//		return imageService.updateUserImage(image, userId);
//	}
//
//	@DeleteMapping("/user/{userId}/image")
//	@ApiOperation("유저 프로필 사진 삭제하기")
//	public ResponseEntity<BasicResponse> deleteUserImage(@PathVariable int userId) {
//		log.info("userController : deleteUserImage");
//
//		return imageService.deleteUserImage(userId);
//	}
	
}
