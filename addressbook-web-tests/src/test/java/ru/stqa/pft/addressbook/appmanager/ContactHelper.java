package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(ChromeDriver wd) {
    super(wd);
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getUserNameParametr());
    type(By.name("middlename"), contactData.getUserMiddleName());
    type(By.name("lastname"), contactData.getUserLastName());
    type(By.name("nickname"), contactData.getUserNickname());
    type(By.name("title"), contactData.getTitle1());
    type(By.name("company"), contactData.getCompanyName());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("home"), contactData.getHomeTel());
    type(By.name("mobile"), contactData.getMobelTel());
    type(By.name("work"), contactData.getWorkTel());
    type(By.name("fax"), contactData.getFax1());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomePage());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
    click(By.name("bday"));
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBounth());
    click(By.name("bmonth"));
    type(By.name("byear"), contactData.getByear());
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
    click(By.name("aday"));
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
    click(By.name("amonth"));
    type(By.name("ayear"), contactData.getAyear());
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void returnToHomePage() { click(By.linkText("home page")); }

  public void initContactModification() { click(By.xpath("//img[@alt='Edit']")); }

  public void submitContactModification() { click(By.name("update")); }

  public void findElement() { click(By.id("4")); }

  public void deleteContact() { click(By.xpath("//input[@value='Delete']")); }

  public void acceptAlertDelete() { wd.switchTo().alert().accept(); }

  public void returnToHome() { click(By.linkText("home")); }

}
