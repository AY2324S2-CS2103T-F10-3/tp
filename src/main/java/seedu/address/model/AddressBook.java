package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<AppointmentView> apptListView = new ArrayList<AppointmentView>();
        for (Appointment appointment : appointments) {
            AppointmentView apptView = createAppointmentView(appointment);
            apptListView.add(apptView);
        }
        this.appointmentView.setAppointmentViews(apptListView);
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
        AppointmentView apptView = createAppointmentView(appt);
        appointmentView.add(apptView);
    }

    /**
     * Adds an appointmentView to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointmentView(AppointmentView apptView) {
        appointmentView.add(apptView);
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
        AppointmentView targetApptView = createAppointmentView(target);
        AppointmentView editedApptView = createAppointmentView(editedAppointment);
        appointmentView.setAppointmentView(targetApptView, editedApptView);

    }

    /**
     * Cancels {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void cancelAppointment(Appointment key) {
        appointments.remove(key);
        appointmentView.remove(new AppointmentView(getPersonWithNric(key.getNric()).getName(), key));
    }

    /**
     * Filters appointments based on the provided criteria.
     *
     * @param nricFilter Optional argument to filter by NRIC.
     * @param dateFilter Optional argument to filter by date.
     * @param timePeriodFilter Optional argument to filter by time period.
     * @return A list of appointments that match the given criteria.
     */
    public void filterAppointments(Optional<Nric> nricFilter,
                                                Optional<Date> dateFilter,
                                                Optional<TimePeriod> timePeriodFilter) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            boolean matchesFilter = true;

            if (nricFilter.isPresent() && !appointment.getNric().equals(nricFilter.get())) {
                matchesFilter = false;
            }
            if (dateFilter.isPresent() && !appointment.getDate().equals(dateFilter.get())) {
                matchesFilter = false;
            }
            if (timePeriodFilter.isPresent() && !appointment.getTimePeriod().equals(timePeriodFilter.get())) {
                matchesFilter = false;
            }

            if (matchesFilter) {
                filteredAppointments.add(appointment);
            }
        }

        this.appointments.setAppointments(filteredAppointments);
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

    public void deleteAppointmentsWithNric(Nric targetNric) {
        appointments.deleteAppointmentsWithNric(targetNric);
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
