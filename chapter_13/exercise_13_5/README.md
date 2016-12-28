
  The sample program PhoneDirectoryFileDemo.java from Subsection 11.3.2 keeps
  data for a "phone directory" in a file in the user's home directory. Exercise
  11.5 asked you to revise that program to use an XML format for the data. Both
  programs have a simple command-line user interface. For this exercise, you
  should provide a GUI interface for the phone directory data. You can base
  your program either on the original sample program or on the modified XML
  version from the exercise. Use a JTable to hold the data. The user should be
  able to edit all the entries in the table. Also, the user should be able to
  add and delete rows. Include either buttonsPanel or menu commands that can be
  used to perform these actions. The delete command should delete the selected
  row, if any. New rows should be added at the end of the table. For this
  program, you can use a standard DefaultTableModel.
 
  Your program should load data from the file when it starts and save data to
  the file when it ends, just as the two previous programs do. For a GUI
  program, you can't simply save the data at the end of the main() routine,
  since main() terminates as soon as the window shows up on the screen. You
  want to save the data when the user closes the window and ends the program.
  There are several approaches. One is to use a WindowListener to detect the
  event that occurs when the window closes. Another is to use a "Quit" command
  to end the program; when the user quits, you can save the data and close the
  window (by calling its dispose() method), and end the program. If you use the
  "Quit" command approach, you don't want the user to be able to end the
  program simply by closing the window. To accomplish this, you should call
 
     frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
 
  where frame refers to the JFrame that you have created for the program's user
  interface. When using a WindowListener, you want the close box on the window
  to close the window, not end the program. For this, you need
  
     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
  When the listener is notified of a window closed event, it can save the data
  and end the program.
 
  Most of the JTable and DefaultTableModel methods that you need for this
  exercise are discussed in Subsection 13.4.3, but there are a few more that
  you need to know about. To determine which row is selected in a JTable, call
  table.getSelectedRow(). This method returns the row number of the selected
  row, or returns -1 if no row is selected. To specify which cell is currently
  being edited, you can use:
  
  table.setRowSelectionInterval(rowNum, rowNum); // Selects row number rowNum.
  table.editCellAt( rowNum, colNum ); // Edit cell at position (rowNum,colNum).
      phoneTable.getEditorComponent().requestFocus(); // Put input cursor in cell.
 
  One particularly troublesome point is that the data that is in the cell that
  is currently being edited is not in the table model. The value in the edit
  cell is not put into the table model until after the editing is finished.
  This means that even though the user sees the data in the cell, it's not
  really part of the table data yet. If you lose that data, the user would be
  justified in complaining. To make sure that you get the right data when you
  save the data at the end of the program, you have to turn off editing before
  retrieving the data from the model. This can be done with the following
  method:
 
      private void stopEditing() { if (table.getCellEditor() != null)
      table.getCellEditor().stopCellEditing(); }
 
  This method must also be called before modifying the table by adding or
  deleting rows; if such modifications are made while editing is in progress,
  the effect can be very strange.
