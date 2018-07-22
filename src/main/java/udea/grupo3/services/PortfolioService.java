package udea.grupo3.services;

import java.util.ArrayList;
import java.util.List;

import io.swagger.model.Portfolio;
import io.swagger.model.Project;

public class PortfolioService {

    public PortfolioService() {

    }

    public static Portfolio GET_PORTFOLIO_BY_ID(Integer idPortfolio) {
        Portfolio pf = null;
        if (idPortfolio >= 0 && idPortfolio < 5) {
            pf = new Portfolio();
            pf.setPortfolioId(idPortfolio);
            pf.setName("my portfolio: " + idPortfolio);
            pf.setDescription("my description: " + idPortfolio);
        }

        return pf;
    }

    public List<Project> getProjects(Integer idPortfolio) {
        ArrayList<Project> result = new ArrayList<>();
        Project project = new Project();
        project.setProjectId(1);
        project.setIdPortFolio(idPortfolio);
        project.setName("VR Surgery");
        project.setDescription("Achieve successful remote surgeries using VR technologies");
        result.add(project);

        return result;
    }
    
    public static List<Portfolio> GET_PORTFOLIOS() {
    	List<Portfolio> lst = new ArrayList<>();
    	for(int i = 0; i < 5 ; i++) {
    		Portfolio p = new Portfolio();
    		p.setPortfolioId(i);
    		p.setName("Portafolio #"+ (i+1));
    		p.setDescription("Descripción del Portafolio #" + (i+1));
    		lst.add(p);
    	}

        return  lst;
    }
}
