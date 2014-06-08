package br.com.fluentcode.mockito.dao;

import java.util.List;

import br.com.fluentcode.mockito.entity.Document;

public interface IDocumentDAO {

	List<Document> findCurrents();

	void update(Document document);

}
