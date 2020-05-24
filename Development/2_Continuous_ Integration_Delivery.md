# Continuous Integration and Continuous Delivery

Continuous Integration and Continuous Delivery are the processes in which **your development team involves frequent code changes that are pushed in the main branch while ensuring that it does not impact any changes made by developers working parallelly**. The aim of it is to reduce the chance of defects(缺点) and conflicts during the integration of the complete project.

## What is Continuous Integration

**Continuous Integration is a development methonology that involves frequent integration of code into a shared repository. The integration may occur several times a day, verified by automated test cases and a build sequence**. It should be kept in mind that **automated testing is not madatory for CI**. It is only practiced typically for ensuring a bug-free code.

### Benefits of Continuous Integration

* **Early Bug Detection**: If there is an error in the local version of the code that has not been checked previously, a build failure occures at an early stage. This is also benefits the QA team since they will mostly work on builds that are stable and bug-free.

* **Reduce Bug Count**: In any application development lifecycle, bugs are likely to occur. However, with Continuous Integration and Continuous Delivery being used, the number of bugs is reduced a lot. Although it depends on the effectiveness of the automated testing scripts. Overall, the risk is reduced a lot since bugs are now easier to detect and fix early.

* **Automating the Process**: The Manual effort is reduced a lot since CI automates build, sanity, and few other tests. This makes sure that the path is clear for a successful continuous delivery process.

* **The Process Becomes Transparent**: A great level of transparency is brought in the overall quality analysis and development process. The team gets a clear idea when the test fails, what is causing the failure and whether there are any significant defects. This enables the team to make a real-time decision on where and how the efficiency can be improved.

* **Cost-Effective Process**: Since the bug count is low, manual testing time is greatly reduced and the clarity increases on the overall system, it optimizes the budget of the project.

## What is Continuous Delivery

**Continuous delivery is the process of getting all kind of changes to production. Changes may include configuration changes, new features, error fixes etc**. They are delivered to the user in a safe, quick and sustainable manner.

The goal of Continuous Delivery is to make deployment predictable and schedule in a routine manger. It is achieved by ensuring that the code always remains in a state where it can be deployed whenever demanded, even when an entire team of developers is constantly making changes to it. Unlike coninuous integration, testing and integrating phases are elimated and the traditional process of code freeze is followed.

## Benefits of Continuous Delivery

* **Reducing the risk**: The main goal of Continuous Delivery is to make deployment easier and faster. Patterns like blue-green deployment make it possible to deploy the code at very low risk and almost no downtime, making deployment totally undetectable to the users.

* **High-Quality application**: Most of the process is automated, testers now have a lot of time to focus on important testing phases like exploratory, usability, security and performance testing. These activities can now be continuously performed during the delivery process, ensuring a higher quality application.

* **Reduced Cost**: When an investment is made on testing, build and deployment, the product involves quite a lot throughout its lifetime. The cost of frequent bug fixes and enhancements are reduced since certain fixed costs that are associated with the release is elimated because of continuous delivery.

* **Happer Team and Better Product**: Since the aim of Continuous Delivery is to make a produce release painless, the team can work in a relaxing manner. Because of frequent release, the team works closely with users and learn what idea work and what new can be implemented to delight the users. Continuous user feedback and new testing methodologies also increase the product's quality.

Not only that, with the development and testing team working together in automating the deployment and build, developers are incorporate regression testing and integration in their daily tasks and reduce the amount of rework required in the traditional application development lifecycle.

## How Is Continuous Delivery Different From Continuous Deployment?

**Continuous integration is usually the process when code changes made by different developers are integrated into the main code branch as soon as possible**. It is usually done several times a day. The process ensures that code changes committed by individual developers do not divert or impact the main code branch. When combined with automated testing, it ensures that your code is dependable and can be moved into the next phase, i.e. testing or production.

![io2](/Development/images/ci_1.gif)

With **Continuous Delivery**, **"Deploy to Production" is a manual process**, meaning that it is initiated manually. The differs from Continuous Development, which is automated all the way through "Post Development Test".


## How To Perform Continuous Delivery?

