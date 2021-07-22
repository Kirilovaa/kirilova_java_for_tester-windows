package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.contact().HomePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Assert.assertEquals(before, after);

  }
}
