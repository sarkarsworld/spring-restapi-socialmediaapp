package com.simplesolutions.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	// URI Versioning example.
	@GetMapping("/v1/getPersonName")
	public PersonV1 getPersonNameV1() {
		return new PersonV1("Alok Sarkar");
	}
	
	@GetMapping("/v2/getPersonName")
	public PersonV2 getPersonNameV2() {
		return new PersonV2(new Name("Alok", "Sarkar"));
	}

}
