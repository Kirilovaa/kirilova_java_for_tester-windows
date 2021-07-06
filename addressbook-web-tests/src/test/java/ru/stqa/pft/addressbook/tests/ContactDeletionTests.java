package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion() {
        app.getContactHelper().returnToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("userName", "userMiddleName", "userLastName", "userNickname", "test1","title1", "companyName", "companyAddress", "homeTel", "mobelTel", "workTel", "fax1", "email1", "email2", "email3", "homePage", "10", "January", "1980", "10", "December", "1999"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlertDelete();
        app.getContactHelper().returnToHomePage();
    }
}
