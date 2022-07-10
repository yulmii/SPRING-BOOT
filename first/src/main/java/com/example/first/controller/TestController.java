package com.example.first.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.dto.ResponseDTO;
import com.example.first.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {

	//localhost:8082/test
	@GetMapping
	public String testController() {
		return "Hello world!";
	}

	//localhost:8082/test/testGetMapping
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello World! testGetMapping ";
	}

	// localhost:8082/test/123
	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
		return "Hello World! ID " + id;
	}

	// localhost:8082/test?id=123
	// test경로는 이미 존재하므로 /test/testRequestParam으로 지정했다.
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		return "Hello World! ID " + id;
	}
	
	// 클라이언트에서 압력받읗 때
	//클라이언트는 Json 형태의 문자열을 넘겨주고 요청받은 값 @RequestBody로 서버에서 받는다. 
	// /test경로는 이미 존재하므로 /test/testRequestBody로 지정했다.
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	// 서버에서 값을 내보낼떼
	// Json이 리턴된다.
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseDTO");
		list.add("I LOVE SPRING BOOT");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	//error 메세지 보낼때 
	//status나 header를 조작하고 싶을 때  ResponseEntity<?>를 반환한다 . 
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		// http status를 400로 설정.
		return ResponseEntity.ok().body(response);
		//return ResponseEntity.badRequest().body(response);
	}
}