* The developer builds their code on the local system that has all new changes or new requirements. 
* Once coding is completed, the developer needs to write automated unit testing scripts that will test the code. This process is optional, however, and can be done by testing team as well.
* A local build is executed which ensures that no breakage is occurring in the application because of code.
* After a successfully build, the developer checks if any of his team members or peers have checked-in anything new.
* If there are any incoming changes, they should be accepted by the developer to make sure that the copy he is uploading is the most recent one.
* Because of the newly merged copies, syncing the code with the main branch may cause certain conflicts.
* In case there is any conflict, they should be fixed to make sure the chagnes made are in sync with the main branch.
* The changes are now ready to be checked in. This process is known as a "code commit".
* After the code is committed, another build of the source code is run on the integration system.
* The new and updated code is finally ready for the next stage, i.e. testing or deployment.

## Continuous Delivery Checklist

* Before any changes are submitted, ensure that the current build is successful. If there are some issues, fix the build before any new code is submitted.
* If the build is in the successful state, rebase your workspace to the configuration in which the build was successful.
* In your local system, build and test the code to check if any functionality is impacted because of the changes you made.
* If everything goes well, check in the code.
* Allow competition of continuous integration with the new code changes.
* If somehow the build fails, stop and go back to the 3rd step in the checklist.
* If the build is successful, work on your next code.

## Best Practices of Continuous Integration and Continuous Delivery

* **Keep a Central Repository**: A large project involves multiple developers constantly pulling and pushing codes that are organized together to build the application. A revision control system should be kept that will help the team to get the latest clean code from the repository at any point of time during the development cycle.

* **Automated Deployment and Build:** Automated build ensures that the team only gets the latest source code available in the repository and it is compiled every time before the final product is built. Automated Build cycle also allows the developers to push the code into different environments quickly, saving a lot of time.

* **Include Automated Unit Testing**: This will help the team to detect bugs before the code is pushed in the repository. Unit testing, as well as interface testing, have greater clarity on the product’s state before it is released. Testing phase becomes easier and issues can be fixed rapidly.

* **Test in the Production’s Clone**: Often an application that has passed all testing scenarios fails when it is deployed in production because of the environment is different. To prevent this, testing should be executed in an environment that is exactly the same as the production environment. This will allow testers and developers to understand how the application behaves before it is deployed into production.

* **Commit the Code Everyday**: To prevent any conflicts, developers should make it a mandatory practice to commit the code every day in the repository. It provides very little scope to look for errors occurring due to conflicts. It also improves the communication between the team members and allows developers to divide their work into small sections and track the progress of their code.

* **Build Faster**: Continuous integration fundamental purpose is to get feedback instantly after a build. A quick and perfect build keeps the development team ahead and prevents any bottleneck that may occur during unit testing.

* **Everyone Can See What Others are Doing**: Continuous Integration and Continuous Delivery essential goal is to make the communication between team members smooth and effective. Everyone should have a clear idea regarding the state of the application and the latest changes that are made on it. Builds that have failed should be reported immediately to the stakeholders who can then make the relevant changes. IMs, Emails and other monitoring tools are used by various organizations to monitor the state of the builds.



## Tools Of Trade For Continuous Integration & Continuous Delivery

* **Jenkins**: An open-source Java-based CI tool that is platform independent. The best part is, it can be configured both using a console or a graphical user interface.
* **Team City**: This is a cloud-based CI server, developed by JetBrains. Although the enterprise edition is paid, there is a free version as well that allowed 3 build agents and a maximum of 100 builds.
* **Travis CI**: One of the oldest Continuous Integration and Continuous Delivery solution, the tool is free for all projects that are open source. It is hosted on GitHub and based on your usage, you can choose the appropriate package from several options.
* **Gitlab**: The CI developed by GitLab is cloud-based, hosted on their official website. It is supported on multiple platforms and has both free and paid versions.
* **Circle CI**: A cloud-based CI tool, it supports GitHub and languages like Node.js, Java, Ruby, Python, Scala, Haskell, and PHP. It allows the parallel building of your code.
* **Codeship**: This is also another hosted tool that comes with basic as well as enterprise editions. The basic version comes with several packages and with expensive enterprise edition, it brings you more options to run parallel builds.




Reference:

https://dzone.com/articles/what-is-continuous-integration-andontinuous-delive

https://dzone.com/articles/continuous-delivery-vs-continuous-deployment-an-ov