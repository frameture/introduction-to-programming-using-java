    The fact that Java has a HashMap class means that no Java programmer has to 
    write an implementation of hash tables from scratch -- unless, of course, 
    that programmer is a computer science student.

    For this exercise, you should write a hash table in which both the keys and 
    the values are of type String. (This is not an exercise in generic programming; 
    do not try to write a generic class.) Write an implementation of hash tables 
    from scratch. Define the following methods: get(key), put(key,value), remove(key), 
    containsKey(key), and size(). Remember that every object, obj, has a method 
    obj.hashCode() that can be used for computing a hash code for the object, 
    so at least you don't have to define your own hash function. Do not use any 
    of Java's built-in generic types; create your own linked lists using nodes 
    as covered in Subsection 9.2.2. However, you do not have to worry about increasing 
    the size of the table when it becomes too full.

    You should also write a short program to test your solution.