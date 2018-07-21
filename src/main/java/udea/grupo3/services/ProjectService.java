package udea.grupo3.services;

import java.util.ArrayList;
import java.util.List;

import io.swagger.model.Portfolio;
import io.swagger.model.Project;

public class ProjectService {

    public ProjectService() {
    }

    public Project getProjectById(Integer id) {
        Project result = new Project();
        if(id == 1) {
            result.setProjectId(id);
            result.setIdPortFolio(1);
            result.setName("VR Surgery");
            result.setDescription("Achieve successful remote surgeries using VR technologies");
        } else if(id == 2) {
            result.setProjectId(id);
            result.idPortFolio(2);
            result.setName("Knowledge Injection");
            result.setDescription("Inject knowledge into the brain without going for year to the school");
        }
        return result;
    }
    
    public static List<Project> GET_PROJECTS() {
    	List<Project> lst = new ArrayList<>();
    	for(int i = 0; i < 7 ; i++) {
    		Project p = new Project();
    		p.setProjectId(i);
    		p.setName("Proyecto #"+ (i+1));
    		p.setDescription("Descripción del Proyecto #" + (i+1));
    		p.setIdPortFolio(1);
    		lst.add(p);
    	}

        return  lst;
    }
}
