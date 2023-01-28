package com.assessment.gumtree;

import com.assessment.gumtree.model.AddressBook;
import com.assessment.gumtree.util.AddressBookUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@SpringBootApplication
public class AddressBookApplication {
	List<AddressBook> addressBookList  = AddressBookUtil.readAddressBookFile();

	public long getNumberOfMalesInAddressBook()
	{
		return addressBookList.stream().
				filter(x -> x.getGender().equalsIgnoreCase("Male")).
				count();

	}

	public long getNumberOfSpecificGenderInAddressBook(String gender)
	{
		return addressBookList.stream().
				filter(x -> x.getGender().equalsIgnoreCase(gender)).
				count();

	}

	public String getOldestPersonInAddressBook(){
		return addressBookList.stream().
				sorted(Comparator.comparing(AddressBook::getDataOfBirth)).
				collect(Collectors.toList()).
				get(0).getName();

	}

	public long findAgeDifferenceInDays(String name1, String name2)
	{
		Predicate<AddressBook> nameFilterPredicate = addressBook ->
				addressBook.getName().contains(name1) ||
						addressBook.getName().contains(name2);
		List<AddressBook> filteredList = addressBookList.stream().
				filter(nameFilterPredicate).collect(Collectors.toList());
		long ageDifference= 0;
		if(filteredList.size() == 2) {
			ageDifference = DAYS.between(filteredList.get(0).getDataOfBirth(),
					filteredList.get(1).getDataOfBirth());
		}else{
			throw new RuntimeException("Name not found in the list");
		}
		return ageDifference;
	}




	public static void main(String[] args) throws IOException {
		AddressBookApplication application = new AddressBookApplication();
		System.out.println(application.getNumberOfMalesInAddressBook());
		System.out.println(application.getNumberOfSpecificGenderInAddressBook("female"));
		System.out.println(application.getOldestPersonInAddressBook());
		System.out.println(application.findAgeDifferenceInDays("Bill",  "Paul"));
		//SpringApplication.run(AddressBookApplication.class, args);
	}

}
