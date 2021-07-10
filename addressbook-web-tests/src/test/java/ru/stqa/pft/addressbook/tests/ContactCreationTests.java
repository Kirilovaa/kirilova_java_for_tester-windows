package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("userName", "userMiddleName", "userLastName", "userNickname", "test1", "title1", "companyName", "companyAddress", "homeTel", "mobelTel", "workTel", "fax1", "email1", "email2", "email3", "homePage", "10", "January", "1980", "10", "December", "1999");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
