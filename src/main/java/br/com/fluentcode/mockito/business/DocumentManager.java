package br.com.fluentcode.mockito.business;

import java.util.List;

import br.com.fluentcode.mockito.dao.IDocumentDao;
import br.com.fluentcode.mockito.entity.Document;

public class DocumentManager {
	
	private IDocumentDao documentDao;
	
	public void expireRefusedDocuments(){
		List<Document> currents = documentDao.findCurrents();
		for(Document document : currents){
			if("Refused".equals(document.getStatus())){
				document.setExpired(true);
				documentDao.update(document);
			}
		}
	}
	
	public void setDocumentDao(IDocumentDao documentDao) {
		this.documentDao = documentDao;
	}

}
