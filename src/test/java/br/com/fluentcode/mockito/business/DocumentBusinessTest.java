package br.com.fluentcode.mockito.business;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.fluentcode.mockito.dao.IDocumentDAO;
import br.com.fluentcode.mockito.entity.Document;

public class DocumentBusinessTest {

	@Test
	public void shouldExpireRefusedDocuments() {
		Document doc1 = new Document("Refused");
		Document doc2 = new Document("Sent");
		List<Document> currents = Arrays.asList(doc1, doc2);
		
		//Simulates the database
		IDocumentDAO dao = mock(IDocumentDAO.class);
		when(dao.findCurrents()).thenReturn(currents);
		
		DocumentBusiness manager = new DocumentBusiness();
		manager.setDocumentDAO(dao);
		
		manager.expireRefusedDocuments();
		
		assertThat(doc1.isExpired(), equalTo(true));
		assertThat(doc2.isExpired(), equalTo(false));
	}
	
	@Test
	public void shouldUpdateExpiredDocuments() {
		Document doc1 = new Document("Refused");
		Document doc2 = new Document("Sent");
		List<Document> currents = Arrays.asList(doc1, doc2);
		
		//Simulates the database
		IDocumentDAO dao = mock(IDocumentDAO.class);
		when(dao.findCurrents()).thenReturn(currents);
		
		DocumentBusiness manager = new DocumentBusiness();
		manager.setDocumentDAO(dao);
		
		manager.expireRefusedDocuments();
		
		//Verifies that the dao update method was invoked
		verify(dao, times(1)).update(doc1);
		verify(dao, never()).update(doc2);
	}
	
	@Test
	public void shouldContinueRunningEvenWhenTheDaoFailure() {
		Document doc1 = new Document("Refused");
		Document doc2 = new Document("Refused");
		List<Document> currents = Arrays.asList(doc1, doc2);
		
		//Simulates the database
		IDocumentDAO dao = mock(IDocumentDAO.class);
		when(dao.findCurrents()).thenReturn(currents);
		//Throws exception when trying to update the doc1
		doThrow(new RuntimeException()).when(dao).update(doc1);
		
		DocumentBusiness manager = new DocumentBusiness();
		manager.setDocumentDAO(dao);
		
		manager.expireRefusedDocuments();
		
		//Verifies that the dao update method was invoked with doc2
		verify(dao, times(1)).update(doc2);
	}

}
