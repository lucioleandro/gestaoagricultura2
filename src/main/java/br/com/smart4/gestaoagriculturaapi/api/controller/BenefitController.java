package br.com.smart4.gestaoagriculturaapi.api.controller;


import br.com.smart4.gestaoagriculturaapi.api.service.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/benefit")
public class BenefitController {

	@Autowired
	private BenefitService benefitService;
	

}
