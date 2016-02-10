package org.example.impl.customer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.example.customer.AccountType;
import org.example.customer.AccountType.Holders;
import org.example.customer.AddressType;
import org.example.customer.CustomerType;
import org.example.customer.Statement;
import org.example.customer.TransactionType;
import org.example.customerservice.CustomerService;

@WebService
public class CustomerServiceImpl implements CustomerService {

	@Override
	@WebMethod
	public CustomerType getCustomer(String customerId, String name) {
		CustomerType customerType = new CustomerType();
		customerType.setCustomerId("1");
		customerType.setName("John Smith");
		AddressType address = new AddressType();
		address.setCity("London");
		address.setCountry("UK");
		address.setHouseNo("123");
		address.setPostCode("SE1");
		address.setStreet("test street");
		customerType.getAddresses().add(address);
		AccountType account = getAccount();
		customerType.getAccounts().add(account);

		return customerType;
	}

	private AccountType getAccount() {
		AccountType accountType = new AccountType();
		accountType.setAccountId("12345678");
		accountType.setAccountNumber("343523");
		accountType.setCurrency("GBP");
		Holders holder = new Holders();
		holder.getCustomerId().add("1");
		accountType.setHolders(holder);
		accountType.setSortCode("123122");
		return accountType;
	}

	@Override
	@WebMethod
	public AccountType getAccounts(String accountNumber, String sortCode, String accountId) {
		// TODO Auto-generated method stub
		return getAccount();
	}

	@Override
	@WebMethod
	public Statement getTransactions(String accountId) {
		Statement statement = new Statement();
		TransactionType transaction = new TransactionType();
		transaction.setAmount(123);
		transaction.setBalance(456);
		try {
			transaction.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.setReference("REF/123");
		transaction.setScheme("CHAPS");
		transaction.setType("DEBIT");

		statement.getTransaction().add(transaction);
		return null;
	}

}
