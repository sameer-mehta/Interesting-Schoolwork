#include "trie.h"
#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>
#include <string.h>
//trie.c
//Created by Sameer Mehta
// some helper functions for this file
int TrieLower(trie * node, char letter);				//function that find the child node
int TrieArrayLetterIndex(char letter);					//function that has the actual letter from node

void TrieAddNode(trie * node, trie * lower);				//function for adding a node to the trie
void TrieAddTerminalLeafNode(trie * node, char * word);		//function to add nodes to an exisiting node to make a word
void TrieInsertHelper(trie * node, char * word, int index);		//function used to help insert the words frome file to trie


//initializes the trie by allocating space
void TrieInitialize(trie ** node) {
    (*node) = malloc(sizeof(trie));					//allocates space by using a double pointer
    (*node)->letter = '\0';						//sets the root to end of line
    int i;
    for (i = 0; i < 54; i++) {						//goes through the A-Za-z and apostrophe
        (* node)->lower[i] = NULL;					//set the letter from the loop in the child node
    }
}

void TrieAdd(trie * node, char * word) {
    TrieInsertHelper(node, word, 0);					//uses the helper function to add the words from file to trie
}

trie * TrieSearch(trie * node, char * prefix) {
    int i;
    trie * result = node;						//takes the node you want to be searched and puts in in 'trie'
    for (i = 0; i < strlen(prefix); i++) {				//loop to search for the node you entered earlier wit help
        char currentChar = prefix[i];					//of helper functions
        int childArrayIndex = TrieArrayLetterIndex(currentChar);
        
        // return a dummy node that does nothing
        if (!TrieLower(result, currentChar)) {
            trie * dummy;
            TrieInitialize(&dummy);
            return dummy;
        }
        
        result = result->lower[childArrayIndex];			//puts all the gathered info of child and parent and puts in 'result'
    }

    return result;							//returns the final searched trie
}


void TriePrinter(trie * node) {
    if (node->letter == '\0' && node->upper != NULL) {			//if end of line is reached for the trie and there is no parent node
        printf("%s\n", node->term);					//prints the term from the ADT
    }
    
    int i;
    for (i = 0; i < 54; i++) {						//loops through all 53(a-zA-z and apotrophe) using indexes
        if (node->lower[i] != NULL)					//if the child is not NULL
            TriePrinter(node->lower[i]);				//uses the print function to print the child node 
    }
}


int TrieLower(trie * node, char letter) {
    int trieArrayIndex = TrieArrayLetterIndex(letter);			//helper function to set the word entered into indexes and back into variable
    if (node->lower[trieArrayIndex] == NULL)				//if the child node is empty
        return 0;							//returns false
    else
        return 1;							//returns true
}

int TrieArrayLetterIndex(char letter) {
    if (islower(letter))						//if the mathc is found:
        return 26 + (letter - 'a');					//returns the 26 letters from non caps using ASCII value
    else if (isupper(letter))						//otherwise uses isupper function
        return 0 + (letter - 'A');					//return the 25 letters from caps using ASCII values
    else if (letter == '\'')						//if the end of nodes is reached
        return 53;							//returns all the indexes without apotrophe
    else    // the character is the null-terminator
        return 54;							//returns everything including parent/child apostrophe
}

void TrieAddNode(trie * node, trie * lower) {
    lower->upper = node;						//the child node is filled with the node added
    
    int index = TrieArrayLetterIndex(lower->letter);
    node->lower[index] = lower;						//the child node is added using the index into the ADDT
}
trie * TrieAddCharacterNode(trie * node, char letter) {
    int index = TrieArrayLetterIndex(letter);				//use of helper function to index the entered string
    
    trie * lower;							//creates a pointer to the child node
    TrieInitialize(&lower);						//initializes the child node to what is added from AddNode
    lower->letter = letter;						//the actual letter is stored
    lower->upper = node;						//adds the parents ndoe as well
    
    node->lower[index] = lower;						//the child node is filled with the what is entered in parameter
    
    return lower;							//returns if the add is successfull
}

void TrieAddTerminalLeafNode(trie * node, char * term) {
    trie * terminalNode;
    TrieInitialize(&terminalNode);
    terminalNode->letter = '\0';
    terminalNode->upper = node;
    
    // make a copy of the string
    char * newTerm = malloc(sizeof(char) * (strlen(term) + 1));
    strcpy(newTerm, term);
    terminalNode->term = newTerm;
    
    node->lower[53] = terminalNode;
}

//function to  help with adding the node(s) to the tire
void TrieInsertHelper(trie * node, char * term, int position) {
    char currentLet = term[position];
    if (currentLet == '\0') {						//end of nodes
        TrieAddTerminalLeafNode(node, term);
        return;
    }
    
    trie * next;
    if (!TrieLower(node, currentLet)) {
        TrieInitialize(&next);
        next->letter = currentLet;
        next->upper = node;
        TrieAddNode(node, next);
    } else {
        int index = TrieArrayLetterIndex(currentLet);
        next = node->lower[index];
    }
    
    TrieInsertHelper(next, term, ++position);
}
