package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
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
//    type(By.name("nickname"), contactData.getUserNickname());
//    type(By.name("title"), contactData.getTitle1());
//    type(By.name("company"), contactData.getCompanyName());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("home"), contactData.getHomeTel());
    type(By.name("mobile"), contactData.getMobelTel());
    type(By.name("work"), contactData.getWorkTel());
//    type(By.name("fax"), contactData.getFax1());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
//    type(By.name("homepage"), contactData.getHomePage());
//    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
//    click(By.name("bday"));
//    new Select(wd.findElement(By.name("bmounth"))).selectByVisibleText(contactData.getBmounth());
//    click(By.name("bmounth"));
//    type(By.name("byear"), contactData.getByear());
//    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
//    click(By.name("aday"));
//    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
//    click(By.name("amonth"));
//    type(By.name("ayear"), contactData.getAyear());
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
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptAlertDelete();
    contactCache = null;
    HomePage();
  }

//
//  public Contacts all() {
//    Contacts contacts = new Contacts();
////      Set<ContactData> contacts = new HashSet<ContactData>();
//    List<WebElement> rows = wd.findElements(By.tagName("entry"));
//    for (int a = 2; a < rows.size(); a++) {
//      WebElement tableRow = wd.findElement(By.xpath("//tr[" + a + "]"));
//      String userLastName = tableRow.findElement(By.xpath(".//td[2]")).getText();
//      String userName = tableRow.findElement(By.xpath(".//td[3]")).getText();
//      String allPhones = tableRow.findElement(By.xpath(".//td[6]")).getText();
//      int id = Integer.parseInt(tableRow.findElement(By.xpath(".//td[1]//input")).getAttribute("value"));
//      contacts.add(new ContactData().withId(id).withUserName(userName).withUserLastName(userLastName)
//              .withAllPhones(allPhones));
//    }
//    return contacts;
//  }
//  //public Set<ContactData> all() {
////    Set<ContactData> contacts = new HashSet<ContactData>();
////    List<WebElement> strings = wd.findElements(By.tagName("tr"));
////    strings.remove(0);
////    for (WebElement string : strings) {
////      List<WebElement> cells = string.findElements(By.tagName("td"));
////      String lastname = cells.get(1).getText();
////      String firstname = cells.get(2).getText();
////      //int id = Integer.parseInt(string.findElement(By.tagName("input")).getAttribute("value"));
////      //ContactData contact = new ContactData().withId(id).withUserLastName(lastname).withUserName(firstname);
////      //contacts.add(contact);
////   // }
//    return contacts;
//}
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
//      String[] Phones = cells.get(5).getText().split("\n");
//      String[] Emails = cells.get(4).getText().split("\n");
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
}

