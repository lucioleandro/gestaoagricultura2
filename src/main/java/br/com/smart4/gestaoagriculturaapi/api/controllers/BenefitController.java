package br.com.smart4.gestaoagriculturaapi.api.controllers;


import br.com.smart4.gestaoagriculturaapi.api.services.BenefitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/benefit")
public class BenefitController {

	private final BenefitService benefitService;


	public BenefitController(BenefitService benefitService) {
		this.benefitService = benefitService;
	}
}
