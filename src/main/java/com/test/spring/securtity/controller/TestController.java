package com.test.spring.securtity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basicauth")
public class TestController {

		@GetMapping("testing")
		public ResponseEntity<String> monitor() {
				return ResponseEntity.ok("Finished testing!");
		}
}
