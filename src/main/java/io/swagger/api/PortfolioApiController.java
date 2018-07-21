package io.swagger.api;

import io.swagger.model.Investment;
import io.swagger.model.Portfolio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import udea.grupo3.services.PortfolioService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
public class PortfolioApiController implements PortfolioApi {

    private static final Logger log = LoggerFactory.getLogger(PortfolioApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PortfolioApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Portfolio> registerPortfolio(@ApiParam(value = "Portfolio to register"  )  @Valid @RequestBody Portfolio portfolio) {
    	
    	//Verificamos el Objeto
    	if(portfolio.getPortfolioId() == null) portfolio.setPortfolioId(6);
    	if(portfolio.getName() == null) portfolio.setName("Portfolio Six");
    	if(portfolio.getDescription() == null) portfolio.setDescription("This is the descripción to portofilo six");
    	
    	//Agregamos las conexiones
    	portfolio.add(ControllerLinkBuilder.linkTo(PortfolioApi.class).slash(portfolio.getPortfolioId()).withSelfRel());
    	
    	//Se prepara el Header de Respuesta
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1_000L);
        return new ResponseEntity<>(portfolio, responseHeaders, HttpStatus.CREATED);
                
    }

    public ResponseEntity<Void> unregisterPortfolio(String portfolioId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1_000L);
        return new ResponseEntity<>(responseHeaders, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Portfolio>> searchAllPortfolios() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Portfolio>>(objectMapper.readValue("[ {  \"name\" : \"Technology\",  \"description\" : \"Projects relationed with technology\",  \"id\" : 1}, {  \"name\" : \"Technology\",  \"description\" : \"Projects relationed with technology\",  \"id\" : 1} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Portfolio>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Portfolio>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Portfolio> searchPortfolio(@ApiParam(value = "id portfolio to find",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        //DTO
        Portfolio pf = PortfolioService.GET_PORTFOLIO_BY_ID(id);
        //Headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1000);
        responseHeaders.set("MiHeader", "valor x");

        if(pf == null) {
            return new ResponseEntity<Portfolio>(pf, responseHeaders, HttpStatus.NOT_FOUND);
        }

        //HATEAOS
        pf.add(linkTo(PortfolioApi.class).slash(pf.getPortfolioId()).withSelfRel());
        List<Project> projectsList = methodOn(PortfolioApiController.class).listProjects(pf.getPortfolioId());
        pf.add(linkTo(projectsList).withRel("allProjects"));

        //Asignar referencias

        return new ResponseEntity<Portfolio>(pf, responseHeaders, HttpStatus.OK);
    }

    public List<Project> listProjects(@PathVariable("id") Integer id) {
        List<Project> result = new PortfolioService().getProjects(id);
        for (Project project : result) {
            project.add(linkTo(ProjectApi.class).slash(project.getProjectId()).withSelfRel());
        }
        return result;
    }

}
