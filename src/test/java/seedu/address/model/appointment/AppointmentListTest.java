package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPT_1;
import static seedu.address.testutil.TypicalAppointments.BOB_APPT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.date.Date;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.appointment.exceptions.OverlappingAppointmentException;
import seedu.address.model.patient.Nric;
import seedu.address.testutil.AppointmentBuilder;

public class AppointmentListTest {

    private final AppointmentList appointmentList = new AppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(appointmentList.contains(ALICE_APPT));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        appointmentList.add(ALICE_APPT);
        assertTrue(appointmentList.contains(ALICE_APPT));
    }

    @Test
    public void contains_appointmentWithSameIdentityAndTimeInList_returnsTrue() {
        appointmentList.add(ALICE_APPT);
        Appointment editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withAppointmentType("Throat Infection").withNote("called in")
                .build();
        assertTrue(appointmentList.contains(editedAliceAppt));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        appointmentList.add(ALICE_APPT);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentList.add(ALICE_APPT));
    }

    @Test
    public void add_overlappingAppointment_throwsOverlappingAppointmentException() {
        appointmentList.add(ALICE_APPT);
        Appointment overlappingAppt = new AppointmentBuilder()
                .withNric(ALICE_APPT.getNric().toString())
                .withDate(ALICE_APPT.getDate().toString())
                .withStartTime(ALICE_APPT.getStartTime().toString())
                .withEndTime("23:00")
                .build();
        assertThrows(OverlappingAppointmentException.class, () -> appointmentList.add(overlappingAppt));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointment(null, ALICE_APPT));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList
                .setAppointment(ALICE_APPT, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentList
                .setAppointment(ALICE_APPT, ALICE_APPT));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        appointmentList.add(ALICE_APPT);
        appointmentList.setAppointment(ALICE_APPT, ALICE_APPT);
        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(ALICE_APPT);
        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        appointmentList.add(ALICE_APPT);
        Appointment editedAliceAppt = new AppointmentBuilder(ALICE_APPT)
                .withAppointmentType("Knee Injury").withNote("Fell down")
                .build();
        appointmentList.setAppointment(ALICE_APPT, editedAliceAppt);
        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(editedAliceAppt);
        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        appointmentList.add(ALICE_APPT);
        appointmentList.setAppointment(ALICE_APPT, BOB_APPT);
        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(BOB_APPT);
        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        appointmentList.add(ALICE_APPT);
        appointmentList.add(BOB_APPT);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentList
                .setAppointment(ALICE_APPT, BOB_APPT));
    }

    @Test
    public void setAppointment_editedAppointmentHasOverlappingAppointment_throwsOverlappingAppointmentException() {
        appointmentList.add(ALICE_APPT);
        appointmentList.add(ALICE_APPT_1);
        Appointment overlappingAppt = new AppointmentBuilder()
                .withNric(ALICE_APPT.getNric().toString())
                .withDate(ALICE_APPT.getDate().toString())
                .withStartTime(ALICE_APPT.getStartTime().toString())
                .withEndTime("23:00")
                .build();
        assertThrows(OverlappingAppointmentException.class, () -> appointmentList
                .setAppointment(ALICE_APPT_1, overlappingAppt));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentList.remove(ALICE_APPT));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        appointmentList.add(ALICE_APPT);
        appointmentList.remove(ALICE_APPT);
        AppointmentList expectedAppointmentList = new AppointmentList();
        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void setAppointments_nullAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointments((AppointmentList) null));
    }

    @Test
    public void setAppointments_appointmentList_replacesOwnListWithProvidedAppointmentList() {
        appointmentList.add(ALICE_APPT_1);
        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(BOB_APPT);
        appointmentList.setAppointments(expectedAppointmentList);
        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        appointmentList.add(ALICE_APPT);
        List<Appointment> listOfAppointments = Collections.singletonList(BOB_APPT);
        appointmentList.setAppointments(listOfAppointments);
        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(BOB_APPT);
        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(ALICE_APPT, ALICE_APPT);
        assertThrows(DuplicateAppointmentException.class, () -> appointmentList
                .setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> appointmentList
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getMatchingAppointment_validInput_appointmentFound() {
        appointmentList.add(ALICE_APPT);
        Nric nricToMatch = ALICE_APPT.getNric();
        Date dateToMatch = ALICE_APPT.getDate();
        Time startTimeToMatch = ALICE_APPT.getStartTime();

        assertEquals(ALICE_APPT, appointmentList.getMatchingAppointment(nricToMatch, dateToMatch, startTimeToMatch));
    }

    @Test
    public void getMatchingAppointment_invalidInput_appointmentFound() {
        appointmentList.add(ALICE_APPT);
        Nric nricToMatch = ALICE_APPT_1.getNric();
        Date dateToMatch = ALICE_APPT_1.getDate();
        Time startTimeToMatch = ALICE_APPT_1.getStartTime();

        assertThrows(AppointmentNotFoundException.class, () ->
                appointmentList.getMatchingAppointment(nricToMatch, dateToMatch, startTimeToMatch));
    }

    @Test
    public void getMatchingAppointment_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.getMatchingAppointment(
                null, null, null)
        );
    }

    @Test
    public void deleteAppointmentsWithNric_validNric_appointmentsRemoved() {
        appointmentList.add(ALICE_APPT);
        appointmentList.add(BOB_APPT);

        appointmentList.deleteAppointmentsWithNric(ALICE_APPT.getNric());

        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(BOB_APPT);

        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void deleteAppointmentsWithNric_nricNotInList_noChange() {
        appointmentList.add(ALICE_APPT);
        appointmentList.add(BOB_APPT);

        appointmentList.deleteAppointmentsWithNric(ALICE_APPT.getNric());
        appointmentList.deleteAppointmentsWithNric(ALICE_APPT.getNric()); //to show use of nric that's not there

        AppointmentList expectedAppointmentList = new AppointmentList();
        expectedAppointmentList.add(BOB_APPT);

        assertEquals(expectedAppointmentList, appointmentList);
    }

    @Test
    public void deleteAppointmentsWithNric_nullNric_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.deleteAppointmentsWithNric(null));
    }

    @Test
    public void hasAppointmentWithDetails_existingAppointment_returnsTrue() {
        appointmentList.add(ALICE_APPT);
        assertTrue(appointmentList.hasAppointmentWithDetails(
                ALICE_APPT.getNric(), ALICE_APPT.getDate(), ALICE_APPT.getStartTime())
        );
    }

    @Test
    public void hasAppointmentWithDetails_nonExistingAppointment_returnsFalse() {
        assertFalse(appointmentList.hasAppointmentWithDetails(
                ALICE_APPT.getNric(), ALICE_APPT.getDate(), ALICE_APPT.getStartTime())
        );
    }

    @Test
    public void hasAppointmentWithDetails_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> appointmentList.hasAppointmentWithDetails(
                null, null, null)
        );
    }
    @Test
    public void samePatientHasOverlappingAppointment_noOverlap_returnsFalse() {
        // Appointments have no overlapping time periods
        Appointment appointment1 = new AppointmentBuilder().withStartTime("09:00").withEndTime("10:00").build();
        Appointment appointment2 = new AppointmentBuilder().withStartTime("10:00").withEndTime("11:00").build();
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.add(appointment1);
        assertFalse(appointmentList.samePatientHasOverlappingAppointment(appointment2));
    }

    @Test
    public void samePatientHasOverlappingAppointment_partialOverlap_returnsTrue() {
        // Appointments have partial overlapping time periods
        Appointment appointment1 = new AppointmentBuilder().withStartTime("09:00").withEndTime("10:00").build();
        Appointment appointment2 = new AppointmentBuilder().withStartTime("08:30").withEndTime("09:30").build();
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.add(appointment1);
        assertTrue(appointmentList.samePatientHasOverlappingAppointment(appointment2));
    }

    @Test
    public void samePatientHasOverlappingAppointment_oneAppointmentInsideAnother_returnsTrue() {
        // One appointment is completely inside the other
        Appointment appointment1 = new AppointmentBuilder().withStartTime("09:00").withEndTime("12:00").build();
        Appointment appointment2 = new AppointmentBuilder().withStartTime("10:00").withEndTime("11:00").build();
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.add(appointment1);
        assertTrue(appointmentList.samePatientHasOverlappingAppointment(appointment2));
    }

    @Test
    public void samePatientHasOverlappingAppointment_sameTimePeriod_returnsTrue() {
        // Appointments have the same time period
        Appointment appointment1 = new AppointmentBuilder().withStartTime("09:00").withEndTime("12:00").build();
        Appointment appointment2 = new AppointmentBuilder().withStartTime("09:00").withEndTime("12:00").build();
        AppointmentList appointmentList = new AppointmentList();
        appointmentList.add(appointment1);
        assertTrue(appointmentList.samePatientHasOverlappingAppointment(appointment2));
    }

    @Test
    public void hasOverlappingAppointmentExcluding_noOverlap() {
        Appointment targetAppt = new AppointmentBuilder()
                .withNric("S1234567A")
                .withDate("2024-04-02")
                .withStartTime("10:00")
                .withEndTime("11:00").build();
        Appointment editedAppt = new AppointmentBuilder()
                .withNric("S1234567A")
                .withDate("2024-04-02")
                .withStartTime("13:00")
                .withEndTime("14:00").build();
        Appointment otherAppt = new AppointmentBuilder()
                .withNric("S1234567A")
                .withDate("2024-04-02")
                .withStartTime("21:00")
                .withEndTime("22:00").build();
        appointmentList.add(targetAppt);
        appointmentList.add(otherAppt);

        assertFalse(appointmentList.hasOverlappingAppointmentExcluding(targetAppt, editedAppt));
    }

    @Test
    public void hasOverlappingAppointmentExcluding_withOverlap() {
        Appointment targetAppt = new AppointmentBuilder()
                .withNric("S1234567A")
                .withDate("2024-04-02")
                .withStartTime("10:00")
                .withEndTime("11:00").build();
        Appointment editedAppt = new AppointmentBuilder()
                .withNric("S1234567A")
                .withDate("2024-04-02")
                .withStartTime("13:00")
                .withEndTime("14:00").build();
        Appointment otherAppt = new AppointmentBuilder()
                .withNric("S1234567A")
                .withDate("2024-04-02")
                .withStartTime("13:00")
                .withEndTime("22:00").build();
        appointmentList.add(targetAppt);
        appointmentList.add(otherAppt);

        assertTrue(appointmentList.hasOverlappingAppointmentExcluding(targetAppt, editedAppt));
    }

    @Test
    public void toStringMethod() {
        assertEquals(appointmentList.asUnmodifiableObservableList().toString(), appointmentList.toString());
    }
}

