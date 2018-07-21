package io.swagger.api;

import io.swagger.model.Portfolio;
import io.swagger.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import udea.grupo3.services.PortfolioService;
import udea.grupo3.services.ProjectService;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ProjectApiController implements ProjectApi {

    private static final Logger log = LoggerFactory.getLogger(ProjectApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProjectApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Project> registerProject(@ApiParam(value = "Project to register"  )  @Valid @RequestBody Project project) {
        if(project.getProjectId() == null) project.setProjectId(5);
        if(project.getName() == null) project.setName("Dummy project");
        if(project.getDescription() == null) project.setDescription("Latest registered project");
        if(project.getIdPortFolio() == null) project.setIdPortFolio(1);
        project.add(ControllerLinkBuilder.linkTo(ProjectApi.class).slash(project.getProjectId()).withSelfRel());
        Portfolio portfolio = ControllerLinkBuilder.methodOn(ProjectApiController.class).getPortfolio(project.getIdPortFolio());
        project.add(ControllerLinkBuilder.linkTo(portfolio).withRel("portfolio"));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1_000L);
        return new ResponseEntity<>(project, responseHeaders, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> unregisterProject(String projectId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1_000L);
        return new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Project>> searchAllProjects() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Project>>(objectMapper.readValue("[ {  \"idPortFolio\" : 1,  \"name\" : \"ACME Corporation\",  \"description\" : \"Project about technology\",  \"id\" : 1}, {  \"idPortFolio\" : 1,  \"name\" : \"ACME Corporation\",  \"description\" : \"Project about technology\",  \"id\" : 1} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Project>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Project>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Project> searchProject(@ApiParam(value = "id project to find",required=true) @PathVariable("projectId") Integer projectId) {
        Project project = new ProjectService().getProjectById(projectId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1000);
        responseHeaders.set("MiHeader", "valor x");

        if(project.getName() != null) {
            project.add(ControllerLinkBuilder.linkTo(ProjectApi.class).slash(project.getProjectId()).withSelfRel());
        } else {
            project = null;
        }

        return new ResponseEntity<>(project, responseHeaders, HttpStatus.OK);
    }

    public Portfolio getPortfolio(@PathVariable("projectId") Integer id) {
        Portfolio portfolio = PortfolioService.GET_PORTFOLIO_BY_ID(id);
        portfolio.add(ControllerLinkBuilder.linkTo(PortfolioApi.class).slash(portfolio.getPortfolioId()).withSelfRel());
        return portfolio;
    }

}
