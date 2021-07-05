package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("userName2", "userMiddleName2", "userLastName", "userNickname", null, "title1","companyName", "companyAddress", "homeTel", "mobelTel", "workTel", "fax1", "email1", "email2", "email3", "homePage", "10", "January", "1980", "10", "December", "1999"), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
