---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# CLInic User Guide

Welcome to **CLInic**, your dedicated digital assistant that can help you efficiently manage your patients and their appointments.

CLInic is tailored specifically for clinic assistants like yourself! Our goal? To create a seamless appointment management experience, allowing you more time to focus on what truly matters - your patients!

We understand that managing appointments in a clinic can be complex, but with CLInic, we hope to simplify this process for you. No more struggling with messy user interfaces and having too many buttons to click! Our user-friendly interface and intuitive commands make it easy for you to keep track of your patients and their appointments.

So, let's get started! Whether you're familiar with the Command Line Interface (CLI) or need a bit of guidance, we're here to guide you every step of the way. Follow this User Guide to uncover the potential of CLInic!


<!-- * Table of Contents -->
<page-nav-print />


---

## Using this guide

This User Guide contains all the essential information you need to use CLInic. For new users, we have detailed sections explaining the
[**installation**](#installing-clinic)  process, the [**design**](#orientation-to-clinic) of our interface and a simple
[**tutorial**](#tutorial-adding-an-appointment-for-a-new-patient) to get you familiarised with the basic commands.

Familarise yourselves with these terminologies! These definitions will be used throughout the User Guide:

| Term           | Explanation                                                                                                                                                     |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **CLI**        | Command Line Interface, a text-based interface for interacting with software by typing commands.                                                                |
| **GUI**        | Graphical User Interface, a visual interface that allows users to interact with software using graphical elements such as windows and buttons.                  |
| **JSON**       | JavaScript Object Notation, a lightweight data-interchange format.                                                                                              |
| **NRIC**       | National Registration Identity Card, a unique identifier for individuals in Singapore.                                                                          |
| **Parameters** | An input value or field for a command                                                                                                                           |
| **Prefix**     | Keywords to tell the command what the input value is. <br/>e.g. the prefix `n/` is used for name, so `n/John` tells the command that name to be input is "John" |


Also, if you see these information boxes scattered throughout the User Guide, this is what they mean:

<box type="warning" seamless>

**Caution:** Warns of potential causes of error
</box>

<box type="info" seamless>

**Note:** Highlights useful information
</box>

<box type="success" light>

**Success**: Highlights successful execution
</box>
<box type="wrong" light>

**Error**: Highlights failed executions

<box type="tip" seamless>

**What to Do:** Highlights possible solutions to the problem
</box>
</box>

---

## Quick start

### Installing CLInic

Let's install CLInic together! Here are the step-by-step instructions on getting CLInic started. Don't worry, just follow the instructions for your operating system and you should be good to go!

1. System Requirements: Ensure you have [**Java 11**](https://www.oracle.com/java/technologies/downloads/#java11) or above installed on your computer.

2. Download the latest `CLInic.jar` from [**here**](https://github.com/AY2324S2-CS2103T-F10-3/tp/releases).

3. Save the file to a location on your computer that will serve as your home folder for CLInic.

4. Open a command terminal on your computer. If you're unsure how to do this, we'll walk you through it.

    - **Windows**: Press `Win + R`, type `cmd`, and press `Enter`.
   
    - **MacOS**: Press `Cmd + Space`, type `Terminal`, and press `Enter`.
    - **Linux**: Press `Ctrl + Alt + T`.
   
5. Navigate to the folder where you saved the `CLInic.jar` file. If you saved it in your `Downloads` folder, you can use the following commands:

    - **Windows**: `cd Downloads`
   
    - **MacOS**: `cd ~/Downloads`
    - **Linux**: `cd ~/Downloads`

6. Type `java -jar CLInic.jar` command into terminal to run the application.<br>
   A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.

   <img src="images/UiStart.png" alt="Ui" width="600"/>

Simple, wasn't it? Let's now orientate you to the GUI and how CLInic works.

--- {.dashed}

<br/>

### Orientation to CLInic

There are two different views in CLInic. The command section in both views are the same. <br/>

#### Overall-View

**Overall-View** is the default view, allowing you to see all patients and appointments on one screen. This view is useful for finding patients or appointments, which you will learn how to do later!

![UiOverallView](images/Ui.png)

#### Day-View

**Day-View** is the alternate view, allowing you to see all appointments scheduled today. This view is useful for you to manage upcoming appointments in the day.

![UiDayView](images/UiDayView.png)

#### Appointment Colour Coding

Appointments are colour-coded in CLInic, allowing you to easily identify the status of each appointment. The breakdown is as follows:

![UiApptColors](images/UiApptColors.png)



For your easy reference, the table below outlines the purpose of each section.

| Section                          | Purpose                                                                                                |
|----------------------------------|--------------------------------------------------------------------------------------------------------|
| **Command Input**                | This is where you will write the commands you will learn later.                                        |
| **Command Feedback**             | This feedback box will show you success or error messages depending on the validity of commands input. |
| **View Toggle**                  | These buttons allow you to toggle between the Overall-View or Day-View.                                |
| **Patient List**                 | Shows you the list of patients in CLInic.                                                              |
| **Appointment List**             | Shows you the list of appointments scheduled in CLInic.                                                |
| **Appointment List for the Day** | Shows you the list of all appointments scheduled in the day, correct as at launch of the app.          |

Great! You are now oriented to the layout of CLInic.

--- {.dashed}

<br/>

### Tutorial: Adding an appointment for a new patient

Now that you've understood the layout of CLInic, let's get started with registering your very first patient and scheduling an appointment for them!
This step-by-step tutorial covers the essential commands of adding a patient and an appointment. Follow along with the instructions given and learn to write
your very first commands.

If this is your first time launching CLInic, you might see sample data listed. Let's clear them before adding our new patient!

* { text="1" t-size="32px" }

  On the Command Input Box, type `clear` and press Enter on your keyboard.

  ![UiTutStep1](images/UiTutorial1.png)
  <box type="info" seamless>

  **Note:** Commands in CLInic are case-sensitive! In order to ensure that your commands are recognised, ensure that they are of the same
  case as the commands given in the guide.
  </box>

  After executing the command, you should see that all the entries have been deleted (see right side of the image above).
  Wow! Just like that, you've successfully executed your very first command. Let's move on to something more challenging.

  Your first patient, Bernice, enters the clinic. Before we can schedule any appointments, we need to first register her patient details.
  This is achieved using the `addPatient` command. Let's learn how to use that.

* { text="2" t-size="32px" }

  On the Command Input Box, type the following command:

  `addPatient i/T0123456A n/Bernice Yu b/2001-12-25 p/98765432 e/bernice@example.com a/Blk 555 Changi Ave 5 S555555`

  Now press Enter on your keyboard.

  ![UiTutStep2](images/UiTutorial2.png)

  You have now registered a patient of **NRIC**: T0123456A, **Name**: Bernice Yu, **Date of Birth (DOB)**: 2001-12-25, **Email**: bernice<span></span>@example.com
  and **Address**: Blk 555 Changi Ave 5 S555555 as seen on the right.

    <box type="info" seamless>

  **Note:** In CLInic, we use prefixes to denote the input parameters. The prefix `i/` is reserved for the patient's NRIC.
  Therefore, in this case, `i/ T0123456A` means that patient we wish to add has the NRIC `T0123456A`. The remaining prefixes
  `n/`, `b/`, `p/`, `e/` and `a/` take in the patient's name, birth date, phone number, email and address respectively.
  The details and constraints of the parameters required for each command will be documented in the features section.
  </box>

  Awesome, you're learning fast! Now that Bernice is registered in our system, we can finally create an appointment for her.
  We will need the `addAppt` command to do this!

* { text="3" t-size="32px" }

  On the Command Input Box, type the following command

  `addAppt i/ T0123456A d/ 2024-05-06 from/ 10:00 to/ 11:00 t/ Medical Check-up`

  Now press Enter on your keyboard.

  ![UiTutStep3](images/UiTutorial3.png)

  You have now created an appointment for Bernice using her **NRIC**: T0123456A. The details of her appointment are **Date**: 2024-05-06,
  **Start Time**: 10:00, **End Time**: 11:00 and **Appointment Type**: Medical Check-up as seen on the right.

    <box type="info" seamless>

  **Note:** In CLInic, you will see that NRIC is used in most commands. We use NRIC numbers to identify each patient uniquely!
  </box>

  Well done! You have completed the tutorial! Your new patient, Bernice, now has an appointment scheduled for her.

  Continue on to the [**Features**](#features) section below to learn more about the full list of commands available in CLInic!
  If you are lost at anytime, typing the command **`help`** and pressing Enter will open the help window with a link that will lead you right back to this User Guide.

---

**Appointment**
* An appointment belongs to one patient. 
* Each appointment is identified by a unique `NRIC`, `DATE` and `START_TIME`
* An appointment has: NRIC, Date, Start Time, End Time, Appointment Type, Note
* An appointment can be: added, deleted, edited, found, marked, unmarked

Restrictions:
* An appointment **cannot** be added if it overlaps with an existing appointment for the same patient. Otherwise, it will be flagged as seen [here](#31-adding-an-appointment-addappt-or-aa).
  * CLInic allows appointments of different patients to overlap as they may be seen concurrently by different doctors or have different tests.
* An appointment **cannot** span across different days or be overnight.
  * CLInic allows appointments to be made anytime within a single day **but not overnight** to simplify daily operations and avoid ambiguity. However, plans for future extensions can be found [here](#appendix-planned-enhancements).

---

## Features

CLInic is designed to keep track of your patient data and appointment schedules. We have 4 broad categories of features:

1. [Seeking Help](#help)
2. [Patient Commands](#patientCommands)
3. [Appointment Commands](#appointmentCommands)
4. [General Commands](#generalCommands)

<box type="info" seamless>

**Notes about the command format:**<br>

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used zero or more times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  
**Notes about the data:**<br>

* Initiating CLInic without CLInic.json will result in the loading of dummy data into CLInic.
</box>

<box type="warning" seamless>

Commands are case-sensitive, including shorthand formats.<br>
  e.g Invalid commands like `AddPatient`, `addpatient`, `Addpatient`, `AP`, `aP` and `Ap` will not be recognised by CLInic.

Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addPatient n/NAME`, `NAME` is a parameter which can be used as `addPatient n/John Doe`.
</box>

--- {.dashed}

<br/>

### <a name="help"></a>1. Viewing help : `help`

If you are facing any issues while using CLInic, you can use this help command which will provide you with a link to this User Guide!

**Format:**
<box>

`help`
</box>

<box type="success" light>

**Expected Outcome**: A window should pop up, with a link that brings you right back to this User Guide!
![help message](images/helpMessage.png)

</box>



--- {.dashed}

<br/>

### <a name="patientCommands"></a>2. Patient Commands

CLInic stores your patients with the following information fields: NRIC (unique), Name, Date of Birth, Phone Number, Email, Address, Medical Allergies (if any).

**Input Fields:**

 Prefix | Field                                                                            | Constraints                                                                                                                                                                                                                                                       |
|----------------|----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **i/**         | Unique ID in Singapore's context - NRIC (e.g. `T0123456A`)                       | - Possible invalid NRICs not accounted for due to uncertainty in checksum of Singapore's system and FIN numbers. <br/> - Also allowing for NRICs beyond current date e.g. `T99...` to allow flexibility of app without having to constantly readjust fields <br/> | 
| **n/**         | Name of patient.                                                                 | - Restricted to alphanumeric characters. <br/> - Extra spacing is allowed within the name to allow for user convenience and flexibility.                                                                                                                          |
| **b/**         | Date of birth of patient.                                                        | - Dates must be in YYYY-MM-DD format.<br/>- Only allows valid dates after 1990-01-01.                                                                                                                                                                             |
| **p/**         | Emergency contact number.                                                        | - Only Singapore phone numbers allowed. <br/> - Duplicate phone numbers allowed in case of children with parent's contact number.                                                                                                                                 |
| **e/**         | Email of patient.                                                                | NA                                                                                                                                                                                                                                                                |
| **a/**         | Address of patient.                                                              | NA                                                                                                                                                                                                                                                                |
| **t/**         | Tag attached to specify patient's medical allergies. e.g. `Paracetamol, Insulin` | - No constraints to allow for flexiblility, although it is recommended to use this tag for medical allergies.
| **newn/**      | New name of patient if change required.                                          | NA                                                                                                                                                                                                                                                                |
| **newp/**      | New phone number of patient if change required.                                  | NA                                                                                                                                                                                                                                                                |
| **newe/**      | New email of patient if change required.                                         | NA                                                                                                                                                                                                                                                                |
| **newa/**      | New address of patient if change required.                                       | NA
| **newt/**      | New tag of patient if change required.                                           | NA

<box type="wrong" light>

**Possible invalid input fields.**
<box type="tip" seamless>

Some of the inputs you have keyed in may be invalid, check out the constraints for the input fields above to understand what values CLInic accepts.
</box>
</box>

<br/>

### <a name="addPatient"></a>2.1 Adding a patient: `addPatient` OR `ap`

Use this command if you wish to add a new patient to CLInic. You would be required to specify important patient personal information and can include the patient's medical allergies, if any.

**Format:**
<box>

Full: `addPatient i/NRIC n/NAME b/DOB p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/MEDICAL_ALLERGY]…​ `

Shorthand: `ap i/NRIC n/NAME  b/DOB p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/MEDiCAL_ALLERGY]…​`
</box>

<box type="warning" seamless>

A patient must have a unique NRIC in CLInic.

</box>

**Examples:**
<box>

Adds a patient without any medical allergies.

>`addPatient i/T0123456A n/John Doe b/2001-05-02 p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
</box>
<box>

Adds a patient with 2 medical allergies (Insulin and Paracetamol), using the shorthand format.

>`ap i/S9876543A n/Betsy Crowe b/1998-02-03 t/Insulin e/betsycrowe@example.com a/Crowe street, block 234, #12-12 p/91234567 t/Paracetamol`
</box>

<box type="success" light>

**Expected Outcome**:
![Add patient expected outcome](./images/addPatientSuccess.png)

</box>

<box type="wrong" light>

**This patient already exists in CLInic.**

<box type="tip" seamless>

There already exists a patient with the NRIC you specified. To view the details of that patient, you can use the [findPatient](#findPatient) command.
</box>
</box>

<br/>

### 2.2 Deleting a patient : `deletePatient` OR `dp`

Use this command if you wish to delete a patient from CLInic.
Corresponding appointments for the specified patient will be deleted too.

<box type="warning" seamless>

Corresponding appointments for the specified patient will be deleted from CLInic too.

</box>

**Format:**
<box>

Full: `deletePatient i/NRIC`

Shorthand: `dp i/NRIC`
</box>

**Examples:**
<box>

Delete patient with NRIC number S9876543A.

>`deletePatient i/S9876543A`
</box>

<box type="wrong" light>

**The NRIC provided is not found in the system.**

<box type="tip" seamless>

CLInic does not have a patient with the provided NRIC, please double-check the NRIC provided.
</box>
</box>

<br/>

### 2.3 Editing a patient : `editPatient` OR `ep`

Use this command if you wish to edits an existing patient in CLInic.

**Format:**
<box>

Format: `editPatient i/NRIC [newn/NEW_NAME] [newb/NEW_DOB] [newp/NEW_PHONE] [newe/NEW_EMAIL] [newa/NEW_ADDRESS] [newt/NEW_MEDICAL_ALLERGY]…​`

Shorthand: `ep i/NRIC [newn/NEW_NAME] [newb/NEW_DOB] [newp/NEW_PHONE] [newe/NEW_EMAIL] [newa/NEW_ADDRESS] [newt/NEW_MEDICAL_ALLERGY]…​`
</box>

<box type="warning" seamless>

Existing values will be updated to the input values.

When editing tags, existing tags of the patient will be removed, i.e., adding tags is not cumulative. Use t/ to remove all tags.

</box>

**Examples:**
<box>

Edits the phone number and email address of the patient with NRIC:`T0123456A` to be `91234567` and `johndoe@example.com` respectively.

>`editPatient i/T0123456A newp/91234567 newe/johndoe@example.com`
</box>

<box>

Edits the name of the patient with NRIC:`S8765432Z` to be `Betsy Crower` and clears all existing tags.

>`editPatient i/S98765432A newn/Betsy Crower newt/`
</box>

<box>

Executes the above command but uses shorthand format

>`ep i/S98765432A newn/Betsy Crower newt/`
</box>

<box type="wrong" light>

**At least one field to edit must be provided.**

<box type="tip" seamless>

CLInic requires that at least one optional field is provided to execute the `editPatient` command.
</box>
</box>

<box type="wrong" light>

**The NRIC provided is not found in the system.**

<box type="tip" seamless>

CLInic does not have a patient with the provided NRIC, please double-check the NRIC provided or create a Patient using the [addPatient](#addPatient) command.
</box>
</box>

<br/>

### <a name="findPatient"></a>2.4 Finding patients: `findPatient` OR `fp`

Use this command if you wish to finds patients whose name OR NRIC fit the given keywords.

**Format:**
<box>

Format: `findPatient n/NAME_KEYWORD [MORE_NAME_KEYWORDS]` OR `findPatient i/NRIC_KEYWORD`

Shorthand: `fp n/NAME_KEYWORD [MORE_NAME_KEYWORDS]` OR `fp i/NRIC_KEYWORD`
</box>

<box type="warning" seamless>

The search is case-insensitive. e.g `hans` will match `Hans`.

Partial words will be matched only if the start of the word is the same e.g. `T01` will match `T0123456A`.

To accommodate for future extensions, special characters can be searched. However, no search results may be found as special characters are currently not supported in `NAME` and `NRIC`.

If currently on Day View, this command will cause a `switchView` to automatically occur.
</box>

<box type="wrong" light>

**Find by either NRIC or name, not both!**

<box type="tip" seamless>

CLInic currently only supports finding patients by a single field. 
</box>
</box>

<br/>

#### 2.4.1 Name Search

**Examples:**
<box>

Find all patients with name beginning with `john`

>`findPatient n/John`
</box>

**Examples:**
<box>

Find all patients with name beginning with either `alex` or `david`, using shorthand command

>`fp n/ alex david`
</box>

<box type="success" light>

**Expected Outcome**:
![Find patient by name expected outcome](./images/findPatientNameSuccess.png)

</box>

<br/>

#### 2.4.2 NRIC Search
**Examples:**
<box>

Find all patients with NRIC born in the year 2001, with NRIC starting with `t01`

>`findPatient i/t01`
</box>


<box type="success" light>

**Expected Outcome**:
![Find patient by NRIC expected outcome](./images/findPatientNricSuccess.png)

</box>

<box type="wrong" light>

**You have provided more than one word of NRIC keywords to match.**

<box type="tip" seamless>

CLInic does not provide support for finding patients with different starting NRICs. Please only provide one starting NRIC.

e.g. `i/T01 T012` will NOT return `T0123456A` as the given keyword is `T01 T012`
</box>
</box>



--- {.dashed}

### <a name="appointmentCommands"></a> 3. Appointment Commands

**Input Fields:**

| Prefix | Field                                                                                      | Constraints                                                                                                                                                                                                                                                                                                                                                                   
|----------------------|--------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **i/**               | Unique ID in Singapore's context - NRIC <br/> (e.g. `T0123456A`) <br/> to identify patient | - Possible invalid NRICs not accounted for due to uncertainty in checksum of Singapore's system and FIN numbers. <br/> - Also allowing for NRICs beyond current date e.g. `T99...` to allow flexibility of app without having to constantly readjust fields <br/> - For foreign visitors, placeholder NRIC eg. `K0000001A`, since foreigners should not be staying long-term. |
| **d/**               | Date of appointment in YYYY-MM-DD format e.g. `2024-02-20`                                 | - Valid dates after 1990-01-01                                                                                                                                                                                                                                                                                                                                                |                                                                                                                                                                                                                                                         |
| **from/**            | Start time of appointment in HH:mm format e.g. `13:00`                                     | - Start time has to be earlier than end time                                                                                                                                                                                                                                                                                                                                  |
| **to/**              | End time of appointment in HH:mm format e.g. `14:30`                                       | - End time has to be later than start time <br/> - To timing is taken to be on same day as `from/`                                                                                                                                                                                                                                                                            |
| **t/**               | Appointment type e.g. `Medical check-up`                                                   | NA                                                                                                                                                                                                                                                                                                                                                                            
| **note/**            | Additional notes for appointment e.g. `X-ray`                                              | NA                                                                                                                                                                                                                                                                                                                                                                            
| **newd/**            | New date of appointment if change required.                                                | NA                                                                                                                                                                                                                                                                                                                                                                            
| **newfrom/**         | New start time of appointment if change required.                                          | NA                                                                                                                                                                                                                                                                                                                                                                            
| **newto/**           | New end time of appointment if change required.                                            | NA                                                                                                                                                                                                                                                                                                                                                                            
| **newt/**            | New type of appointment if change required.                                                | NA                                                                                                                                                                                                                                                                                                                                                                            
| **newnote/**         | New note of appointment if change required.                                                | NA                                                                                                                                                                                                                                                                                                                                                                            

**Possible invalid input fields.** 

Some of the inputs you have keyed in may be invalid, check out the constraints for the input fields above to understand what values CLInic accepts.

### 3.1 Adding an Appointment: `addAppt` OR `aa`

Use this command if you wish to add an appointment to CLInic. You would be required to specify:

1. who the patient is (identified by their NRIC)
2. their desired time frame
3. their purpose of visit
4. include additional notes for the appointment.

**Format:**

<box>

Full: 

> `addAppt i/NRIC d/DATE from/START_TIME to/END_TIME t/APPOINTMENT_TYPE [note/NOTE]` <br/>

Shorthand: 

> `aa i/NRIC d/DATE from/START_TIME to/END_TIME t/APPOINTMENT_TYPE [note/NOTE]`

</box>

<box type="warning" seamless>

Patient with this NRIC **must exist within CLInic**. <br/>

You cannot schedule an appointment for a patient on a date before their date of birth.</box>

<box type="info" seamless>

If new appointment overlaps with an existing appointment for the same patient, all overlapping appointments will be shown on Overall View. If currently on Day View, see <a href=#switchView>here</a>.

</box>

#### 3.1.1 Use Cases

**Examples:**
<box>

Add appointment for `john` whose IC is `T0123456A` and is coming for a `Medical Check-up` on `2024-02-20` from `11:00` to `11:30` with a note `Routine check-in`

> `addAppt i/T0123456A d/2024-02-20 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in`
</box>

<box>

Add appointment, using shorthand command, with above example

> `aa i/T0123456A d/2024-02-20 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in`

</box>

<box type="success" light>

**Expected Outcome**:

![Add appointment expected outcome](./images/AddApptSuccess.png)

</box>

<box type="wrong" light>

**You have provided an appointment that carries onto the next day.**

<box type="tip" seamless>

CLInic does not provide support for overnight appointments. Please only provide an appointment within the same day.

e.g. `d/2024-02-20 from/23:00 to/01:00` will not be accepted as the appointment spans across two days.
</box>
</box>

### 3.2 Deleting an Appointment: `deleteAppt` OR `da`

Use this command if you wish to delete an appointment from CLInic. You would be required to specify the patient's NRIC, the date and start time of the appointment.

**Format:**

<box>

Full: 

> `deleteAppt i/NRIC d/DATE from/START_TIME` <br/>

Shorthand: 

> `da i/NRIC d/DATE from/START_TIME`

</box>

<box type="warning" seamless>

Appointment with the stated details **must exist within CLInic**.

You would not need to input `END_TIME` as same patient can never have overlapping appointments, hence `START_TIME` is unique.

</box>

#### 3.2.1 Use Cases

**Examples:**

<box>

Delete appointment for `john` whose IC is `T0123456A` on `2024-02-20` starting from `11:00`

> `deleteAppt i/T0123456A d/2024-02-20 from/11:00`

</box>

<box>

Delete appointment, using shorthand command, with above example

> `da i/T0123456A d/2024-02-20 from/11:00`

</box>

### 3.3 Editing an Appointment : `editAppointment` OR `ea`

Use this command if you wish to edit an existing appointment in CLInic. 
You would require the appointment details, specifically patient's NRIC, date, and start time.
Existing values will be updated to the input values.

**Format:**

<box>

Full: 

> `editAppt i/NRIC d/DATE from/START_TIME [newd/NEW_DATE] [newfrom/NEW_START_TIME] [newto/NEW_END_TIME] [newt/NEW_APPOINTMENT_TYPE] [newnote/NEW_NOTE]` <br/>

Shorthand: 

> `ea i/NRIC d/DATE from/START_TIME [newd/NEW_DATE] [newfrom/NEW_START_TIME] [newto/NEW_END_TIME] [newt/NEW_APPOINTMENT_TYPE] [newnote/NEW_NOTE]`

</box>

<box type="warning" seamless>

You would need to provide at least one optional field for editing.

You would need to ensure the NRIC is valid and exists in the system.

</box>

#### 3.3.1 Use Cases

**Examples:**

<box>

Edit the date of the appointment with NRIC:`T0123456A`, DATE: `2024-02-20`, START_TIME: `11:00`, to be `2024-02-21` instead.

> `editAppt i/T0123456A d/2024-02-20 from/11:00 newd/2024-02-21`

</box>

<box>

Edit appointment, using shorthand, with above example.

> `ea i/T0123456A d/2024-02-20 from/11:00 newd/2024-02-21`

</box>

<box>

Clears note for appointments.

> `editAppt i/S8743880A d/2024-10-20 from/14:00 newnote/`

</box>

<box type="wrong" light>

**You have provided an appointment that overlaps with an existing appointment for the same patient.**

<box type="tip" seamless>

All overlapping appointments will be shown on Overall View. If currently on Day View, see <a href=#switchView>here</a>.

e.g. `d/2024-02-20 from/10:00 to/11:00` will not be accepted if there is an existing appointment for another
patient within that time frame.

</box>
</box>

### 3.4 Finding appointments: `findAppt` OR `fa`

Use this command if you wish to find appointments based on certain identifiers.
You can use any combination of the three: NRIC, date or start time.

<box>

**Format:**

Full: 

> `findAppt [i/NRIC] [d/DATE] [from/START_TIME]`

Shorthand: 

> `fa [i/NRIC] [d/DATE] [from/START_TIME]`

</box>

<box type="info" seamless>

For argument concerning TIME, all appointments that start at the given time and later than that are returned.

Fetching for TIME without DATE will return all appointments whose start from that time or later than that on any date.

If currently on Day View, this command will cause a `switchView` to automatically occur.

</box>

#### 3.4.1 Use Cases

**Examples:**

<box>

Find all appointments on `2024-02-20` starting from `11:00` and later.

> `findAppt d/2024-02-20 from/11:00`

</box>

<box>

Find all appointments, using shorthand command, with above example.

> `fa d/2024-02-20 from/11:00`

</box>

### 3.5 Marking an Appointment: `mark`

Use this command if you wish to mark an appointment from CLInic.
You would be required to specify the patient's NRIC, the date and start time of the appointment.

<box>

**Format:**

> `mark i/NRIC d/DATE from/START_TIME`

</box>

#### 3.5.1 Use Cases

**Examples:**

<box>

Mark appointment for the patient with NRIC:`T0123456A`, on `2024-02-20` from `11:00`.

> `mark i/T0123456A d/2024-02-20 from/11:00`

</box>

<box type="warning" seamless>

Appointment with the stated details **must exist within CLInic**.

You would not need `END_TIME` as same patient can never have overlapping appointments, hence `START_TIME` is sufficient.

</box>

### 3.6 Unmarking an Appointment: `unmark`

Use this command if you wish to unmark an appointment from the address book.
You would be required to specify the patient's NRIC, the date and start time of the appointment.

<box>

**Format:**

> `unmark i/NRIC d/DATE from/START_TIME`

</box>

#### 3.6.1 Use Cases

**Examples:**

<box>

Unmark appointment for the patient with NRIC:`T0123456A`, on `2024-02-20` from `11:00`.

> `unmark i/T0123456A d/2024-02-20 from/11:00`

</box>

<box type="warning" seamless>

Appointment with the stated details **must exist within CLInic**.

You would not need `END_TIME` as same patient can never have overlapping appointments, hence `START_TIME` is sufficient.

</box>

--- {.dashed}

### <a name="generalCommands"></a>4. General Commands

General commands are simple commands with no prefixes.

<box type="warning" seamless>

Any extraneous parameters for these commands will be ignored.

e.g. `list 123` will be interpreted as `list`
</box>
                                   
### <a name="list"></a>4.1 Listing all patients and appointments : `list` OR `ls`

Use this command to show a list of all patients and appointments in CLInic.

<box>

Full: `list`

Shorthand: `ls​`
</box>

### <a name="switchView"></a>4.2 Switch between Overall View and Day View : `switchView` OR `sv`

Use this command to show a list of all patients and appointments in CLInic.

<box>

Full: `switchView`

Shorthand: `sv`
</box>

### <a name="clear"></a>4.3 Clearing all entries : `clear` 

Use this command if you wish to clear all entries of patients and appointments from CLInic.

<box>

`clear`

</box>

<box type="warning" seamless>

This action is irreversible. Please proceed with caution. 

Once this command is executed, it would not be possible to restore the deleted data.

</box>

### <a name="exit"></a>4.4 Exiting the program : `exit` 

Exits CLInic.

<box>

`exit`

</box>

### Saving the data

CLInic data are saved in the hard disk automatically after any command that changes the data. You do not need to save manually.

### Editing the data file

CLInic data are saved automatically as a JSON file `[JAR file location]/data/CLInic.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, CLInic will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the CLInic to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

##### Patients
**Q**: Why are the `EMAIL` and `PHONE_NUMBER` fields compulsory?
**A**: Emergency contact details are necessary even for children or elderly. In such cases, their `EMAIL` and `PHONE_NUMBER` can be completed with their guardian's contact details.

**Q**: Will `EMAIL` accept emails without an .xx domain? Will emails like user@local, user@nus.edu.sg be accepted?
**A**: Yes, CLInic would like to allow you to have flexibility in emails accepted, as to allow compatibility with legacy systems or to align with your specific network configuration and security protocols. However, special characters such as slashes `/` are often not allowed as part of email domains or names, hence we decided to restrict that.

**Q**: Will foreign ID or phone numbers be accepted?
**A**: Foreign ID and phone numbers are currently not supported by CLInic as we roll out the basic functionalities suited to a local context. However, we have plans to implement this as seen [here](#appendix-planned-enhancements).

**Q**: Am I able to put NA for the address field?
**A**: CLInic does not allow the address field to be blank, as an address can be essential in medical emergencies. However, you can opt to fill it with a `-` if you deem fit.

**Q**: Why am I allowed to add duplicate phone numbers for different patients?
**A**: CLInic accounts for events where both a child and their parent are patients, or patients are related. In these events, these patients may decide to provide the same phone number as their contact details. 

**Q**: Am I allowed to edit a patient's NRIC?
**A**: No, CLInic does not support editing a patient's NRIC. Weighing the pros and cons, we decided on this to protect against data manipulation and errors. Should a patient change their NRIC, you can use the <a href=#addPatient>addPatient command</a>.

**Q**: 
**A**:

##### Appointments

##### General
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CLInic home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **When missing read/write permissions**, the application may not work. Ensure that read/write permissions are enabled for CLInic.

--------------------------------------------------------------------------------------------------------------------

## Glossary
| Term                 | Explanation                                                                                                                                    |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| **CLI**              | Command Line Interface, a text-based interface for interacting with software by typing commands.                                               |
| **GUI**              | Graphical User Interface, a visual interface that allows users to interact with software using graphical elements such as windows and buttons. |
| **JSON**             | JavaScript Object Notation, a lightweight data-interchange format.                                                                             |
| **NRIC**             | National Registration Identity Card, a unique identifier for individuals in Singapore.                                                         |
| **TAG**              | A keyword or term assigned to a piece of information, making it easier to search for and organize.                                             |
| **APPOINTMENT_TYPE** | The type of appointment, e.g., Medical Check-up, Blood Test, etc.                                                                              |
| **NOTE**             | Additional information or comments about an appointment.                                                                                       |

--------------------------------------------------------------------------------------------------------------------

## Prefix summary for patients
| Prefix | Field                                                                             | Caveats                                                                                                                                                                                                                                                                                                                                                                       
|-----------------|-----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **i/**          | Unique ID in Singapore's context - NRIC (e.g. `T0123456A`)                        | - Possible invalid NRICs not accounted for due to uncertainty in checksum of Singapore's system and FIN numbers. <br/> - Also allowing for NRICs beyond current date e.g. `T99...` to allow flexibility of app without having to constantly readjust fields <br/> - For foreign visitors, placeholder NRIC eg. `K0000001A`, since foreigners should not be staying long-term. |
| **n/**          | Name of patient.                                                                  | - Will restrict to alphanumeric characters to avoid parsing errors, since data is stored as JSON. <br/> - Extra spacing is allowed within the name to allow for user convenience and flexibility.                                                                                                                                                                             |                                                                 |
| **b/**          | Date of birth of patient.                                                         | - Only allows valid dates after 1 Jan 1990.                                                                                                                                                                                                                                                                                                                                   |
| **p/**          | Emergency contact number.                                                         | - Only Singapore phone numbers allowed. <br/> - Duplicate phone numbers allowed in case of children with parent's contact number.                                                                                                                                                                                                                                             |
| **e/**          | Email of patient.                                                                 |                                                                                                                                                                                                                                                                                                                                                                               |
| **a/**          | Address of patient.                                                               |                                                                                                                                                                                                                                                                                                                                                                               |
| **t/**          | Tag attached to patient for extra information. e.g. `Fall risk, Hokkien speaking` |                                                                                                                                                                                                                                                                                                                                                                               |
| **newn/**       | New name of patient if change required.                                           |                                                                                                                                                                                                                                                                                                                                                                               |
| **newp/**       | New phone number of patient if change required.                                   |                                                                                                                                                                                                                                                                                                                                                                               |
| **newe/**       | New email of patient if change required.                                          |                                                                                                                                                                                                                                                                                                                                                                               |
| **newa/**       | New address of patient if change required.                                        |                                                                                                                                                                                                                                                                                                               
| **newt/**       | New tag of patient if change required.                                            |

## Prefix summary for appointments
| Prefix | Field                                                                                      | Caveats                                                                                                                                                                                                                                                           
|----------------------|--------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **i/**               | Unique ID in Singapore's context - NRIC <br/> (e.g. `T0123456A`) <br/> to identify patient | - Possible invalid NRICs not accounted for due to uncertainty in checksum of Singapore's system and FIN numbers. <br/> - Also allowing for NRICs beyond current date e.g. `T99...` to allow flexibility of app without having to constantly readjust fields <br/> - For foreign visitors, placeholder NRIC eg. `K0000001A`, since foreigners should not be staying long-term. |
| **d/**               | Date of appointment in YYYY-MM-DD format e.g. `2024-02-20`                                 | - Valid dates after 1 Jan 1990                                                                                                                                                                                                                                    |                                                                                                                                                                                                                                                         |
| **from/**            | Start time of appointment in HH:mm format e.g. `13:00`                                     | - Start time has to be earlier than end time                                                                                                                                                                                                                      |
| **to/**              | End time of appointment in HH:mm format e.g. `14:30`                                       | - End time has to be later than start time <br/> - To timing is taken to be on same day as `from/`                                                                                                                                                                |
| **t/**               | Appointment type e.g. `Medical check-up`                                                   |
| **note/**            | Additional notes for appointment e.g. `X-ray`                                              |
| **newd/**            | New date of appointment if change required.                                                |
| **newfrom/**         | New start time of appointment if change required.                                          
| **newto/**           | New end time of appointment if change required.                                            
| **newt/**            | New type of appointment if change required.                                                |
| **newnote/**         | New note of appointment if change required.                                                |

### Additional Information
- Our commands check for the validity of the input data and will prompt the user if the input is invalid.
- Usage of prefixes (from this list above) in commands that do not require them will result in an error.
- This includes unintentional use of known prefixes in other fields
--------------------------------------------------------------------------------------------------------------------

## Command summary
| Action            | Format, Examples                                                                                                                                                                                                 |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **AddPatient**    | `addPatient i/NRIC n/NAME b/DOB p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addPatient i/T0123456A n/John Doe b/2001-05-02 p/98765432 e/johnd@example.com a/John street, block 123, #01-01`          |
| **DeletePatient** | `deletePatient i/NRIC`<br> e.g., `deletePatient i/T0123456A`                                                                                                                                                     |                                                                 |
| **EditPatient**   | `editPatient i/NRIC [newn/NEW_NAME] [newp/NEW_PHONE_NUMBER] [newe/NEW_EMAIL] [newa/NEW_ADDRESS] [newt/NEW_TAG]…​`<br> e.g.,`editPatient i/T0123456A newn/James Lee newe/jameslee@example.com`                    |
| **FindPatient**   | `findPatient n/NAME_KEYWORD [MORE_NAME_KEYWORDS]` OR `findPatient i/NRIC_KEYWORD`<br> e.g., `findPatient n/James Jake`                                                                                                        |
| **AddAppt**       | `addAppt i/NRIC d/DATE from/START_TIME to/END_TIME t/APPOINTMENT_TYPE note/NOTE`<br> e.g., `addAppt i/T0123456A d/2024-02-20 from/11:00 to/11:30 t/Medical Check-up note/Routine check-in`                       |
| **DeleteAppt**    | `deleteAppt i/NRIC d/DATE from/START_TIME` <br> e.g., `deleteAppt i/S8743880A d/2024-02-20 from/11:00`                                                                                                           |
| **EditAppt**      | `editAppt i/NRIC d/DATE from/START_TIME [newd/NEW_DATE] [newfrom/NEW_START_TIME] [newto/NEW_END_TIME] [newt/NEW_APPOINTMENT_TYPE] [newnote/NEW_NOTE]` <br> e.g., `editAppt i/T0123456A d/2024-02-20 from/11:00 newd/2024-02-21` |
| **FindAppt**      | `findAppt [i/NRIC] [d/DATE] [from/START_TIME]` <br> e.g., `findAppt i/T0123456A d/2024-02-20 from/11:00`                                                                                                         |
| **Mark**          | `mark i/NRIC d/DATE from/START_TIME` <br> e.g., `mark i/T0123456A d/2024-02-20 from/11:00`                                                                                                                       |
| **Unmark**        | `unmark i/NRIC d/DATE from/START_TIME` <br> e.g., `unmark i/T0123456A d/2024-02-20 from/11:00`                                                                                                                   |
| **List**          | `list`                                                                                                                                                                                                           
| **SwitchView**    | `switchView`                                                                                                                                                                                                     
| **Clear**         | `clear`                                                                                                                                                                                                          |
| **Exit**          | `exit`                                                                                                                                                                                                           |
| **Help**          | `help`                                                                                                                                                                                                           |

--------------------------------------------------------------------------------------------------------------------

## Appendix: Planned Enhancements

Team size: 5

##### Patients
1. **Accept Foreign ID and phone numbers**: CLInic currently restricts a patient's ID to be a Singaporean NRIC or FIN number, and restricts a patient's phone number to be 8 digits. We plan to make the validation less restrictive to accommodate for foreign ID or phone numbers and validate them accordingly. 
2. **Increase character limit for addresses**: CLInic currently restricts addresses to be less than 60 characters. We hope to broaden the restrictions on addresses to accommodate longer addresses in the future.
3. **Allow non-capital letters for ID**: To support faster typing, CLInic will allow for non-capital letters inputted for ID in future iterations, 


