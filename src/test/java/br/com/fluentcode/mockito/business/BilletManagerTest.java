package br.com.fluentcode.mockito.business;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.fluentcode.mockito.dao.IBilletDao;
import br.com.fluentcode.mockito.dao.IDocumentDao;
import br.com.fluentcode.mockito.entity.Billet;
import br.com.fluentcode.mockito.entity.Document;

public class BilletManagerTest {

	@Test
	public void shoudGenerateBilletWithGreaterPremium() {
		Document doc1 = new Document(2500.43);
		Document doc2 = new Document(2000.89);
		List<Document> currents = Arrays.asList(doc1, doc2);
		
		//Simulates the database
		IDocumentDao documentDao = mock(IDocumentDao.class);
		when(documentDao.findCurrents()).thenReturn(currents);
		
		//Simulates the database
		IBilletDao billetDao = mock(IBilletDao.class);
		
		BilletManager manager = new BilletManager();
		manager.setDocumentDao(documentDao);
		manager.setBilletDao(billetDao);
		
		manager.generateBilletWithGreaterPremium();
		
		//Creates ArgumentCaptor that knows capturing a Billet
		ArgumentCaptor<Billet> argument = ArgumentCaptor.forClass(Billet.class);
		//Captures the billet that was passed to the save method
		verify(billetDao).save(argument.capture());
		Billet generatedBillet = argument.getValue();
		
		assertThat(generatedBillet.getAmountPayable(), equalTo(2500.43));
	}

}
