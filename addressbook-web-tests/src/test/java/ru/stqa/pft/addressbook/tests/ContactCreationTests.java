package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation(){
    app.getContactHelper().gotoNewContact();
    app.getContactHelper().fillContactForm(new ContactData("userName", "userMiddleName", "userLastName", "userNickname", "title1", "companyName", "companyAddress", "homeTel", "mobelTel", "workTel", "fax1", "email1", "email2", "email3", "homePage", "10", "January", "1980", "10", "December", "1999"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }
}
