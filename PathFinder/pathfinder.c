//  pathfinder.c
//  
//
//  Created by Sameer Mehta on 2015-10-11.
//

#include <stdio.h>
#include "path.h"
#include "vector.h"

//The recursive alogrithm of the program to make the path using the rule given
int AllPathsRec(int position, Vector *V, Path *Solution, int *foundPath)
{
    //Checks if the position is in the path
    //Base case for the recursive algorithm
    for(int i = 0; i < Solution->maxSize; i++)
    {
        if(Solution->actualPath[i] == position)
        {
            return 0;
        }
    }
    //Checks if position is the last position in the Vector
    PathAddEntry(Solution, position);       //adds the newest/latest position in the Solution Path
    if(position == ((V->size) -1))
    {
        PathPrint(Solution);                //Calls the print function to print the Solution Path
        PathRemoveEntry(Solution);          //Now it removes the entry so the search happens again
        *foundPath = 1;                     //Solution is found so makes foundPath to 1
    }
    //if the position is not in the the last position for the vector
    else
    {
        if(position + V->item[position] <= (V->size -1))            //checks for the path using the i+x
           AllPathsRec(position + V->item[position], V, Solution, foundPath);
        if(position - V->item[position] > 0)                        //checks for the path using the i-x
           AllPathsRec(position - V->item[position], V, Solution, foundPath);
        PathRemoveEntry(Solution);
    }
    return 0;
    
}

//Main function of the entire program, where everyone is started/runs
int main()
{
    Vector V;                               //Creates a Vector 'V' from struct in header
    Path P;                                 //Creates a Path 'P' from struct in header
    
    VectorRead(&V);                         //Calls the method from the vector class (vector.c)
    PathInit(&P, (&V)->size);               //Calls the method from the path class (path.c)
    
    int foundPath;                          //Creates a primitive type of integer named 'foundPath'
    foundPath = 0;                          //Initializes the variable above to 0

    AllPathsRec(0, &V , &P, &foundPath);    //Calls the method above for run the recursive algorithm

    if(foundPath ==0)                       //If a solution is found then it makes 'foundPath' to 1
    {                                       //If foundPath is 0 then it enters this if statement
        printf("No Solution\n");            //Simply print 'No Solution' on the screen foundPath is 0

    }
}