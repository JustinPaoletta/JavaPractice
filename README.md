# 2 JAVA PROGRAMS AND A SERVER

### Installation
- Clone Repo From GitHub
- Open the project in your favorite IDE, Mine is IntelliJ but you can use anyone you choose

### Running Each Task

> Task 1
######Overview
This Program takes in a user input value of N, generates N number of random integers
between 1 and 1,000,000,000 and prints each number on a separate line. The filepath
is named N.txt. If the file already exists it will be overwritten and if the file does
not exist it will be created.

######Test It
In an IDE there should be a button to click to run the Task1.java file  
From there you will be asked to enter a whole number in the console   
Make sure to enter a valid input, or the program will not continue to the next step   
There are a couple of files I already created by running this as an example  (15.txt & 30.txt)  

> Task 2
######Overview
This Program reads a file of the users choosing and sorts the numbers in it by ascending (default) or descending if that option is chosen.
If the file doesn't exist an exception will be thrown.

######Test It
In an IDE there should be a button to click to run the Task1.java file  
From there you will be asked to answer a few questions in the console   
Make sure to enter in valid inputs, or the program will not continue to the next step   
You can use the files I already created in Task 1 (15.txt & 30.txt) or create your own 

> Task 3
######Overview
This Program is a basic HTTP server in java that will return the html file requested in the URL as long as it exists.
If it doesn't exist it will return the 404 page.

######Test It
In an IDE there should be a button to click to run the Task1.java file  
From there you will be asked to enter the port of your choosing   
Make sure to enter a valid input, or the program will throw an exception   
If you go to localhost:PORT then by default you will get the index.html file.
If you go to localhost:PORT/index.html or any other file name that exists it will return that html document.
Test this out by going to localhost:PORT/WHODAT.html 

### Comments
- Server      
1- "Implements Runnable" allowed me to create however many threads I needed easily handling 10+ parallel requests.  
2- Reading Files Through Streams eliminates most of the issues with memory because you are never storing much in RAM at any given time.   
3- Using Scanner & System.in allows the user to set their PORT of choosing   
4- Check out localhost:PORT/WHODAT.html

Thread Pools
    
Though Not implemented in this basic server thread pools help cut down on the execution time of requests because they reuse already created threads rather than needing to create new ones for each task



