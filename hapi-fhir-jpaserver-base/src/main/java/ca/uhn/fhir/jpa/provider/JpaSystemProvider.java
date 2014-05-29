package ca.uhn.fhir.jpa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import ca.uhn.fhir.jpa.dao.IFhirSystemDao;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.History;
import ca.uhn.fhir.rest.annotation.Since;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;

public class JpaSystemProvider {

	private IFhirSystemDao myDao;

	public JpaSystemProvider() {
		// nothing
	}

	public JpaSystemProvider(IFhirSystemDao theDao) {
		myDao = theDao;
	}

	@Required
	public void setDao(IFhirSystemDao theDao) {
		myDao = theDao;
	}

	@Transaction
	public List<IResource> transaction(@TransactionParam List<IResource> theResources) {
		myDao.transaction(theResources);
		return theResources;
	}

	@History
	public List<IResource> getHistoryServerWithCriteria(@Since Date theDate, @Count Integer theCount) {
		return myDao.history(theDate, theCount);
	}
}