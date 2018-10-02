# employee-management-system
This repo is for Employee Management System (as a part of CS5800 course project)

## Steps to setup project

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
    	- http://localhost:8080/
		- http://localhost:8080/ems/access/statuscheck

8. Install front-end packages
	1. Install NodeJS and NPM for your system: https://www.npmjs.com/get-npm
		1. [Mac instructions](https://blog.teamtreehouse.com/install-node-js-npm-mac)
		2. [Windows instructions](https://blog.teamtreehouse.com/install-node-js-npm-windows)
	2. In terminal or command prompt, `cd src/main/resources/webapp` and `npm install` to install packages defined in `package.json`. These are packages needed for this project.
	3. In terminal or command prompt, run this command to build to `src/main/resources/static/`: `npm run build`
	4. If you want to add a package, see [instructions](https://docs.npmjs.com/cli/install)

9. Running the UI locally
	1. After installing front-end packages, with the server running in step 7, the URL http://localhost:8080/ should now display a simple UI that executes the same as  http://localhost:8080/abc/?oldpass=123&newpass=your_new_password after filling in inputs and click Update button.

## Notes

- Developers, please read this before beginning development: [Coding Standars](https://github.com/Ultimates1/employee-management-system/wiki/Coding-Standards)
- Check out [Wikis](https://github.com/Ultimates1/employee-management-system/wiki) for more documentation.
