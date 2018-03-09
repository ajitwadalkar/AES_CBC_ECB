To run the project from Linux terminal use following command

javac aes.java
java aes $COLUMNS

//$COLUMS will give nice look to the borders, but can be ignored on other systems, As far as I checked that will not throw any error, but incase there is any error, please put 150 instead of $COLUMNS.


This will print the current data in the files and will give you options to choose the function as follows.

-------------------------------------------------------------------------------------------------------------------------------------------------
Current Data in all files:
plaintext.txt : Welcome to data security and privacy .
ciphertext.txt : 284C9FE5146EB80B1156E25E87431235EC64A27024F0962A41D06C133810E1D4CA56905469923AA6BEC3E5385A0813FD8EEA69EBF7EEFCFAA86904E0C1FD1C12
key.txt : 91F6E712F813E399C5E473814A1C64884FC0ACAC09759D52170E291B32411A8D
iv.txt : 396BC201A989E6A017D77B37B2D7A936
result.txt : Welcome to data security and privacy .
-------------------------------------------------------------------------------------------------------------------------------------------------

Following are the functionalities in the code, choose anyone:
1.Generate new random Key
2.Encode plaintext
3.Decode ciphertext
4.Compare AES CBC and ECB
5.Read current files
6.Exit
Please choose the value in between 1,2,3,4,5 or 6


Here you will need to input the number corresponding to the function.
once the function is executed it will print the updated status of all the files.
once that part is done, the program will ask you if you want to re-run the program, you can select 1 to rerun it or 2 to terminate the program.

-------------------------------------------------------------------------------------------------------------------------------------------------
Do you want to re-run the program?
Please choose from the following.
1. Yes
2. No
-------------------------------------------------------------------------------------------------------------------------------------------------
if you select 1 it will reprint the function menu and if you press 2 the program will terminate.

