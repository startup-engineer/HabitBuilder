This branch holds the algorithms we used to create a save file. 

HabitItem.java: classs that acts as both a data holder and a node

myMain.java: class that implements saving, breadth-first-search, and reading

output.txt - the output.txt of the save file from saving. It holds data in the following order:
		- Name
		- DaysCompleted (7 bools)
		- ID num
		- List of children ("--" if none exist)

reOutput.txt - As a means of testing our saving/reading functionality, we saved the program, read the output,
		then used the output to resave our data. If you use a diff checker, you will realize that the
		the outputs are the same, strongly indicating that the save feature works.


We explained this in the documentation, but the save feature never really got implemented. We tried until the
last minute to fit it in, but some slight data differences (particularly, our program doesn't have a header node
but rather a list of header nodes) made saving require a little extra work (which we didn't have time for). 
Additionally, we had a hard time choosing between internal and external storage. Internal storage seemed ideal 
but too difficult to debug with (we don't know any way to view internal storage).