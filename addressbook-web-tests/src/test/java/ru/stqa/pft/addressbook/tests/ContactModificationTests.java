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
            app.contact().create(new ContactData().withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().initContactModificationById(modifiedContact.getId());
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName");
        app.contact().fillContactForm((contact), false);
        app.contact().submitContactModification();
        app.contact().HomePage();
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
