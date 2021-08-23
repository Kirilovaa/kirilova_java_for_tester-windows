package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter (names = "-c", description = "Contact count")
    public int count;
    @Parameter (names = "-f", description = "Target file")
    public String  file;
    @Parameter (names = "-d", description = "Data format")
    public String  format;

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if  (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if  (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;\n", contact.getUserName(), contact.getUserLastName()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        File photo = new File("src/test/resources/осоед.jpg");
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withUserName(String.format("name %s", i))
                    .withUserLastName(String.format("lastName %s", i))
                    .withEmail1(String.format("a%s@a.ru", i))
                    .withEmail2(String.format("b%s@a.ru", i))
                    .withEmail3(String.format("c%s@a.ru", i))
                    .withMobelTel(String.format("+7916111111%s", i))
                    .withWorkTel(String.format("+7916111111%s", i))
                    .withHomeTel(String.format("+7916111111%s", i))
                    .withPhoto(photo)
                    .withCompanyAddress(String.format("city S, street %s", i))
                    .withGroup(String.format("test %s", i)));
        }
        return contacts;
    }

}
