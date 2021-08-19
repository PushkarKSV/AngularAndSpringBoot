package com.ibm.mgb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mgb.util.MgbContants;

@CrossOrigin(origins=MgbContants.server_path)
@RestController
public abstract class MgbController {
	
}
