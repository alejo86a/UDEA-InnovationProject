package udea.grupo3.services;

import io.swagger.model.Project;

public class ProjectService {

    public ProjectService() {
    }

    public Project getProjectById(Integer id) {
        Project result = new Project();
        if(id == 1) {
            result.setId(id);
            result.setIdPortFolio(1);
            result.setName("VR Surgery");
            result.setDescription("Achieve successful remote surgeries using VR technologies");
        } else if(id == 2) {
            result.setId(id);
            result.idPortFolio(2);
            result.setName("Knowledge Injection");
            result.setDescription("Inject knowledge into the brain without going for year to the school");
        }
        return result;
    }
}
