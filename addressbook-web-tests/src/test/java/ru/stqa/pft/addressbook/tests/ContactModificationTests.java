package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().HomePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData().withUserName("userName")
                    .withUserLastName("userLastName").withGroup ("[none]")
                    .withHomeTel("home").withMobelTel("mobil").withWorkTel("work")
                    .withEmail1("email1").withEmail2("email2").withEmail3("email3"), true);
            app.contact().HomePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withUserName("userNameM").withUserLastName("userLastNameM")
                .withGroup ("test 1")
                .withHomeTel("homeM").withMobelTel("mobilM").withWorkTel("workM")
                .withEmail1("email1M").withEmail2("email2M").withEmail3("email3M");
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListUI();
    }

}
