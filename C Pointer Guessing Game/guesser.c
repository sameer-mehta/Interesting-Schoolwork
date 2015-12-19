#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

/*
Author:Sameer Mehta
Since:September 28,2015
Compiled and tested with gcc
C standard: c99
Please compile with the following command gcc -std=c99 guesser.c -o guesser
*/

//Functions
int check(int firstnum, int secondnum,int i,int j);
void hints(int hint_type, int arrsize);
int comparison(char *input, int i, int j,int size);
int guess(int arg);

//Complex structure
typedef struct
{
        int real_part;
        int imag_part;
} complex ;

complex **arrptr; //2d complex pointer that points to M[0][0]


/*
Function main
Handles command line arguments and calls other functions
*/
int main(int argc, char* argv[])
{
int arg;

if(argc == 1)
{
        guess(5);
        return 0;
}
else if(argc == 2)
{
	arg = atoi(argv[1]);
	guess(arg);
	return 0;

}
else
{
	exit (1);
}

}


/*
Function guess
Creates 2d complex pointer array
Loops till user enteres Q and calls function to parse user input
When quiting prints stats
*/
int guess(int arg)
{
int questions = 0,right_on_first = 0,right_after_tries = 0,right_after_hints = 0,hints = 0; //Counters for stats
char *input; //stores user input
int run = 1;
input = malloc(sizeof(char) *1024);

do
{

	//Creates array here
        arrptr = malloc(arg * sizeof(complex *));
        for (int row = 0; row < arg; row++)
        {
	arrptr[row] = malloc(arg * sizeof(complex *));
                for (int col = 0; col < arg; col++)
                {
		arrptr[row][col].real_part = rand() % arg;
                arrptr[row][col].imag_part = rand() % arg;
                }
        }

	do
	{
	//Prints question and gets input
	//Calls comparision function to parse input
	srand((unsigned)time(NULL));
        int i = rand() % arg;
        int j = rand() % arg;


	printf("\nM[0][0]=%p. ",arrptr);
        printf("M[i][i]=%p ", &arrptr[i][j]);
        printf("What's i and j? (or Q or H or HH or HHH): ");
	gets(input);
	run = comparison(input,i,j,arg);
	if (run == 1) { right_on_first++; } //run = 1 means the user's answer was correct
					  //therefore increment variable that counts how many times the user was roght on the first try

	//run = -2 means user needed a hint and thus we trigger the hint counter and set run to -1 which prints the same question again.
	if(run == -2)
                {
                hints = -1;
                run = -1;
                }

	//run = -1 means the user was either wrong or needed a hint thus display question again
	if(run == -1)
	{
		do{

		printf("\nM[0][0]=%p. ",arrptr);
	        printf("M[i][j]=%p ", &arrptr[i][j]);
        	printf("What's i and j? (or Q or H or HH or HHH): ");
		gets(input);
	        run = comparison(input,i,j,arg);
		if(run == -2)
		{
		hints = -1;
		run = -1;
		}

		 if(hints == -1 && run == 1) { right_after_hints++; } //if user used a hint and was right after using hint ,increment hint counter
                 else if(run != 0) { right_after_tries++; }//if user used no hint then increment right after 2 or more tries counter

		}while(run == -1);


	}


	} while(run);//while Q is entered


}while(run); //while Q is entered

questions += right_on_first + right_after_tries + right_after_hints; //Get nubmer of questions asked
if(questions > 0) //Print stats if questions asked were greater than 0
{
//stats calculation
right_on_first = (right_on_first * 100) / questions;
right_after_tries = (right_after_tries * 100) / questions;
right_after_hints = (right_after_hints * 100) / questions;

printf("-----------------------------------------------------------------\n");
printf("%d\tTotal Number\n",questions);
printf("%d%%\tCorrect first time (no hints)\n",right_on_first);
printf("%d%%\tCorrect after 2 or more guesses (no hints)\n",right_after_tries);
printf("%d%%\tCorrect after hint(s)\n",right_after_hints);
printf("-----------------------------------------------------------------\n");
}
//Free pointers
free(arrptr);
free(input);


return 0;

}

int comparison(char *input,int i, int j,int size)
{

int firstnum,secondnum; //firstnum and seconnum hold user's guesses if they are numbers

		//Check if user entered H and run hints function with the appropriate function call
		if(strcmp("H", input)== 0)
		{
			hints(1,size);
			return -2;
		}

		else if (strcmp("HH",input) == 0)
		{
			hints(2,size);
			return -2;
		}

		else if(strcmp("HHH",input) == 0)
		{
			hints(3,size);
			return -2;
		}

		else if(strcmp("Q",input) == 0)
		{
			return 0;
		}

		else
		{

			//if user has entered anything other than the above get numbers nd call check function to check is uer is right
			sscanf(input,"%d %d", &firstnum, &secondnum);
			int checker = check(firstnum,secondnum,i,j);

			if(checker == 1)
			{
				printf("RIGHT");
				return 1;
			}

			else
			{
				printf("WRONG");
				return -1;
			}

		}


}
/*
Prints hints
*/
void hints(int hint_type, int arrsize )
{

int row = arrsize;
int col = arrsize;

//Print weak hint
if(hint_type == 1)
{
	for(int c = 0; c < col; c++)
	{
		printf("M[%d][%d]",c,c);
		printf("%*c", 1 , ' ');
		printf("%p", &arrptr[c][c]);
		printf("\n");
	}

}

//Prints moderate hint
if(hint_type == 2)
{

	//Top row
	for(int r = 0; r < row; r++)
	{

		printf("\tM[%d][%d]\t",0,r);



	}

	printf("\n");


	//Second row
	for(int c = 0; c < col;c++)
	{


		if(!c)
		{

		printf("M[%d][%d]", 0,0);
		printf("%*c", 1 , ' ');

		}

                printf("%p\t", &arrptr[0][c]);
	}

	printf("\n");

	//Rest of rows
	for(int c = 1; c < col; c++)
        {
                printf("M[%d][%d]",c,0);
                printf("%*c", 1 , ' ');
                printf("%p", &arrptr[c][c]);
                printf("\n");
        }


}

//Prints Strong hint
if(hint_type == 3)
{

	//Top row
        for(int r = 0; r < row; r++)
        {

                printf("\tM[%d][%d]\t",0,r);


        }

        printf("\n");

	//Rest or Rows
        for(int c = 0; c < col;c++)
        {

		for(int r = 0; r< row; r++)
		{
                	if(!r)
                	{

               		printf("M[%d][%d]", c,0);
	                printf("%*c", 1 , ' ');

        	        }
	               	printf("%p\t", &arrptr[c][r]);
        		}

		printf("\n");

		}



}


}

//Checks if user was right by comparing i and j of pointer to their guess
int check(int firstnum, int secondnum, int i, int j)
{
int firstnumber = firstnum;
int secondnumber = secondnum;

if(firstnumber == i && secondnumber == j)
{
	return 1;
}
return 0;


}
