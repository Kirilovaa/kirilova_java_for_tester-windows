package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getUserName());
    type(By.name("middlename"), contactData.getUserMiddleName());
    type(By.name("lastname"), contactData.getUserLastName());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("home"), contactData.getHomeTel());
    type(By.name("mobile"), contactData.getMobelTel());
    type(By.name("work"), contactData.getWorkTel());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attach(By.name("photo"), contactData.getPhoto());
//
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void initContactModificationById(int id){
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public void HomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public boolean isThereAContactWithoutGroup() {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contact, boolean b) {
    gotoNewContact();
    fillContactForm(contact, b);
    submitContactCreation();
    contactCache = null;
    HomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    HomePage();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptAlertDelete();
    contactCache = null;
    HomePage();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : rows){
      List<WebElement> cells = element.findElements(By.xpath(".//td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String userLastName = cells.get(1).getText();
      String userName = cells.get(2).getText();
      String companyAddress = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withUserName(userName).withUserLastName(userLastName)
      .withAllPhones(allPhones)
      .withAllEmails(allEmails)
      .withCompanyAddress(companyAddress));
    }
    return new Contacts(contactCache);
}
    public ContactData infoFormEditForm (ContactData contact){
      initContactModificationById(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      String email = wd.findElement(By.name("email")).getAttribute("value");
      String email2 = wd.findElement(By.name("email2")).getAttribute("value");
      String email3 = wd.findElement(By.name("email3")).getAttribute("value");

      wd.navigate().back();
      return new ContactData().withId(contact.getId()).withUserName(firstname).withUserLastName(lastname)
              .withHomeTel(home).withMobelTel(mobile).withWorkTel(work)
              .withCompanyAddress(address).withEmail1(email).withEmail2(email2).withEmail3(email3);
    }


  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void selectGroupList(GroupData group) {
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(group.getId()));
  }
  public void addInGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
    click(By.xpath("(//input[@name='add'])"));
  }
//  public void addToGroup(ContactData contact, GroupData group){
//    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
//    wd.findElements(By.name("selected[]"));
//    wd.findElement(By.linkText("group page \"test 1\"")).click();
//    wd.findElement(By.name("add")).click();
//  }


  public void removeInGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    click(By.xpath("(//input[@name='remove'])"));
  }
  public Groups findGroupForAdding(ContactData contact, Groups groups) {
    Groups groupsInContact = contact.getGroups();
    groups.removeAll(groupsInContact);
    return groups;
  }
}

