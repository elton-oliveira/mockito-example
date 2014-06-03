package br.com.fluentcode.mockexample.business;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.fluentcode.mockito.business.DocumentManager;
import br.com.fluentcode.mockito.dao.IDocumentDao;
import br.com.fluentcode.mockito.entity.Document;

public class DocumentManagerTest {

	@Test
	public void shouldExpireRefusedDocuments() {
		Document doc1 = new Document("Refused");
		Document doc2 = new Document("Sent");
		List<Document> currents = Arrays.asList(doc1, doc2);
		
		IDocumentDao dao = mock(IDocumentDao.class);
		
		when(dao.findCurrents()).thenReturn(currents);
		
		DocumentManager manager = new DocumentManager();
		manager.setDocumentDao(dao);
		
		manager.expireRefusedDocuments();
		
		assertThat(doc1.isExpired(), equalTo(true));
		assertThat(doc2.isExpired(), equalTo(false));
		
	}

}
