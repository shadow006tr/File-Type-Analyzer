# File Type Analyzer #
## a HyperSkill.org project ##
### Completed ###

 This is my code for [JetBrains Academy](https://hyperskill.org/)'s File Type Analyzer Project.

 The program will determine a file type by searching a specific pattern in it.

 It will go by all of the files in a folder and check them paterns, by usind the Rabin-Karp algorithm


 Each file will be checked in a different thread, each file will be checked with each pattern from a text file

 Takes 2 arguments

> 1.Folder - the path to a folder with files / other folders.

> 2.Pattern Database - a pattern database, holding 3 parameters for each pattern:
>
>> int Priority - the priority of the pattern. Higher priority patterns will be checked first
>
>> String Pattern - the pattern to check
>
>> String Result - the result to be printed if the pattern is found in the file
>
> See PatternsDB_Example.txt for an example

 In the case the pattern will not be found in the file,

 the program will print "Unknown file type"


### Course ###

> Java
