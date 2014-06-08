package br.com.fluentcode.mockito.business;

import java.util.List;

import br.com.fluentcode.mockito.dao.IDocumentDAO;
import br.com.fluentcode.mockito.entity.Document;

public class DocumentBusiness {
	
	private IDocumentDAO documentDAO;
	
	public void expireRefusedDocuments(){
		List<Document> currents = documentDAO.findCurrents();
		for(Document document : currents){
			try{
				if("Refused".equals(document.getStatus())){
					document.setExpired(true);
					documentDAO.update(document);
				}
			}catch(Exception e){
				System.out.println("Error when trying to update the document");
			}
		}
	}
	
	public void setDocumentDAO(IDocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

}
