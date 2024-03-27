package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.date.Date;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.appointment.AppointmentView;
import seedu.address.model.appointment.AppointmentViewList;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final AppointmentList appointments;
    private final AppointmentViewList appointmentView;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        appointments = new AppointmentList();
        appointmentView = new AppointmentViewList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setAppointments(newData.getAppointmentList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same name as {@code person} exists in the address book.
     */
    public boolean hasPersonWithNric(Nric nric) {
        requireNonNull(nric);
        return persons.hasPersonWithNric(nric);
    }

    /**
     * Returns true if a person with the same name as {@code person} exists in the address book.
     */
    public Person getPersonWithNric(Nric nric) {
        requireNonNull(nric);
        return persons.getPersonWithNric(nric);
    }

    /**
     * Deletes if a person with the same nric as {@code nric} exists in the address book.
     */
    public void deletePersonWithNric(Nric nric) {
        requireNonNull(nric);
        persons.deletePersonWithNric(nric);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// appointment-level operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
        this.appointmentView.setAppointmentViews(persons, appointments);
    }


    /**
     * Returns true if an appointment with the same identity as {@code appointment}
     * exists in the address book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointment(Appointment appt) {
        appointments.add(appt);
        this.appointmentView.setAppointmentViews(persons, appointments);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedAppointment} must not be the same as another
     * existing person in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        appointments.setAppointment(target, editedAppointment);
        this.appointmentView.setAppointmentViews(persons, appointments);
    }

    /**
     * Cancels {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void cancelAppointment(Appointment key) {
        appointments.remove(key);
        this.appointmentView.setAppointmentViews(persons, appointments);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<AppointmentView> getAppointmentViewList() {
        return appointmentView.asUnmodifiableObservableList();
    }

    public Appointment getMatchingAppointment(Nric nric, Date date, TimePeriod timePeriod) {
        return appointments.getMatchingAppointment(nric, date, timePeriod);
    }

    /** delete appointments when person is deleted */
    public void deleteAppointmentsWithNric(Nric targetNric) {
        appointments.deleteAppointmentsWithNric(targetNric);
        this.appointmentView.setAppointmentViews(persons, appointments);
    }

    /**
     * Create AppointmentView from appointment
     */
    public AppointmentView createAppointmentView(Appointment appointment) {
        Person person = getPersonWithNric(appointment.getNric());
        return new AppointmentView(person.getName(), appointment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
