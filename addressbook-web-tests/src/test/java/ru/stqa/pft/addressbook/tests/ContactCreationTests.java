package ru.stqa.pft.addressbook.tests;



import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.contact().HomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName").withHomeTel("123")
              .withMobelTel("123").withAllPhones("123").withGroup("[none]");

    app.contact().create(contact, true);
//    assertThat(app.contact().count(), equalTo(app.contact().count() + 1));
    Contacts after = app.contact().all();

   // assertEquals(after.size(), equalTo(before.size() + 1));
    Assert.assertEquals(after.size(),before.size()+1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
