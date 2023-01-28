package com.assessment.gumtree.util;

import com.assessment.gumtree.model.AddressBook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddressBookUtil {

    public static List<AddressBook> addressBookList = new ArrayList<>();
    public static List<AddressBook> readAddressBookFile() {

        try {
            Files.lines(Paths.get("src/main/resources/AddressBook.txt"))
                    .map(line -> line.split(","))
                    .forEach(record -> addressBookList.add(new AddressBook(record[0].trim(),
                            record[1].trim(),
                            convertStringDateToLocalDate(record[2].trim()))));
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return addressBookList;

    }

    private static LocalDate convertStringDateToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        //Substracting 100 years assuming birth century as 20th Century as century of birth is not mentioned  in the input
        localDate = localDate.minusYears(100);
        return localDate;

    }
}
