package com.example.spring.securtity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
		@GetMapping("/admin/test")
		public ResponseEntity<String> monitor() {
				return ResponseEntity.ok("Admin - Finished testing!");
		}

		@GetMapping("/user/test")
		public ResponseEntity<String> callbackEpi() {
				return ResponseEntity.ok("User - Finished testing!");
		}

}
