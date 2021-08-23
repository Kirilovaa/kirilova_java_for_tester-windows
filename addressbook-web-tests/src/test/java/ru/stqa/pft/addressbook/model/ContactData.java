package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table (name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @Id
  @Column (name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;;

  @Column (name = "firstname")
  @Expose
  private  String userName;

  @Transient
  private  String userMiddleName;

  @Column (name = "lastname")
  @Expose
  private  String userLastName;
//  private  String userNickname;
  @Transient
  private  String group;
//  private  String title1;
//  private  String companyName;
  @Transient
  private  String companyAddress;

  @Column (name = "home")
  @Type(type = "text")
  private  String homeTel;

  @Column (name = "mobile")
  @Type(type = "text")
  private  String mobelTel;

  @Column (name = "work")
  @Type(type = "text")
  private  String workTel;
//  private  String fax1;

  @Column (name = "email")
  @Type(type = "text")
  private  String email1;

  @Column (name = "email2")
  @Type(type = "text")
  private  String email2;

  @Column (name = "email3")
  @Type(type = "text")
  private  String email3;
//  private  String homePage;
//  private  String bday;
//  private  String bmounth;
//  private  String byear;
//  private  String aday;
//  private  String amonth;
//  private  String ayear;

  @Transient
  private  String allPhones;

  @Transient
  private String allEmails;

  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  public static void setId(int max1) {
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } else {
      return null;
    }
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(userName, that.userName) && Objects.equals(userLastName, that.userLastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, userLastName);
  }

  public String getUserName() {
    return userName;
  }

  public String getUserMiddleName() {
    return userMiddleName;
  }

  public String getUserLastName() {
    return userLastName;
  }

//  public String getUserNickname() {
//    return userNickname;
//  }
//
//  public String getTitle1() {
//    return title1;
//  }
//
//  public String getCompanyName() {
//    return companyName;
//  }
//
  public String getCompanyAddress() {
    return companyAddress;
  }

  public String getHomeTel() {
    return homeTel;
  }

  public String getMobelTel() {
    return mobelTel;
  }

  public String getWorkTel() {
    return workTel;
  }
//
//  public String getFax1() {
//    return fax1;
//  }
//
  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

//  public String getHomePage() {
//    return homePage;
//  }
//
//  public String getBday() {
//    return bday;
//  }
//
//  public String getBmounth() {
//    return bmounth;
//  }
//
//  public String getByear() {
//    return byear;
//  }
//
//  public String getAday() {
//    return aday;
//  }
//
//  public String getAmonth() {
//    return amonth;
//  }
//
//  public String getAyear() {
//    return ayear;
//  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withUserName(String userName) {
    this.userName = userName;
    return this;
  }
  public ContactData withUserMiddleName(String userMiddleName) {
    this.userMiddleName = userMiddleName;
    return this;
  }
  public ContactData withUserLastName(String userLastName) {
    this.userLastName = userLastName;
    return this;
  }
//  public ContactData withUserNickname(String userNickname) {
//    this.userNickname = userNickname;
//    return this;
//  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
//  public ContactData withTitle1(String title1) {
//    this.title1 = title1;
//    return this;
//  }
//  public ContactData withCompanyName(String companyName) {
//    this.companyName = companyName;
//    return this;
//  }
  public ContactData withCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
    return this;
  }
  public ContactData withHomeTel(String homeTel) {
    this.homeTel = homeTel;
    return this;
  }
  public ContactData withMobelTel(String mobelTel) {
    this.mobelTel = mobelTel;
    return this;
  }
  public ContactData withWorkTel(String workTel) {
    this.workTel = workTel;
    return this;
  }
//  public ContactData withFax1(String fax1) {
//    this.fax1 = fax1;
//    return this;
//  }
  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }
  public String getAllEmails() {
    return allEmails;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userLastName='" + userLastName + '\'' +
            '}';
  }
//  public ContactData withHomePage(String homePage) {
//    this.homePage = homePage;
//    return this;
//  }
//  public ContactData withBday(String bday) {
//    this.bday = bday;
//    return this;
//  }
//  public ContactData withBmounth(String bmounth) {
//    this.bmounth = bmounth;
//    return this;
//  }
//  public ContactData withByear(String byear) {
//    this.byear = byear;
//    return this;
//  }
//  public ContactData withAday(String aday) {
//    this.aday = aday;
//    return this;
//  }
//  public ContactData withAmonth(String amonth) {
//    this.amonth = amonth;
//    return this;
//  }
//  public ContactData withAyear(String ayear) {
//    this.ayear = ayear;
//    return this;
//  }
//  public static void setId(int id) {
//    this.id = id;
  }
