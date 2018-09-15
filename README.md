# employee-management-system
This repo is for Employee Management System (as a part of CS5800 course project)

Steps to setup project:

1. Download git for your laptop/desktop
https://git-scm.com/downloads

2. Clone project from https://github.com/Ultimates1/employee-management-system.git
Please find help on this site https://help.github.com/articles/cloning-a-repository/
command$ git clone https://github.com/Ultimates1/employee-management-system.git

3. Download Eclipse 
https://www.eclipse.org/downloads/packages/release/photon/r/eclipse-ide-java-ee-developers

4. Install plugin in Eclipse->Help->Eclipse Marketplace
	1. (m2e) Maven Integration for Eclipse(Luna) 1.5.0
	2. (sts) Spring Tools (aka Spring IDE...) 3.9.5 RELEASE
	
5. Importing the Project into Eclipse
	1. File->Import->Maven->Existing Maven Projects
	2. Select the directory containing the pom.xml file
	3. Finish
	
6. Building the Project for the First Time
	1. Right-click on the project root folder->Maven->Update Project
	
7. Running the Project Locally
	1. Locate the EmployeeManagementSystemApplication.java in src/main/java source folder and right-click on it->Run As->Java Application
	2. Verify the running process in your web browser by the following URLs:
    http://localhost:8080/
		http://localhost:8080/ems/access/statuscheck
