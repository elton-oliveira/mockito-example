package br.com.fluentcode.mockito.business;

import java.util.List;

import br.com.fluentcode.mockito.dao.IBilletDAO;
import br.com.fluentcode.mockito.dao.IDocumentDAO;
import br.com.fluentcode.mockito.entity.Billet;
import br.com.fluentcode.mockito.entity.Document;

public class BilletBusiness {
	
	private IDocumentDAO documentDAO;
	private IBilletDAO billetDAO;
	
	public void generateBilletWithGreaterPremium() {
		List<Document> currents = documentDAO.findCurrents();
		double greaterPremium = Double.NEGATIVE_INFINITY;
        for(Document document : currents) {
        	//get greater premium
        	if(greaterPremium < document.getPremium()){
        		greaterPremium = document.getPremium();
        	}
        }
		Billet billet = new Billet(greaterPremium);
		this.billetDAO.save(billet);
    }
	
	public void setDocumentDAO(IDocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	
	public void setBilletDAO(IBilletDAO billetDAO) {
		this.billetDAO = billetDAO;
	}

}
