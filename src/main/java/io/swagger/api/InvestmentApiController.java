package io.swagger.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Investment;
import io.swagger.model.Project;
import udea.grupo3.services.InvestmentService;

@Controller
public class InvestmentApiController implements InvestmentApi {

	private InvestmentService investmentService;

	public InvestmentApiController() {
		this.investmentService = new InvestmentService();
	}

	public ResponseEntity<Investment> registerInvestment(
			@ApiParam(value = "Invest tot register") @Valid @RequestBody Investment invest) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setExpires(1000);
		responseHeaders.set("MiHeader", "valor x");
		invest.add(linkTo(InvestmentApi.class).slash(invest.getIdInvestment()).withSelfRel());
		return new ResponseEntity<Investment>(invest, responseHeaders, HttpStatus.CREATED);

	}

	public @ResponseBody List<Investment> searchAllInvestments() {
		List<Investment> investments = new InvestmentService().getInvestments();
		for (Investment investment : investments) {
			Link investmentLink = linkTo(InvestmentApi.class).slash(investment.getIdInvestment()).withSelfRel();
			investment.add(investmentLink);
		}
		return investments;
	}

	public ResponseEntity<Investment> searchInvestment(
			@ApiParam(value = "id investment to find", required = true) @PathVariable("id") Integer id) {

		Investment investment = investmentService.getInvestmentById(id);

		investment.add(linkTo(InvestmentApi.class).slash(investment.getIdInvestment()).withSelfRel());

		// Headers
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setExpires(1000);
		responseHeaders.set("MiHeader", "valor x");

		return new ResponseEntity<Investment>(investment, responseHeaders, HttpStatus.OK);
	}

	@Override
	public @ResponseBody List<Project> listProjects(@PathVariable("id") Integer idInvestment) {
		List<Project> projects = investmentService.getProjects(idInvestment);
		for (Project project : projects) {
			Link projectLink = linkTo(ProjectApi.class).slash(project.getIdProject()).withSelfRel();
			project.add(projectLink);
		}
		return projects;

	}

	@Override
	public ResponseEntity<Void> deleteInvestment(@PathVariable("id") String projectId) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setExpires(1_000L);
		return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
	}

}
