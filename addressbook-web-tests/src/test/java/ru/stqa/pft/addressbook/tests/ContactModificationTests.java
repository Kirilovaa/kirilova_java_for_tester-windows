package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().returnToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("userName", "userMiddleName", "userLastName", "userNickname", "test1", "title1", "companyName", "companyAddress", "homeTel", "mobelTel", "workTel", "fax1", "email1", "email2", "email3", "homePage", "10", "January", "1980", "10", "December", "1999"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().selectContact(index);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(index).getId(), "userName", "userMiddleName", "userLastName", "userNickname", "test1", "title1", "companyName", "companyAddress", "homeTel", "mobelTel", "workTel", "fax1", "email1", "email2", "email3", "homePage", "10", "January", "1980", "10", "December", "1999");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
