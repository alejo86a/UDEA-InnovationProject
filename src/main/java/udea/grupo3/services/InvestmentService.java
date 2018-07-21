package udea.grupo3.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.swagger.model.Investment;
import io.swagger.model.Project;

public class InvestmentService {

	public List<Project> getProjects(Integer idPortfolio) {
		ArrayList<Project> result = new ArrayList<>();
		Project project = new Project();
		if (idPortfolio == 1) {
			project.setProjectId(1);
			project.setIdPortFolio(1);
			project.setName("VR Surgery");
			project.setDescription("Achieve successful remote surgeries using VR technologies");
		} else if (idPortfolio == 2) {
			project.setProjectId(2);
			project.idPortFolio(2);
			project.setName("Knowledge Injection");
			project.setDescription("Inject knowledge into the brain without going for year to the school");
		}
		result.add(project);
		return result;
	}

	public List<Investment> getInvestments() {
		ArrayList<Investment> investments = new ArrayList<>();

		Investment investment = new Investment();
		investment.setAmountInvestment(200000);
		investment.setIdInvestment(1);
		investment.setIdProject(1);
		investment.setPorcentageToReturn(new BigDecimal(1.0));
		investment.setTimeToReturnInvestment(10);

		Investment investment1 = new Investment();
		investment1.setAmountInvestment(1000);
		investment1.setIdInvestment(2);
		investment1.setIdProject(2);
		investment1.setPorcentageToReturn(new BigDecimal(2.0));
		investment1.setTimeToReturnInvestment(70);

		investments.add(investment);
		investments.add(investment1);

		return investments;
	}

	public Investment getInvestmentById(Integer id) {
		Investment investment = new Investment();
		if (id == 1) {
			investment.setIdInvestment(id);
			investment.setAmountInvestment(200000);
			investment.setIdProject(1);
			investment.setPorcentageToReturn(new BigDecimal(1.0));
			investment.setTimeToReturnInvestment(10);
		} else if (id == 2) {
			investment.setIdInvestment(id);
			investment.setIdProject(2);
			investment.setPorcentageToReturn(new BigDecimal(2.0));
			investment.setTimeToReturnInvestment(70);
		}
		return investment;
	}

}
