package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.contact().HomePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData().withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName"), true);
        }
        List<ContactData> before = app.contact().getContactList();
        int index = before.size() - 1;
        app.contact().selectContact(index);
        app.contact().initContactModification();
        ContactData contact = new ContactData().withId(before.get(index).getId()).withUserName("userName").withUserMiddleName("userMiddleName").withUserLastName("userLastName");
        app.contact().fillContactForm(contact, false);
        app.contact().submitContactModification();
        app.contact().HomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
