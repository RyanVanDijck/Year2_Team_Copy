# Team Implementation Report
*This section should describe the technical details of your implementation.  The subheadings and italicised text below may be used to guide you.*

## Technical Diagrams
*Include a class diagram / circuit diagram, and/or any other relevant technical diagrams.*

## Technical Description
*This section should describe the software implementation in prose form.  Focus on how the code was designed and built.* 
*It should make a clear description that could be used by any future developers to maintain and extend your code, if necessary.*
*Describe important functions / classes / class hierarchies.*
*In this section, you should also wish to highlight any technical achievements your team is particularly proud of, including relevant code snippets.*

## Algorithms and Data Structures
A class *student* exists to store all the information about each student read from the file. Various attributes of varying types are used to store the data such as a string for the registration number.
The most complex data structure used to store the marks of each student is a hashmap. This maps a String as the key to an Integer as the value (a module code to a mark). To retrieve 
the mark the module code is given as the key and the corresponding value is retrieved from the map for use. The space complexity for a hashmap is O(n), that is the space required to
store the data linearly increases as the amount of entries stored within the map increases. Likewise, the time complexity of the Hashmap is O(1) which is very efficient as the time
to find the value given the key is minimal. This is assuming the hash function is implemented very well meaning the buckets are well distributed leaving only the time to process the
hash function to retrieve or add data.

Within the program where statistics are process a mergesort algorithm is used with the "Arrays.sort" function and then the "Arrays.reverse" function to sort the students from best to worst.
The students are sorted by their average mark through the use of comparator causing the sort to be merge sort which has time complexity O(nlogn) and space complexity O(n). This is
a very efficient sorting algorithm when sorting objects by one of their values (in this case Student's average mark). It divides all arrays in half until many sub arrays are created
and sorts the values when merging the arrays back together. E.g. [7,3,12,4] becomes [7,3], [12,4] [7],[3],[12],4] [3,7], [4,12] and finally [3,4,7,12]. Due to the maximum amounr of
sub arrays being equal to the number of values to sort, the space complexity is O(n) which increases in a linear fashion with more values.

## Imported Libraries 
*List any 3rd party libraries that were used and describe what functionality they provided.*
The third party library that was used in our product is called JPDF. This library was used to export statistical data to a pdf. The processing of our statistics is outputted using
graphics objects where each graphics object drawn is one page of the pdf. This includes the "drawing" of text and graphs.