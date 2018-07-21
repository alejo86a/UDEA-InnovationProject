package io.swagger.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Investment;
import io.swagger.model.Project;

@Controller
public class InvestmentApiController implements InvestmentApi {

    private static final Logger log = LoggerFactory.getLogger(InvestmentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public InvestmentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Investment> registerInvestment(@ApiParam(value = "Invest tot register"  )  @Valid @RequestBody Investment invest) {
    	
    	HttpHeaders responseHeaders = new HttpHeaders();
         responseHeaders.setExpires(1000);
         responseHeaders.set("MiHeader", "valor x");
         invest.add(linkTo(InvestmentApi.class).slash(invest.getIdInvestment()).withSelfRel());
         return new ResponseEntity<Investment>(invest, responseHeaders,HttpStatus.CREATED);
    	
    }

    public @ResponseBody List<Investment> searchAllInvestments() {
     return null;
    }

    public ResponseEntity<Investment> searchInvestment(@ApiParam(value = "id investment to find",required=true) @PathVariable("id") Integer id) {

    	Investment investment = new Investment();
    	investment.setAmountInvestment(200000);
    	investment.setIdInvestment(1);
    	investment.setIdProject(1);
    	investment.setPorcentageToReturn(new BigDecimal(1.0));
    	investment.setTimeToReturnInvestment(10);
    	
    	investment.add(linkTo(InvestmentApi.class).slash(investment.getIdInvestment()).withSelfRel());
       
    	  //Headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1000);
        responseHeaders.set("MiHeader", "valor x");

        return new ResponseEntity<Investment>(investment,responseHeaders,HttpStatus.OK);
    }

	@Override
	public @ResponseBody List<Project> listProjects(@PathVariable("idInvestment") Integer idTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

}
