Write a program that has a JTextArea where the user can enter some text. Then 
program should have a button such that when the user clicks on the button, the 
panel will count the number of lines in the user's input, the number of words 
in the user's input, and the number of characters in the user's input. This 
information should be displayed on three labels. Recall that if textInput is 
a JTextArea, then you can get the contents of the JTextArea by calling the 
function textInput.getText(). This function returns a String containing all 
the text from the text area. The number of characters is just the length of 
this String. Lines in the String are separated by the new line character, '\n', 
so the number of lines is just the number of new line characters in the String, 
plus one. Words are a little harder to count. Exercise 3.4 has some advice about 
finding the words in a String. Essentially, you want to count the number of 
characters that are first characters in words. Don't forget to put your JTextArea 
in a JScrollPane, and add the scroll pane to the container, not the text area. 
Scrollbars should appear when the user types more text than will fit in the 
available area.