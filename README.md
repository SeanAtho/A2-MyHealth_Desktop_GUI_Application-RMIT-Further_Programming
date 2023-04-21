## MyHealth Application
Summary of the application design

Classes:
- User: Represents a user with attributes such as username, password, first name, and last name. This class has methods to set and get the attributes.
- HealthRecord: Represents a health record with attributes such as weight, temperature, blood pressure, note, and date. This class has methods to set and get the attributes.
- UserProfile: Represents a user profile with attributes such as personal information and a list of health records. This class has methods to add and delete health records, as well as get a list of health records.
- Database: Represents a database with methods to add, delete, and update user profiles and health records.
- UserController: Represents a controller for user-related actions such as creating, editing, and deleting profiles, as well as logging in and out.
- HealthRecordController: Represents a controller for health record-related actions such as adding and deleting health records, searching for health records by date, and getting a list of health records.

Attributes and Methods:
User:
- Attributes: username, password, firstName, lastName
- Methods: setUsername, setPassword, setFirstName, setLastName, getUsername, getPassword, getFirstName, getLastName
HealthRecord:
- Attributes: weight, temperature, bloodPressure, note, date
- Methods: setWeight, setTemperature, setBloodPressure, setNote, setDate, getWeight, getTemperature, getBloodPressure, getNote, getDate
UserProfile:
- Attributes: personalInfo, healthRecords
- Methods: setPersonalInfo, addHealthRecord, deleteHealthRecord, getHealthRecords
Database:
- Methods: addProfile, deleteProfile, updateProfile, addHealthRecord, deleteHealthRecord
UserController:
- Methods: createProfile, login, editProfile, deleteProfile, logout
HealthRecordController:
- Methods: addHealthRecord, deleteHealthRecord, searchRecord, getHealthRecords




## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
