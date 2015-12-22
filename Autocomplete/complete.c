#include "trie.h"
#include <stdio.h>
//complete.c
//Created by Sameer Mehta
int main() {
	trie * print; //for printing
	// load all the dictionary words into the tree
	trie * trie;							//creates a instance of trie from ADT
	TrieInitialize(&trie);						//initializes the creates instance

	FILE * infile = fopen("american-english-no-accents", "r");	//creates a file to read to 'infile'
	char line[256];							//size of lines
	while ((fscanf(infile, "%s", line)) != EOF) {			//scan function to scan the file
		TrieAdd(trie, line);					//adds the file to the trie usign the fucntion TrieAdd
	}
	fclose(infile);							//close the reading of the file

	//get user input
    char * prefix;
   // trie * print;							//instance is made
    while (1) {								//while true
    	printf("Enter the word: ");					//asks for user input
    	fscanf(stdin, "%s", prefix);
    	fflush(stdin);							//deallocated/frees memory

    	print = TrieSearch(trie, prefix);				//prints the trie fby calling the search function
    	TriePrinter(print);						//prints what was initialized above using the printer function
    }
}
