//
//  vector.c
//  
//
//  Created by Sameer Mehta on 2015-10-11.
//  
//

#include "vector.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//This is the first function called to set the size(first input) of the Solution
//and to use the integer values entered(second input) to make a Solution Path
void VectorRead(Vector* V){
    int size;
    scanf("%d ", &size);                    //asks user the input and stores into variable 'size'
    
    V->size = size;                         //sets the user entered 'size' and sets into the struct variable
                                            //size(using the pointer functionality
    
    V->item = malloc(sizeof(int) * size);   //allocates space for the user entered path
    
    //Uses the for loop to store the input path to make Solution Path
    //Loops for the number of variable the size is(the user input called before)
    for(int i = 0 ; i < V->size; i++)
    {
        scanf("%d", &(V->item[i]));
    }
}
