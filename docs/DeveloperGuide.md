---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# CLInic Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* [JavaFX](https://openjfx.io) for the API to render the GUI.
* [Jackson](https://github.com/FasterXML/jackson) for the API to parse JSON files.
* [JUnit5](https://junit.org/junit5/) for the testing framework.

Additionally, we acknowledge that CLInic is based on the [AddressBook-Level 3](https://se-education.org/addressbook-level3/) 
(AB-3) project created by the [SE-EDU initiative](https://se-education.org). 
All features developed in CLInic are built upon those existing in AB-3.

In developing our User-Guide and Developer-Guide, our project drew inspiration off the structure of past projects: [Connectify](https://github.com/AY2324S1-CS2103T-T15-4/tp),
[[Ba]king [Br]ead](https://github.com/AY2324S1-CS2103T-F10-3/tp) and [WedLog](https://github.com/AY2324S1-CS2103T-F11-2/tp/).


--------------------------------------------------------------------------------------------------------------------

## **Setting up, Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S2-CS2103T-F10-3/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103T-F10-3/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletePatient i/T0123456A` using the shorthand `dp i/T0123456A`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="804" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

<br/> 

### UI Component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103T-F10-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103T-F10-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103T-F10-3/tp/blob/master/src/main/resources/view/MainWindow.fxml).

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Patient` object residing in the `Model`.

### Logic Component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-F10-3/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("deletePatient i/T0123456A")` as the shorthand command `execute("dp i/T0123456A")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `dp i/T0123456A` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeletePatientCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g.,`DeletePatientCommandParser`) and uses it to parse the command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePatientCommandParser`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to delete a patient).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeletePatientCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<br/>

### Model Component
**API** : [`Model.java`](https://github.com/AY2324S2-CS2103T-F10-3/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="850" />


The `Model` component,

* stores CLInic data i.e., all `Patient`, `Appointment`, `AppointmentView` objects (which are contained in a `UniquePatientList`, `AppointmentList` and `AppointmentViewList` objects respectively). An `AppointmentView` object has both an `Appointment` and the `Name` of the `Patient` it is for, so that it contains the necessary information to be displayed on the UI.
* stores the currently 'selected' `Patient` and `AppointmentView` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Patient>` and `ObservableList<AppointmentView>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores `AppointmentView` objects for today (i.e. date of appointment is today) as a separate _filtered_ list using similar logic as above, for the purpose of Day-View.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Patient` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Patient` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="600" />

</box>


### Storage Component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103T-F10-3/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="650" />

The `Storage` component,
* can save both CLInic data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`).

### Common Classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Patient Feature

#### Implementation

The implementation of `AddPatientCommand` is supported by the `Patient` class stored internally in a `UniquePatientList` in `AddressBook`.

Fields that a `Patient` has include:
* `Nric`
* `Name`
* `DateOfBirth`
* `Phone`
* `Email`
* `Address`
* `Tag` [optional]

To add a `Patient`, `UniquePatientList` implements the following operations:
* `UniquePatientList#hasPatientWithNric(Nric)` - Checks if there is another patient with the same NRIC already in the list.
* `UniquePatientList#add(Patient)` - Add the Patient to the list.

Those operations are exposed in the `Model` interface as `Model#hasPatientWithNric(Nric)` and `Model#addPatient(Patient)` respectively.

#### Example Usage Scenario

1. The user executes the command `ap i/T0123456A n/John Doe b/2001-05-02 p/98765432 e/johnd@example.com a/John street, block 123, #01-01` (shorthand for addPatient).
2. The `AddPatientCommand` calls `Model#hasPatientWithNric(Nric)` using `Patient#getNric()` to get the NRIC of the patient to be added. This checks if there is already a patient with an NRIC of T0123456A. If that is the case, we throw a `CommandException` highlighting to the user that the patient they are trying to add already exists in the CLInic.
3. Suppose the patient's NRIC is not already in CLInic, the `AddPatientCommand` calls `Model#AddPatient(Patient)`, to add the patient to the `AddressBook`.

The following diagram shows how an AddPatientCommand goes through the `Logic` component:

<puml src="diagrams/AddPatientSequenceDiagram.puml" alt="AddPatientSequenceDiagram" />

The following activity diagram summarizes what happens when a user executes an `AddPatientCommand`:

<puml src="diagrams/AddPatientActivityDiagram.puml" alt="AddPatientActivityDiagram" width="400"/>


<br/>

### Add Appointment Feature

#### Implementation

The implementation of `AddApptCommand` is supported by the `Appointment` class and its related classes.
The management of all appointments is achieved through the `AppointmentList` class stored in the `AddressBook`,
similar to the `UniquePatientList`.

Details captured in an `Appointment` class include:
* NRIC
* Date
* TimePeriod (composed of start and end time)
* Appointment Type
* [Optional] Note


To add an `AppointmentList`, `UniquePatientList` and `AddressBook` implements the following operations:
* `UniquePatientList#hasPatientWithNric(Nric)` - Checks if there exists a patient with the NRIC in the list.
* `AppointmentList#hasAppointment(Appointment)` - Checks if there exists an appointment with the same NRIC, date and start time.
* `AddressBook#isValidApptForPatient(Appointment)` - Checks if appointment date is not before date of birth of patient.
* `AppointmentList#samePatientHasOverlappingAppointment(Appointment)` - Checks if same patient has another appointment with overlapping time period.
* `AppointmentList#addAppointment(Appointment)` - Add the appointment to the list.

Those operations are exposed in the `Model` interface as `Model#hasPatientWithNric(Nric)`,  `Model#hasAppointment(Appointment)`, `Model#isValidApptForPatient(Appointment)`, `Model#samePatientHasOverlappingAppointment(Appointment)` and `Model#addAppointment(Appointment)` respectively.

#### Example Usage Scenario

1. The user executes the command `aa i/T0123456A d/2024-03-27 from/00:00 to/00:30 t/Medical Check-up` (shorthand for addAppt).
2. `AddApptCommand` calls `Model#hasPatientWithNric(Nric)` using `Appointment#getNric()` to get the NRIC of the patient that the appointment is for. This checks if T0123456A (NRIC) belongs to an existing patient in the system. If yes, continue. Else, throw a `CommandException` to highlight to the user that the given NRIC does not exist.
3. `AddApptCommand` calls `Model#hasAppointment(Appointment)` to check if an equivalent appointment exists in the system. If no, continue. Else, throw a `CommandException` to highlight to the user that the appointment to be created already exists.
4. `AddApptCommand` calls `Model#isValidApptForPatient(Appointment)` to check if the date of appointment is before the corresponding patient's date of birth. If no, continue. Else, throw a `CommandException` to highlight to the user that the date of the appointment cannot be before patient is born.
5. `AddApptCommand` calls `Model#samePatientHasOverlappingAppointment(Appointment)` to check if there exists another appointment for the same patient over an overlapping time period. If no, continue. Else throw a `CommandException` to highlight to the user that an existing appointment overlaps with the appointment to be created.
6. `AddApptCommand` calls `Model#addAppointment(Appointment)` to add the appointment to the `AddressBook`.

The following sequence diagram shows how an addAppt operation goes through the `Logic` component:

<puml src="diagrams/AddApptSequenceDiagram.puml" alt="AddApptSequenceDiagram" />

The following activity diagram summarises what happens when a user executes addApptCommand.

<puml src="diagrams/AddApptActivityDiagram.puml" alt="AddApptActivityDiagram" width="950"/>


#### Design considerations:

**Aspect: How to match an `Appointment` to a `Patient`:**

* **Alternative 1 (current choice):** Store only the NRIC of the `Patient` in `Appointment`.
    * Pros: Data integrity since NRIC is the "primary key" of `Patient` and will not change, space efficient.
    * Cons: May have performance issues as we need to search the whole list to get other details of the `Person`.

* **Alternative 2:** Store `Patient` in `Appointment`
    * Pros: Time efficient as we have access to all details of the `Patient` from `Appointment`.
    * Cons: Space inefficient, we double store `Patient`. We need to ensure `Patient` in `UniquePatientList` and `Patient` in
        each `Appointment` are always consistent which can be tricky.

<br/>

### Edit Appointment Feature

#### Implementation

The Edit Appointment feature will involve parsing user input and updating the existing appointment with new values.
An appointment's `DATE`, `START_TIME`, `END_TIME`, `APPOINTMENT_TYPE` and `NOTE` can be edited. `NRIC` cannot be edited.

The implementation will include the following key components:

1. **Parsing User Input**: The application will parse user input to extract values for the target appointment (NRIC, DATE, START_TIME) and optional prefixes for new values such as newnote/, newd/.
2. **Executing Edit Queries**: The application will search through the list of appointments stored in the database and identify the target appointment. It will then set the appointment with the new values inputted.
3. **Presenting Updated Results**: The matched appointments will be presented to the user in a clear and organized manner, displaying relevant details such as the updated appointment time, date, and associated appointment information.

#### Example Usage Scenario

1. **Context**: User wants to edit the date of an appointment with a specific NRIC, date, start time.
2. **User Input**: The user enters the command `ea i/ T0123456A d/ 2024-02-20 from/ 11:00 newd/ 2024-02-21` (shorthand for editAppt).

<puml src="diagrams/EditApptSequenceDiagram.puml" alt="EditApptSeqDiag" />

3. **Parsing**: The application parses the user input and extracts the NRIC (`T0123456A`), date (`2024-02-20`) and start time (`11:00`) criteria for the target appointment. The new date is parsed as (`2024-02-21`).
4. **Search Execution**: The application searches through the list of appointments and identifies a target appointment that matches the specified NRIC, date and start time criteria.
5. **Update Execution**: The application sets the target appointment with a new appointment created that has the new values inputted, in this case date (`2024-02-21`).
6. **Presentation**: The updated appointment is presented to the user as a message, and the upcoming appointments list is updated as well.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/EditApptActivityDiagram.puml" alt="EditApptActivityDiagram" width="950" />

#### Design considerations:

**Aspect: How to differentiate prefixes for target appointment and prefixes for new values:**

* **Alternative 1 (current choice):** Use the `new` prefix in addition to the original prefixes.
    * Pros: Data integrity since unique prefix for new values.
    * Cons: May be less efficient as it requires the user to input a longer prefix.

* **Alternative 2:** Edit appointments by a unique index.
    * Pros: Time efficient as we can make use of the shorter prefixes because there's no need to differentiate between target appointment and new appointment details. Easier to correct mistakes in updating wrong appointment.
    * Cons: Prone to errors as just a wrong number could cause the user to update the wrong appointment. Huge list could make it difficult to find a specific index.

<br/>

### Find Appointment Feature

#### Implementation

The Find Appointment feature will involve parsing user input, executing search queries based on specified criteria, and presenting the results to the user.

The implementation will include the following key components:

1. **Parsing User Input**: The application will parse user input to extract search criteria such as NRIC, date, or time (any combination of the three).
2. **Executing Search Queries**: The application will search through the list of appointments stored in the database and identify appointments that match the specified criteria.
3. **Presenting Search Results**: The matched appointments will be presented to the user in a clear and organized manner, displaying relevant details such as appointment time, date, and associated patient information.

#### Example Usage Scenario

1. **Context**: User wants to find an appointment with a specific NRIC, date, and start time.
2. **User Input**: The user enters the command `fa i/T0123456A d/2024-03-23 from/11:00` (shorthand for findAppt).

<puml src="diagrams/FindApptSequenceDiagram.puml" alt="FindApptSeqDiag" />

3. **Parsing**: The application parses the user input and extracts the NRIC (`T0123456A`), date (`2024-03-23`), and start time (`11:00`) criteria for the search.
4. **Search Execution**: The application searches through the list of appointments and identifies appointments that match the specified NRIC, date, and start time criteria.
5. **Presentation**: The matched appointments are presented to the user, displaying relevant details such as appointment time, date, and associated patient information.
6. **User Interaction**: The user can view the search results and perform additional actions such as viewing detailed information about specific appointments or modifying appointments as needed.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/FindApptActivityDiagram.puml" alt="FindApptActivityDiagram" width="350" />

#### Design Considerations

##### User Experience
- **Feedback Mechanism**: Provide feedback to the user during the search process to indicate progress and inform them of any issues encountered.
- **Support for Multiple Criteria**: Allow users to specify multiple search criteria (e.g., any combination of NRIC, date, start time) to enable more precise searches.

##### Error Handling
- **Invalid Input Handling**: Implement robust error handling mechanisms to handle cases where users provide invalid or incomplete search criteria.
- **No Matching Results**: Handle scenarios where no appointments match the specified criteria gracefully, providing informative feedback to the user.

<br/>

### Mark/Unmark Appointment Feature

#### Implementation

The Mark Appointment feature will involve parsing user input and marking the appointment as completed/ not completed.

The implementation will include the following key components:

1. **Parsing User Input**: The application will parse user input to extract values to find the target appointment (`NRIC`, `DATE`, `START_TIME`).
2. **Executing Mark Queries** The application will search through the list of appointments and identify the target appointment to be marked/unmarked. It will then set the mark boolean condition based on the mark/unmark command.
3. **Appointment Status Updated Results** The appointment will be updated accordingly to show whether it has been marked/unmarked successfully based on color code.

#### Example Usage Scenario
1. Context: User wants to mark a specific appointment as completed.
2. User Input: The user enters the command `mark i/T0123456A d/2024-02-20 from/11:00`.

<puml src="diagrams/MarkApptSequenceDiagram.puml" alt="MarkApptSeqDiag" />

3. **Parsing**: The application parses the user input and extracts the NRIC (`T0123456A`), date (`2024-02-20`) and start time (`11:00`) criteria for the target appointment.
4. **Search Execution**: The application searches through the list of appointments and identifies a target appointment that matches the specified NRIC, date and start time criteria.
5. **Update Execution**: The application sets the target appointment with a new appointment created that has the new values inputted, in this case mark (true).
6. **Presentation**: The updated appointment is presented to the user as a message, and the upcoming appointments list is updated as well.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/MarkApptActivityDiagram.puml" alt="MarkApptActivityDiagram" width="350" />

<br/>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, Logging, Testing, Configuration, Dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product Scope

**Target user profile**:

* has a need to manage a significant number of patient information.
* has a need to schedule patients for appointments.
* prefers to manage patient information and appointments in one application.
* prefer desktop apps over other types.
* can type fast.
* prefers typing to mouse interactions.
* is reasonably comfortable using CLI apps.

**Value proposition**: manage patient appointments faster than a typical mouse/GUI driven app.

<br/>

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                                       | So that I can…​                                                                  |
|----------|---------|------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `* * *`  | user    | add a new patient                                                                  |                                                                                  |
| `* * *`  | user    | delete a patient                                                                   |                                                                                  |
| `* * *`  | user    | schedule an appointment for a patient                                              |                                                                                  |
| `* * *`  | user    | cancel an appointment                                                              | account for changes in scheduling                                                |
| `* * *`  | user    | have an overall view of upcoming patient appointments                              | have situational awareness of the schedule for the day                           |
| `* * *`  | user    | mark patients who have been seen for the day                                       | track patient's appointment attendance                                           |
| `* *`    | user    | update a patient's information                                                     | keep the database up to date                                                     |
| `* *`    | user    | easily filter the patients by their medical records                                | see which is in need of more assistance or follow up care                        |
| `* *`    | user    | search for patients by their name                                                  | look up their appointment information quickly                                    |
| `* *`    | user    | update the details of the appointment                                              | reschedule appointments as needed                                                |
| `* *`    | user    | view the list of patients for the given date and/or time                           | see the schedule for a given time frame                                          |
| `* *`    | user    | tag appointments based on appointment type                                         | I can categorize which appointments require test or room bookings                |
| `* *`    | user    | input commands without having inputs to be in a specific order                     | key in commands fast in busy periods                                             |
| `**`     | user    | prevent appointments from overlapping                                              | ensure the patients have enough time to be seen for their different appointments |
| `**`     | user    | quickly navigate the CLI with intuitive commands                                   | increase my efficiency                                                           |
| `* `     | user    | tag appointments based on insurance type                                           | prepare necessary insurance documents before patient’s appointments              |
| `*`      | user    | sort the time to a patient's appointment                                           | remind patients of their appointment                                             |
| `*`      | user    | see how long it has been since a patient's last appointment                        | remind patients to come for another checkup                                      |
| `*`      | user    | set notifications for upcoming appointments                                        | staff and patients can be well informed early in advance                         |
| `*`      | user    | track if the patients have been sent reminders on their appointments               | patients do not get spammed with reminders                                       |
| `*`      | user    | be notified of upcoming appointments on entry into the system                      | will not miss approaching deadlines                                              |
| `*`      | user    | easily contact the patients via SMS or email through the program                   | update patients about their details and upcoming appointments                    |
| `*`      | user    | check if patients are related to one another                                       | have alternate contacts                                                          |
| `*`      | user    | update or create new records in bulk                                               | process a family more efficiently                                                |
| `*`      | user    | set recurring tasks                                                                | I do not have to keep scheduling recurring appointments                          |
| `*`      | user    | retrieve past records or revert changes easily                                     | revert my changes if I accidentally delete or wrongly edit a patient’s records   |
| `*`      | user    | select what information is available when I view the list of patients              | cater the view to my needs                                                       |
| `*`      | user    | add notes to a patient                                                             | include other additional information                                             |
| `*`      | user    | easily generate reports of the patient details and export it to the doctor/patient | have easy access                                                                 |

<br/>

### Use Cases

**Use case (UC1) : Add new patient information to the database**

**MSS**

1.  User requests to add new patient information.
2.  CLInic adds the patient's information to the database.

    Use case ends.

**Extensions**

* 1a. User gives incomplete or invalid patient details.
    * 1a1. CLInic informs user of the error.
  
    Use case resumes at step 1.

* 1b. User attempts to add a patient that already exists in CLInic.
    *  1b1. CLInic informs user that patient with given NRIC already exists.
  
    Use case ends.

**Use case (UC2) : Delete patient information from the database**

**MSS**

1.  User requests to delete patient information with a specified NRIC from the database.
2.  CLInic deletes the patient and the corresponding appointments.

    Use case ends.

**Extensions**

* 1a. User did not provide the NRIC or gives an invalid NRIC.
    * 1a1. CLInic informs user of the error.
  
        Use case resumes at step 1.

* 1b. User provides an NRIC that does not belong to any patient in the database.
    * 1b1. CLInic informs user that no such patient exists.
  
        Use case resumes at step 1.

**Use case (UC3) : Edit patient information in the database**

**MSS**

1.  User requests to edit patient information with a specified NRIC.
2.  CLInic edits patient information to the updated details.

    Use case ends.

**Extensions**

* 1a. User does not provide any field to update.
    * 1a1. CLInic informs user to provide at least one field.

        Use case resumes at step 1.

* 1b. User gives invalid patient details.
    * 1b1. CLInic informs user of the error.

        Use case resumes at step 1.

* 1c. User provides an NRIC that does not belong to any patient in the database.
    * 1c1. CLInic informs user that no such patient exists.

        Use case ends.

**Use case (UC4) : Find patient information in the database**

**MSS**

1.  User requests to find patient information by NRIC or by name.
2.  CLInic displays all patients satisfying the search criteria.

    Use case ends.

**Extensions**

* 1a. User does not provide any search criteria.
    * 1a1. CLInic informs user to search by either NRIC or name.

      Use case resumes at step 1.

* 1b. User provides both NRIC and name for search.
    * 1b1. CLInic informs user to provide only one.

      Use case resumes at step 1.

* 2a. User is currently on Day-View.
    * 2a1. CLInic changes the view to Overall-View where the results will be shown.

      Use case ends.

* 2b. There are no search results. 

    Use case ends.


**Use case (UC5) : Schedule an appointment for a patient**

**MSS**

1.  User requests to add an appointment for a patient with a specified NRIC.
2.  CLInic adds the appointment to the database.

    Use case ends.

**Extensions**

* 1a. User gives incomplete or invalid appointment details.
    * 1a1. CLInic informs user of the error.

      Use case resumes at step 1.

* 1b. User provides an NRIC that does not belong to any patient in the database.
    * 1b1. CLInic informs user that no such patient exists and appointment cannot be created.

      Use case ends.

* 1c. User attempts to add an appointment that already exists in CLInic.
    * 1c1. CLInic informs user that appointment with given details already exists.

      Use case ends.

* 1d. User attempts to create an appointment that overlaps with another appointment for the same patient.
    * 1d1. CLInic informs user of the error and shows user other possible time slots.

      Use case resumes at step 1.


**Use case (UC6) : Cancel an appointment**

**MSS**

1.  User requests to cancel an appointment for a patient with a specified NRIC.
2.  CLInic deletes the appointment from the database.

    Use case ends.

**Extensions**

* 1a. User gives incomplete or invalid appointment details.
    * 1a1. CLInic informs user of the error.

      Use case resumes at step 1.

* 1b. User gives an NRIC or appointment information that do not exist in the system.
    * 1b1. CLInic informs user that no such patient or appointment exists.

      Use case resumes at step 1.

**Use case (UC7) : Edit an appointment**

**MSS**

1.  User requests to edit appointment information for a patient with a specified NRIC.
2.  CLInic edit appointment information to the updated details.

    Use case ends.

**Extensions**

* 1a. User does not provide any field to update.
    * 1a1. CLInic informs user to provide at least one field.

      Use case resumes at step 1.

* 1b. User gives incomplete or invalid appointment details.
    * 1b1. CLInic informs user of the error.

      Use case resumes at step 1.

* 1c. User gives an NRIC or appointment information that do not exist in the system.
    * 1c1. CLInic informs user that no such patient or appointment exists.

      Use case ends.

* 1d. User attempts to edit an appointment into a time that overlaps with another appointment for the same patient.
    * 1d1. CLInic informs user of the error and shows user other possible time slots.

      Use case resumes at step 1.


**Use case (UC8) : Find appointment information in the database**

**MSS**

1.  User requests to find appointment information by NRIC, date and/or start time.
2.  CLInic displays all appointments satisfying the search criteria.

    Use case ends.

**Extensions**

* 1a. User does not provide any search criteria.
    * 1a1. CLInic informs user to search by at least one field.

      Use case resumes at step 1.

* 1b. User gives invalid NRIC, date or start time fields.
    * 1b1. CLInic informs user of the error.

      Use case resumes at step 1.

* 2a. User is currently on Day-View.
    * 2a1. CLInic changes the view to Overall-View where the results will be shown.
  
      Use case ends.

* 2b. There are no search results.

  Use case ends.


**Use case (UC9) : Mark / unmark an appointment**

**MSS**

1.  User requests to mark / unmark an appointment for a patient with a specified NRIC.
2.  CLInic marks / unmarks the appointment as complete / incomplete.

    Use case ends.

**Extensions**

* 1a. User gives incomplete or invalid appointment details.
    * 1a1. CLInic informs user of the error.

      Use case resumes at step 1.

* 1b. User gives an NRIC or appointment information that do not exist in the system.
    * 1b1. CLInic informs user that no such patient or appointment exists.

      Use case resumes at step 1.

**Use case (UC10) : View all patients and appointments displayed in a concise format**

**MSS**

1.  User requests to view all patients and appointments.
2.  CLInic shows a list of all appointments on that day.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case (UC11) : Switch between Overall-View and Day-View**

**MSS**

1.  User requests to change the view from Overall-View to Day-View or vice versa.
2.  CLInic changes the view.

    Use case ends.

**Use case (UC12) : Clear all data in CLInic**

**MSS**

1.  User requests to clear all patient and appointment information in the database.
2.  CLInic deletes all patients and appointment information.

    Use case ends.

**Use case (UC13) : Exiting CLInic**

**MSS**

1.  User requests to exit the application.
2.  CLInic stops running and closes.

    Use case ends.

**Use case (UC14) : Getting help in CLInic**

**MSS**

1.  User requests for help.
2.  CLInic provides a link to the User-Guide for the user's reference.

    Use case ends.


<br/>

### Non-Functional Requirements
1. Patients should not have overlapping appointments.
2. Appointments can be backdated should the need arise.
3. Should be compatible with any _mainstream OS_ with Java `11` or above installed.
4. Should load patient records and appointment details within ten seconds.
5. Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.
6. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
7. The project is expected to adhere to a schedule that delivers a feature set for each milestone.
8. The product is not required to have mouse-click navigation.
9. The product is not required to integrate with other systems.
10. The product should avoid terminology or graphics that are insensitive to patients.
11. The product should be for a single user (not a multi-user product).

<br/>

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS.
* **Appointment**: A designated time slot for a patient to visit the clinic.
* **Appointment Type**: Categorises the purpose of visit eg. Vaccination, Medical Check-up, etc.
* **Insurance Type**: Categorises insurance schemes applicable to the patient eg. Medisave, ElderShield, etc.
* **Medical Records**: Refer to the patient's details.
* **Recurring Appointments**: Refer to appointments that occur regularly eg. weekly or monthly.
* **Day-View**: View that shows user today's appointments.
* **Overall-View**: View that shows users all patients and appointments. Entries showed in this view can be changed with `findAppt`, `findPatient` or `list` commands.
* **UI**: User Interface.
* **API**: Application Programming Interface.
* **MSS**: Main Success Scenario.
* **Extension**: Alternative Scenario.
* **System**: Software system under consideration. Refers to CLInic unless otherwise stated.
* **Actor**: User interacting with the system. Refers to a user using CLInic unless otherwise stated.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and Shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a Patient
1. Adding a patient

   1. Test case: `addPatient i/T0123456A n/John Doe b/2001-05-02 p/98765432 e/johnd@example.com a/John street, block 123, #01-01` <br>
     Expected: New patient with NRIC T0123456A is added. Details of the added patient is shown in the status message.

   2. Command with invalid value: `addPatient i/x ...`, `ap i/T0123456A n/John Doe b/x ...` (where x is an invalid value for the parameter) <br>
     Expected: No patient is added. Error details shown in the status message.

   2. Other incorrect commands to try: `add`, `addP`, `...`  <br>
      Expected: Similar to previous.


2. Adding a duplicate patient
  
   1. Prerequisites: Completing the first test case for Adding a Patient.

   2. Test case: `addPatient i/T0123456A n/John Doe b/2001-05-02 p/98765432 e/johnd@example.com a/John street, block 123, #01-01` <br>
   Expected: The error message *This patient already exists in CLInic* should be displayed in the status message.


### Deleting a Patient

1. Deleting a patient

   1. Prerequisites: Have a patient with NRIC T0123456A in CLInic. 

   1. Test case: `deletePatient i/T0123456A`<br>
      Expected: Patient with corresponding NRIC T0123456A is removed. Details of the deleted patient is shown in the status message.
   
   1. Test case: `deletePatient i/T0123456`<br>
      Expected: No patient is deleted. Error details shown in the status message.

   1. Other incorrect commands to try: `deletePatient`, `deletePatient x`, `...` (where x is an invalid NRIC)<br>
      Expected: Similar to previous.


### Editing a Patient

1. Editing a patient while all patients are being shown

   1. Prerequisites: Have a patient with NRIC T0123456A in CLInic. 

   1. Test case: `editPatient i/T0123456A newp/91234567 newe/johndoe@example.com`<br>
      Expected: Patient with corresponding NRIC T0123456A is successfully edited. Details of the edited patient is shown in the status message.

   1. Test case: `editPatient i/T0123456A`<br>
      Expected: No patient is edited. The error message *At least one field to edit must be provided.* should be displayed in the status message.

   1. Test case: `editPatient i/x ...` (where x is an invalid NRIC) <br>
      Expected: No patient is edited. Error details shown in the status message.

   1. Other incorrect commands to try: `edit`, `editP`, `...` <br>
      Expected: Similar to previous.

### Finding a Patient

1. Finding a patient while all patients are being shown
   
   1. Test case: `findPatient n/John`<br>
      Expected: Patients with name starting with 'John' are successfully listed.

   1. Test case: `findPatient i/T01`<br>
      Expected: Patients with NRIC starting with 'T01' is successfully listed.

   1. Test case: `findPatient n/John i/T01`  <br>
      Expected: The error message *Find by either NRIC or name, not both!* should be displayed in the status message.

   1. Other incorrect commands to try: `fin`, `find`, `...` <br>
      Expected: Error details shown in the status message.

### Adding an Appointment
1. Adding an appointment 

   1. Prerequisites: Have a patient with NRIC T0123456A in CLInic. 

   1. Test case: `addAppt i/T0123456A d/2024-05-20 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in` <br>
     Expected: New appointment for patient with NRIC T0123456A is added. Details of the added appointment is shown in the status message.

   1. Test case : `addAppt i/T0123456A d/2024-05-20 from/12:00 to/11:30 t/Medical Check-up note/Routine check-in` <br>
      Expected: The error message *End time of appointment cannot be earlier than start time.* should be displayed in the status message.

   2. Command with invalid value: `addAppt i/x ...`, `addAppt i/T0123456A d/x ...` (where x is an invalid value for the parameter) <br>
     Expected: No appointment is added. Error details shown in the status message.

   2. Other incorrect commands to try: `add`, `addA`, `...`  <br>
      Expected: Similar to previous.

2. Add appointment that overlaps with existing appointment for the same patient. 

   1. Prerequisites: Have a patient with NRIC T0123456A in CLInic. 
   
   2. Test case: `addAppt i/T0123456A d/2024-05-20 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in` <br>
   Expected: The error message *New appointment overlaps with an existing appointment for the same patient* should be displayed in the status message.

### Deleting an Appointment
1. Deleting an appointment 

   1. Prerequisites: Have an appointment for a patient with NRIC T0123456A on 2024-05-20 from 11:00 in CLInic.

   1. Test case: `deleteAppt i/T0123456A d/2024-05-20 from/11:00` <br>
     Expected: Appointment with appointment details specified is removed. Details of the deleted appointment is shown in the status message.

   1. Test case : `deleteAppt i/x d/x from/x` (where x is an invalid value for the parameter) <br>
      Expected: No appointment is deleted. Error details shown in the status message.

   2. Other incorrect commands to try: `delete`, `deleteA`, `...`  <br>
      Expected: Similar to previous.

### Editing an Appointment

1. Editing an appointment 
  
   1. Prerequisites: Have an appointment for a patient with NRIC T0123456A on 2024-05-20 from 11:00 in CLInic.

   1. Test case: `editAppt i/T0123456A d/2024-05-20 from/11:00 newd/2024-05-21`<br>
      Expected: Appointment with specified appointment details is successfully edited. Details of the edited appointment is shown in the status message.

   1. Test case: `editAppt i/T0123456A`<br>
      Expected: No appointment is edited. The error message *At least one field to edit must be provided.* should be displayed in the status message.

   1. Test case: `editAppt i/x d/x from/x ...` (where x is an invalid value for the parameter) <br>
      Expected: No appointment is edited. Error details shown in the status message.

   1. Other incorrect commands to try: `edit`, `editA`, `...` <br>
      Expected: Similar to previous.

2. Edit appointment that overlaps with existing appointment for the same patient. 
   1. Prerequisites: Add two appointments with the following commands:<br>
      - `addAppt i/T0123456A d/2024-05-19 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in` <br>
      - `addAppt i/T0123456A d/2024-05-20 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in`

   2. Test case: `editAppt i/T0123456A d/2024-05-19 from/11:00 newd/2024-05-20` <br>
   Expected: The error message *Edited appointment information overlaps with an existing appointment for the same patient* should be displayed in the status message.

### Finding an Appointment

1. Finding an appointment while all appointments are being shown

   1. Test case: `findAppt i/T0123456A`<br>
      Expected:  Appointments for patient with NRIC 'T0123456A' are successfully listed.

   1. Test case: `findAppt d/2024-05-20`<br>
      Expected: Appointments on the date '2024-05-20' are successfully listed.

   1. Test case: `findAppt from/11:00`  <br>
      Expected: Appointments starting on or after 11:00 on any date are successfully listed.

   1. Other incorrect delete commands to try: `fin`, `find`, `...` <br>
      Expected: Error details shown in the status message.

2. Finding an appointment while on Day-View

   1. Prerequisites: Have an appointment with the specified details `i/T0123456A d/2024-05-20 from/11:00` in CLInic.

   1. Test case: `findAppt i/T0123456A`<br>
      Expected:  A switchView will occur to switch to Overall-View. <br>
      Appointments for patient with NRIC 'T0123456A' are successfully listed.

### Marking an Appointment

1. Marking an appointment 
   
   1. Prerequisites: Have an appointment with the specified details `i/T0123456A d/2024-05-20 from/11:00` in CLInic.

   1. Test case: `mark i/T0123456A d/2024-05-20 from/11:00`<br>
      Expected:  Appointment with appointment details specified is marked. Details of the marked appointment is shown in the status message.

   1. Test case : `mark i/x d/x from/x` (where x is an invalid value for the parameter) <br>
      Expected: No appointment is marked. Error details shown in the status message.

   2. Other incorrect commands to try: `m`, `markAppointment`, `...`  <br>
      Expected: Similar to previous.

### Unmarking an Appointment

1. Unmarking an appointment 
   
   1. Prerequisites: Have an appointment with the specified details `i/T0123456A d/2024-05-20 from/11:00` in CLInic.
   
   1. Test case: `unmark i/T0123456A d/2024-05-20 from/11:00`<br>
      Expected:  Appointment with appointment details specified is unmarked. Details of the unmarked appointment is shown in the status message.

   1. Test case : `unmark i/x d/x from/x` (where x is an invalid value for the parameter) <br>
      Expected: No appointment is unmarked. Error details shown in the status message.

   2. Other incorrect commands to try: `um`, `unmarkAppointment`, `...`  <br>
      Expected: Similar to previous.

### Saving Data

1. Dealing with missing data files

   1. Delete CLInic.json within the data folder where your jar file is located. `data/CLInic.json`

   2. Launch CLInic. <br> 
      Expected: CLInic should display a list with sample data. <br>

2. Dealing with corrupted data files

   1. Fields in CLInic.json are modified to become invalid. E.g change date field to null. 

   2. Launch CLInic. <br> 
      Expected: CLInic displays an empty list with warnings sent in the console. <br>

--------------------------------------------------------------------------------------------------------------------

## Appendix: Planned Enhancements

Team size: 5

### Patient Management

#### 1. Compatibility with Foreign Patients
**Current Issues:** CLInic currently restricts a patient's ID to be a Singaporean NRIC or FIN number, restricts a patient's phone number to be a Singaporean phone number with 8 digits and is not compatible with long foreign names exceeding 55 characters. <br/>
**Planned Enhancement:** We plan to accommodate passport numbers, foreign phone numbers and longer patient names in the system. However, to do this, we need more research into how we can give less restriction but yet validate the fields accordingly to prevent erroneous data entries. 
For example, we could consider including the nationality of the patient and validating based on that, or perhaps giving a warning to the user to take note of these details.

#### 2. Less Restriction on Character Limits for Various Fields
**Current Issues:** CLInic currently restricts addresses, tags, notes, etc to have less than some number of characters. E.g. Address should have less than 60 characters. We have received tester feedback that these constraints could be very limiting, especially for long addresses. <br/>
**Planned Enhancement:** We plan to broaden restrictions on character limits, especially on address, to accommodate longer inputs. However, we will need to ensure that these entries can be viewed on the user interface with no issues. To do so, we could consider using 
wrapping or other UI capabilities.

#### 3. Case Sensitivity for Commands and IDs
**Current Issues:** CLInic is currently case-sensitive for command and NRIC input. <br/>
**Planned Enhancement:** To support faster typing, we plan to allow for ID input to be not case-sensitive in future iterations. Also, for commands to be case-insensitive, e.g. `deletePatient` should work as well. 

#### 4. Compatibility with Names with Special Symbols and Characters
**Current Issues:** The current restrictions for names do not allow for special characters, such as in "S/O" or "D/O". Although the workaround such as "SO" and "DO" exists, we hope to accommodate such names in the future. <br/>
**Planned Enhancement:**  We plan to account for this by removing strict restrictions of no special characters, but rather allow exceptional symbols that may be used in names.

#### 5. More Comprehensive NRIC Validation
**Current Issues:** CLInic does not support NRIC validation in line with Singapore's NRIC checksum algorithms. This means that there are no checks for invalid starting alphabets, or checks to ensure that the start of the NRIC is in line with the DOB given. <br/>
**Planned Enhancement:** As we work towards building stronger NRIC validation, we plan to first validate this for patients born after 01/01/1968, which was when this synchronisation was implemented as seen <a href="https://www.spic.com.sg/national-identification-numbers-and-the-nric/" target="_blank" >here</a>. Afterwards, we will need to conduct more research into the algorithms available to check if an NRIC is valid or not.

<br/> 

### Appointment Management

#### 6. Overnight Clinic Compatibility
**Current Issues:** CLInic is currently catered towards day clinics that work regular hours. Therefore, adding overnight appointments is not possible. Furthermore, the "today" in Day-View is taken to be the date at which the application was launched, CLInic will not auto-sync at 12am going into a new day. <br/>
**Planned Enhancement:** We plan to make the feature for adding and editing appointments to allow for a start date, start time, end date and end time. Along with this, day-view will be updated to show live appointments that start on the current date or spans the current date as well.

#### 7. Improving Logic for Editing of Marked Appointments
**Current Issues:** Currently, an appointment remains marked even if it is edited to a future time. <br/>
**Planned Enhancement:** We plan to automatically unmark an appointment when it is moved to a future time and inform the user accordingly. This is to accommodate for the intuitive understanding that future appointments should be likely unmarked by default.

<br/> 

### General Trouble-shooting

#### 8. Improving Specificity of Error Messages
**Current Issues:** Currently, CLInic does not flag edits that give the exact same details as before or flag marks that attempt to mark an already marked appointment. <br/>
**Planned Enhancement:** We plan to handle this as an error in the future, such that you will not mistakenly believe an edit had been made even if it hadn't. We could consider a warning given to the user to inform them that they have inputted 
the same details as the current appointment.

#### 9. Improving the Accessibility and Navigation to Help
**Current Issues:** Currently, using the `help` command opens up a pop-up which requires the user to use the mouse and navigate to the user guide link to see the commands available. <br/>
**Planned Enhancement:** We plan to include an in-built help message to orientate the user to the list of commands available without needing to navigate to external links. A simple list of commands could be provided in the command feedback within CLInic instead.

#### 10. Improving the Dummy Data 
**Current Issues:** Currently, the dummy data that is loaded when a user first uses CLInic shows tags that are not examples of medical allergies. <br/>
**Planned Enhancement:** We plan to include a wider variety of dummy data, especially including the specific use cases of tags to tag medical allergies as that is one of the main scenarios we intended for it to be used for.


--------------------------------------------------------------------------------------------------------------------

## Appendix: Effort

### Summary

AB3 only deals with one entity type, `Person`, or `Patient` as refactored in CLInic.
In building onto AB3 to develop CLInic, we needed to implement a new module, `Appointment` which has a many-to-one relationship with a `Patient`.
This brought about new logic, commands and other improvements that were developed.

Overall, the main effort in the development process went towards:
1. **Building the `model.appointment` package**
    * Creating accompanying classes for each of the fields and corresponding validation.
    * Ensuring compatibility of `Appointment` with other classes, such as `AddressBook`, so that they be managed and stored.
    * Implementation of `Appointment` classes were designed to be similar to `Patient` classes to allow for easy maintenance.

1. **Adapting the existing `Person` module into `Patient`**
    * Refactoring `Person` to `Patient` to standardise terminologies.
    * Updating the `model.patient` package with `NRIC` compatibility.

1. **Adding new features to suit the new adapted use case**
    * Introduction of new commands such as `addAppt`, `findAppt`, `markAppt`, etc to allow efficient management of patients and their appointments.
    * Modifying existing commands such as `add` and `edit` to `addPatient`, `editPatient` respectively and ensuring `NRIC` compatibility.

1. **Adding JUnit tests for new and existing features and packages**
    * JUnit tests comprehensively test that features and packages work as intended.

1. **User interface improvements to support efficient management of patient and appointments**
    * Implemented new panel to display appointments alongside patients in Overall-View.
    * Introduced new view, Day-View, to show all appointments for the day, allowing users to see upcoming appointments.
    * Colour-coded appointments to allow visual differentiation of appointments that are completed, missed or neither.
    * Updates to overall colour scheme to suit clinic usage.

1. **Documentation improvements**
    * Updates to User-Guide include a revamp in structure and improvements in comprehensiveness of documentation across all sections.
    * Updates to Developer-Guide include changes from AB3 and documentation of how some of the new commands were implemented.

<br/>
