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
        if (idPortfolio == 1 || idPortfolio == 2) {
            pf = new Portfolio();
            pf.setId(idPortfolio);
            pf.setName("my first portfolio");
            pf.setDescription("my description");
        }

        return pf;
    }

    public List<Project> getProjects(Integer idPortfolio) {
        ArrayList<Project> result = new ArrayList<>();
        Project project = new Project();
        if(idPortfolio == 1) {
            project.setId(1);
            project.setIdPortFolio(1);
            project.setName("VR Surgery");
            project.setDescription("Achieve successful remote surgeries using VR technologies");
        } else if(idPortfolio == 2) {
            project.setId(2);
            project.idPortFolio(2);
            project.setName("Knowledge Injection");
            project.setDescription("Inject knowledge into the brain without going for year to the school");
        }
        result.add(project);
        return result;
    }
}
