package ru.stqa.pft.addressbook.tests;


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
    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((с) -> с.getId()).max().getAsInt()))));
  }
}
