package udea.grupo3.services;

import io.swagger.model.Portfolio;

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
}
