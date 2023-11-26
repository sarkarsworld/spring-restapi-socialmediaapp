package com.simplesolutions.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	// ----------------  URI Versioning example.
	@GetMapping("/v1/getPersonName")
	public PersonV1 getPersonNameV1() {
		return new PersonV1("Alok Sarkar");
	}
	
	@GetMapping("/v2/getPersonName")
	public PersonV2 getPersonNameV2() {
		return new PersonV2(new Name("Alok", "Sarkar"));
	}
	
	// ----------------  Request Parameter Versioning example.
	@GetMapping(path = "/getPersonName", params = "v=1")
	public PersonV1 getPersonNameV1RequestParams() {
		return new PersonV1("Alok Sarkar");
	}
	
	@GetMapping(path = "/getPersonName", params = "v=2")
	public PersonV2 getPersonNameV2RequestParams() {
		return new PersonV2(new Name("Alok", "Sarkar"));
	}
	
	
	// ----------------  Request Header Versioning example.
	@GetMapping(path = "/getPersonName", headers = "v=1")
	public PersonV1 getPersonNameV1RequestHeaders() {
		return new PersonV1("Alok Sarkar");		
	}
	
	@GetMapping(path = "/getPersonName", headers = "v=2")
	public PersonV2 getPersonNameV2RequestHeaders() {
		return new PersonV2(new Name("Alok", "Sarkar"));
	}

}
