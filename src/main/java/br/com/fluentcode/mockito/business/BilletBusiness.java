package br.com.fluentcode.mockito.business;

import java.util.List;

import br.com.fluentcode.mockito.dao.IBilletDao;
import br.com.fluentcode.mockito.dao.IDocumentDao;
import br.com.fluentcode.mockito.entity.Billet;
import br.com.fluentcode.mockito.entity.Document;

public class BilletBusiness {
	
	private IDocumentDao documentDao;
	private IBilletDao billetDao;
	
	public void generateBilletWithGreaterPremium() {
		List<Document> currents = documentDao.findCurrents();
		double greaterPremium = Double.NEGATIVE_INFINITY;
        for(Document document : currents) {
        	//get greater premium
        	if(greaterPremium < document.getPremium()){
        		greaterPremium = document.getPremium();
        	}
        }
		Billet billet = new Billet(greaterPremium);
		this.billetDao.save(billet);
    }
	
	public void setDocumentDao(IDocumentDao documentDao) {
		this.documentDao = documentDao;
	}
	
	public void setBilletDao(IBilletDao billetDao) {
		this.billetDao = billetDao;
	}

}
