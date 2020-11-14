# Wang Zhenquan - Project Portfolio Page

## Overview
### Project: Project Tracker
Project Tracker is a desktop app for managing and tracking projects, optimized for use via a Command Line Interface (CLI).

### Summary of Contributions

Given below are my contributions to the project.

### Code contributed: 
[RepoSense link](https://nus-tic4001-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wang&sort=groupTitle&sortWithin=title&since=2020-10-09&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=Impala36&zR=AY2021S1-TIC4001-1%2Ftp%5Bmaster%5D&zACS=167.22222222222223&zS=2020-10-09&zFS=wang&zU=2020-11-14&zMG=false&zFTF=commit&zFGS=groupByAuthors&zFR=false)

### Enhancements implemented:

- Added the skeleton code [#1](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5) for the team to build onto, which includes:
   - The main program file in [Tracker.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-7ae1edf9dcf7cac365f07a82b43d95280382f61fc5d924e8afa3b2d47529d11b).
   - The abstract Command template in [Command.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-414201120759ea092bad1f0aa0f2054fbf43d788b3d5a31b1fdf356c65bd5ead).
   - The Delete command in [Delete.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-2dcd348a6ccc48dada01cd0d02ce2dc79488585e4f7c165bdf5edab27a7d5cc8).
   - The Exit command in [Exit.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-746aea2581586cf9644f8acbccf974e41d156cca5be1a86c5530deaa8be82928).
   - The Help command that displays all commands to the user in [Help.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-b8352aac6a8e599cab0ee4bae279a790b4d039af9e7d5d5784a322043c5b0b69).
   - The Invalid command that handles invalid input in [Invalid.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-cf8b4b12db25bd4ad3c39109d4b1e4d7db1a3cff11a18e71c53c98cf3945acb7).
   - The List command that shows all projects in the list in [List.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-985e6a6469626c6d041dc66eead1d72126b6b143d7ba1f1d92160ccc3e2407d5).
   - The [TrackerException.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-540ca5d2307b98adb471c88ce64764a7786b3c712a4c1c7d1f084f3b22bff405) class which inherits the Exception class
   - The [Parser.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-452e3cc6273e299c12039559ba6b45f9dd64699e90559db124e642ebf008df36) class which effectively parses the user input to the desired command by switch.
   - The [Project.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-8b0ef7b91569fd8c90186adca91e435ea2a005d1c85211305e03e119f294ede4) class for each project.
   - The [ProjectList.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-b80ab90d683fcea23f6d778ac71ee12c211d70adc2a2029dc1069804368d959e) class which contains all the projects as an ArrayList.
   - The [Storage.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-10a5ab8e25d5668d050a93073ab800159f2233b3fa66174e52a691c36c07b52f) class which has all the necessary functions that reads and extracts projects from the text file, and updates whenever there is a change in the in-memory project list. 
   - The [Ui.java](https://github.com/AY2021S1-TIC4001-1/tp/commit/ee4684caa7f413290a14b1e8d963895923384fd5#diff-ee0d26d21e133872bc1c37849c51db042dd0b82b33a0922c80563a26a892bb65) class which displays meaningful messages to the user.

- Updated the Storage feature to accommodate the newProject additions. [#11](https://github.com/AY2021S1-TIC4001-1/tp/pull/11/commits/e538046a550b7db985b95a4778a7501c3848de2a#diff-10a5ab8e25d5668d050a93073ab800159f2233b3fa66174e52a691c36c07b52f)
- Reformatted the UI and added printProjectCreated and printProjectRemoved. [#11](https://github.com/AY2021S1-TIC4001-1/tp/pull/11/commits/e538046a550b7db985b95a4778a7501c3848de2a#diff-ee0d26d21e133872bc1c37849c51db042dd0b82b33a0922c80563a26a892bb65)
- Added the find and replace feature to the Find command. [#62](https://github.com/AY2021S1-TIC4001-1/tp/pull/62/commits/3cf319a8001187e7106e1421d6a7224a506d8503)
- Enhanced Edit, Delete & Find with more exception catches and added Equivalence Partitioning JUnit tests for them. [#63](https://github.com/AY2021S1-TIC4001-1/tp/pull/63/commits/4333553a812bf983e76b44d5dd1c6ab36e561352)

### Contributions to team-based tasks :

- Modified the [Add](https://github.com/AY2021S1-TIC4001-1/tp/commit/e538046a550b7db985b95a4778a7501c3848de2a#diff-16e022cf43517eb4d688065d44e62cb18738c674c2ff631dace43c6912396d2f), [Project](https://github.com/AY2021S1-TIC4001-1/tp/commit/e538046a550b7db985b95a4778a7501c3848de2a#diff-9a0424312fe5c4724970952f2c3a9e2d70bd3378ae608c6bb206e79ed92f6f4f), [Replace](https://github.com/AY2021S1-TIC4001-1/tp/commit/e538046a550b7db985b95a4778a7501c3848de2a#diff-6cf4b54d4304eb6f3fc8c7c1fd3276a1f6050e26c66b71792a0993c991b409a5) features in [#11](https://github.com/AY2021S1-TIC4001-1/tp/pull/11/commits/e538046a550b7db985b95a4778a7501c3848de2a) with a better coding structure, for better readability and to follow the Java coding quality and standards.
- Shifted the displaying of project from the List class to the Ui class so that it can be used by many other commands, as a new method [displayProject()](https://github.com/AY2021S1-TIC4001-1/tp/pull/13/commits/1c333f76ad1eb2735d4a0188c2bf153af377bb68#diff-ee0d26d21e133872bc1c37849c51db042dd0b82b33a0922c80563a26a892bb65) in [#13](https://github.com/AY2021S1-TIC4001-1/tp/pull/13/commits/1c333f76ad1eb2735d4a0188c2bf153af377bb68).
- Updated the [AboutUs.md](https://github.com/AY2021S1-TIC4001-1/tp/commit/ae7bad06099dbbc153d5ce727fe5ba97136f053f) for the team.
- Corrected all checkstyle errors in all files for the team in [#86](https://github.com/AY2021S1-TIC4001-1/tp/pull/86/commits/f7c6fbfc41ecc9e4e3dc5ba4c1df49753bf5dbdd), as well as fixing the Status writing to and reading from Storage, removed --duration from Storage, checking the date format in Edit and Find, and ui.displayProject to use getDateDiff for duration.
- Changed dateChecker with a better calendar date checker in [#106](https://github.com/AY2021S1-TIC4001-1/tp/pull/106/commits/f87f0a7e98e1988ff4730598dd5523bb8e0a6adb), as well as fixing the date checking in Edit and Find, and the removal of unnecessary ParseExceptions found in many classes.

### Documentation:
#### User Guide:

- Updated the [UserGuide.md](https://github.com/AY2021S1-TIC4001-1/tp/commit/a9bb29b75980b347f1f368f48aaa88ad3a087776) with the updated Help, Project, Add, Delete and List expected outputs and some minor description changes.
- Updated the UserGuide.md [#68](https://github.com/AY2021S1-TIC4001-1/tp/pull/68/commits/1c20b5efb159b47a7eaebfa9fa1e8edde3177d08) with better command descriptions, added in Find and Replace, and the new expected output for Find.

#### Developer Guide:

- Updated the [Class Diagram](https://github.com/AY2021S1-TIC4001-1/tp/blob/9cf46ad1f0ae7dd2691c30e9ef3691b108f80f3e/docs/UML%20V2.png) with all the cards' variables and methods, and reorganised the arrangement to look neater.
- Updated the [Class Diagram](https://github.com/AY2021S1-TIC4001-1/tp/blob/ff6f9b1bed52554f0e26dc70327fbd97310a361c/docs/UML%20V2.png) so that the Commands are below the Tracker cards and connected appropriately.

